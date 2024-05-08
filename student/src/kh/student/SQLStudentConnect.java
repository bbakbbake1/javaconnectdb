package kh.student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLStudentConnect {
	public static Connection makeConnection() throws FileNotFoundException, IOException {
		String filePath = "D:/doitjava/student/src/kh/student/db.properties";
		Properties properties = new Properties();
		properties.load(new FileReader(filePath));
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("데이터베이스 드라이브 로드 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("데이터베이스 접속 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패");
		}
		return con;
	}
}
