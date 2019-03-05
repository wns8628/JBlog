<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>${blogVo.title }</h1>
<ul>
	<c:choose>
		<c:when test="${empty authuser}">
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>			
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath }/${authuser.id}/admin">블로그 관리</a></li>	
			<li><a href="${pageContext.request.contextPath }/${authuser.id}">블로그</a></li>		
		</c:otherwise>
	</c:choose>
</ul>