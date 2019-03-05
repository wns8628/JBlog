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
				<c:param name="menu" value="main"/>
			</c:import>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${postVo.content }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList}" var="postListVo" varStatus="status">	
					  	<c:choose>				
					  		<c:when test="${postVo.no == postListVo.no}">
							  	 <li class="selected">
				                 	<a href="${pageContext.request.contextPath}/${userId}/${postListVo.categoryNo}/${postListVo.no}">
										${postListVo.title }
									</a><sapn style="color:red">(현재 글)</span> 
									<span>${postListVo.regDate }</span>	
			                  	 </li>
					  		</c:when>
					  		<c:otherwise>
				  				<li>
									<a href="${pageContext.request.contextPath}/${userId}/${postListVo.categoryNo}/${postListVo.no}">
										${postListVo.title }
									</a> 
									<span>${postListVo.regDate }</span>	
								</li>
					  		</c:otherwise>
					  	</c:choose>						
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList}" var="categoryVo" varStatus="status">					
					<li><a href="${pageContext.request.contextPath}/${userId}/${categoryVo.no }/${categoryVo.topPostNo}">${categoryVo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/blog-footer.jsp"/>
		</div>
	</div>
</body>
</html>