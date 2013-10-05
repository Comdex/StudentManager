package com.student;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.student.manage.MainFrame;
import com.student.sql.DBUtil;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.UIManager;

/**
 * Copyright 2008 - 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project StudentManager
 * @author Comdex
 * @email wcomdex@foxmail.com
 * @version 1.0
 */

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField managerField;
	private JTextField pwdField;
	private String loginManager;
	private String loginPassword;
	private String loginID;//登录成功的管理ID
	
	private ImageIcon background;
	JButton loginButton;
	JButton quitButton;
	
	//标识管理员名和密码是否正确
	private boolean isManager = false;
	private boolean isPassword = false;
	private JTextField mnameField;
	private JTextField textField;
	

	/**
	 * Create the frame.
	 */
	public Login() {
		setBackground(new Color(230, 230, 250));
		
		this.setUndecorated(true);
		this.setOpacity((float)0.7);
		//this.setBackground(Color.WHITE);
		//com.sun.awt.AWTUtilities.setWindowOpacity(this, (float)0.4);

		background = new ImageIcon("login.png");
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());

		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到LayeredPane面板里。
		
		label.setBounds(0,0,background.getIconWidth(), background.getIconHeight());//设置背景标签的位置 
		
		Container cp=this.getContentPane();
		getContentPane().setLayout(null);
		
		JLabel mnameLabel = new JLabel("\u7BA1\u7406\u5458\uFF1A");
		mnameLabel.setBounds(41, 77, 54, 15);
		getContentPane().add(mnameLabel);
		
		mnameField = new JTextField();
		mnameField.setBounds(101, 74, 109, 21);
		getContentPane().add(mnameField);
		mnameField.setColumns(10);
		
		JLabel pwdLabel = new JLabel("\u5BC6\u7801\uFF1A");
		pwdLabel.setBounds(52, 120, 43, 15);
		getContentPane().add(pwdLabel);
		
		pwdField = new JTextField();
		pwdField.setBounds(101, 117, 109, 21);
		getContentPane().add(pwdField);
		
		loginButton = new JButton("\u767B\u5F55");
		loginButton.addActionListener(this);
		loginButton.setBounds(52, 174, 72, 23);
		getContentPane().add(loginButton);
		
		quitButton = new JButton("\u9000\u51FA");
		quitButton.addActionListener(this);
		quitButton.setBounds(153, 174, 72, 23);
		getContentPane().add(quitButton);
		pwdField.setColumns(10);
		((JPanel)cp).setOpaque(false); 
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		
		this.setSize(284,246); 
		this.setLocation( (int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);

		
		 
		    

		
		setTitle("\u5B66\u751F\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == loginButton)
		{
			if(mnameField.getText().equals("") || pwdField.getText().equals(""))
			{
				
				JOptionPane.showMessageDialog(null, "管理员名和密码均不能为空！");
				return;
			}
			else
			{
				try 
				{
					Connection conn = DBUtil.getConnection();
					
					if (!conn.isClosed()) 
					{
						PreparedStatement stmt = conn.prepareStatement("select id,Manager,Password from Admin where Manager = '" + mnameField.getText().trim() + "'");
						//执行操作
				
						ResultSet rs = stmt.executeQuery();
						while (rs.next()) 
						{
							
							loginID = rs.getString(1);
							loginManager = rs.getString(2);
							loginPassword = rs.getString(3);
	
							//判断登录信息符合
							if(mnameField.getText().equals(loginManager) )
							{
								isManager = true;
								if( pwdField.getText().equals(loginPassword) )
								{
									isPassword = true;
									MainFrame.managerID = Integer.parseInt(loginID);
									new MainFrame();
									this.setVisible(false);
									this.dispose();
									return;
								}
								
							
							}
					}
				}
				else 
				{
					return;
				}
				
						
						
						if(isManager == false)
						{
							JOptionPane.showMessageDialog(null, "管理员名有误!", "登录提示", JOptionPane.ERROR_MESSAGE);
						}
						else if(isPassword == false)
						{
							JOptionPane.showMessageDialog(null, "密码有误!", "登录提示", JOptionPane.ERROR_MESSAGE);
						}
						
						
						try
						{
							if(conn != null)
								conn.close();
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
						
						
					
				} 
				catch (Exception e2) 
				{
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, e2.getMessage());
					//e2.printStackTrace();
				}
			}
			
			
		}
		else 
		{
			this.setVisible(false);
			this.dispose();
		}
		
	}
}

