<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<!DOCTYPE html>
<html>
<head>
  <title>JSON ajax 연습</title>
 <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
 <script>
    $(function() {  	
        $("#checkJson").click(function() {
          //전송할 회원 정보를 JSON 데이터 형식의 문자열을 만들어서 저장
    	   var _jsonInfo ='{"name":"박지성","age":"25","gender":"남자","nickname":"날센돌이"}';
    	   $.ajax({
             type:"post",
             async:false, 
             url:"<%=request.getContextPath()%>/json",
             data : {jsonInfo: _jsonInfo}, // 매개변수 이름 jsoinInfo로  JSON 데이터형식의 문자열을 ajax기술로 전송 함.
             success:function (data,textStatus){
	     },
	     error:function(data,textStatus){
	        alert("에러가 발생했습니다.");ㅣ
	     },
	     complete:function(data,textStatus){
	     }
	   });  //end ajax	

       });
    });
 </script>
</head>
<body>
   <a id="checkJson" style="cursor:pointer">전송</a><br><br>
    <div id="output"></div>
</body>
</html>
