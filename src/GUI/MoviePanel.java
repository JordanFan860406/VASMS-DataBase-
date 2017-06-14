package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import DB.DB_Movie;
import DB.DB_connect;
import Object.Actor;
import Object.Movie;

public class MoviePanel extends JPanel implements ActionListener{
	
	private JTextField tfName;
	private JComboBox type;
	private JComboBox year;
	private JComboBox sort;
	private JComboBox actor;
	ArrayList<MovieSearchPanel> seapan=new ArrayList<MovieSearchPanel>();
	ArrayList<Movie> movieArray;
	JButton btnNew= new JButton("新增");
	JButton btnDelete= new JButton("刪除");
	JPanel searchPanel;
	JPanel MovieSearchPanel;
	JPanel Panel;
	JPanel Panel1;
	DB_connect DB;
	String [] a={"所有類型"};
	String [] b={"所有年代","2017","2005~2015","2016"};
	String [] c={"所有演員"};
	
	public MoviePanel(DB_connect DB) throws Exception{
		initialize();
		this.DB=DB;
		updateData(DB);
	}
	
	void updateData(DB_connect DB) throws Exception{
		ArrayList<String> genresArray=DB.getGenresDB().searchGenres();
		for(String i:genresArray){
			type.addItem(i);
			//System.out.print(i);
		}
		ArrayList<Actor> actorArray=DB.getActorDB().searchActor();
		for(Actor i:actorArray){
			actor.addItem(i.getName());
		}
	}
	
	void initialize() {
		this.setSize(1000, 800);
		this.setLayout(null);
		this.setBackground(Color.getHSBColor(111, 75, 50));
		
		searchPanel=new JPanel();
		searchPanel.setBounds(20, 20,600,200);
		searchPanel.setBackground(Color.lightGray);
		searchPanel.setLayout(null);
		this.add(searchPanel);
		
		btnNew.addActionListener(this);
		btnNew.setBounds(720,120,200, 150);
		btnNew.setFont(new Font("標楷體",Font.PLAIN ,48));
		//btnClose.setBounds(x, y, width, height);
		this.add(btnNew);
		
		btnDelete.addActionListener(this);
		btnDelete.setBounds(720,400,200, 150);
		btnDelete.setFont(new Font("標楷體",Font.PLAIN ,48));
		//btnClose.setBounds(x, y, width, height);
		this.add(btnDelete);
		
		JLabel panName=new JLabel("__________________________搜尋條件___________________________");
		panName.setFont(new Font("新細明體",Font.PLAIN ,20));
		panName.setBounds(5,0,800,30);
		searchPanel.add(panName);
		
		JLabel name=new JLabel("名稱:");
		name.setFont(new Font("新細明體",Font.PLAIN ,20));
		name.setBounds(10,50,100,30);
		searchPanel.add(name);
	
		tfName=new JTextField();
		tfName.setBounds(70,45, 150,35);
		tfName.setFont(new Font("新細明體",Font.PLAIN ,24));
		searchPanel.add(tfName);
		
		JLabel lbType=new JLabel("類型:");
		lbType.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbType.setBounds(250,50,100,30);
		searchPanel.add(lbType);
		
		
		type=new JComboBox(a);
		type.setFont(new Font("新細明體",Font.PLAIN ,20));
		type.setBounds(300,50,150,30);
		searchPanel.add(type);
		
		JButton btnSearch= new JButton("搜尋");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(480,140,100, 50);
		//btnClose.setBounds(x, y, width, height);
		searchPanel.add(btnSearch);
		
		JLabel lbYear=new JLabel("電影年代 :");
		lbYear.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbYear.setBounds(10,120,100,30);
		searchPanel.add(lbYear);
		
		year=new JComboBox(b);
		year.setFont(new Font("新細明體",Font.PLAIN ,20));
		year.setBounds(110,120,150,30);
		searchPanel.add(year);
		
		JLabel lbActor= new JLabel("演員 :");
		lbActor.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbActor.setBounds(275,120,100,30);
		searchPanel.add(lbActor);
		
		actor=new JComboBox(c);
		actor.setFont(new Font("新細明體",Font.PLAIN ,20));
		actor.setBounds(330,120,150,30);
		searchPanel.add(actor);
		
		
		
		Panel=new JPanel();
		Panel.setSize(600,550);
		Panel.setBackground(Color.lightGray);
		Panel.setLocation(20,240);
		Panel.setLayout(null);
		this.add(Panel);
		
		JLabel lbAns=new JLabel("__________________________搜尋結果_____________________________");
		lbAns.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbAns.setBounds(10,5,600,30);
		Panel.add(lbAns);
		

		
		Panel1=new JPanel();
		Panel1.setSize(550 ,1125);
		JScrollPane scrollPane=new JScrollPane(Panel1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(25,100,550 ,425);
		Panel1.setBackground(Color.white);
		Panel.add(scrollPane);
		
		
	}


	public void search(String name,String type,String year,String actor,DB_connect DB) throws Exception{
		Panel1.removeAll();
		Panel1.setLayout(new GridBagLayout() );
		Panel1.setBackground(Color.lightGray);
		GridBagConstraints GBC= new GridBagConstraints();
		GBC.insets = new Insets(1,1,1,1);
		GBC.gridheight = 1;
		GBC.gridwidth = 0;
		GBC.weightx = 1;
		GBC.weighty = 0;
		GBC.fill = GridBagConstraints.HORIZONTAL;
		
		ArrayList<Movie> movieArray=DB.getMovieDB().searchMovie(name,type,year,actor);
		
		for(Movie i:movieArray){
			
			MovieSearchPanel mov= new MovieSearchPanel(i,DB);
			seapan.add(mov);
			Panel1.add(mov,GBC);
		}
		for(int i=0;i<7;i++){
			Panel1.add(new EmptyPanel(),GBC);
		}
		Panel1.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case"搜尋":
			try {

				String name=tfName.getText();
				String genres=type.getSelectedItem().toString();
				String years=year.getSelectedItem().toString();
				String actors=actor.getSelectedItem().toString();
				search(name,genres,years,actors,DB);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case"新增":
			try {
				new MovieInsert(DB);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("新增");
			break;
		case"刪除":
			System.out.println("刪除");
			for(MovieSearchPanel i:seapan){
				if(i.getSelsec()){
					try {
						DB.getMovieDB().delete(i.getMovie());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
			String name=tfName.getText();
			String genres=type.getSelectedItem().toString();
			String years=year.getSelectedItem().toString();
			String actors=actor.getSelectedItem().toString();
			try {
				search(name,genres,years,actors,DB);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
		
		
	}
	
}
class EmptyPanel extends JPanel{
	JLabel jl;
	public EmptyPanel(){
		initialize();
		
	}
	void initialize() {
		
		jl = new JLabel("   ");
		jl.setFont(new Font("新細明體",Font.PLAIN ,36));
		this.setBackground(Color.white);
		this.add(jl);
	}
}
