package com.huifer.idgen.my.service.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: wang
 * @description: ip获取工具
 */
@Slf4j
public class IpUtils {

	public static String getHostIp() {
		String ip = null;
		try {

			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()
					) {
						ip = inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			log.error("ip 获取异常{}", e);
		}
		return ip;
	}

	public static String getHostName() {
		String hostName = null;
		try {
			Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface intf = en.nextElement();
				Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = enumIpAddr
							.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						hostName = inetAddress.getHostName();
					}
				}
			}
		} catch (SocketException e) {
			log.error("host 获取异常{}", e);
		}
		return hostName;
	}

}