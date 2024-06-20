<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<style>
	.text_center{
		text-align:center;
	}
</style>
<script>
function check(){
	var regExpId = /^[a-zA-Z0-9]+$/;
	var regExpPwd = /^[0-9]+$/;
	var regExpName = /^[가-힣]+$/;
	var regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		
	var form = document.newMember;
		
	var id = form.id.value;
	var pwd = form.pwd.value;
	var name = form.name.value;
	var email = form.email.value;
	
	
	if(!regExpId.test(id)){
		alert("아이디는 영문과 숫자만 입력해주세요.");
		form.id.select();
		return false;
	}
	
	if(!regExpPwd.test(pwd)){
		alert("비밀번호는 숫자만 입력해주세요.")
		form.pwd.select();
		return false;
	}
	
	if(!regExpName.test(name)){
			alert("이름은 한글만 입력해주세요.")
		form.name.select();
		return false;
		}
		
	if(!regExpEmail.test(email)){
			alert("이메일 형식이 올바르지 않습니다.")
		form.email.select();
		return false;
		}
	return true;
	form.submit();
}
</script>
</head>
<body>
	<form method="post" action="${contextPath}/member/addMember.do" name="newMember" onSubmit="return check()">
		<h1 class="text_center">회원가입창</h1>
		<table align="center">
			<tr>
				<td width="200"><p align="right">아이디</td>
				<td width="400"><input type="text" name="id"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">비밀번호</td>
				<td width="400"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이름</td>
				<td width="400"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이메일</td>
				<td width="400"><input type="text" name="email"></td>
			</tr>
			<tr>
				<td width="200"><p>&nbsp;</p></td>
				<td width="400"><input type="submit" value="가입하기">
				<input type="reset" value="다시입력"</td>
			</tr>
		</table>
	</form>
</body>
</html>