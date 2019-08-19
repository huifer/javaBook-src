# 心跳机制
## socket
两个服务器A,B.
- A服务器上运行服务端用来接收消息,并返回是否接受到消息
- B服务器上运行消费端用来发送消息给A服务器,根据获取结果判断A服务器是否存活

- 消息包
```java
package com.huifer.heartbeat.socket;

import java.io.Serializable;

/**
 * 消息包
 */
public class LiveBack implements Serializable {

	private static final long serialVersionUID = 6512279542859907453L;


	private String msg;

	@Override
	public String toString() {
		return "LiveBack{" +
				"msg='" + msg + '\'' +
				'}';
	}

	public LiveBack() {
	}

	public LiveBack(String msg) {
		this.msg = msg;
	}
}

```
- 服务端代码
```java
package com.huifer.heartbeat.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

/**
 * 心跳服务端:<br/>
 * <p>  主要实现一个长效的链接对象(socket),提供给客户端进行访问使用</p>
 * <p> 实现功能如下</p>
 * <li>记录链接对象</li>
 */
public class HeartServer {
	protected static Logger logger = Logger.getLogger(HeartServer.class.getName());
	/**
	 * 端口号
	 */
	private int port;
	/**
	 * 本server的运行情况
	 */
	private volatile boolean running = false;

	/**
	 * 允许延迟时间
	 */
	private long delayTime = 5000L;

	/**
	 * 存放socket 链接
	 */
	private CopyOnWriteArraySet<Socket> socketList = new CopyOnWriteArraySet<>();

	public HeartServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		new HeartServer(10086).start();
	}

	/**
	 * 启动方法
	 */
	private void start() {
		if (this.running) {
			return;
		}
		this.running = true;
		new Thread(new WatchDog()).start();
	}

	private void stop() {
		if (this.running) {
			this.running = false;
		}
	}

	/**
	 * 看门狗 ^_^ , 用来进行数据读取检测
	 */
	class WatchDog implements Runnable {

		@Override
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(port, 5);
				logger.info("心跳检测服务端启动");
				while (running) {
					Socket accept = serverSocket.accept();
					new Thread(new Work(accept)).start();
				}
			} catch (IOException e) {
				// 出异常停止
				HeartServer.this.stop();
			}
		}

		/**
		 * 工作类,用来处理socket 发送过来的内容
		 */
		class Work implements Runnable {
			private Socket socket;
			private boolean workRunning = true;
			long lastTime = System.currentTimeMillis();

			public Work(Socket socket) {
				this.socket = socket;
			}

			@Override
			public void run() {
				while (running && workRunning) {
					if (System.currentTimeMillis() - lastTime > delayTime) {
						clean();
					} else {
						try {

							InputStream in = socket.getInputStream();
							if (in.available() > 0) {
								ObjectInputStream ois = new ObjectInputStream(in);
								// 发送过来的对象
								Object o = ois.readObject();
								lastTime = System.currentTimeMillis();
								logger.info("当前接受内容为:\t" + o.toString());
								socketList.add(socket);
								System.out.println(socketList);
							} else {
								Thread.sleep(100);
							}
						} catch (Exception e) {
							e.printStackTrace();
							clean();
						}
					}
				}
			}

			/**
			 * 清理数据
			 */
			private void clean() {
				if (workRunning) {
					workRunning = false;
				}
				if (null != socket) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				socketList.remove(socket);
				System.out.println(socketList);
				logger.info("有客户端关闭:\t" + socket.getRemoteSocketAddress());
			}
		}
	}


}

```
- 客户端代码
```java
package com.huifer.heartbeat.socket.client;

import com.huifer.heartbeat.socket.LiveBack;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * 心跳客户端<br/>
 * <p>主要实现:向心跳服务端发送心跳包数据{@link LiveBack}</p>
 */
public class HeartClient {
	protected static Logger logger = Logger.getLogger(HeartClient.class.getName());
	/**
	 * 心跳服务ip地址
	 */
	private String heartServerIp;
	/**
	 * 心跳服务端口号
	 */
	private int heartServerPort;
	/**
	 * socket
	 */
	private Socket socket;
	/**
	 * 是否运行
	 */
	private volatile boolean running = false;
	/**
	 * 最后一次操作
	 */
	private long lastTime;

	public static void main(String[] args) {
		HeartClient client = new HeartClient("127.0.0.1", 10086);
		client.start();
	}

	public HeartClient(String heartServerIp, int heartServerPort) {
		this.heartServerIp = heartServerIp;
		this.heartServerPort = heartServerPort;
	}

	/**
	 * 启动器
	 */
	private void start() {
		if (this.running) {
			return;
		}
		try {
			this.socket = new Socket(this.heartServerIp, this.heartServerPort);
			this.lastTime = System.currentTimeMillis();
			this.running = true;

			logger.info("心跳检测客户端启动");
			new Thread(new KeepSocket()).start();
			new Thread(new workRunnable()).start();

		} catch (Exception e) {
			logger.info("与心跳检测服务端断开链接");
		}
	}

	/**
	 * 停止服务
	 */
	private void stop() {
		if (this.running) {
			running = false;
		}
	}

	/**
	 * 向心跳服务{@link com.huifer.heartbeat.socket.server.HeartServer} 发送消息
	 *
	 * @param o 消息对象
	 */
	private void sendObject(Object o) {
		try {
			ObjectOutputStream oot = new ObjectOutputStream(this.socket.getOutputStream());
			oot.writeObject(o);
			logger.info("发送消息:" + o.toString());
			oot.flush();
		} catch (Exception e) {
			logger.info("与心跳检测服务端断开链接");
		}
	}

	/**
	 * 保持长连接的socket
	 */
	class KeepSocket implements Runnable {
		long checkDelay = 30L;
		long keepDelay = 1000L;

		@Override
		public void run() {
			while (running) {
				if (System.currentTimeMillis() - lastTime > keepDelay) {
					try {
						HeartClient.this.sendObject(new LiveBack("心跳包数据"));
					} catch (Exception e) {
						e.printStackTrace();
						HeartClient.this.stop();
					}
					lastTime = System.currentTimeMillis();
				} else {
					try {
						Thread.sleep(checkDelay);
					} catch (Exception e) {
						e.printStackTrace();
						HeartClient.this.stop();
					}
				}
			}
		}
	}

	/**
	 * 处理{@link com.huifer.heartbeat.socket.server.HeartServer} 返回的消息
	 */
	class workRunnable implements Runnable {
		protected Logger logger = Logger.getLogger(workRunnable.class.getName());

		@Override
		public void run() {
			while (running) {
				try {
					InputStream in = socket.getInputStream();
					if (in.available() > 0) {
						ObjectInputStream ois = new ObjectInputStream(in);
						Object o = ois.readObject();
						logger.info("接收:" + o);
					} else {
						Thread.sleep(100L);
					}
				} catch (Exception e) {
					e.printStackTrace();
					HeartClient.this.stop();
				}
			}
		}
	}

}

```