package sec03.brd01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



// 이 클래스는  /board/listArticles.do로 요청시 화면에 글 목록을 출력하는 역할을 합니다. 
//@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
	BoardService boardService;
	ArticleVO articleVO;

	//서블릿 초기화시  BoardService 객체를 생성 합니다.
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//요청명을 가져옵니다.
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			
			if (action == null) {//요청명이 null일때
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board01/listArticles.jsp";
			} else if (action.equals("/listArticles.do")) {//요청명이 /listArticles.do 이면 DB에 저장된 전체글을 조회함.
				articlesList = boardService.listArticles();//BoardService클래스의 listArticles() 메서드를 호출해 전체 글을 조회함.
				request.setAttribute("articlesList", articlesList);//조회된 글 목록을 articlesList로 바인딩한 후  listArticles.jsp로 포워딩합니다.
				nextPage = "/board01/listArticles.jsp";
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
