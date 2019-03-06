<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var FormValidator= {		
		init: function(){				
			$("#write-form").submit(this.onFormSubmit.bind(this));
		},
		onFormSubmit: function(){		
			if($("#title").val() == ""){
				alert("제목은 필수 입력 항목입니다.");
				$("#title").focus();
				return false;
			}
			if($("#contentPost").val() == ""){
				alert("내용은 필수 입력 항목입니다.");
				$("#contentPost").focus();
				return false;
			}
			if($("#selectCategory").val() == "not"){
				alert("카테고리를 선택하세요");
				$("#selectCategory").focus();
				return false;
			}
			//인증됨!
			return true;
		}
	}
$(function(){
	FormValidator.init();
});
</script>
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
	
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog-header.jsp">
				<c:param name="menu" value="admin-write"/>
			</c:import>
		</div>
		
		<div id="wrapper">
			<div id="content" class="full-screen">
			
				<c:import url="/WEB-INF/views/includes/blog-navigation.jsp">
					<c:param name="menu" value="blog-write"/>
				</c:import>
				
				<form id="write-form" action="${pageContext.request.contextPath}/${authuser.id}/admin/write" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" id="title" name="title">
				      			<select name="no" id="selectCategory">
			      					<option value="not">카테고리 선택</option>
				      				<c:forEach  items="${categoryList}" var="categoryListVo" varStatus="status">	
				      					<option value="${categoryListVo.no }">${categoryListVo.name}</option>
				      				</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="content" id="contentPost"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
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