package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DB.DB_connect;
import Object.Company;

public class CompanyInsert extends JFrame implements ActionListener{
	
	JLabel lbCom = new JLabel("公司名稱:");
	JLabel lbAddress = new JLabel("公司地址:");
	JLabel lbFrame = new JLabel("公司資料新增");
	JTextField tfCom ;
	JTextField tfAddress;
	JButton btnInsert;
	JButton btnCancel;
	DB_connect DB;
	
	
	public CompanyInsert(DB_connect DB){
		this.DB = DB;
		initialize();
	}
	
	void initialize(){
		this.setBounds(100,100,500,500);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		
		lbFrame.setFont(new Font("新細明體",Font.PLAIN ,32));
		lbFrame.setBounds(135, 10,200,50);
		this.add(lbFrame);
		
		lbCom.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbCom.setBounds(10, 110,150,50);
		this.add(lbCom);
		
		tfCom = new JTextField();
		tfCom.setBounds(130, 110, 250,50);
		tfCom.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfCom);
		
		lbAddress.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbAddress.setBounds(10, 210,150,50);
		this.add(lbAddress);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(130, 210, 250,50);
		tfAddress.setFont(new Font("新細明體",Font.PLAIN ,28));
		this.add(tfAddress);
		
		btnInsert = new JButton("儲存後離開");
		btnInsert.setBounds(50, 310, 160, 100);
		btnInsert.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnInsert);
		btnInsert.addActionListener(this);
		
		btnCancel = new JButton("取消");
		btnCancel.setBounds(250, 310, 160, 100);
		btnCancel.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnCancel);
		btnCancel.addActionListener(this);
		
	}
	
	public void insert() throws Exception{
		String comName = tfCom.getText();
		String comAddress = tfAddress.getText();
		Company com = new Company(comName, comAddress);
		DB.getCompanyDB().insertCompany(com);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		
			case"儲存後離開":
			try {
				insert();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				this.dispose();
				break;
			case"取消":
				this.dispose();
				break;
		}
	}

}
