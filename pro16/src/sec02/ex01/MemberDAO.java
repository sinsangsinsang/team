package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//서블릿에서 전달된 ID로 SQL문의 조건식에 설정한 후 ID에 대한 회원 정보를 조회하여 그결과를 true 또는 false로 반환함.
	public boolean overlappedID(String id){
		boolean result = false;
		try {
			con = dataFactory.getConnection();
			//오라클의 decode() 함수를 이용하여 서블릿에서 전달된 ID에 해당하는 데이터를 검색하여 true 또는 false를 반환하는데..
			//검색한 갯수가 1(검색한 레코드가 존재하면)이면  true를 반환,
			//존재 하지 않으면 false를 문자열로 반환 하여 조회 합니다.
			String query = "select decode(count(*),1,'true','false') as result from t_member";
				   query += " where id=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			//문자열 "true" 또는 "false"를  Boolean자료형으로 변환하여 저장
			result = Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
