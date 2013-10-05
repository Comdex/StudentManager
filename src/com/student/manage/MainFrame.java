//学生信息管理主界面
package com.student.manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField snoField;
	public static JTable table;
	private SModel sm;//学生信息表格模型
	public static int managerID = 0;//标识是否为超级管理员,id为1时为超管

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		this.setUndecorated(true);
		this.setOpacity((float)0.7);
		setTitle("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 720, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel snoLabel = new JLabel("\u5B66\u53F7(\u59D3\u540D)\uFF1A");
		panel.add(snoLabel);
		
		snoField = new JTextField();
		panel.add(snoField);
		snoField.setColumns(15);
		
		JButton checkButton = new JButton("\u67E5\u8BE2");
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idName = snoField.getText().trim();
				
				String sql = "select * from Stu where SNo = '" + idName +"' or SName = '" + idName + "'";
				
				sm = new SModel(sql);
				
				table.setModel(sm);
			}
		});
		panel.add(checkButton);
		
		JButton addButton = new JButton("\u6DFB\u52A0");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddInfoDialog().setVisible(true);
			}	
		});
		panel.add(addButton);
		
		//修改按钮及其监听接口实现
		JButton setButton = new JButton("\u4FEE\u6539");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(snoField.getText().equals(""))
				{
					int row = MainFrame.table.getSelectedRow();
					
					if(row == -1)
					{
						
						JOptionPane.showMessageDialog(null, "请选择要修改的行或输入要修改的学生学号！");
					
					}
					else 
					{
						new UpdateInfoDialog(row).setVisible(true);
					}
				}
				else 
				{
					new UpdateInfoDialog(snoField.getText()).setVisible(true);
				}
				
				
			}
		});
		panel.add(setButton);
		
		//删除按钮及其监听接口实现
		JButton deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(snoField.getText().equals(""))
				{
					int row = MainFrame.table.getSelectedRow();
					
					if(row == -1)
					{
						
						JOptionPane.showMessageDialog(null, "请选择要删除的行!");
						return;
					
					}
					else 
					{
						int option = JOptionPane.showConfirmDialog(null, "确定删除选中行的信息吗？");
						if(option == JOptionPane.OK_OPTION)
						{
							String sno =(String)sm.getValueAt(row, 0);
							//创建sql语句删除
							String sql="delete from Stu where SNo=?";
							String[] paras={sno};
							sm.updateInfo(sql, paras);
							SModel smt = new SModel();
							table.setModel(smt);
							JOptionPane.showMessageDialog(null, "删除成功！");
						}
					}
				}
				else 
				{
					int option = JOptionPane.showConfirmDialog(null, "确定删除该学号为" + snoField.getText() + "学生的信息吗？");
					if(option == JOptionPane.OK_OPTION)
					{
						//创建sql语句删除
						String sql="delete from Stu where SNo=?";
						String[] paras={snoField.getText()};
						sm.updateInfo(sql, paras);
						SModel smt = new SModel();
						table.setModel(smt);
						JOptionPane.showMessageDialog(null, "删除成功！");
					}
				}
				
			}
		});
		panel.add(deleteButton);
		
		//显示所有学生信息的按钮及其监听接口实现
		JButton allButton = new JButton("\u6240\u6709");
		allButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SModel stmt = new SModel();
				table.setModel(stmt);
			}
		});
		panel.add(allButton);
		
		//管理员账号设置按钮
		JButton managerButton = new JButton("\u7BA1\u7406\u5458\u8BBE\u7F6E");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//id为1进入超级管理员设置界面，否则为普通管理员修改界面
				if(managerID == 1)
				{
					new SManager();
				}
				else 
				{
					new Manager(managerID);
				}
				
			}
		});
		panel.add(managerButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		sm = new SModel();
		table = new JTable(sm);
		scrollPane.setViewportView(table);
		this.setVisible(true);
	}
}
