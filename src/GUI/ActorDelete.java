package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DB.DB_connect;
import Object.Actor;
import Object.Company;

public class ActorDelete extends JFrame implements ActionListener {
	JLabel lbFrame = new JLabel("刪除演員資料");
	JLabel lbAct = new JLabel("演員名稱:");
	JComboBox jbAct ;
	String[]act = {"請選擇演員"};
	JButton btnDelete;
	JButton btnCancel;
	DB_connect DB;
	
	public ActorDelete(DB_connect DB) throws Exception{
		this.DB = DB;
		initialize();
		setData();
	}
	
	void initialize(){
		this.setBounds(100,100,500,500);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);

		lbFrame.setFont(new Font("新細明體",Font.PLAIN ,32));
		lbFrame.setBounds(135, 10,200,50);
		this.add(lbFrame);
		
		lbAct.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbAct.setBounds(10, 110,150,50);
		this.add(lbAct);
		
		jbAct = new JComboBox(act);
		jbAct.setBounds(130, 110, 330,50);
		jbAct.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbAct);
		
		btnDelete = new JButton("刪除後離開");
		btnDelete.setBounds(50, 310, 160, 100);
		btnDelete.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnCancel = new JButton("取消");
		btnCancel.setBounds(250, 310, 160, 100);
		btnCancel.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnCancel);
		btnCancel.addActionListener(this);
		
	}
	
	public void setData() throws Exception{
		for(Actor i : DB.getActorDB().searchActor()){
			jbAct.addItem(i.getName());
		}
	}
	
	public void delete() throws Exception{
		String actName = jbAct.getSelectedItem().toString();
		DB.getActorDB().deleteActor(actName);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			
			case"刪除後離開":
				try {
					delete();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.dispose();
				break;
			case"取消":
				this.dispose();
				break;
		}
	}

}
