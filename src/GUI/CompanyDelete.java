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

public class CompanyDelete extends JFrame implements ActionListener{
	JLabel lbFrame = new JLabel("刪除公司資料");
	JLabel lbCom = new JLabel("公司名稱:");
	JComboBox jbCom ;
	String[]com = {"請選擇公司"};
	JButton btnDelete;
	JButton btnCancel;
	DB_connect DB;
	
	public CompanyDelete(DB_connect DB) throws Exception{
		this.DB = DB;
		initialize();
		setData();
	}
	
	void initialize(){
		this.setBounds(100,100,500,500);
		this.setLayout(null);
		this.setVisible(true);
		

		lbFrame.setFont(new Font("新細明體",Font.PLAIN ,32));
		lbFrame.setBounds(135, 10,200,50);
		this.add(lbFrame);
		
		lbCom.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbCom.setBounds(10, 110,150,50);
		this.add(lbCom);
		
		jbCom = new JComboBox(com);
		jbCom.setBounds(130, 110, 330,50);
		jbCom.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbCom);
		
		btnDelete = new JButton("刪除後離開");
		btnDelete.setBounds(50, 310, 160, 100);
		btnDelete.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnCancel = new JButton("取消");
		btnCancel.setBounds(250, 310, 160, 100);
		btnCancel.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnCancel);
		btnCancel.addActionListener(this);
		
	}
	
	public void setData() throws Exception{
		for(Company i : DB.getCompanyDB().searchAllCom()){
			jbCom.addItem(i.getName());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			
			case"刪除後離開":
				this.dispose();
				break;
			case"取消":
				this.dispose();
				break;
		}
	}

}
