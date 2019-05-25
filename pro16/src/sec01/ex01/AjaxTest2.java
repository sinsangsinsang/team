package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxTest2
 */
@WebServlet("/ajaxTest2")
public class AjaxTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String result = "";
		PrintWriter writer = response.getWriter();
		//도서 정보를 XML로 작성한 후 클라이언트로 전송 합니다.
		result="<main><book>"+
		         "<title><![CDATA[Java의 정석]]></title>" +
		         "<writer><![CDATA[저자 | 남궁성]]></writer>" +                             
		         "<image><![CDATA[http://localhost:8090/pro16/image/image1.jpg]]></image>"+
		      "</book>"+
		      "<book>"+
		         "<title><![CDATA[JSP 2.3 & Servlet 3.1]]></title>" +
		         "<writer><![CDATA[저자 | 오정원]]></writer>" +                 
		        "<image><![CDATA[http://localhost:8090/pro16/image/image2.jpg]]></image>"+
		      "</book></main>";
		System.out.println(result);
		writer.print(result);
	}

}
