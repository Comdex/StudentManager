//添加学生信息
package com.student.manage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;

public class AddInfoDialog extends JDialog implements ActionListener{
	

	private final JPanel contentPanel = new JPanel();
	private JTextField snoField;
	private JTextField snameField;
	private JTextField sexField;
	private JTextField sdepartmentField;
	private JTextField sgradeField;
	private JTextField smajorField;
	private JTextField sphoneField;
	JButton addButton = new JButton("\u6DFB\u52A0");
	JButton cancelButton = new JButton("\u53D6\u6D88");
	private SModel sm;


	/**
	 * Create the dialog.
	 */
	public AddInfoDialog() {
		setTitle("\u6DFB\u52A0\u5B66\u751F\u4FE1\u606F");
		setResizable(false);
		setBounds(430, 280, 320, 270);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new BorderLayout(0, 0));
		
			JPanel pane1 = new JPanel();
			contentPanel.add(pane1,BorderLayout.WEST);
			pane1.setLayout(new GridLayout(7, 1));
		
		
			JLabel snoLabel = new JLabel("\u5B66\u53F7\uFF1A");
			pane1.add(snoLabel);
			
			JLabel snameLabel = new JLabel("\u59D3\u540D\uFF1A");
			pane1.add(snameLabel);
			
			JLabel sexLabel = new JLabel("\u6027\u522B\uFF1A");
			pane1.add(sexLabel);
			
			JLabel sdeparementLabel = new JLabel("\u7CFB\u522B\uFF1A");
			pane1.add(sdeparementLabel);
			
			JLabel sgradeLabel = new JLabel("\u7EA7\u522B\uFF1A");
			pane1.add(sgradeLabel);
			
			JLabel smajorLabel = new JLabel("\u4E13\u4E1A\uFF1A");
			pane1.add(smajorLabel);
			
			JLabel sphoneLabel = new JLabel("\u624B\u673A\uFF1A");
			pane1.add(sphoneLabel);
		
		
			JPanel pane2 = new JPanel();
			contentPanel.add(pane2,BorderLayout.CENTER);
			pane2.setLayout(new GridLayout(7, 1));
			
			snoField = new JTextField();
			pane2.add(snoField);
			snoField.setColumns(10);
			
			snameField = new JTextField();
			pane2.add(snameField);
			snameField.setColumns(10);
			
			sexField = new JTextField();
			pane2.add(sexField);
			sexField.setColumns(10);
			
			sdepartmentField = new JTextField();
			pane2.add(sdepartmentField);
			sdepartmentField.setColumns(10);
			
			sgradeField = new JTextField();
			pane2.add(sgradeField);
			sgradeField.setColumns(10);
			
			smajorField = new JTextField();
			pane2.add(smajorField);
			smajorField.setColumns(10);
			
			sphoneField = new JTextField();
			pane2.add(sphoneField);
			sphoneField.setColumns(10);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				
				addButton.addActionListener(this);
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);
			
			
				
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			
		
	}
	
	//添加和取消按钮的监听方法
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == addButton)
		{
			if(snoField.getText().trim().equals("") || snameField.getText().trim().equals("") || sexField.getText().trim().equals("") || sdepartmentField.getText().trim().equals("") ||
					sphoneField.getText().trim().equals("") || sgradeField.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(null, "学号，姓名，性别，系别，级别，专业均不能为空!","错误",JOptionPane.WARNING_MESSAGE);
			}
			else 
			{
				String[] paras = {snoField.getText().trim(),snameField.getText().trim(), sexField.getText().trim(),sdepartmentField.getText().trim() , sgradeField.getText().trim(),smajorField.getText().trim() , sphoneField.getText().trim()};
				String sql = "insert into Stu values(?,?,?,?,?,?,?)";
				sm = new SModel();
				sm.updateInfo(sql, paras);
				MainFrame.table.setModel(sm);
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
