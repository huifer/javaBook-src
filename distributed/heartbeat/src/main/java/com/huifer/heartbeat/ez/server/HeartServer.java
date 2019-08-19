package com.huifer.heartbeat.ez.server;

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
