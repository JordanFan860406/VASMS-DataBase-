package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DB.DB_connect;
import Object.Movie;
import Object.member;

public class DownloadInsert extends JFrame implements ActionListener {
	JLabel lbName = new JLabel("會員名稱:");
	JLabel lbMv = new JLabel("電影名稱:");
	JLabel lbDw = new JLabel("下載時間:");
	JComboBox memName;
	JComboBox mvName;
	private JTextField tfyear;
	private JTextField tfmonth;
	private JTextField tfday;
	JButton jbInsert;
	JButton jbCancel;
	DB_connect DB;
	String[]a={"選擇電影"};
	String[]b={"會員名稱"};
	public DownloadInsert(DB_connect DB) throws Exception{
		this.DB = DB;
		initialize();
		getData();
	}
	
	void initialize(){
		this.setBounds(100,100,500,500);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		
		lbName.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbName.setBounds(10, 10,150,50);
		this.add(lbName);
		
		memName = new JComboBox(b);
		memName.setBounds(130, 10, 250,50);
		memName.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(memName);
		
		lbMv.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbMv.setBounds(10,110,200,50);
		this.add(lbMv);
			
		mvName = new JComboBox(a);
		mvName.setBounds(130, 110, 250,50);
		mvName.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(mvName);
		
		lbDw.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbDw.setBounds(10, 210,150,50);
		this.add(lbDw);
		
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
		
		jbInsert = new JButton("新增");
		jbInsert.setBounds(10, 310, 200, 70);
		jbInsert.setFont(new Font("新細明體",Font.PLAIN ,28));
		this.add(jbInsert);
		jbInsert.addActionListener(this);
		
		jbCancel = new JButton("取消");
		jbCancel.setBounds(280, 310, 200, 70);
		jbCancel.setFont(new Font("新細明體",Font.PLAIN ,28));
		this.add(jbCancel);
		jbCancel.addActionListener(this);
	}
	
	public void setData() throws Exception{
		String memberName = memName.getSelectedItem().toString();
		String movieName = mvName.getSelectedItem().toString();
		String date = tfyear.getText() + "-" + tfmonth.getText() + "-" + tfday.getText();
		ArrayList<String>temp = DB.getDownloadDB().searchID(memberName, movieName);
		DB.getDownloadDB().insertDownload(temp.get(0), temp.get(1), date);
	}
	
	public void getData() throws Exception{
		for(Movie i : DB.getMovieDB().searchMovie("", "所有類型", "所有年代" , "所有演員")){
			mvName.addItem(i.getTitle());
		}
		for(member j : DB.getMemberDB().searchAllMember("")){
			memName.addItem(j.getName());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case"新增":
				try {
					setData();
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
