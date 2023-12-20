<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>뷰페이지</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/read.css">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>
  	$(function(){
  		$(".replyBtn").click(function(){
  			alert("게시글 답변쓰기 이동합니다.");
  			$("#bFrm").attr("action","bReply").submit()
  		});//.replyBtn
  		
  		$(".updateBtn").click(function(){
  			alert("게시글 수정페이지로 이동합니다.");
  			$("#bFrm").attr("action","bUpdate").submit()
  		});//.updateBtn
  		
  	  $(".delBtn").click(function(){
		  if(confirm("게시글을 삭제하시겠습니까?")){
			 $("#bFrm").attr("action","bDelete").submit();  //bDelete페이지 전송
		  }//if
	  });//.delBtn
  		
  	});//제이쿼리 최신
  </script>
</head>
<section>
    <h1>NOTICE</h1>
   <form action="bDelete" id="bFrm" name="bFrm" method="post" enctype="multipart/form-data">
   <input type="hidden" name="bno" value="${map.bdto.bno }">
    <table>
      <colgroup>
        <col width="80%">
        <col width="10%">
        <col width="10%">
      </colgroup>
      <tr>
        <td colspan="3"><strong>제목</strong> <span class="separator">|</span> ${map.bdto.btitle }</td>
      </tr>
      <tr>
        <td colspan="1"><strong>작성자</strong> <span class="separator">|</span> ${map.bdto.id }</td>
        <td><strong>조회수</strong> <span class="separator">|</span>${map.bdto.bhit }</td>
      </tr>
      <tr>
        <td colspan="3" class="article"><strong>내 용</strong> <span class="separator">|</span>${map.bdto.bcontent }</td>
      </tr>
      <c:if test="${map.bdto.bfile==null}">
	    <tr>
		    <td><strong>파일이름</strong></td>
	      	<td style="color: red">※ 첨부파일 없음</td>
      	</tr>
      </c:if>
      <c:if test="${map.bdto.bfile!=null}">
	      <tr>
	        <td colspan="3" class="article"><strong>파일이름</strong> <span class="separator">|</span>${map.bdto.bfile }</td>
	      </tr>
      </c:if>
      
      <c:if test="${map.bdto.bfile==null}">
	      <tr>
	        <td><strong>이미지</strong></td>
	        <td colspan="3" class="article"><i class="fa fa-window-close" aria-hidden="true"></i>이미지 없음</td>
	      </tr>
      </c:if>
      <c:if test="${map.bdto.bfile!=null}">
	      <tr>
	        <td colspan="3" class="article" style="align-content: center;"><strong>이미지</strong> <span class="separator">|</span><img src="/upload/${map.bdto.bfile }"></td>
	      </tr>
      </c:if>
      
      <c:if test="${map.nextDto.btitle!=null }">
	      <tr>
	        <td colspan="3"><strong>다음글</strong> <span class="separator">|</span><a href="bView?bno=${map.nextDto.bno }">${map.nextDto.btitle }</a></td>
	      </tr>
      </c:if>
      <c:if test="${map.nextDto.btitle==null }">
	      <tr>
	        <td colspan="3"><strong>다음글</strong> <span class="separator">|</span>다음글이 존재하지 않습니다.</td>
	      </tr>
      </c:if>
      <c:if test="${map.prevDto.btitle!=null }">
	      <tr>
	        <td colspan="3"><strong>이전글</strong> <span class="separator">|</span><a href="bView?bno=${map.prevDto.bno }">${map.prevDto.btitle }</a></td>
	      </tr>
      </c:if>
      <c:if test="${map.prevDto.btitle==null }">
	      <tr>
	        <td colspan="3"><strong>이전글</strong> <span class="separator">|</span>이전글이 존재하지 않습니다.</td>
	      </tr>
      </c:if>
    </table>

    <a href="bList"><div class="list">목록</div></a>
    <c:if test="${session_id==map.bdto.id }">
		<div class="list delBtn" style="cursor: pointer">삭제</div>
		<div class="list updateBtn" style="cursor: pointer">수정</div>
		<div class="list replyBtn" style="cursor: pointer">답변달기</div>
    </c:if>
    </form>
  </section>
</body>
</html>