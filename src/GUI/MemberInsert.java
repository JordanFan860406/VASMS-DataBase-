package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DB.DB_connect;
import Object.Company;
import Object.Direct;
import Object.Movie;
import Object.member;
import Object.time;

public class MemberInsert extends JFrame implements ActionListener{
	JLabel lb = new JLabel("用戶名稱:");
	JLabel lb1 = new JLabel("性別:");
	JLabel lb2 = new JLabel("已付金額:");
	JLabel lb3 = new JLabel("生日:");

	JTextField tfName;

	private JTextField tfpay;
	private JTextField tfyear;
	private JTextField tfmonth;
	private JTextField tfday;
	private JTextField tfsex;


	JButton jbEdit;
	JButton jb;
	DB_connect DB;
	member member;
	public MemberInsert(DB_connect DB) throws SQLException{
		this.DB=DB;
		initialize();

		
	}
	void initialize(){
		this.setBounds(100,100,500,600);
		this.setLayout(null);
		this.setVisible(true);
		//用戶名稱
		lb.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb.setBounds(10, 10,150,50);
		this.add(lb);
		
		tfName=new JTextField();
		tfName.setBounds(130, 10, 250,50);
		tfName.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfName);
		
		//性別名稱
		lb1.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb1.setBounds(10,110,200,50);
		this.add(lb1);
		
		tfsex=new JTextField();
		tfsex.setBounds(130, 110, 100,50);
		tfsex.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfsex);

		//生日
		lb3.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb3.setBounds(10, 210,150,50);
		this.add(lb3);
		
		tfyear=new JTextField();
		tfyear.setBounds(130, 220,50,30);
		tfyear.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfyear);
		
		JLabel lb6 = new JLabel("年");
		lb6.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb6.setBounds(190, 210,100,50);
		this.add(lb6);
		
		tfmonth=new JTextField();
		tfmonth.setBounds(250, 220,50,30);
		tfmonth.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfmonth);
		
		JLabel lb7 = new JLabel("月");
		lb7.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb7.setBounds(300, 210,150,50);
		this.add(lb7);
		
		tfday=new JTextField();
		tfday.setBounds(350, 220,50,30);
		tfday.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfday);
		
		JLabel lb8 = new JLabel("日");
		lb8.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb8.setBounds(400, 210,150,50);
		this.add(lb8);
		

		jbEdit=new JButton("確定");
		jbEdit.setBounds(10,400,200, 70);
		jbEdit.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbEdit);
		jbEdit.addActionListener(this);
		
		jb=new JButton("取消");
		jb.setBounds(250, 400,200, 70);
		jb.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jb);
		jb.addActionListener(this);
		
		
	}
	public void getData(ArrayList<member> memberArray) throws SQLException{
		boolean a=false;
		for(member i:memberArray){
			if(i.getName().equals(tfName.getText())){
				a=true;
				break;
			}
		}
		if(tfName.getText().equals("")){
			a=true;
		}
		if(a){
			 JOptionPane.showMessageDialog(null,"請輸入正確名稱");
			
		}
		else{
			member member=new member();
			String ID="mem000"+String.valueOf(memberArray.size()+1);
			
			member.setID(ID);
			member.setName(tfName.getText());
			
			
			
			try{
				member.setBir(new time(Integer.parseInt(tfyear.getText()),Integer.parseInt(tfmonth.getText()),Integer.parseInt(tfday.getText())));
				member.setsex(tfsex.getText());
				
				
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null,"請輸入正確資料");
			}
			DB.getMemberDB().insertMember(member);;
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
		case"確定":
			try {
				getData(DB.getMemberDB().searchAllMember(""));
				this.dispose();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"取消":
			this.dispose();
			break;
		}
	}
}
