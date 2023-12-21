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
  <title>게시판</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/notice_list.css">
</head>
<body>
<section>
    <h1>NOTICE</h1>
    <div class="wrapper">
      <form action="/search" name="search" method="post">
        <select name="category" id="category">
          <option value="0">전체</option>
          <option value="title">제목</option>
          <option value="content">내용</option>
        </select>

        <div class="title">
          <input type="text" size="16">
        </div>
  
        <button type="submit"><i class="fas fa-search"></i></button>
      </form>
    </div>

    <table>
      <colgroup>
        <col width="18%">
        <col width="50%">
        <col width="18%">
        <col width="10%">
      </colgroup>
      <!-- 제목부분 -->
      <tr>
        <th>No.</th>
        <th>제목</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
      <!-- 내용부분 -->
		<c:if test="${map.list.size() ==0 }">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>

      <c:forEach var="bdto" items="${map.list }">
      <tr>
        <td><span class="table-notice">${bdto.bno }</span></td>
        <td class="table-title">
        	<a href="bView?bno=${bdto.bno }">
        	<c:forEach var="i" begin="1" end="${bdto.bindent }" step="1">▶</c:forEach>
        	${bdto.btitle }</a>
        </td>
        <td>
        	<fmt:formatDate value="${bdto.bdate }" pattern="yyyy-MM-dd"/>
      	</td>
        <td>${bdto.bhit }</td>
      </tr>
      </c:forEach>
      
    </table>
	<!-- 하단넘버링 -->
    <ul class="page-num">
      <a href="bList?page=1"> <li class="first"></li></a>
     <!-- 이전페이지 화살표 -->
     <c:if test="${map.page<=1 }">
      	<li class="prev"></a>
     </c:if>
     <c:if test="${map.page>1 }">
      	<a href="bList?page=${map.page-1 }"><li class="prev"></li></a>
     </c:if>
     <!-- 이전페이지 화살표 끝 -->
      <!-- 넘버 -->
      <c:forEach var="i" begin="${map.startPage }" end="${map.endPage }" step="1">
      	<c:if test="${map.page==i }">
	      		<li class="num on"><div>${i}</div></li>
      	</c:if>
      	<c:if test="${map.page!=i }">
	      	<a href="bList?page=${i}">
	      		<li class="num"><div>${i}</div></li>
	      	</a>
      	</c:if>
      </c:forEach>
     <!-- 넘버끝 -->
     
     <!-- 다음페이지 화살표 -->
      <c:if test="${map.page<map.maxPage }">
      	<a href="bList?page=${map.page+1 }"><li class="next"></li></a>
      </c:if>
      <c:if test="${map.page>=map.maxPage }">
      	<li class="next"></li>
      </c:if>
     <!-- 다음페이지 화살표 끝 -->
      <a href="bList?page=${map.maxPage }"><li class="last"></li></a>
    </ul>
	<!-- 하단넘버링 끝-->

    <a href="bInsert"><div class="write">쓰기</div></a>
  </section>

</body>
</html>