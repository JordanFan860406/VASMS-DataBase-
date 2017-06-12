package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class DownloadPanel extends JPanel implements ActionListener{
	private JComboBox type;
	private JComboBox Year;
	private JComboBox movieType;
	private JLabel lbYear;
	JButton btnNew= new JButton("新增");
	JButton btnDelete= new JButton("刪除");
	JPanel searchPanel;
	JPanel Panel;
	JPanel Panel1;
	String [] a={"查詢項目", "依年代查詢", "依電影類型查詢"};
	String [] b={"2005", "2006", "2007", "2008", "2009", "2010", "2011",
			"2012", "2013", "2014", "2015", "2005~2015"};
	String [] c={"所有類型","驚悚","動作","愛情"};
	public DownloadPanel(){
		initialize();
	}
	
	void initialize(){
		this.setSize(1000, 800);
		this.setLayout(null);
		
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
		
    	movieType=new JComboBox(c);
    	movieType.setFont(new Font("新細明體",Font.PLAIN ,20));
    	movieType.setBounds(125,80,150,30);
    	movieType.setVisible(false);
		searchPanel.add(movieType);
//		lbYear=new JLabel("年代");
//    	lbYear.setFont(new Font("新細明體",Font.PLAIN ,20));
//    	lbYear.setBounds(5,80,120,30);
//		searchPanel.add(lbYear);
		
//		Year=new JComboBox(b);
//		Year.setFont(new Font("新細明體",Font.PLAIN ,20));
//		Year.setBounds(125,80,150,30);
//		searchPanel.add(Year);
		
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
                }
                
                else if(selected.toString().equals("依電影類型查詢")){
                	Year.setVisible(false);
                	movieType.setVisible(true);
                	
                }
			}	
		});
		
//		JLabel lbYear=new JLabel("");
//		lbType.setFont(new Font("新細明體",Font.PLAIN ,20));
//		lbType.setBounds(5,50,120,30);
//		searchPanel.add(lbType);
		
		JButton btnSearch= new JButton("搜尋");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(480,140,100, 50);
		//btnClose.setBounds(x, y, width, height);
		searchPanel.add(btnSearch);
		
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
		JScrollPane scrollPane=new JScrollPane(Panel1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25,100,550 ,425);
		Panel1.setBackground(Color.white);
		Panel.add(scrollPane);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
