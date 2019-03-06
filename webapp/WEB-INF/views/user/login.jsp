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
		$("#login-form").submit(this.onFormSubmit.bind(this));
	},
	
	
	onFormSubmit: function(){
		//1.아이디
		if($("#id").val() == ""){
			alert("아이디를 입력하세요");
			$("#id").focus();
			return false;
		}
		//2.비번
		if($("#password").val() == ""){
			alert("비밀번호를 입력하세요");
			$("#password").focus();
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
	
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="login"/>
		</c:import>
		
		<form id="login-form" class="login-form" action="${pageContext.request.contextPath}/user/auth" method="post">
      		<label>아이디</label> <input type="text" id="id" name="id">
      		<label>패스워드</label> <input type="text" id="password" name="password">
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
