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
		<h1>index.jsp_1219_02 페이지 입니다.</h1>
		<ul>
			<c:if test="${session_id==null }">
				<li><strong style="font-size: 20px">로그인을 해주세요</strong></li>
				<li><a href="member/login">로그인</a></li>
			</c:if>
			<c:if test="${session_id!=null }">
				<li><strong style="font-size: 20px">${session_name }</strong> 회원님 환영합니다.</li>
				<li><a href="member/logout">로그아웃</a></li>
			</c:if>
			<li><a href="board/bList">공지사항 리스트(list)</a></li>
			<li><a href="board/bInsert">글쓰기(insert)</a></li>
			<li><a href="board/bView">공지사항보기(view)</a></li>
			<li><a href="member/mInsert">회원가입</a></li>
			<li><a href="board/boardBno">글번호</a></li> <!-- @PathVariable 관련 -->
		</ul>
	</body>
</html>