package sec03.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@WebServlet("/json")
public class JsonServlet1 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//Ajax로 전달된 JSON 문자열을 getParameter()메소드를 이용해 가져 옵니다.
		String jsonInfo = request.getParameter("jsonInfo");
		try {
			
			//참고 : JSONParser 클래스는 JSON 문자열을 파싱해서 
			//JSONObject 객체로 반환하는 기능을 제공합니다
			
			//JSON 문자열을 파싱해서 JSONObject 객체로 반환할 객체 생성
			JSONParser jsonParser = new JSONParser();
			
			//parse(jsonInfo)메소드를 호출하면..
			//JSON 문자열을 파싱해서 JSONObject 객체로 반환한다.
			//참고 : JSONObject객체 내부를 살펴 보면  HashMap의 구조로 각인덱스 위치에  key:value를 쌍으로 저장 한다.
			//JSONObject 객체 내부
			//{"gender":"남자","name":"박지성","nickname":"날센돌이","age":"25"}
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);
			System.out.println("* 회원 정보 *");
			//JSON 데이터의 name속성들을 get()에 전달하여 value를 출력합니다.
			System.out.println(jsonObject.get("name"));
			System.out.println(jsonObject.get("age"));
			System.out.println(jsonObject.get("gender"));
			System.out.println(jsonObject.get("nickname"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
