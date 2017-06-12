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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MoviePanel extends JPanel implements ActionListener{
	
	private JTextField tfName;
	private JComboBox type;
	private JComboBox year;
	private JComboBox sort;
	JButton btnNew= new JButton("新增");
	JButton btnDelete= new JButton("刪除");
	JPanel searchPanel;
	JPanel MovieSearchPanel;
	JPanel Panel;
	JPanel Panel1;
	String [] a={"所有類型","驚悚","動作","愛情"};
	String [] b={"所有年代","2017","2015","2000~2015"};
	String [] c={"無","下載次數","年代(小~大)","年代(大~小)"};
	public MoviePanel(){
		initialize();
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
		
		JLabel lbYear=new JLabel("電影年代 :");
		lbYear.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbYear.setBounds(10,120,100,30);
		searchPanel.add(lbYear);
		
		year=new JComboBox(b);
		year.setFont(new Font("新細明體",Font.PLAIN ,20));
		year.setBounds(120,120,150,30);
		searchPanel.add(year);
		
		
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
		
		JLabel lbSort=new JLabel("排序方式:");
		lbSort.setFont(new Font("新細明體",Font.PLAIN ,20));
		lbSort.setBounds(300,60,100,30);
		Panel.add(lbSort);
		
		sort=new JComboBox(c);
		sort.setFont(new Font("新細明體",Font.PLAIN ,20));
		sort.setBounds(400,60,150,30);
		Panel.add(sort);
		
		Panel1=new JPanel();
		Panel1.setSize(550 ,1125);
		JScrollPane scrollPane=new JScrollPane(Panel1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25,100,550 ,425);
		Panel1.setBackground(Color.white);
		Panel.add(scrollPane);
		
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case"搜尋":
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
			Panel1.add(new MovieSearchPanel("無敵鐵金剛"),GBC);
			Panel1.add(new MovieSearchPanel("多拉A夢"),GBC);
			
			for(int i=0;i<1;i++){
				Panel1.add(new MovieSearchPanel("鋼鐵人"),GBC);
			}
			for(int i=0;i<7;i++){
				Panel1.add(new EmptyPanel(),GBC);
			}
			Panel1.updateUI();
			break;
		case"新增":
			System.out.println("新增");
			break;
		case"刪除":
			System.out.println("刪除");
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
