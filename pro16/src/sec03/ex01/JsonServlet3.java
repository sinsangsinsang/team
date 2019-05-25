package sec03.ex01;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/json3")
public class JsonServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();		
		//배열을 저장할 JSONObject객체 생성
		JSONObject totaObject = new JSONObject();
		//HashMap의 구조와 같은 JSONObject객체들을 저장할  JSONArray객체배열 생성
		JSONArray membersArray = new JSONArray();		
		//회원 한명의 정보가 들어갈 JSONObjecr객체 생성
		JSONObject memberInfo = new JSONObject();
		//회원 한명의 정보를 name/value 쌍으로 저장 합니다. 
		memberInfo.put("name", "박지성");
		memberInfo.put("age", "25");
		memberInfo.put("gender", "남자");
		memberInfo.put("nickname", "날센돌이");
		//회원 한명의 정보가 저장되어 있는 JSONObject객체를 JSONArray객체 배열에 저장합니다. 
		membersArray.add(memberInfo);		
		//회원 한명의 정보가 들어갈 JSONObjecr객체 생성
		memberInfo = new JSONObject();
		//회원 한명의 정보를 name/value 쌍으로 저장 합니다. 
		memberInfo.put("name", "김연아");
		memberInfo.put("age", "21");
		memberInfo.put("gender", "여자");
		memberInfo.put("nickname", "칼치");
		//회원 한명의 정보가 저장되어 있는 JSONObject객체를 JSONArray객체 배열에 저장합니다. 
		membersArray.add(memberInfo);		
		//배열을 저장할 JSONObject객체에 members라는 name으로  membersArray를 value로 저장합니다.
		totaObject.put("members", membersArray);
	
		//HashMap의 구조와 같은 JSONObject객체들을 저장할  JSONArray객체배열 생성
		JSONArray bookArray = new JSONArray();		
		//도서 정보가 들어갈 JSONObjecr객체 생성
		JSONObject bookInfo = new JSONObject();
		//도서 정보를 name/value 쌍으로 저장 합니다. 
		bookInfo.put("title", "자바의 정석");
		bookInfo.put("writer", "남궁성");
		bookInfo.put("price", "30000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pro16/image/image1.jpg");
		//하나의 도서 정보가 저장되어 있는 JSONObject객체를 JSONArray객체 배열에 저장합니다. 
		bookArray.add(bookInfo);
		
		//도서 정보가 들어갈 JSONObjecr객체 생성
		bookInfo = new JSONObject();
		//도서 정보를 name/value 쌍으로 저장 합니다.
		bookInfo.put("title", "JSP & Servlet");
		bookInfo.put("writer", "오정현");
		bookInfo.put("price", "32000");
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8090/pro16/image/image2.jpg");
		//하나의 도서 정보가 저장되어 있는 JSONObject객체를 JSONArray객체 배열에 저장합니다.
		bookArray.add(bookInfo);
		//배열을 저장할 JSONObject객체에 books라는 name으로  bookArray를 value로 저장합니다.
		totaObject.put("books", bookArray);
		
		//배열이 저장된 JSONObject객체를 문자열로 변환 하여 저장
		String jsonInfo = totaObject.toJSONString();		
		System.out.print(jsonInfo);
		writer.print(jsonInfo);//문자열로 변환된  JSON 데이터를 웹브라우저로 전송하여 응답 합니다.
/*
		{  
			   "books":[  
					      {  
					         "image":"http:\/\/localhost:8090\/pro16\/image\/image1.jpg",
					         "price":"30000",
					         "genre":"IT",
					         "writer":"남궁성",
					         "title":"자바의 정석"
					      },
					      {  
					         "image":"http:\/\/localhost:8090\/pro16\/image\/image2.jpg",
					         "price":"32000",
					         "genre":"IT",
					         "writer":"오정현",
					         "title":"JSP & Servlet"
					      }
			   		   ],
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
	}
}