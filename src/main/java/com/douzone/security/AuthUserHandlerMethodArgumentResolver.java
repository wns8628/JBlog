package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter,
								  ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest,
								  WebDataBinderFactory binderFactory) throws Exception {

		if(supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		System.out.println("오나요?");
		//@AuthUser가 붙어있고 타입이 UserVo라는 얘기이다.
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();//was종류떄문에 NativeWebRequest이걸 파라미터로준다.
																						//그래서우리걸 뺴와야지 
		HttpSession session = request.getSession();
		if(session == null) {
			return null; //이게그 컨트롤러 파라미터에 세팅됨 
		}
		
		
		return session.getAttribute("authuser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) { //타입이나 메소드맞는지확인하고		
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

		System.out.println("오나요?");
		//@AuthUser 가 안붙어있음 
		if(authUser == null) {
			return false;					
		}
		
		//파라미터 타입이 UserVo인지 확인해라
		if(parameter.getParameterType().equals(UserVo.class) == false ) {
			return false;
		}
		return true;
	}	
}
