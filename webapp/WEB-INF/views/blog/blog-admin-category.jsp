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
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

var render = function(vo, mode){
	var htmls = 
         "<tr data-no='" + vo.no + "'>" +
         "<td>" + vo.no + "</td>" +
         "<td>" + vo.name + "</td>" +
         "<td>" + vo.postCount + "</td>" +
         "<td>" + vo.description + "</td>" +
         "<td>" + "<img src ='${pageContext.request.contextPath}/assets/images/deletes.jpg' data-no='"+ vo.no + "'>" + "</td>" +
         "</tr>";			
         
	if(mode == true){
		$(".admin-cat").append(htmls);		
	}else{	
		$(".admin-cat").append(htmls);
	}
}
var fetchList = function(){
	$.ajax({
		url:"/jblog/123/admin/category/list",
		type:"get",
		dataType:"json",
		data:"",//잭슨이바ㅜ꺼줌?먼솔
		success: function(response){
			console.log("안옴?");
			console.log(response.data);
			
			if(response.result =="fail"){
				console.warn(response.message);
				return;
			}
			console.log(response.data);
			
			//rendering
			$.each(response.data, function(index, vo){
				render(vo, false);
			});  
			
		},
 		error:function(xhr, status, e){ // xhr왜안씀? 객체니깐  통신더할려면 쓰던가. 근데 지금은 에러만출력하고 끝내니깐 
			console.error(status + ":" + e);
		} 
	});
}

var pushCategory = function(){
	
	//validate form data 유효성검사
	var name = $("#name").val();
	var desc = $("#desc").val();

	$.ajax({
		url:"/jblog/123/admin/category/list", //리퀘스트 매핑,파라미터받고하면 ? a=list이딴거안해도됨 프리티 url가능 
		type:"post",
		dataType:"json",
		data:"name="+ name +
			 "&description=" + desc,
					   
		success: function(response){
			
 			if(response.result =="fail"){
				console.warn(response.data);
				return;
			}			
			//rendering
			render(response.data, true);	
		},
		error:function(xhr, status, e){ // xhr왜안씀? 객체니깐  통신더할려면 쓰던가. 근데 지금은 에러만출력하고 끝내니깐 
			console.error(status + ":" + e);
		}
	});
}

$(function(){	
	fetchList();
	
	$("#submit").click(function(event){
		pushCategory();		
	});
	
	
})

</script>
</head>
<body>
	<div id="container">
	
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog-header.jsp">
				<c:param name="menu" value="admin-category"/>
			</c:import>
		</div>
		
		<div id="wrapper">
			<div id="content" class="full-screen">
				
				<c:import url="/WEB-INF/views/includes/blog-navigation.jsp">
					<c:param name="menu" value="blog-category"/>
				</c:import>

		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>				  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="name" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="desc" name="description" ></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="button" id="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/blog-footer.jsp"/>
		</div>
		
	</div>
</body>
</html>