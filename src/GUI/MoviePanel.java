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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MoviePanel extends JPanel implements ActionListener{
	
	private JTextField tfName;
	private JLabel lbName;
	JPanel searchPanel;
	JPanel MovieSearchPanel;
	JPanel Panel;
	JPanel Panel1;
	public MoviePanel(){
		initialize();
	}
	
	
	
	
	void initialize() {
		this.setSize(1000, 800);
		//this.setBackground(Color.pink);
		this.setLayout(null);
		
		searchPanel=new JPanel();
		searchPanel.setBounds(20, 20,600,200);
		searchPanel.setBackground(Color.lightGray);
		this.add(searchPanel);
		
		
		Panel=new JPanel();
		Panel.setSize(600,550);
		Panel.setBackground(Color.white);
		Panel.setLocation(20,240);
		Panel.setLayout(null);
		this.add(Panel);
		
		Panel1=new JPanel();
		Panel1.setSize(550 ,1125);
		JScrollPane scrollPane=new JScrollPane(Panel1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(25,100,550 ,425);
		Panel1.setBackground(Color.white);
		Panel.add(scrollPane);
		
		JButton btnClose= new JButton("Close");
		btnClose.addActionListener(this);
		//btnClose.setBounds(x, y, width, height);
		searchPanel.add(btnClose);
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Panel1.removeAll();
		Panel1.setLayout(new GridBagLayout() );
		Panel1.setBackground(Color.white);
		GridBagConstraints GBC= new GridBagConstraints();
		GBC.insets = new Insets(1,1,1,1);
		GBC.gridheight = 1;
		GBC.gridwidth = 0;
		GBC.weightx = 1;
		GBC.weighty = 0;
		GBC.fill = GridBagConstraints.HORIZONTAL;
		//Panel1.add(new MovieSearchPanel("無敵鐵金剛"),GBC);
		//Panel1.add(new MovieSearchPanel("多拉A夢"),GBC);
		
		for(int i=0;i<1;i++){
			Panel1.add(new MovieSearchPanel("鋼鐵人"),GBC);
		}
		for(int i=0;i<7;i++){
			Panel1.add(new EmptyPanel(),GBC);
		}
		Panel1.updateUI();
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
		this.setBackground(Color.lightGray);
		this.add(jl);
	}
}
