package sec03.brd01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//BoardService 클래스에서 BoardDAO의 selectAllAricles()메소드를 호출하면
	//계층형 SQL문을 이용해 계층형 구조로 전체 글을 조회한 후 반환합니다.
	public List selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();//컨넥션풀에서 커넥션 얻기
			//계층형 구조로 전체 글을 조회하는 오라클의 계층형 SQL문 
			String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" 
			             + " from t_board"
					     + " START WITH  parentNO=0"  
			             + " CONNECT BY PRIOR articleNO=parentNO"
					     + " ORDER SIBLINGS BY articleNO DESC";
		/*
		   위 SELECT구문 참고 설명

		   1. 먼저 START WITH parentNO=0 parenNO의 값이 0인 글이 최상위 계층이다라는 의미입니다.
			  parentNO가 0이면 그 글은 최상위 부모글이 되는 겁니다.

		   2. CONNECT BY PRIOR articleNO=parentNO 는 각 글이 어떤 부모글과 연결되는 지를 나타냅니다.

		   3. ORDER SIBLINGS BY articleNO DESC 는 계층 구조로 조회된 글을 articleBNO 순으로 내림차순으로 정렬합니다.

		   4. selct문의 LEVEL은 계층형 SQL문 실행 시, CONNECT BY PRIOR articleNO=parentNO로 글이 나열되면서 각 글의 깊이를 나타냅니다. 
			   오라클에서 알아서 부모글에 대해서 깊이를 계산해서 LEVEL로 반환합니다.

		   5. 따라서 계층형 SQL문을 실행하면 오라클이 전체 글에 대해서 내부적으로 모든 글의 articleNO에 대해, 
			  다른 글들의 parentNO와 비교해서 같으면 그 글들을 parentNO의 글 아래 정렬한후, articleNO의 내림차순으로 정렬하는 과정을 거칩니다.
		*/
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");//각 글의 깊이(계층)를 level변수에 저장합니다.
				int articleNO = rs.getInt("articleNO"); //글번호는 숫자형이므로 getInt()로 값을 가져옵니다.
				int parentNO = rs.getInt("parentNO");//부모글번호 
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				//검색한 글정보를 ArticleVO() 객체의 각 변수(속성)에 저장합니다.
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				//ArryList에 ArticleVO() 객체 추가
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//ArryList 반환
		return articlesList;
	}
}
