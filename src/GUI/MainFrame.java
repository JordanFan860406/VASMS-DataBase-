package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DB.DB_connect;

public class MainFrame implements ActionListener{
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel btnPanel;
	JButton btnMov = new JButton("電影");
	JButton btnMem = new JButton("會員");
	JButton btn = new JButton("下載");
	JLabel lb = new JLabel("               歡迎使用本系統");
	DB_connect DB;
	String DBB;
	public static void  main(String [] args) throws Exception{
		new MainFrame();
	}
	
	
	
	public MainFrame() throws Exception{
		initialize();
		frame.setVisible(true);
		DB=new DB_connect();
	}
	
	void initialize() {
		frame = new JFrame();
		frame.setTitle("");
		frame.setBounds(300, 100, 1205,835 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		
		btnPanel=new JPanel();
		btnPanel.setBackground(Color.white);
		btnPanel.setBounds(0,0,200,800 );
		frame.getContentPane().add(btnPanel);
		btnPanel.setLayout(new GridLayout(4, 1));
		
		mainPanel=new JPanel();
		mainPanel.setBackground(Color.pink);
		mainPanel.setBounds(200,0,1000,800 );
		mainPanel.setLayout(new GridLayout(1, 1));
		frame.getContentPane().add(mainPanel);
		
		lb.setFont(new Font("標楷體",Font.PLAIN ,48));
		mainPanel.add(lb);
		
		btnMov.setFont(new Font("標楷體",Font.PLAIN ,48));
		btnMov.addActionListener(this);
		btnPanel.add(btnMov);
		
		
		btnMem.setFont(new Font("標楷體",Font.PLAIN ,48));
		btnMem.addActionListener(this);
		btnPanel.add(btnMem);
		
		
		btn.setFont(new Font("標楷體",Font.PLAIN ,48));
		btn.addActionListener(this);
		btnPanel.add(btn);
		
		
		btnPanel.add(new JButton("離開"));
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getActionCommand()){
		case"電影":
			JPanel JPanel1;
			try {
				JPanel1 = new MoviePanel(DB);
				mainPanel.removeAll();
				mainPanel.add(JPanel1);
				mainPanel.updateUI();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case"會員":
			JPanel  MemberPanel=new MemberPanel();
			mainPanel.removeAll();
			mainPanel.add(MemberPanel);
			mainPanel.updateUI();
			
			break;
			
		case"下載":
			JPanel  JPanel3= new DownloadPanel(DB);
			//JPanel3.setBackground(Color.lightGray);
			mainPanel.removeAll();
			mainPanel.add(JPanel3);
			mainPanel.updateUI();
			
			break;
			
		}
	}
}
