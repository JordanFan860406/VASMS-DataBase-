package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DB.DB_connect;
import Object.Company;
import Object.Direct;
import Object.Movie;
import Object.time;

public class MovieInsert extends JFrame implements ActionListener{
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
	private JTextField tfgen;
	JButton jbEnter;
	JButton jbEdit;
	JButton jb;
	JButton jbClose;
	DB_connect DB;
	JComboBox com;
	JComboBox director;
	
	public MovieInsert(DB_connect DB) throws SQLException{
		this.DB=DB;
		initialize();
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
		
		com=new JComboBox();
		com.setBounds(130, 110, 250,50);
		com.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(com);
		 com.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					try {
						changeAdress(com.getSelectedItem().toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
	        });
		
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
		
		//類型
		JLabel lb9 = new JLabel("類型");
		lb9.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb9.setBounds(10,510,150,50);
		this.add(lb9);
		
		tfgen=new JTextField();
		tfgen.setBounds(130, 510, 100,50);
		tfgen.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfgen);
		
		JLabel lb10 = new JLabel("導演:");
		lb10.setFont(new Font("新細明體",Font.PLAIN ,24));
		lb10.setBounds(240,410,150,50);
		this.add(lb10);
		
		director=new JComboBox();
		director.setBounds(300, 410, 150,50);
		director.setFont(new Font("新細明體",Font.PLAIN ,20));
		this.add(director);
		
		jbEdit=new JButton("確定");
		jbEdit.setBounds(10, 600,200, 70);
		this.add(jbEdit);
		jbEdit.addActionListener(this);
		
		jb=new JButton("取消");
		jb.setBounds(250, 600,200, 70);
		this.add(jb);
		jb.addActionListener(this);
	}
	public void changeAdress(String name) throws SQLException{
		for(Company i:DB.getCompanyDB().searchAllCom()){
			if(i.getName().equals(name)){
				tfadd.setText(i.getAddress());
				break;
			}
			
		}
		
	}
	public void setData() throws SQLException{
		for(Company i:DB.getCompanyDB().searchAllCom()){
			com.addItem(i.getName());
		}
		for(Direct i:DB.getDictorDB().searchAllDirector()){
				director.addItem(i.getName());
		}
	}
	public void getData(ArrayList<Movie> movieArray) throws SQLException{
		boolean a=false;
		for(Movie i:movieArray){
			if(i.getTitle().equals(tfName.getText())){
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
			Movie movie=new Movie();
			int k=DB.getMovieDB().countMovie();
			String ID="mv000"+String.valueOf(k+1);
			
			movie.setID(ID);
			movie.setTitle(tfName.getText());
			movie.setCompany(new Company(com.getSelectedItem().toString(),tfadd.getText()));
			//加入導演
			for(Direct i:DB.getDictorDB().searchAllDirector()){
				if(i.getName().equals(director.getSelectedItem().toString())){
					movie.setDirect(i);
					break;
				}
			}
			try{
				movie.setTime(new time(Integer.parseInt(tfyear.getText()),Integer.parseInt(tfmonth.getText()),Integer.parseInt(tfday.getText())));
				movie.setcharge(Integer.parseInt(tfmon.getText()));
				movie.setGeners(tfgen.getText());
				
				System.out.print(tfgen.getText());
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null,"請輸入正確資料");
			}
			DB.getMovieDB().insertMovie(movie);
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
		case"確定":
			try {
				ArrayList<Movie> movieArray=DB.getMovieDB().searchMovie("","所有類型","所有年代","所有演員");
				
				
				getData(movieArray);
				this.dispose();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
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
