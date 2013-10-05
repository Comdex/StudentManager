//ѧ����Ϣ���ģ�Ͳ�����װ��
package com.student.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.student.sql.DBUtil;

public class SModel extends AbstractTableModel {

	//row�����ݣ�columnNames����
	Vector row,columnNames;
	
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs = null;
	
	
	//�༭ѧ������ӣ��޸ģ�ɾ����
	public boolean updateInfo(String sql,String []paras)
	{
		boolean checkSet = true;//�жϱ༭�Ƿ�ɹ�
		
		try {
			conn = DBUtil.getConnection();
			
			//����Statment����
			pstmt = conn.prepareStatement(sql);
			
			//Ϊ������ֵ
			for(int i=0;i<paras.length;i++)
			{
				pstmt.setString(i+1, paras[i]);
			}
			
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
	
	
	
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from Stu";
		}
		//��ӱ������
		columnNames = new Vector();
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("ϵ��");
		columnNames.add("����");
		columnNames.add("רҵ");
		columnNames.add("�ֻ�");
		
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
	public SModel(String sql)
	{
		this.init(sql);
	}
	
	//���캯�������ڳ�ʼ������ģ��
	public SModel()
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

