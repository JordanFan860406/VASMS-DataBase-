package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import DB.DB_connect;
import Object.Movie;
import Object.member;

public class MemberPanel extends JPanel implements ActionListener{
	private JTextField tfName;
	private JComboBox type;
	JButton btnNew= new JButton("新增");
	JButton btnDelete= new JButton("刪除");
	JPanel searchPanel;
	JPanel MovieSearchPanel;
	DB_connect DB;
	JPanel Panel;
	JPanel Panel1;
	ArrayList<MemberSearchPanel> seapan=new ArrayList<MemberSearchPanel>();
	ArrayList<member> memberArray;
	String [] a={"所有類型","前10付錢最多"};
	public MemberPanel(DB_connect DB){
		initialize();
		this.DB=DB;
	}
	
	
	
	void initialize() {
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
	public void search(String name,String str) throws SQLException{
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
		ArrayList<member> memberArray=DB.getMemberDB().searchAllMember(name);
		if(str.equals("所有類型")){
			for(member i:memberArray){
				MemberSearchPanel mov= new MemberSearchPanel(i,DB);
				seapan.add(mov);
				Panel1.add(mov,GBC);
			}
		}
		else{
			for(int k=0;k<10;k++){
				int money=0;
				member member=null ;
				for(member i:memberArray){
					if( Integer.valueOf(i.getPay())>money){
						money=Integer.valueOf(i.getPay());
						member=i;
					}
				}
				if(member!=null){
					MemberSearchPanel mov= new MemberSearchPanel(member,DB);
					seapan.add(mov);
					Panel1.add(mov,GBC);
					memberArray.remove(member);
				}
			}
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
				search(tfName.getText(),type.getSelectedItem().toString());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Panel1.updateUI();
			break;
		case"新增":
			try {
				new MemberInsert(DB);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("新增");
			break;
		case"刪除":
			System.out.println("刪除");
			for(MemberSearchPanel i:seapan){
				if(i.getSelsec()){
					try {
						DB.getMemberDB().delete(i.getMember());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
			try {
				search(tfName.getText(),type.getSelectedItem().toString());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
		
		
	}
	
}
