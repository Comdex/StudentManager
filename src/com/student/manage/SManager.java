//��������Ա���ý�����
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
		
		//�޸İ�ť
		JButton setButton = new JButton("\u4FEE\u6539");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idField.getText().equals(""))
				{
					int row = SManager.table.getSelectedRow();
					
					if(row == -1)
					{
						
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��л�����Ҫ�޸ĵĹ���ID��");
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
		
		//������ť
		JButton addButton = new JButton("\u65B0\u589E");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddManager().setVisible(true);
			}
		});
		pane1.add(addButton);
		
		//ɾ����ť
		JButton deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idField.getText().equals(""))
				{
					int row = SManager.table.getSelectedRow();
					
					if(row == -1)
					{
						
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����л�����Ҫɾ���Ĺ���ԱID!");
						return;
					
					}
					else 
					{
						int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��ѡ���е���Ϣ��");
						if(option == JOptionPane.OK_OPTION)
						{
							String id =(String)mm.getValueAt(row, 0);
							//����sql���ɾ��
							String sql="delete from Admin where id=" + id ;
							mm.playSql(sql);
							MModel model = new MModel();
							table.setModel(model);
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						}
					}
				}
				else 
				{
					int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��IDΪ" + idField.getText() + "�Ĺ���Ա���˺���");
					if(option == JOptionPane.OK_OPTION)
					{
						//����sql���ɾ��
						String sql="delete from Admin where id=" + idField.getText();
						//���÷���ִ��
						mm.playSql(sql);
						MModel model = new MModel();
						table.setModel(model);
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
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
