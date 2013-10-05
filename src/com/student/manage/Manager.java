//普通管理员设置界面类
package com.student.manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import com.student.sql.DBUtil;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Manager extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField mnameField;
	private JTextField pwdField;
	private int managerId;
	private String mnameString;
	private String pwdString;

	
	/**
	 * Create the frame.
	 */
	//传入管理员ID的构造方法
	public Manager(final int managerId) {
		this.managerId = managerId;
		setTitle("\u7BA1\u7406\u5458\u8BBE\u7F6E");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(430, 280, 243, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		try 
		{
			Connection conn = DBUtil.getConnection();
			
			if (!conn.isClosed()) 
			{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select Manager,Password from Admin where id = '" + managerId + " ' ");
				while (rs.next()) 
				{
					
					mnameString = rs.getString(1);
					pwdString = rs.getString(2);
				}
				
				try
				{
					if(conn != null)
						conn.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}
		} 
		catch (SQLException e1) 
		{
			// TODO 自动生成的 catch 块
			JOptionPane.showMessageDialog(null,e1.getMessage());
		}
		
		JPanel pane = new JPanel();
		contentPane.add(pane, BorderLayout.CENTER);
		pane.setLayout(new BorderLayout(0, 0));
		
		JPanel pane1 = new JPanel();
		pane.add(pane1, BorderLayout.CENTER);
		pane1.setLayout(null);
		
		JLabel idLabel = new JLabel("\u5F53\u524D\u7BA1\u7406ID\uFF1A");
		idLabel.setBounds(27, 10, 80, 23);
		pane1.add(idLabel);
		
		JLabel mnameLabel = new JLabel("\u7BA1\u7406\u5458\u540D\uFF1A");
		mnameLabel.setBounds(27, 46, 68, 23);
		pane1.add(mnameLabel);
		
		mnameField = new JTextField();
		mnameField.setText(mnameString);
		mnameField.setBounds(97, 47, 92, 21);
		pane1.add(mnameField);
		mnameField.setColumns(10);
		
		JLabel pwdLabel = new JLabel("\u7BA1\u7406\u5BC6\u7801\uFF1A");
		pwdLabel.setBounds(27, 96, 68, 15);
		pane1.add(pwdLabel);
		
		pwdField = new JTextField();
		pwdField.setText(pwdString);
		pwdField.setBounds(97, 93, 92, 21);
		pane1.add(pwdField);
		pwdField.setColumns(10);
		
		//修改按钮
		JButton setButton = new JButton("\u4FEE\u6539");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(mnameField.getText().trim().equals("") || pwdField.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, "修改的管理员名和密码均不能为空!");
				}
				else 
				{
					try 
					{
						Connection conn = DBUtil.getConnection();
						
						if (!conn.isClosed()) 
						{
							PreparedStatement pstmt = conn.prepareStatement("update Admin set Manager = '" + mnameField.getText().trim() + "' , Password = '" + pwdField.getText().trim() + "'" + "where id = '" + managerId + "'");
							
							if(pstmt.executeUpdate() != 1)
							{
								JOptionPane.showMessageDialog(null,"  修改失败!");
							}
							else 
							{
								JOptionPane.showMessageDialog(null,"  修改成功!");
							}
							try
							{
								if(conn != null)
									conn.close();
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, ex.getMessage());
								
								
								return;
							}
							
						}
					} 
					catch (SQLException e1) 
					{
						// TODO 自动生成的 catch 块
						//JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		setButton.setBounds(27, 140, 60, 23);
		pane1.add(setButton);
		
		
		//取消按钮
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setBounds(129, 140, 60, 23);
		pane1.add(cancelButton);
		cancelButton.addActionListener(this);
		
		JLabel midLabel = new JLabel("");
		midLabel.setText(managerId + "");
		midLabel.setBounds(117, 14, 25, 15);
		pane1.add(midLabel);
		
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO 自动生成的方法存根
		this.setVisible(false);
		this.dispose();
	}
}
