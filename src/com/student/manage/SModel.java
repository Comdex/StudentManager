//学生信息表格模型操作封装类
package com.student.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.student.sql.DBUtil;

public class SModel extends AbstractTableModel {

	//row行数据，columnNames列名
	Vector row,columnNames;
	
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	
	//编辑学生（添加，修改，删除）
	public boolean updateInfo(String sql,String []paras)
	{
		boolean checkSet = true;//判断编辑是否成功
		
		try {
			conn = DBUtil.getConnection();
			
			//创建Statment对象
			pstmt = conn.prepareStatement(sql);
			
			//为参数赋值
			for(int i=0;i<paras.length;i++)
			{
				pstmt.setString(i+1, paras[i]);
			}
			
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
	
	
	
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from Stu";
		}
		//添加表格列名
		columnNames = new Vector();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("系别");
		columnNames.add("级别");
		columnNames.add("专业");
		columnNames.add("手机");
		
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
		    	rowRecord.add(rs.getString(4));
		    	rowRecord.add(rs.getString(5));
		    	rowRecord.add(rs.getString(6));
		    	rowRecord.add(rs.getString(7));
		    	
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
	public SModel(String sql)
	{
		this.init(sql);
	}
	
	//构造函数，用于初始化数据模型
	public SModel()
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

