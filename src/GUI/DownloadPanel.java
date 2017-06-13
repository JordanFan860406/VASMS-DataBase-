package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import DB.DB_connect;

public class DownloadPanel extends JPanel {
	private JComboBox type;
	private JComboBox Year;
	private JComboBox movieType;
	private JLabel lbYear;
	DB_connect DB;
	String year;
	String mvType;
	JList reList;
	JPanel searchPanel;
	JPanel Panel;
	JPanel Panel1;
	String select;
	String [] a={"查詢項目", "依年代查詢", "依電影類型查詢"};
	String [] b={"2005", "2006", "2007", "2008", "2009", "2010", "2011",
			"2012", "2013", "2014", "2015", "2005~2015", "2016", "2017"};
	String [] c={"所有類型","驚悚","動作","愛情"};
	public DownloadPanel(DB_connect DB){
		initialize();
		this.DB = DB;
	}
	
	void initialize(){
		this.setSize(1000, 800);
		this.setLayout(null);
		
		searchPanel=new JPanel();
		searchPanel.setBounds(20, 20,600,200);
		searchPanel.setBackground(Color.lightGray);
		searchPanel.setLayout(null);
		this.add(searchPanel);
		
		JLabel panName=new JLabel("__________________________搜尋條件___________________________");
		panName.setFont(new Font("新細明體",Font.PLAIN ,20));
		panName.setBounds(5,0,800,30);
		searchPanel.add(panName);
	
		JLabel lbType=new JLabel("依項目查詢");
		lbType.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbType.setBounds(5,50,120,30);
		searchPanel.add(lbType);
		
		type=new JComboBox(a);
		type.setFont(new Font("新細明體",Font.PLAIN ,20));
		type.setBounds(125,50,150,30);
		searchPanel.add(type);
		
		Year=new JComboBox(b);
		Year.setFont(new Font("新細明體",Font.PLAIN ,20));
		Year.setBounds(125,80,150,30);
		Year.setVisible(false);
		searchPanel.add(Year);
		Year.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				year = Year.getSelectedItem().toString();
			}
			
		});
		
    	movieType=new JComboBox(c);
    	movieType.setFont(new Font("新細明體",Font.PLAIN ,20));
    	movieType.setBounds(125,80,150,30);
    	movieType.setVisible(false);
		searchPanel.add(movieType);
		movieType.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mvType = movieType.getSelectedItem().toString();
			}
			
		});
		
		type.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox comboBox = (JComboBox) e.getSource();

                Object selected = comboBox.getSelectedItem();
                if(selected.toString().equals("依年代查詢")){
                	//searchPanel.remove(movieType);
                	movieType.setVisible(false);
                	Year.setVisible(true);
                	mvType = null;
                }
                
                else if(selected.toString().equals("依電影類型查詢")){
                	Year.setVisible(false);
                	movieType.setVisible(true);
                	
                	mvType = movieType.getSelectedItem().toString();
                	year = null;
                }
			}	
		});
		
		
		JButton btnSearch= new JButton("搜尋");
		btnSearch.setBounds(480,140,100, 50);
		//btnClose.setBounds(x, y, width, height);
		searchPanel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ArrayList<String>tmpList = DB.searchDownload(year, mvType);
					reList.setListData(tmpList.toArray(a));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
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
		reList = new JList();
		reList.setBounds(25,100,550 ,425);
		Panel1.setBackground(Color.white);
		Panel.add(reList);
		
		
	}
	
	

}
