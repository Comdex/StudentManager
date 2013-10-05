//管理员信息表格模型操作封装类
package com.student.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.student.sql.DBUtil;

public class MModel extends AbstractTableModel
{

	//row行数据，columnNames列名
	Vector row,columnNames;
	
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	//执行sql语句的封装方法
	public boolean playSql(String sql)
	{
		boolean checkSet = true;//判断编辑是否成功
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
		
			//执行操作
			if(pstmt.executeUpdate() != 1)
			{
				checkSet = false;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			checkSet = false;
			e.printStackTrace();
		}finally{
			
			//关闭
			try {
				if(rs != null) 
					rs.close();
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return checkSet;
	}
	
	//编辑管理员（新增，修改）
	public boolean updateInfo(String sql,String []paras)
	{
		boolean checkSet = true;//判断编辑是否成功
		
		try {
			conn = DBUtil.getConnection();
			//3.创建ps
			pstmt = conn.prepareStatement(sql);
			//给ps的问号赋值
			for(int i=0;i<paras.length;i++)
			{
				pstmt.setString(i+1, paras[i]);
			}
			//4.执行操作
			if(pstmt.executeUpdate() != 1)
			{
				checkSet = false;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			checkSet = false;
			e.printStackTrace();
		}finally{
			
			//关闭
			try {
				if(rs != null) 
					rs.close();
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return checkSet;
	}
	
	
	
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from Admin";
		}
		//中间
		columnNames = new Vector();
		columnNames.add("ID");
		columnNames.add("管理员");
		columnNames.add("密码");
		
		
		row = new Vector();
		
		try {
			
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
		    rs = pstmt.executeQuery();
		    
		    while(rs.next())
		    {
		    	
		    	Vector rowRecord = new Vector();
		    	rowRecord.add(rs.getString(1));
		    	rowRecord.add(rs.getString(2));
		    	rowRecord.add(rs.getString(3));
		    	
		    	
		    	//加入行
		    	row.add(rowRecord);
		    }
		    
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//关闭
			try {
				if(rs != null) 
					rs.close();
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	
	//构造函数，用于初始化数据模型
	public MModel(String sql)
	{
		this.init(sql);
	}
	
	//构造函数，用于初始化数据模型
	public MModel()
	{
		this.init("");
	}
	

	
	@Override
	//得到列数
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.columnNames.size();
	}

	@Override
	//得到行数
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.row.size();
	}

	@Override
	//得到某列某行的数据
	public Object getValueAt(int row, int column) {
		// TODO 自动生成的方法存根
		return ((Vector) this.row.get(row)).get(column);
	}

	@Override
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String)this.columnNames.get(column);
	}
}
