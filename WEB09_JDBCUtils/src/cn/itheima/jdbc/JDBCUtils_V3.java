package cn.itheima.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.BoundedRangeModel;

/* 提供获取连接和释放资源的方法*/
public class JDBCUtils_V3 {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	/* 静态代码块加载配置文件信息*/
	static {
		try {
			//1. 通过当前类，获取类加载器
			ClassLoader classLoader = JDBCUtils_V3.class.getClassLoader();
			//2.通过类加载器的方法获得一个输入流
			InputStream is = classLoader.getResourceAsStream("db.properties");
			//3.创建一个 properties对象
			Properties props = new Properties();
			//4.加载输入流
			props.load(is);
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* 获取连接方法*/
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
