<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 컨텍스트명 얻기 --%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
  </style>
  <meta charset="UTF-8">
  <title>글목록창</title>
</head>
<body>
	<table align="center" border="1"  width="80%"  >
			<tr height="10" align="center"  bgcolor="lightgreen">
			   <td >글번호</td>
			   <td >작성자</td>              
			   <td >제목</td>
			   <td >작성일</td>
			</tr>
<c:choose>
<%-- 	BoardController.java서블릿으로 부터  전달 받은 request영역에 articleList 속성으로 바인딩된... --%>
<%-- 	ArrayList객체가 저장되어 있지 않다면.. --%>
	<c:when test="${articlesList ==null }" >
			<tr  height="10">
			  <td colspan="4">
			     <p align="center">
			        <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
			    </p>
			  </td>  
			</tr>
	</c:when>
	
<%--    BoardController.java서블릿으로 부터  전달 받은 request영역에 articleList 속성으로 바인딩된... --%>
<%-- 	ArrayList객체가 저장되어 있다면.. --%>
	<c:when test="${articlesList !=null }"> 
	
	    <%--BoardController.java서블릿으로 부터  전달 받은 request영역에 articleList 속성으로 바인딩된...
	        ArrayList객체의 크기(검색한 글의 갯수(ArticleVO객체의 갯수))만큼 반복하여 
	     	검색한 글정보(ArticleVO객체)들을  ArryList객체 내부의 각 인덱스 위치로부터 차례대로 꺼내와 글목록을 표시합니다.--%>
    	<c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
		    <tr align="center">
				<td width="5%">${articleNum.count}</td><%--varStatus의 count속성을 이용해 글 번호를 1부터 자동으로 표시합니다.  --%>
				<td width="10%">${article.id }</td><%-- ArticleVO객체(검색한 글하나의 정보)의  id변수값(작성자 id)출력--%>
				<td align='left'  width="35%">
			  		<span style="padding-right:30px"></span> <%--왼쪽으로 30PX만큼 여백을 준 후 글제목을 표시할 목적으로 여백을 줌 --%>
	   				<c:choose>
						<%--조건: <forEach> 태그 반복 시 각 글의 level 값이 1보다 크면?  답글(자식글)이므로 ....--%>
						<c:when test='${article.level > 1 }'>  
							<%-- 다시 내부 <forEach>태그를 이용해 1부터 level값까지 반복하면서 부모글 밑에 공백으로 들여 쓰기하여  답글(자식 글)임을 표시 합니다.  --%>
							<c:forEach begin="1" end="${article.level }" step="1">
								<span style="padding-left:20px"></span>    
							</c:forEach>
								<%-- 공백 다음에 자식 글을 표시합니다. --%>
								<span style="font-size:12px;">[답변]</span>
								<a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
						</c:when>
						
						<%--조건  : 이때 level 값이 1보다 크지 않으면  부모 글이므로 공백 없이 표시 합니다. --%>
						<c:otherwise>
								<a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
						</c:otherwise>
			          </c:choose>
				  </td>
				  <td  width="10%"><fmt:formatDate value="${article.writeDate}" /></td> 
			</tr>
		</c:forEach>
	</c:when>
</c:choose>
	</table>
	<a  class="cls1"  href="#"><p class="cls2">글쓰기</p></a>
</body>
</html>
