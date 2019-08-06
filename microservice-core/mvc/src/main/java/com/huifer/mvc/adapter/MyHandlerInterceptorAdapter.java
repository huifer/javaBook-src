package com.huifer.mvc.adapter;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	/**
	 * 超时10秒
	 */
	public static final long time_out = 10 * 1000L;



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String userId = request.getParameter("user_id");
		HttpSession session = request.getSession();
		if (Strings.isNotBlank(userId)) {
			UserCount userCount = new UserCount();
			userCount.setUserId(userId);
			long currentTimeMillis = System.currentTimeMillis();
			userCount.setLastTime(currentTimeMillis);
			userCount.setErrorTime(currentTimeMillis + time_out);
			// 可以通过设置redis有效时间来控制账户登录session
//			redisTemplate.opsForValue().set("mykeys", myvalue, 1L, TimeUnit.DAYS);
		}
		return super.preHandle(request, response, handler);
	}



	@Data
	private static class UserCount {
		private String userId;
		/**
		 * 最后操作时间
		 */
		private Long lastTime;
		/**
		 * 错误时间: 最后操作时间 + 固定时间
		 */
		private Long errorTime;
	}

}
