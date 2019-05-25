package sec03.brd01;

import java.util.List;

//BoadrdDAO 객체를 생성한 후  selectAllArticles()메소드를 호출해 전체 글을 검색해 가져옵니다.
public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO(); //생성자 호출시 BoardDAO 객체를 생성합니다.
	}

	public List<ArticleVO> listArticles() {
		//BoardDAO의 selectAllArticles()메소드를 후출해 전체 글을 검색해 가져옴
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
}
/*
 여기서 잠깐! 쉬어가기!
 BoardDAO 클래스의 메서드 이름은 보통 각 메서드들이 실행하는 SQL문에 의해 결정됩니다.
  예를 들어 selectAllArticles() 메서드는 전체 글 정보를 조회하는 SQL문을 실행하므로 메서드 이름에  selectAll이 들어 갑니다.
 */
