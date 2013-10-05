//更新管理员账号的对话框
package com.student.manage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.student.sql.DBUtil;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateManager extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	JButton okButton = new JButton("确定");
	JButton cancelButton = new JButton("取消");
	private JTextField mnameField;
	private JTextField pwdField;
	private int row;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public static int id = 1;
	private JTextField midField;
	
	
	/**
	 * Create the dialog.
	 */
	//传入所选择的表格的行数的构造方法
	public UpdateManager(int rowNum) {
		row = rowNum;
		setTitle("\u4FEE\u6539\u7BA1\u7406\u5458\u4FE1\u606F");
		setBounds(430, 280, 227, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel mnameLabel = new JLabel("\u7BA1\u7406\u5458\uFF1A");
		mnameLabel.setBounds(10, 20, 56, 24);
		contentPanel.add(mnameLabel);
		
		mnameField = new JTextField();
		mnameField.setText((String)SManager.table.getValueAt(rowNum, 1));
		mnameField.setBounds(72, 22, 120, 21);
		contentPanel.add(mnameField);
		mnameField.setColumns(10);
		
		JLabel pwdLabel = new JLabel("\u5BC6\u7801\uFF1A");
		pwdLabel.setBounds(21, 68, 45, 24);
		contentPanel.add(pwdLabel);
		
		pwdField = new JTextField();
		pwdField.setText((String)SManager.table.getValueAt(rowNum, 2));
		pwdField.setBounds(72, 70, 120, 21);
		contentPanel.add(pwdField);
		pwdField.setColumns(10);
		
		JLabel midLabel = new JLabel("\u7BA1\u7406ID\uFF1A");
		midLabel.setBounds(10, 6, 54, 21);
		contentPanel.add(midLabel);
		
		midField = new JTextField();
		midField.setText((String)SManager.table.getValueAt(rowNum, 0));
		midField.setBounds(72, 6, 120, 21);
		contentPanel.add(midField);
		midField.setColumns(10);
		
		JLabel infoLabel = new JLabel("\u6CE8\u610F\uFF1A\u7BA1\u7406\u5458ID\u65E0\u6CD5\u4FEE\u6539\uFF01");
		infoLabel.setForeground(new Color(255, 51, 0));
		infoLabel.setBounds(31, 108, 178, 15);
		contentPanel.add(infoLabel);
			
			
		
		
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				
				okButton.addActionListener(this);
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			
		
	}

	
	public UpdateManager() {
		
		setTitle("\u4FEE\u6539\u7BA1\u7406\u5458\u4FE1\u606F");
		setBounds(430, 280, 227, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel mnameLabel = new JLabel("\u7BA1\u7406\u5458\uFF1A");
		mnameLabel.setBounds(10, 37, 56, 21);
		contentPanel.add(mnameLabel);
		
		mnameField = new JTextField();
		
		mnameField.setBounds(72, 37, 120, 21);
		contentPanel.add(mnameField);
		mnameField.setColumns(10);
		
		JLabel pwdLabel = new JLabel("\u5BC6\u7801\uFF1A");
		pwdLabel.setBounds(21, 68, 45, 24);
		contentPanel.add(pwdLabel);
		
		pwdField = new JTextField();
		
		pwdField.setBounds(72, 70, 120, 21);
		contentPanel.add(pwdField);
		pwdField.setColumns(10);
		
		JLabel infoLabel = new JLabel("\u6CE8\u610F\uFF1A\u7BA1\u7406\u5458ID\u65E0\u6CD5\u4FEE\u6539\uFF01");
		infoLabel.setForeground(new Color(255, 51, 0));
		infoLabel.setBounds(31, 108, 178, 15);
		contentPanel.add(infoLabel);
		
		JLabel midLabel = new JLabel("\u7BA1\u7406ID\uFF1A");
		midLabel.setBounds(10, 6, 54, 21);
		contentPanel.add(midLabel);
		
		midField = new JTextField();
		midField.setEditable(false);
		midField.setText(id+"");
		midField.setBounds(72, 6, 120, 21);
		contentPanel.add(midField);
		midField.setColumns(10);
		
		try {
			
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from Admin where id = " + id);
			rs = pstmt.executeQuery();
		    
		    while(rs.next())
		    {
		    	//显示管理员信息在输入框
		    	mnameField.setText(rs.getString(2));
		    	pwdField.setText(rs.getString(3));
		    	
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
			
		
		
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				
				okButton.addActionListener(this);
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			
		
	}
	
	
	
	//执行修改的监听接口实现
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == okButton)
		{
			if(mnameField.getText().trim().equals("") || pwdField.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(null,"管理员名和密码均不能为空!");
				return;
			}
			else 
			{
				
					String sql = "update Admin set Manager=? , Password=?  where id=" + row ;
					String[] paras = {mnameField.getText().trim(),pwdField.getText().trim()};
					MModel mm = new MModel();
					mm.updateInfo(sql, paras);
					MModel model = new MModel();
					SManager.table.setModel(model);
					JOptionPane.showMessageDialog(null, "修改成功!");
					this.setVisible(false);
					this.dispose();
				
			}
			
		}
		else 
		{
			this.setVisible(false);
			this.dispose();
		}
	}
}