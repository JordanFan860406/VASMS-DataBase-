package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DB.DB_connect;
import Object.Company;
import Object.Direct;
import Object.Movie;
import Object.member;
import Object.time;

public class MemberModify extends JFrame implements ActionListener{
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
	private JTextField tfActor;
	private JTextField tfActress;
	JButton jbEnter;
	JButton jbEdit;
	JButton jbClose;
	DB_connect DB;
	member member;
	public MemberModify(DB_connect DB,member member) throws SQLException{
		this.DB=DB;
		this.member=member;
		initialize();
		setEditMode(false);
		setData();
		
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
		tfsex.setBounds(130, 110, 60,50);
		tfsex.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfsex);
		//最愛男演員
		JLabel lb10 = new JLabel("最愛男演員:");
		lb10.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb10.setBounds(210,110,200,50);
		this.add(lb10);
		tfActor=new JTextField();
		tfActor.setBounds(345, 110, 130,50);
		tfActor.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfActor);
		
		//已付金額
		lb2.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb2.setBounds(10,210,150,50);
		this.add(lb2);
		
		tfpay=new JTextField();
		tfpay.setBounds(130, 210,60,50);
		tfpay.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfpay);
		//最愛女演員
		JLabel lb11 = new JLabel("最愛女演員:");
		lb11.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb11.setBounds(210,210,200,50);
		this.add(lb11);
		
		tfActress=new JTextField();
		tfActress.setBounds(345, 210, 130,50);
		tfActress.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfActress);
		//生日
		lb3.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb3.setBounds(10, 310,150,50);
		this.add(lb3);
		
		tfyear=new JTextField();
		tfyear.setBounds(130, 320,50,30);
		tfyear.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfyear);
		
		JLabel lb6 = new JLabel("年");
		lb6.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb6.setBounds(190, 310,100,50);
		this.add(lb6);
		
		tfmonth=new JTextField();
		tfmonth.setBounds(250, 320,50,30);
		tfmonth.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfmonth);
		
		JLabel lb7 = new JLabel("月");
		lb7.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb7.setBounds(300, 310,150,50);
		this.add(lb7);
		
		tfday=new JTextField();
		tfday.setBounds(350, 320,50,30);
		tfday.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfday);
		
		JLabel lb8 = new JLabel("日");
		lb8.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb8.setBounds(400, 310,150,50);
		this.add(lb8);
		

		jbEdit=new JButton();
		jbEdit.setBounds(10,400,200, 70);
		this.add(jbEdit);
		jbEdit.addActionListener(this);
		
		
	}
	private void setEditMode(Boolean mode){
		tfName.setEditable(mode);
		tfpay.setEditable(false);
		tfActress.setEditable(false);
		tfActor.setEditable(false);
		tfyear.setEditable(mode);
		tfmonth.setEditable(mode);
		tfday.setEditable(mode);
		tfsex.setEditable(mode);
		if(mode){
			jbEdit.setText("儲存後關閉");
			jbEdit.setFont(new Font("新細明體",Font.PLAIN ,24));
		}
		else{
			jbEdit.setText("編輯");
			jbEdit.setFont(new Font("新細明體",Font.PLAIN ,24));
		}
	}
	
	public void setData() throws SQLException{
		tfName.setText(member.getName());;
		tfpay.setText(member.getPay());
		tfyear.setText(String.valueOf(member.getBir().getYear()));
		tfmonth.setText(String.valueOf(member.getBir().getMonth()));
		tfday.setText(String.valueOf(member.getBir().getDay()));
		tfsex.setText(member.getSex());
		tfActress.setText(member.getActress());
		tfActor.setText(member.getfavActor());
	}
	public void modifyData() throws SQLException{
		member.setName(tfName.getText());
		member.setBir(new time(Integer.parseInt(tfyear.getText()),Integer.parseInt(tfmonth.getText()),Integer.parseInt(tfday.getText())));
		member.setsex(tfsex.getText());

		DB.getMemberDB().updateMember(member);;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
		case"編輯":
			setEditMode(true);
			break;
		
		case"儲存後關閉":
			try {
				modifyData();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.dispose();
			break;
		case"取消":
			
			this.dispose();
			break;	
		}
		
	}
}
