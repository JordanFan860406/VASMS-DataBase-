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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DB.DB_connect;
import Object.Movie;

public class DownloadPanel extends JPanel {
	private JComboBox type;
	private JComboBox Year;
	private JRadioButton month;
	private JComboBox movieType;
	private JLabel lbYear;
	JList dwName;
	DB_connect DB;
	String year;
	String mvType;
	String getMonth;
	String mvName;
	JList reList;
	JPanel searchPanel;
	JPanel Panel;
	JPanel Panel1;
	String getMvName;
	String [] b={"所有年份", "2005", "2006", "2007", "2008", "2009", "2010", "2011",
			"2012", "2013", "2014", "2015", "2005~2015", "2016", "2017"};
	String [] c={"所有類型"};
	String [] d={"所有月份", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	public DownloadPanel(DB_connect DB) throws Exception{
		initialize();
		this.DB = DB;
		setDate();
	}
	
	void initialize(){
		this.setSize(1000, 800);
		this.setLayout(null);
		this.setBackground(Color.CYAN);;
		
		searchPanel=new JPanel();
		searchPanel.setBounds(20, 20,600,200);
		searchPanel.setBackground(Color.lightGray);
		searchPanel.setLayout(null);
		this.add(searchPanel);
		
		JLabel panName=new JLabel("__________________________搜尋條件___________________________");
		panName.setFont(new Font("新細明體",Font.PLAIN ,20));
		panName.setBounds(5,0,800,30);
		searchPanel.add(panName);

		JLabel lbYear=new JLabel("下載年份");
		lbYear.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbYear.setBounds(5,50,120,30);
		searchPanel.add(lbYear);
		
		Year=new JComboBox(b);
		Year.setFont(new Font("新細明體",Font.PLAIN ,20));
		Year.setBounds(125,50,150,30);
		Year.setVisible(true);
		searchPanel.add(Year);
		
		JLabel lbmvType=new JLabel("類型");
		lbmvType.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbmvType.setBounds(300,50,120,30);
		searchPanel.add(lbmvType);
		
    	movieType=new JComboBox(c);
    	movieType.setFont(new Font("新細明體",Font.PLAIN ,20));
    	movieType.setBounds(380,50,150,30);
    	movieType.setVisible(true);
		searchPanel.add(movieType);
		
		JLabel lbMonth=new JLabel("");
		lbMonth.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbMonth.setBounds(5,100,120,30);
		searchPanel.add(lbMonth);
		
		month = new JRadioButton("按月份排出下載量");
		month.setBounds(140, 100, 200, 30);
		month.setBackground(Color.white);
		month.setFont(new Font("新細明體",Font.PLAIN ,20));
		searchPanel.add(month);
		
		
		JButton btnSearch= new JButton("搜尋");
		btnSearch.setBounds(480,140,100, 50);
		//btnClose.setBounds(x, y, width, height);
		searchPanel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					reList.removeAll();
					year = Year.getSelectedItem().toString();
					mvType = movieType.getSelectedItem().toString();
					boolean check = month.isSelected();
					ArrayList<String>tmpList = DB.getDownloadDB().searchDownload(year, check, mvType);
					reList.setListData(tmpList.toArray());
					dwName.removeAll();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
		JButton btnInsert = new JButton("新增");
		btnInsert.setFont(new Font("新細明體",Font.PLAIN ,28));
		btnInsert.setBounds(650, 5, 150, 100);
		this.add(btnInsert);
		btnInsert.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					new DownloadInsert(DB);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		
		JButton btnDelete = new JButton("刪除");
		btnDelete.setFont(new Font("新細明體",Font.PLAIN ,28));
		btnDelete.setBounds(820, 5, 150, 100);
		this.add(btnDelete);
		btnDelete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[]arr = dwName.getSelectedValue().toString().split(" : ");
				String[]arr2 = arr[1].split("  ");
				
				System.out.println(arr2[0] + arr[2]);
				try {
					ArrayList<String>seId = DB.getDownloadDB().searchID(arr2[0], arr[2]);
					DB.getDownloadDB().deleteDownLoad(seId.get(0), arr[2]);
					dwName.repaint();
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
		
		JLabel lbAns=new JLabel("____________________下載量結果_________________________________");
		lbAns.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbAns.setBounds(10,5,600,30);
		Panel.add(lbAns);
		
		
		Panel1=new JPanel();
		Panel1.setSize(550 ,1125);
		reList = new JList();
		reList.setBounds(25,100,550 ,425);
		reList.setFont(new Font("新細明體",Font.PLAIN ,20));
		Panel1.setBackground(Color.white);
		Panel.add(reList);
		
		reList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()){
					int index = reList.getSelectedIndex();
					try {
						dwName.removeAll();
						String[]temp = reList.getSelectedValue().toString().split(" ");
						String str1 = temp[2];
						String str2 = temp[6];
						boolean check = month.isSelected();
						ArrayList<String> rsList = DB.getDownloadDB().downloadName(str1, str2, check);
						dwName.setListData(rsList.toArray());		
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		
	
		
		JLabel dwData = new JLabel("下載詳細資料");
		dwData.setFont(new Font("新細明體",Font.PLAIN ,36));
		dwData.setBounds(650, 180, 250, 50);
		this.add(dwData);
		
		dwName = new JList();
		dwName.setFont(new Font("新細明體",Font.PLAIN ,18));
		dwName.setBounds(630, 240, 350, 550);
		this.add(dwName);
	}
	public void setDate() throws Exception{
		ArrayList<String> genresArray=DB.getGenresDB().searchGenres();
		for(String i:genresArray){
			movieType.addItem(i);
			System.out.print(i);
		}
	}
	

}
