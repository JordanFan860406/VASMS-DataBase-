package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Object.Movie;

public class MovieSearchPanel extends JPanel implements ActionListener{
	
	JLabel jlName;
	JRadioButton radioButton;
	JButton jb;
	boolean select=false;
	
	public MovieSearchPanel(Movie movie){
		initialize();
		this.jlName.setText("名稱:"+movie.getTitle());
		/*btnClos1.setBounds(0, 50,50,50);
		this.add(btnClos1);*/
	}
	
	void initialize() {
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		GridBagConstraints GBC= new GridBagConstraints();
		
		GBC.insets = new Insets(2,2,10,10);
		GBC.gridheight=1;
		GBC.gridwidth=1;
		GBC.fill = GridBagConstraints.EAST;
		radioButton= new JRadioButton();
		radioButton.setBackground(Color.white);
		radioButton.addActionListener(this);
		this.add(radioButton,GBC);
		
	
		jlName = new JLabel();
		jlName.setFont(new Font("新細明體",Font.PLAIN ,24));
		GBC.gridheight=1;
		GBC.gridwidth=1;
		this.add(jlName,GBC);
		
		GBC.fill = GridBagConstraints.WEST;
		jb=new JButton("詳細資料");
		GBC.gridheight=1;
		GBC.gridwidth=1;
		jb.setFont(new Font("新細明體",Font.PLAIN ,36));
		this.add(jb,GBC);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			if(radioButton.isSelected()){
				this.setBackground(Color.lightGray);
				radioButton.setBackground(Color.lightGray);
				System.out.print("11");
			}
			else{
				this.setBackground(Color.white);
				radioButton.setBackground(Color.white);
			}
			this.updateUI();
			
		
		
	}
	
}
