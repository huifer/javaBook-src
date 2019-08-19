package com.huifer.heartbeat.ez.client;

import com.huifer.heartbeat.ez.LiveBack;

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
	 * 向心跳服务{@link com.huifer.heartbeat.ez.server.HeartServer} 发送消息
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
	 * 处理{@link com.huifer.heartbeat.ez.server.HeartServer} 返回的消息
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
