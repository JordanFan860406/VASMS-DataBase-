package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import DB.DB_connect;
import Object.Movie;
import Object.member;

public class MemberSearchPanel extends JPanel implements ActionListener{
	JLabel jlName;
	JLabel jlpay;
	JRadioButton radioButton;
	JButton jb;
	DB_connect DB;
	member member;
	boolean isselect=false;
	public MemberSearchPanel(member member,DB_connect DB){
		initialize();
		this.jlName.setText("名稱:"+member.getName());
		this.jlpay.setText("已付:"+member.getPay());
		this.DB=DB;
		this.member=member;
	}
	
	void initialize() {
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		GridBagConstraints GBC= new GridBagConstraints();
		
		GBC.insets = new Insets(2,2,10,10);
		GBC.gridheight=1;
		GBC.gridwidth=0;
		GBC.fill = GridBagConstraints.EAST;
		radioButton= new JRadioButton();
		radioButton.setBackground(Color.white);
		radioButton.addActionListener(this);
		this.add(radioButton,GBC);
		
	
		jlName = new JLabel();
		jlName.setFont(new Font("新細明體",Font.PLAIN ,24));
		GBC.gridheight=0;
		GBC.gridwidth=1;
		GBC.fill = GridBagConstraints.CENTER;
		this.add(jlName,GBC);
		
		jlpay = new JLabel();
		jlpay.setFont(new Font("新細明體",Font.PLAIN ,24));
		GBC.gridheight=0;
		GBC.gridwidth=1;
		GBC.fill = GridBagConstraints.CENTER;
		this.add(jlpay,GBC);
		
		GBC.fill = GridBagConstraints.WEST;
		jb=new JButton("詳細資料");
		GBC.gridheight=1;
		GBC.gridwidth=1;
		jb.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jb,GBC);
		jb.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) 
			{ 
				try {
					new MemberModify(DB,member);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		}); 
	}
	public boolean getSelsec(){
		return isselect;
	}
	public member getMember(){
		return member;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
			if(radioButton.isSelected()){  
				this.setBackground(Color.lightGray);
				radioButton.setBackground(Color.lightGray);
				isselect=true;
			}
			else{
				this.setBackground(Color.white);
				radioButton.setBackground(Color.white);
				isselect=false;
			}
			this.updateUI();
			
		
		
	}
}
