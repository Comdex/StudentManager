//���ݿ���Ϣ���ӷ�װ��
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
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ����JDBC����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Student"; // ���ӷ����������ݿ�test
		String userName = "sa"; // Ĭ���û���
		String userPwd = "123456"; // ��װsql server 2005ʱ������
		Connection dbConn;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn ;
		}
		catch(Exception e)
		{
			throw new SQLException("���ӳ���");
		}

	}
	
}
