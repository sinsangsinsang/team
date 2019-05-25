<%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 
<head>
<meta charset="UTF-8">
<title>회원 정보 수정창</title>
<style>
  .cls1 {
     font-size:40px;
     text-align:center;
   }
</style>
</head>
<body>
 <h1 class="cls1">회원 정보 수정창</h1>
 <%--
 	회원 정보 수정창에서 수정할 회원 정보를 입력하고 수정하기를 클릭하면
 	action 속성에 설정한 요청명   /member/modMember.do와 회원 ID를 전달해 수정을 요청하도록 구현 합니다.
  --%>
<form  method="post" action="${contextPath}/member/modMember.do?id=${memInfo.id}">
 <table align="center" >
   <tr>
     <td width="200"><p align="right" >아이디</td>	       <%--조회한 회원 정보를 텍스트 박스에 표시함.--%>
     <td width="400"><input   type="text" name="id" value="${memInfo.id}" disabled ></td>
     
   </tr>
 <tr>
     <td width="200"><p align="right" >비밀번호</td>        <%--조회한 회원 정보를 텍스트 박스에 표시함.--%>
     <td width="400"><input   type="password" name="pwd" value="${memInfo.pwd}" >
     </td>
   </tr>
   <tr>
     <td width="200"><p align="right" >이름</td>
     <td width="400"><input   type="text" name="name" value="${memInfo.name}" ></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >이메일</td>
     <td width="400"><input   type="text" name="email"  value="${memInfo.email}" ></td>
   </tr>
   <tr>
     <td width="200"><p align="right" >가입일</td>
     <td width="400"><input   type="text"  name="joinDate" value="${memInfo.joinDate }" disabled  ></td>
   </tr>
   <tr align="center" >
    <td colspan="2" width="400"><input type="submit" value="수정하기" >
       <input type="reset" value="다시입력" > </td>
   </tr>
 </table>
</form>
</html>
