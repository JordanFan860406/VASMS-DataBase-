package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DB.DB_connect;

public class DownloadInsert extends JFrame implements ActionListener {
	JLabel lbName = new JLabel("會員名稱:");
	JLabel lbMv = new JLabel("電影名稱:");
	JLabel lbDw = new JLabel("下載時間:");
	JTextField tfName;
	private JTextField tfMvName;
	private JTextField tfyear;
	private JTextField tfmonth;
	private JTextField tfday;
	JButton jbInsert;
	DB_connect DB;
	
	public DownloadInsert(DB_connect DB){
		this.DB = DB;
		initialize();
	}
	
	void initialize(){
		this.setBounds(100,100,500,500);
		this.setLayout(null);
		this.setVisible(true);
		lbName.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbName.setBounds(10, 10,150,50);
		this.add(lbName);
		
		tfName = new JTextField();
		tfName.setBounds(130, 10, 250,50);
		tfName.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfName);
		
		lbMv.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbMv.setBounds(10,110,200,50);
		this.add(lbMv);
		
		tfMvName = new JTextField();
		tfMvName.setBounds(130, 110, 250,50);
		tfMvName.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfMvName);
		
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
	}
	
	public void setData(){
		String memName = tfName.getText();
		String movieName = tfMvName.getText();
		String date = tfyear.getText() + "-" + tfmonth.getText() + "-" + tfday.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
