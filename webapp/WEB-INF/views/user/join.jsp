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
var FormValidator = {

		$imageCheck: null,
		$buttonCheckId: null,
		$inputTextId: null,
		
		
		init: function(){		
			this.$imageCheck = $( "#img-checkId" );
			this.$buttonCheckId = $( "#btn-checkId" );
			this.$inputTextId = $( "#blog-id" );
			
			this.$inputTextId.keypress(this.onIdInputTextKeypressd.bind(this));
			this.$buttonCheckId.click(this.onAjaxCheckIdButtonClicked.bind(this));			
			$("#join-form").submit(this.onFormSubmit.bind(this));
		},
			
		onFormSubmit: function(){
			//1.이름체크
			if($("#name").val() == ""){
				alert("이름을입력하세요");
				$("#name").focus();
				return false;
			}

			//2-1.아이디체크
			if($("#blog-id").val() == ""){
				alert("아이디을입력하세요");
				$("#blog-id").focus();
				return false;
			}
			//2-2 아이디 중복체크
 			if(this.$imageCheck.is(":visible") == false){
				alert("아이디 중복체크를 해야합니다.");
				return false;
			} 
			
			//3. 비밀번호 확인
			if($("input[type='password']").val() == ""){
				alert("비밀번호를 입력하세요");
				$("input[type='password']").focus();
				return false;
			}
			
			//4. 약관동의
			if( $("#agree-prov").is(":checked")== false){
				alert("약관동의를 체크해야 회원가입이 됩니다.");
				return false;
			}
			//인증됨!
			return true;
		},
		
		onIdInputTextKeypressd: function(){
			this.$buttonCheckId.show();
			this.$imageCheck.hide();
		},
		
		onAjaxCheckIdButtonClicked:function(){
			var id =$("#blog-id").val();
			if(id == ""){
				return;
			}
			$.ajax({
				url:"${pageContext.request.contextPath }/user/api/checkId?id=" + id,
				type:"get",
				dataType:"json",
				//data:"",
				success: this.onCheckIdAjaxSuccess.bind(this),
				error: this.onCheckIdAjaxError.bind(this)
			});
		},
		
		onCheckIdAjaxSuccess : function(response){
			//통신실패
			if(response.result == "fail"){
				console.error(response.message);
				return;
			}
			
			if(response.data  == true){
				alert("이미 존재하는 아이디입니다. 다른 아이디을 사용해주세요");
				$("#blog-id").val("").focus();
				return;
			}else{
				//사용가능한 아이디
				this.$buttonCheckId.hide();
				this.$imageCheck.show();			
			}

		},
		
		onCheckIdAjaxError:function(xhr, status, e){
			console.error(status+":"+e);
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
	<div class="center-content">

		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="join"/>
		</c:import>
	
	
		<form class="join-form" id="join-form" method="post" action="">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-checkId" type="button" value="id 중복체크">
			<img id="img-checkId" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
