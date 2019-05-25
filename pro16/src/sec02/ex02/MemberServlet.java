package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/mem2")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandler(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandler(request, response);
	}

	protected void doHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
	
		MemberDAO memberDAO = new MemberDAO();
	
		List memberList = memberDAO.listMembers();
		
		String result = null;
		//조회한 회원 정보를 XML 문자열로 만든다.
		result="<main>";
		
		for(int i=0;  i<memberList.size(); i++) {
			MemberBean m = (MemberBean)memberList.get(i);
			String id = m.getId();
			String name = m.getName();
			String email = m.getEmail();
			Date date = m.getJoinDate();
			String pwd = m.getPwd();
			
			result += 	"<member>"
						+ "<id>" + id + "</id>" 
						+ "<name>" + name + "</name>"
						+ "<email>" + email + "</email>"
						+ "<date>" + date + "</date>"
						+ "<pwd>" + pwd + "</pwd>"
						+ "</member>";				 
		}
		
		result +="</main>";
		PrintWriter writer = response.getWriter();
		writer.print(result);
	
	}

}



