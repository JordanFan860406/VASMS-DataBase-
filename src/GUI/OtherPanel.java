package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import DB.DB_connect;

public class OtherPanel extends JPanel {
	DB_connect DB;
	JButton comInsert;
	JButton comRemove;
	JButton dirInsert;
	JButton dirRemove;
	JButton btnAct;
	JButton actorInsert;
	JButton actorRemove;
	JPanel comPanel;
	JPanel dirPanel;
	JPanel actPanel;
	
	public OtherPanel(DB_connect DB){
		this.DB = DB;
		initialize();
	}
	
	void initialize(){
		this.setSize(1000, 800);
		this.setLayout(null);
		
		comPanel = new JPanel();
		comPanel.setBounds(20, 20, 430, 350);
		comPanel.setBackground(Color.lightGray);
		comPanel.setLayout(null);
		this.add(comPanel);
		
		JLabel lbCom = new JLabel("製片公司");
		lbCom.setFont(new Font("標楷體",Font.PLAIN ,48));
		lbCom.setBounds(105, 0, 300, 120);
		comPanel.add(lbCom);
		
		comInsert = new JButton("新增");
		comInsert.setBounds(50, 100, 310, 110);
		comInsert.setFont(new Font("標楷體",Font.PLAIN ,48));
		comPanel.add(comInsert);
		comInsert.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CompanyInsert(DB);
			}
			
		});
		
		
		comRemove = new JButton("刪除");
		comRemove.setBounds(50, 230, 310, 110);
		comRemove.setFont(new Font("標楷體",Font.PLAIN ,48));
		comPanel.add(comRemove);
		comRemove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new CompanyDelete(DB);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		dirPanel = new JPanel();
		dirPanel.setBounds(20, 420, 430, 350);
		dirPanel.setBackground(Color.lightGray);
		dirPanel.setLayout(null);
		this.add(dirPanel);
		
		JLabel lbDir = new JLabel("導演");
		lbDir.setFont(new Font("標楷體",Font.PLAIN ,48));
		lbDir.setBounds(150, 0, 300, 120);
		dirPanel.add(lbDir);
		
		dirInsert = new JButton("新增");
		dirInsert.setBounds(50, 100, 310, 110);
		dirInsert.setFont(new Font("標楷體",Font.PLAIN ,48));
		dirPanel.add(dirInsert);
		dirInsert.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DirectorInsert(DB);
			}
			
		});
		
		dirRemove = new JButton("刪除");
		dirRemove.setBounds(50, 230, 310, 110);
		dirRemove.setFont(new Font("標楷體",Font.PLAIN ,48));
		dirPanel.add(dirRemove);
		dirRemove.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new DirectorDelete(DB);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		actPanel = new JPanel();
		actPanel.setBounds(480, 20, 500, 750);
		actPanel.setLayout(null);
		actPanel.setBackground(Color.lightGray);
		this.add(actPanel);
		
		JLabel lbAct = new JLabel("演員");
		lbAct.setFont(new Font("標楷體",Font.PLAIN ,48));
		lbAct.setBounds(200, 0, 300, 120);
		actPanel.add(lbAct);
		
		actorInsert = new JButton("新增");
		actorInsert.setBounds(100, 150, 310, 150);
		actorInsert.setFont(new Font("標楷體",Font.PLAIN ,48));
		actPanel.add(actorInsert);
		actorInsert.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ActorInsert(DB);
			}
			
		});
		
		actorRemove = new JButton("刪除");
		actorRemove.setBounds(100, 550, 310, 150);
		actorRemove.setFont(new Font("標楷體",Font.PLAIN ,48));
		actPanel.add(actorRemove);
		actorRemove.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new ActorDelete(DB);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		btnAct = new JButton("演出");
		btnAct.setBounds(100, 350, 310, 150);
		btnAct.setFont(new Font("標楷體",Font.PLAIN ,48));
		actPanel.add(btnAct);
		btnAct.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new ActNew(DB);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}

}
