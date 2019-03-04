<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<a href="${pageContext.request.contextPath}">    
	<h1 class="logo">JBlog</h1>
</a>
<c:choose>
		<c:when test="${param.menu == 'main' }">
			<ul class="menu">			
				<c:choose>
					<c:when test="${empty authuser}">
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>					
					</c:when>
					<c:otherwise>
						<li>${authuser.name } 님 반갑습니다.</li>						
						<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/${authuser.name}">내블로그</a></li>					
					</c:otherwise>
				</c:choose>
			</ul>
		</c:when>
		<c:when test="${param.menu == 'join' }">
			<ul class="menu">			
				<c:choose>
					<c:when test="${empty authuser}">
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>					
					</c:when>
					<c:otherwise>
						<li>${authuser.name } 님 반갑습니다.</li>
						<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/${authuser.name}">내블로그</a></li>		
					</c:otherwise>
				</c:choose>
			</ul>
		</c:when>
		<c:when test="${param.menu == 'login' }">
			<ul class="menu">			
				<c:choose>
					<c:when test="${empty authuser}">
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>					
					</c:when>
					<c:otherwise>
						<li>${authuser.name } 님 반갑습니다.</li>
						<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/${authuser.name}">내블로그</a></li>				
					</c:otherwise>
				</c:choose>
			</ul>
		</c:when>
</c:choose>