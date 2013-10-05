//添加普通管理员账号
package com.student.manage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class AddManager extends JDialog implements ActionListener{
	
	JButton addButton = new JButton("\u6DFB\u52A0");
	JButton cancelButton = new JButton("\u53D6\u6D88");
	private JTextField pwdField;
	private JTextField mnameField;
	private MModel mm = new MModel();

	
	/**
	 * Create the dialog.
	 */
	public AddManager() {
		setTitle("\u65B0\u589E\u666E\u901A\u7BA1\u7406\u5458");
		setResizable(false);
		setBounds(430, 280, 226, 197);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
			
			
		
		
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				
				addButton.addActionListener(this);
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);
			
			
				
				buttonPane.add(cancelButton);
				
				JPanel pane = new JPanel();
				getContentPane().add(pane, BorderLayout.CENTER);
				pane.setLayout(null);
				
				JLabel mnameLabel = new JLabel("\u7BA1\u7406\u5458\uFF1A");
				mnameLabel.setBounds(10, 10, 54, 28);
				pane.add(mnameLabel);
				
				JLabel pwdLabel = new JLabel("\u5BC6\u7801\uFF1A");
				pwdLabel.setBounds(10, 66, 54, 15);
				pane.add(pwdLabel);
				
				pwdField = new JTextField();
				pwdField.setBounds(65, 63, 145, 21);
				pane.add(pwdField);
				pwdField.setColumns(10);
				
				mnameField = new JTextField();
				mnameField.setBounds(65, 14, 145, 21);
				pane.add(mnameField);
				mnameField.setColumns(10);
				
				JLabel infoLabel = new JLabel("\u6CE8\u610F\uFF1A\u7BA1\u7406\u5458ID\u4E3A\u7CFB\u7EDF\u9ED8\u8BA4\u5206\u914D\uFF01");
				infoLabel.setForeground(new Color(255, 51, 0));
				infoLabel.setBounds(10, 97, 200, 25);
				pane.add(infoLabel);
				cancelButton.addActionListener(this);
			
		
	}
	
	//添加和取消按钮的监听方法
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == addButton)
		{
			if(mnameField.getText().trim().equals("") || pwdField.getText().trim().equals("") )
			{
				JOptionPane.showMessageDialog(null, "管理员名和密码均不能为空!","错误",JOptionPane.WARNING_MESSAGE);
			}
			else 
			{
				String[] paras = {mnameField.getText().trim(),pwdField.getText().trim()};
				String sql = "insert into Admin (Manager,Password) values(?,?)";
				mm = new MModel();
				mm.updateInfo(sql, paras);
				MModel model = new MModel();
				SManager.table.setModel(model);
				JOptionPane.showMessageDialog(null, "添加成功！");
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
