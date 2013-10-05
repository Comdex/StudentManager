//数据库信息连接封装类
package com.student.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil 
{
	public static Connection getConnection()throws SQLException
	{
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Student"; // 连接服务器和数据库test
		String userName = "sa"; // 默认用户名
		String userPwd = "123456"; // 安装sql server 2005时的密码
		Connection dbConn;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn ;
		}
		catch(Exception e)
		{
			throw new SQLException("连接出错");
		}

	}
	
}
