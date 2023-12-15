<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>index.jsp</title>
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<h1>index.jsp_1215_01 페이지 입니다.</h1>
		<ul>
			<li><a href="/">메인화면</a></li>
			<li><a href="bList">공지사항 리스트(list)</a></li>
			<li><a href="mInsert">회원가입</a></li>
			<li><a href="boardBno/15/100">글번호</a></li> <!-- @PathVariable 관련 -->
			<li><a href="login">로그인</a></li>
			<li><a href="bInsert">공지사항 글쓰기(insert)</a></li>
			<li><a href="bView">공지사항 보 기(view)</a></li>
		</ul>
	</body>
</html>