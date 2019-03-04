package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.vo.UserVo;
import com.douzone.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//1. Handler 종류 확인     그냥 핸들러/디폴트서블릿뭐시기(제외시켯는데가능함? ㅇㅇ txt이런것들로)
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		//2. Casting 
		HandlerMethod handlerMethod = (HandlerMethod)handler;

		//3. Method에 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation( Auth.class );
		
//		handlerMethod.getMethod().getDeclaringClass().getAnnotation(annotationClass)//이걸쓰면이제 클래스에 붙은거 여부확일할떄 사용가능
		//3-1. Method에 @Auth가 안붙어있으면 class에 붙었는지확인 
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		//4. Method에 @Auth가 안 붙어 있으면
		if(auth == null) {
			return true;
		}

		//5. @Auth가 붙어있기 때문에 로그인여부확인(인증여부)
		HttpSession session = request.getSession();
		UserVo authUser = null;
		if(session != null) {
			authUser = (UserVo)session.getAttribute("authuser");
		}
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//6.접근허용
		return true;
	}
}
