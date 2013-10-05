//更新学生信息的对话框
package com.student.manage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.student.sql.DBUtil;

public class UpdateInfoDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField snoField;
	private JTextField snameField;
	private JTextField sexField;
	private JTextField sdepartmentField;
	private JTextField sgradeField;
	private JTextField smajorField;
	private JTextField sphoneField;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	JButton okButton = new JButton("确定");
	JButton cancelButton = new JButton("取消");
	
	/**
	 * Create the dialog.
	 *
	 */
	//传入所选择的表格的行数的构造方法
	public UpdateInfoDialog(int rowNum) {
		setTitle("\u4FEE\u6539\u5B66\u751F\u4FE1\u606F");
		setBounds(430, 280, 303, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
			JPanel pane1 = new JPanel();
			contentPanel.add(pane1, BorderLayout.WEST);
			pane1.setLayout(new GridLayout(7, 1, 0, 0));
		
			JLabel snoLabel = new JLabel("学号：");
			pane1.add(snoLabel);
			
			JLabel snameLabel = new JLabel("\u59D3\u540D\uFF1A");
			pane1.add(snameLabel);
			
			JLabel sexLabel = new JLabel("\u6027\u522B\uFF1A");
			pane1.add(sexLabel);
			
			JLabel sdepartmentLabel = new JLabel("\u7CFB\u522B\uFF1A");
			pane1.add(sdepartmentLabel);
			
			JLabel sgradeLabel = new JLabel("\u7EA7\u522B\uFF1A");
			pane1.add(sgradeLabel);
			
			JLabel smajorLabel = new JLabel("\u4E13\u4E1A\uFF1A");
			pane1.add(smajorLabel);
			
			JLabel sphoneLabel = new JLabel("\u624B\u673A\uFF1A");
			pane1.add(sphoneLabel);
			
			JPanel pane2 = new JPanel();
			contentPanel.add(pane2, BorderLayout.CENTER);
			pane2.setLayout(new GridLayout(7, 1, 0, 0));
			
			snoField = new JTextField();
			snoField.setEditable(false);
			pane2.add(snoField);
			//获取学生信息显示在输入框
			snoField.setText((String)MainFrame.table.getValueAt(rowNum, 0));
			snoField.setColumns(10);
			
			snameField = new JTextField();
			pane2.add(snameField);
			snameField.setText((String)MainFrame.table.getValueAt(rowNum, 1));
			snameField.setColumns(10);
			
			sexField = new JTextField();
			pane2.add(sexField);
			sexField.setText((String)MainFrame.table.getValueAt(rowNum, 2));
			sexField.setColumns(10);
			
			sdepartmentField = new JTextField();
			pane2.add(sdepartmentField);
			sdepartmentField.setText((String)MainFrame.table.getValueAt(rowNum, 3));
			sdepartmentField.setColumns(10);
			
			sgradeField = new JTextField();
			pane2.add(sgradeField);
			sgradeField.setText((String)MainFrame.table.getValueAt(rowNum, 4));
			sgradeField.setColumns(10);
			
			smajorField = new JTextField();
			pane2.add(smajorField);
			smajorField.setText((String)MainFrame.table.getValueAt(rowNum, 5));
			smajorField.setColumns(10);
			
			sphoneField = new JTextField();
			pane2.add(sphoneField);
			sphoneField.setText((String)MainFrame.table.getValueAt(rowNum, 6));
			sphoneField.setColumns(10);
			
			
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				//确定按钮
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				//取消按钮
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			
		
	}

	
	//传入学号的构造方法
	public UpdateInfoDialog(String sno) {
		setTitle("\u4FEE\u6539\u5B66\u751F\u4FE1\u606F");
		setBounds(430, 280, 303, 255);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
			JPanel pane1 = new JPanel();
			contentPanel.add(pane1, BorderLayout.WEST);
			pane1.setLayout(new GridLayout(7, 1, 0, 0));
		
			JLabel snoLabel = new JLabel("学号：");
			pane1.add(snoLabel);
			
			JLabel snameLabel = new JLabel("\u59D3\u540D\uFF1A");
			pane1.add(snameLabel);
			
			JLabel sexLabel = new JLabel("\u6027\u522B\uFF1A");
			pane1.add(sexLabel);
			
			JLabel sdepartmentLabel = new JLabel("\u7CFB\u522B\uFF1A");
			pane1.add(sdepartmentLabel);
			
			JLabel sgradeLabel = new JLabel("\u7EA7\u522B\uFF1A");
			pane1.add(sgradeLabel);
			
			JLabel smajorLabel = new JLabel("\u4E13\u4E1A\uFF1A");
			pane1.add(smajorLabel);
			
			JLabel sphoneLabel = new JLabel("\u624B\u673A\uFF1A");
			pane1.add(sphoneLabel);
			
			JPanel pane2 = new JPanel();
			contentPanel.add(pane2, BorderLayout.CENTER);
			pane2.setLayout(new GridLayout(7, 1, 0, 0));
			
			snoField = new JTextField();
			snoField.setEditable(false);
			pane2.add(snoField);
			snoField.setText(sno);
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
			
			try {
				
				conn = DBUtil.getConnection();
				pstmt = conn.prepareStatement("select * from Stu where Sno = '" + sno + "'");
				rs = pstmt.executeQuery();
			    
			    while(rs.next())
			    {
			    	//获取学生信息显示在输入框
			    	snameField.setText(rs.getString(2));
			    	sexField.setText(rs.getString(3));
			    	sdepartmentField.setText(rs.getString(4));
			    	sgradeField.setText(rs.getString(5));
			    	smajorField.setText(rs.getString(6));
			    	sphoneField.setText(rs.getString(7));
			    	
			    	
			    	
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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			
		
	}

	
	
	
	
	//确定和取消按钮的监听接口实现
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == okButton)
		{
			if(snoField.getText().trim().equals("") || snameField.getText().trim().equals("") || sexField.getText().trim().equals("") || sdepartmentField.getText().trim().equals("") || sgradeField.getText().trim().equals("") || smajorField.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(null,"学号，姓名，性别，系别，级别，专业均不能为空!");
				return;
			}
			else 
			{
				
				if(sexField.getText().trim().equals("男") || sexField.getText().trim().equals("女"))
				{
					String sql = "update Stu set Sname=? , Sex=? , SDepartment=? , SGrade=? , SMajor=? , SPhone=?  where SNo=?";
					String[] paras = {snameField.getText().trim(),sexField.getText().trim(),sdepartmentField.getText().trim(),sgradeField.getText().trim(),smajorField.getText().trim(),sphoneField.getText().trim(),snoField.getText().trim()};
					SModel sm = new SModel();
					sm.updateInfo(sql, paras);
					SModel stmt = new SModel();
					MainFrame.table.setModel(stmt);
					JOptionPane.showMessageDialog(null, "修改成功!");
					this.setVisible(false);
					this.dispose();
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"性别输入有误!");
					return;
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
