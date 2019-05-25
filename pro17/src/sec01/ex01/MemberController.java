package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/mem.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	//init()메소드에서 MemberDAO 객체를 초기화 함
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		//MemberDAO의 listMembers()메소드를 호출하여  요청에 대해  DB로부터 검색한 회원 정보를 ArrayList로 반환 받습니다.
		List membersList = memberDAO.listMembers();
		//이때 request내장객체에 검색한 회원 정보를 memberList 속성 이름으로 바인딩 합니다.  
		request.setAttribute("membersList", membersList);
		//그런 다음 RequestDispatcher 클래스를 이용해 회원 목록창(listMembers.jsp 뷰페이지)으로 포워딩 합니다.
		RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMembers.jsp");
		dispatch.forward(request, response);
	}

}


