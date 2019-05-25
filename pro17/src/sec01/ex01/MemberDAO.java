package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List listMembers() {// SQL문을 이용해 회원정보를 조회한 후 결과를 ArryList로 반환합니다.
		List membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			//SQL문을 작성 합니다.
			String query = "select * from  t_member order by joinDate desc";
			System.out.println(query);
			//PreparedStatement 객체를 생성하면서 SQL문을 인자로 전달합니다.
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				//조회한 회원 정보를 레코드별로 MemberVO 객체의 속성에 저장 합니다.
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
				//memberList에 MemberVO객체들을 차례대로 저장합니다.
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}
	
	public void addMember(MemberVO m) {//새로운 회원정보를  DB에 추가 
		try {
			conn = dataFactory.getConnection();
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "INSERT INTO t_member(id, pwd, name, email)" 
						 + " VALUES(?, ? ,? ,?)";
			System.out.println(query);
			//PreparedStatement 객체를 생성하면서 SQL문을 인자로 전달 합니다.
			pstmt = conn.prepareStatement(query);
			//?값 셋팅
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			//SQL문을 실행합니다.
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
}



