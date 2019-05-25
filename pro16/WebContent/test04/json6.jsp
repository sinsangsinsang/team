<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSON 테스트</title>
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script>
    $(function() {
        $("#checkJson").click(function() {
    	$.ajax({
            type:"post",
            async:false, 
            url:"<%=request.getContextPath()%>/json2",
            success:function (data,textStatus){
            //서버에서 응답한  data변수의 값은  JSON객체배열 데이터 형태의 문자열 이다.
/*		
		{  
			   "members":[  
			      {  
			         "gender":"남자",
			         "name":"박지성",
			         "nickname":"날센돌이",
			         "age":"25"
			      },
			      {  
			         "gender":"여자",
			         "name":"김연아",
			         "nickname":"칼치",
			         "age":"21"
			      }
			   ]
		}
 */		
            //parse(data)메소드를 호출하면..
			//JSON객체배열 데이터 형태의 문자열을  파싱해서 JSONObject 객체로 반환한다.
			//참고 : JSONObject객체 내부를 살펴 보면  HashMap의 구조로 각인덱스 위치에  key:value를 쌍으로 저장 한다.
           	var jsonInfo = JSON.parse(data);
           	var memberInfo ="회원 정보<br>";
	        memberInfo += "=======<br>";
	        for(var i in jsonInfo.members){
			   memberInfo += "이름: " + jsonInfo.members[i].name+"<br>";
			   memberInfo += "나이: " + jsonInfo.members[i].age+"<br>";
			   memberInfo += "성별: " + jsonInfo.members[i].gender+"<br>";
			   memberInfo += "별명: " + jsonInfo.members[i].nickname+"<br><br><br>";
	        }
	        $("#output").html(memberInfo);
	       },
	      error:function(data,textStatus){
	         alert("에러가 발생했습니다.");ㅣ
	      },
	      complete:function(data,textStatus){
	      }
	   }); 
       });
    });
 </script>
</head>
<body>
   <a id="checkJson" style="cursor:pointer">회원 정보 수신하기</a><br><br>
   <div id="output"></div>
</body>    
    