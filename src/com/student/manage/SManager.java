//超级管理员设置界面类
package com.student.manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SManager extends JFrame 
{

	private JPanel contentPane;
	private JTextField idField;
	public static JTable table;
	private  MModel mm;

	
	/**
	 * Create the frame.
	 */
	public SManager() {
		setTitle("\u8D85\u7EA7\u7BA1\u7406\u5458\u8BBE\u7F6E");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 616, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel pane1 = new JPanel();
		contentPane.add(pane1, BorderLayout.NORTH);
		pane1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel idLabel = new JLabel("\u5F53\u524D\u7BA1\u7406\u5458ID\uFF1A1\uFF08\u8D85\u7EA7\u7BA1\u7406\u5458\uFF09");
		pane1.add(idLabel);
		
		JLabel midLabel = new JLabel("\u7BA1\u7406\u5458ID\uFF1A");
		pane1.add(midLabel);
		
		idField = new JTextField();
		pane1.add(idField);
		idField.setColumns(10);
		
		//修改按钮
		JButton setButton = new JButton("\u4FEE\u6539");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idField.getText().equals(""))
				{
					int row = SManager.table.getSelectedRow();
					
					if(row == -1)
					{
						
						JOptionPane.showMessageDialog(null, "请选择要修改的行或输入要修改的管理ID！");
						return;
					}
					else 
					{
						new UpdateManager(row).setVisible(true);
					}
				}
				else 
				{
					UpdateManager.id = Integer.parseInt(idField.getText());
					new UpdateManager().setVisible(true);
				}
				
			}
		});
		pane1.add(setButton);
		
		//新增按钮
		JButton addButton = new JButton("\u65B0\u589E");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddManager().setVisible(true);
			}
		});
		pane1.add(addButton);
		
		//删除按钮
		JButton deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idField.getText().equals(""))
				{
					int row = SManager.table.getSelectedRow();
					
					if(row == -1)
					{
						
						JOptionPane.showMessageDialog(null, "请选择要删除的行或输入要删除的管理员ID!");
						return;
					
					}
					else 
					{
						int option = JOptionPane.showConfirmDialog(null, "确定删除选中行的信息吗？");
						if(option == JOptionPane.OK_OPTION)
						{
							String id =(String)mm.getValueAt(row, 0);
							//创建sql语句删除
							String sql="delete from Admin where id=" + id ;
							mm.playSql(sql);
							MModel model = new MModel();
							table.setModel(model);
							JOptionPane.showMessageDialog(null, "删除成功！");
						}
					}
				}
				else 
				{
					int option = JOptionPane.showConfirmDialog(null, "确定删除ID为" + idField.getText() + "的管理员的账号吗？");
					if(option == JOptionPane.OK_OPTION)
					{
						//创建sql语句删除
						String sql="delete from Admin where id=" + idField.getText();
						//调用方法执行
						mm.playSql(sql);
						MModel model = new MModel();
						table.setModel(model);
						JOptionPane.showMessageDialog(null, "删除成功！");
					}
				}
			}
		});
		pane1.add(deleteButton);
		
		mm = new MModel();
		table = new JTable(mm);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
		
	}

	
}
