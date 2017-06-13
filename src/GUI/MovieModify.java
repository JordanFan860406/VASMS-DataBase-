package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DB.DB_connect;
import Object.Movie;

public class MovieModify extends JFrame implements ActionListener{
	JLabel lb = new JLabel("電影名稱:");
	JLabel lb1 = new JLabel("出版公司:");
	JLabel lb2 = new JLabel("公司地址:");
	JLabel lb3 = new JLabel("出版時間:");
	JLabel lb4 = new JLabel("價格:");
	JTextField tfName;
	private JTextField tfcom;
	private JTextField tfadd;
	private JTextField tfyear;
	private JTextField tfmonth;
	private JTextField tfday;
	private JTextField tfmon;
	JButton jbEnter;
	JButton jbEdit;
	JButton jbClose;
	DB_connect DB;
	Movie movie;
	public MovieModify(DB_connect DB,Movie movie){
		this.DB=DB;
		this.movie=movie;
		initialize();
		setEditMode(false);
		setData();
		
	}
	void initialize(){
		this.setBounds(100,100,500,800);
		this.setLayout(null);
		this.setVisible(true);
		//電影名稱
		lb.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb.setBounds(10, 10,150,50);
		this.add(lb);
		
		tfName=new JTextField();
		tfName.setBounds(130, 10, 250,50);
		tfName.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfName);
		
		//公司名稱
		lb1.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb1.setBounds(10,110,200,50);
		this.add(lb1);
		
		tfcom=new JTextField();
		tfcom.setBounds(130, 110, 250,50);
		tfcom.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfcom);
		
		//公司地址
		lb2.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb2.setBounds(10,210,150,50);
		this.add(lb2);
		
		tfadd=new JTextField();
		tfadd.setBounds(130, 210, 300,50);
		tfadd.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(tfadd);
		
		//出版時間
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
		
		//價格
		lb4.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb4.setBounds(10,410,150,50);
		this.add(lb4);
		
		tfmon=new JTextField();
		tfmon.setBounds(130, 410, 100,50);
		tfmon.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfmon);
		
		
		
		jbEdit=new JButton();
		jbEdit.setBounds(10, 600,200, 70);
		this.add(jbEdit);
		jbEdit.addActionListener(this);
		
	}
	private void setEditMode(Boolean mode){
		tfName.setEditable(mode);
		tfcom.setEditable(mode);
		tfadd.setEditable(mode);
		tfyear.setEditable(mode);
		tfmonth.setEditable(mode);
		tfday.setEditable(mode);
		tfmon.setEditable(mode);
		
		if(mode){
			jbEdit.setText("儲存後關閉");
			jbEdit.setFont(new Font("新細明體",Font.PLAIN ,24));
		}
		else{
			jbEdit.setText("編輯");
			jbEdit.setFont(new Font("新細明體",Font.PLAIN ,24));
		}
	}
	public void setData(){
		tfName.setText(movie.getTitle());
		tfcom.setText(movie.getCompanyName());
		tfadd.setText(movie.getCompanyAddress());
		tfyear.setText(String.valueOf(movie.getTime().getYear()));
		tfmonth.setText(String.valueOf(movie.getTime().getMonth()));
		tfday.setText(String.valueOf(movie.getTime().getDay()));
		tfmon.setText(String.valueOf(movie.get()));
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
		case"編輯":
			setEditMode(true);
			break;
		
		case"儲存後關閉":
			
			this.dispose();
			break;
		case"取消":
			
			this.dispose();
			break;	
		}
		
	}
}





