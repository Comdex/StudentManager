//����Ա��Ϣ���ģ�Ͳ�����װ��
package com.student.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.student.sql.DBUtil;

public class MModel extends AbstractTableModel
{

	//row�����ݣ�columnNames����
	Vector row,columnNames;
	
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	//ִ��sql���ķ�װ����
	public boolean playSql(String sql)
	{
		boolean checkSet = true;//�жϱ༭�Ƿ�ɹ�
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
		
			//ִ�в���
			if(pstmt.executeUpdate() != 1)
			{
				checkSet = false;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			checkSet = false;
			e.printStackTrace();
		}finally{
			
			//�ر�
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
	
	//�༭����Ա���������޸ģ�
	public boolean updateInfo(String sql,String []paras)
	{
		boolean checkSet = true;//�жϱ༭�Ƿ�ɹ�
		
		try {
			conn = DBUtil.getConnection();
			//3.����ps
			pstmt = conn.prepareStatement(sql);
			//��ps���ʺŸ�ֵ
			for(int i=0;i<paras.length;i++)
			{
				pstmt.setString(i+1, paras[i]);
			}
			//4.ִ�в���
			if(pstmt.executeUpdate() != 1)
			{
				checkSet = false;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			checkSet = false;
			e.printStackTrace();
		}finally{
			
			//�ر�
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
		//�м�
		columnNames = new Vector();
		columnNames.add("ID");
		columnNames.add("����Ա");
		columnNames.add("����");
		
		
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
		    	
		    	
		    	//������
		    	row.add(rowRecord);
		    }
		    
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//�ر�
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
	
	
	//���캯�������ڳ�ʼ������ģ��
	public MModel(String sql)
	{
		this.init(sql);
	}
	
	//���캯�������ڳ�ʼ������ģ��
	public MModel()
	{
		this.init("");
	}
	

	
	@Override
	//�õ�����
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return this.columnNames.size();
	}

	@Override
	//�õ�����
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return this.row.size();
	}

	@Override
	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int row, int column) {
		// TODO �Զ����ɵķ������
		return ((Vector) this.row.get(row)).get(column);
	}

	@Override
	public String getColumnName(int column) {
		// TODO �Զ����ɵķ������
		return (String)this.columnNames.get(column);
	}
}
