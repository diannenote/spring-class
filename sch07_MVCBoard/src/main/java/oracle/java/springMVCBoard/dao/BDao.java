package oracle.java.springMVCBoard.dao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BDao {
	DataSource dataSource;
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("생성자 dataSource-->" + e.getMessage());
			e.printStackTrace();
		}
	}
}
