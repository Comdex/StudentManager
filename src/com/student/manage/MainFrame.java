//ѧ����Ϣ����������
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
	private SModel sm;//ѧ����Ϣ���ģ��
	public static int managerID = 0;//��ʶ�Ƿ�Ϊ��������Ա,idΪ1ʱΪ����

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
		
		//�޸İ�ť��������ӿ�ʵ��
		JButton setButton = new JButton("\u4FEE\u6539");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(snoField.getText().equals(""))
				{
					int row = MainFrame.table.getSelectedRow();
					
					if(row == -1)
					{
						
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��л�����Ҫ�޸ĵ�ѧ��ѧ�ţ�");
					
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
		
		//ɾ����ť��������ӿ�ʵ��
		JButton deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(snoField.getText().equals(""))
				{
					int row = MainFrame.table.getSelectedRow();
					
					if(row == -1)
					{
						
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������!");
						return;
					
					}
					else 
					{
						int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��ѡ���е���Ϣ��");
						if(option == JOptionPane.OK_OPTION)
						{
							String sno =(String)sm.getValueAt(row, 0);
							//����sql���ɾ��
							String sql="delete from Stu where SNo=?";
							String[] paras={sno};
							sm.updateInfo(sql, paras);
							SModel smt = new SModel();
							table.setModel(smt);
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						}
					}
				}
				else 
				{
					int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����ѧ��Ϊ" + snoField.getText() + "ѧ������Ϣ��");
					if(option == JOptionPane.OK_OPTION)
					{
						//����sql���ɾ��
						String sql="delete from Stu where SNo=?";
						String[] paras={snoField.getText()};
						sm.updateInfo(sql, paras);
						SModel smt = new SModel();
						table.setModel(smt);
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
					}
				}
				
			}
		});
		panel.add(deleteButton);
		
		//��ʾ����ѧ����Ϣ�İ�ť��������ӿ�ʵ��
		JButton allButton = new JButton("\u6240\u6709");
		allButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SModel stmt = new SModel();
				table.setModel(stmt);
			}
		});
		panel.add(allButton);
		
		//����Ա�˺����ð�ť
		JButton managerButton = new JButton("\u7BA1\u7406\u5458\u8BBE\u7F6E");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//idΪ1���볬������Ա���ý��棬����Ϊ��ͨ����Ա�޸Ľ���
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
