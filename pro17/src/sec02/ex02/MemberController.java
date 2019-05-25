package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

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

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	
			throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		if (action == null || action.equals("/listMembers.do")) {
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		} else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			request.setAttribute("msg", "addMember");
			nextPage = "/member/listMembers.do";
		} else if (action.equals("/memberForm.do")) {
			nextPage = "/test03/memberForm.jsp";
		
		//listMembers.jsp페이지에서 수정링크를 클릭 했을때...
		//컨트롤러에 회원정보 수정창 요청시  ID로 회원 정보를 조회한 후 수정창으로 포워딩합니다.
		}else if(action.equals("/modMemberForm.do")){
			
		     String id=request.getParameter("id");
		    //회원정보 수정창을 요청하면서 전송된 ID를 이용해 수정 전 회원 정보를 검색 해옵니다. 
		     MemberVO memInfo = memberDAO.findMember(id);
		     //request에 바인딩하여 회원정보 수정창에 수정하기 전의 회원정보를 전달 합니다.
		     request.setAttribute("memInfo", memInfo);
		     //회원정보 수정창 페이지로 포워딩 하기 위한 URL 저장
		     nextPage="/test03/modMemberForm.jsp";
		
		//회원정보 수정창에서 회원수정 정보를 입력후  수정하기 버튼을 클릭한후..
		// http://localhost:8090/pro17/member/modMember.do로 DB 테이블의 데이터 수정 요청이 들오면.. 수정함.
		}else if(action.equals("/modMember.do")){
			//회원 정보 수정창에서 전송된 수정 회원 정보를 가져온 후  
		     String id=request.getParameter("id");
		     String pwd=request.getParameter("pwd");
		     String name= request.getParameter("name");
	         String email= request.getParameter("email");
	         //MemberVO 객체 속성에 설정 합니다.
	         MemberVO memberVO = new MemberVO(id, pwd, name, email);
		     //DB 회원 테이블의 데이터 수정
		     memberDAO.modMember(memberVO);
		     //수정후 회원목록창(listMembers.jsp)으로 수정작업 완료 메세지를 전달하기 위해..
		     //request에 바인딩 함.
		     request.setAttribute("msg", "modified");
		     //수정후 회원목록창(listMembers.jsp)으로 바인딩 하기 위한 URL 설정
		     nextPage="/member/listMembers.do";
		     
		//회원 ID를  SQL문으로 전달해 테이블의 회원 정보를 삭제 합니다.
		}else if(action.equals("/delMember.do")){
			//삭제할 회원 ID를 받아옵니다.
		     String id=request.getParameter("id");
		     //삭제할 회원 ID를 이용하여 회 DB 회원 테이블의 데이터 삭제
		     memberDAO.delMember(id);
		     //삭제후 회원목록창(listMembers.jsp)으로 삭제작업 완료 메세지를 전달하기 위해..
		     //request에 바인딩 함.
		     request.setAttribute("msg", "deleted");
		     //삭제후 회원목록창(listMembers.jsp)으로 바인딩 하기 위한 URL 설정
		     nextPage="/member/listMembers.do";
		}else {
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
