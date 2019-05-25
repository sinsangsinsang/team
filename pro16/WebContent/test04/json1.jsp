<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSON 테스트</title>
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(function() {
        $("#checkJson").click(function() {
        //JSON객체는 중괄호{}로 둘러 싸서 표현 합니다.
        //{ }중괄호 안에 실제 값은  name:value 를 쌍으로 나열 합니다.
        //"name" -> 배열 이름
        //["홍길동", "이순신", "임꺽정"] -> 배열
         var jsonStr  = '{"name": ["홍길동", "이순신", "임꺽정"] }';  
	    
        //JSON.parse() 메소드
	    //- parse 메소드는 String객체를 json객체로 변환시켜줍니다.
        var jsonInfo = JSON.parse(jsonStr);
//	    alert(jsonInfo); // Object   <-- JSON 객체
	    
	    //JSON.stringify() 메소드
		//- stringify 메소드는 json 객체를 String 객체로 변환시켜 줍니다.
//	    var S  = JSON.stringify(jsonInfo);
//	    alert(S); // {"name": ["홍길동", "이순신", "임꺽정"] }  <-- String 객체
	    
	    
        var output ="회원 이름<br>";
        output += "=======<br>";
        //배열 이름 name으로 배열의 인덱스 위치에 차례대로 접근하여  값을 가져옵니다. 
        for(var i in jsonInfo.name) {
            output += jsonInfo.name[i]+"<br>";
        }
        //id속성값이 output인  <div>태그 영역에 회원 이름을 출력 합니다.
        $("#output").html(output);
      });
    });
        
</script>
  </head>
  <body>
    <a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
  </body>
</html>
