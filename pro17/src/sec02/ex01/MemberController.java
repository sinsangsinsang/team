package sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//컨트롤러 역할을 하는 MemberController 클래스.
//이 컨트롤러에서는 getPathInfo()메소드를 이용해 두 단계로 이루어진 요청을 가져옵니다.
//action변수값에 따라 if문을 분기해서 요청한 작업을 수행합니다.


//@WebServlet("/member/*")//웹브라우저에서 요청 시 두 단게로 요창이 이루어집니다.
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//getPathInfo()메소드를 이용해 URL에서 요청명을 가져옵니다.
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		//action변수의 값에 따라 if문을 분기해서 요청한 작업을 수행하는데..
		//만약 action변수의 값이  null이거나 /listMembers.do인 경우에 회원 검색 기능을 수행 합니다.
		if (action == null || action.equals("/listMembers.do")) {
			
			//MemberDAO의 listMembers()메소드를 호출하여  요청에 대해  DB로부터 검색한 회원 정보를 ArrayList로 반환 받습니다.
			List membersList = memberDAO.listMembers();
			
			//이때 request내장객체에 검색한 회원 정보를 memberList 속성 이름으로 바인딩 합니다.
			request.setAttribute("membersList", membersList);
			
			//검색한 회원정보(응답메세지)를 보여줄 View페이지 주소 설정
			//= test02폴더의 listMembers.jsp로 포워딩 하기 위해 주소 저장
			nextPage = "/test02/listMembers.jsp";
		
		//action변수의 값이  /addMember.do이면 전송된(요청된) 회원 정보를 가져와서 DB의 테이블에 insert 추가 합니다. 
		} else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);//(요청된) 회원 정보를 가져와서 DB의 테이블에 insert 추가 합니다. 
			//회원 등록 후 다시 회원 목록을 보여주는 View페이지로 이동 하기 위해 포워딩할 주소 설정
			nextPage = "/member/listMembers.do";
		
		//action변수의 값이 /memberForm.do이면 회원가입창을 화면에 출력합니다. 
		} else if (action.equals("/memberForm.do")) {
			
			//회원가입창을 화면으로 포워딩할 주소를 설정
			nextPage = "/test02/memberForm.jsp";
		
		//그외 action변수에  다른 요청 URL이 저장되어 있으면 회원목록을 출력 합니다.
		} else {
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMembers.jsp";
		}
		
		//nextPage변수에 저장한 요청명으로 다시 서블릿에 요청합니다.
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
