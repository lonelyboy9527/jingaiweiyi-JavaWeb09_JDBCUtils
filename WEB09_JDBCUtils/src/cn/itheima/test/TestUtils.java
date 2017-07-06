package cn.itheima.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.itheima.jdbc.JDBCUtils_V1;
import cn.itheima.jdbc.JDBCUtils_V2;
import cn.itheima.jdbc.JDBCUtils_V3;

/* 测试工具类*/
public class TestUtils {
	
	/* 根据id更新用户信息*/
	@Test 
	public void testUpdateById() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils_V3.getConnection();
			String sql = "UPDATE tbl_user SET uname=? WHERE uid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "嘻嘻嘻");
			pstmt.setInt(2, 8);
			int i = pstmt.executeUpdate();
			if (i>0) {
				System.out.println("更新成功!");
			} else {
				System.out.println("更新失败!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			JDBCUtils_V3.release(con, pstmt, null);
		}
		
	}
	
	/* 根据id删除用户信息*/
	@Test 
	public void testDeleteById() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils_V3.getConnection();
			String sql = "DELETE FROM tbl_user WHERE uid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 3);
			int i = pstmt.executeUpdate();
			if (i>0) {
				System.out.println("删除成功!");
			} else {
				System.out.println("删除失败!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			JDBCUtils_V3.release(con, pstmt, null);
		}
		
	}
	
	/* 添加用户信息方法*/
	@Test
	public void testAdd() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils_V2.getConnection();
			String sql = "INSERT INTO tbl_user VALUES (null, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "哈哈哈,新加入"); // 第一个参数
			pstmt.setString(2, "1234123");	   // 第二个参数
			int i = pstmt.executeUpdate();
			if (i>0) {
				System.out.println("添加成功!");
			} else {
				System.out.println("添加失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils_V2.release(con, pstmt, null);
		}
		
	}
	
	/* 根据id查询用户信息*/
	@Test
	public void testFindUserId(){
		Connection  con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 获取连接
			con = JDBCUtils_V1.getConnection();
			// 编写sql 
			String sql = "SELECT * FROM tbl_user WHERE uid=?";
			//获取执行sql对象
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(2)+ "------" + rs.getString("upassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils_V1.release(con, pstmt, rs);
		}
		
	} 
}
