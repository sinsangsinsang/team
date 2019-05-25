package sec03.brd02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	
	//BoardController에서 호출한 메소드로써...
	//글쓰기 창에서 입력된  정보를 AricleVO객체에 설정한 후  매개변수로 전달받아..
	//다시~ BoardDAO객체의 insertNewArticle()메소드를 호출하면서 추가할 새글정보(AricleVO객체)를 인자로 전달 하여 
	//DB에 INSERT작업을 하게 됨
	public void addArticle(ArticleVO article){ //DB에 글추가를 명령 하는 메소드 
		
		boardDAO.insertNewArticle(article);		
	}

}
