<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
	
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog-header.jsp">
				<c:param name="menu" value="admin-basic"/>
			</c:import>
		</div>
		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="">카테고리</a></li>
					<li><a href="">글작성</a></li>
				</ul>
				<form action="${pageContext.request.contextPath}/${authuser.id}/admin" method="post"
					  enctype="multipart/form-data">
					  
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td>
			      				<input type="text" size="40" name="title" value="${blogVo.title }">
			      			</td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img src="${pageContext.request.contextPath}${blogVo.logo}"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="logo-file"/></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
			      	
				</form>
			</div>
		</div>
		
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/blog-footer.jsp"/>
		</div>
	</div>
</body>
</html>