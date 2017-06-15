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
import Object.Actor;
import Object.Movie;

public class ActNew extends JFrame implements ActionListener{
	JLabel lbActor = new JLabel("演員名字:");
	JLabel lbMv = new JLabel("電影名稱:");
	JLabel lbRole = new JLabel("角       色:");
	JLabel lbFrame = new JLabel("演出資料新增");
	JTextField tfRole;
	JComboBox jbActor;
	JComboBox jbMovie;
	JButton btnInsert;
	JButton btnCancel;
	String[]actor = {"演員"};
	String[]movie = {"電影"};
	DB_connect DB;
	
	public ActNew(DB_connect DB) throws Exception{
		this.DB = DB;
		initialize();
		setData();
	}
	
	void initialize(){
		this.setBounds(100,100,500,700);
		this.setLayout(null);
		this.setVisible(true);
		
		lbFrame.setFont(new Font("新細明體",Font.PLAIN ,32));
		lbFrame.setBounds(135, 10,200,50);
		this.add(lbFrame);
		
		lbActor.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbActor.setBounds(10, 110,150,50);
		this.add(lbActor);
		
		jbActor = new JComboBox(actor);
		jbActor.setBounds(130, 110, 250,50);
		jbActor.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbActor);
		
		lbMv.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbMv.setBounds(10, 210,150,50);
		this.add(lbMv);
		
		jbMovie = new JComboBox(movie);
		jbMovie.setBounds(130, 210, 250, 50);
		jbMovie.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbMovie);
		
		lbRole.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbRole.setBounds(10, 310,150,50);
		this.add(lbRole);
		
		tfRole = new JTextField();
		tfRole.setFont(new Font("新細明體",Font.PLAIN ,24));
		tfRole.setBounds(130, 310, 250, 50);
		this.add(tfRole);
		
		btnInsert = new JButton("儲存後離開");
		btnInsert.setBounds(50, 450, 160, 100);
		btnInsert.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnInsert);
		btnInsert.addActionListener(this);
		
		btnCancel = new JButton("取消");
		btnCancel.setBounds(250, 450, 160, 100);
		btnCancel.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnCancel);
		btnCancel.addActionListener(this);
	}
	
	public void setData() throws Exception{
		for(Actor i : DB.getActorDB().searchActor()){
			jbActor.addItem(i.getName());
		}
		for(Movie i : DB.getMovieDB().searchMovie("", "所有類型", "所有年代", "所有演員")){
			jbMovie.addItem(i.getTitle());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case"儲存後離開":
				this.dispose();
				break;
			case"取消":
				this.dispose();
				break;
		}
	}

}
