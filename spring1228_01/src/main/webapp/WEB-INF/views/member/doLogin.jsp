<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>doLogin</title>
	</head>
	<body>
		<script>
		if(${result==1}){
			alert("※로그인 되었습니다.");
			location.href="/";
		} else {
			alert("※아이디 또는 비밀번호가 일치하지 않습니다. 다시 로그인해주세요");
			location.href="/member/login";
		}//if-else
		</script>
	</body>
</html>