package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DB.DB_connect;
import Object.Direct;

public class DirectorDelete extends JFrame implements ActionListener{
	JLabel lbFrame = new JLabel("刪除導演資料");
	JLabel lbDir = new JLabel("導演名稱:");
	JComboBox jbDir ;
	String[]dir = {"導演"};
	JButton btnDelete;
	JButton btnCancel;
	DB_connect DB;
	
	public DirectorDelete(DB_connect DB) throws Exception{
		this.DB = DB;
		initialize();
		setData();
	}
	
	void initialize(){
		this.setBounds(100,100,500,500);
		this.setLayout(null);
		this.setVisible(true);
		
		lbFrame.setFont(new Font("新細明體",Font.PLAIN ,32));
		lbFrame.setBounds(135, 10,200,50);
		this.add(lbFrame);
		
		lbDir.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbDir.setBounds(10, 110,150,50);
		this.add(lbDir);
		
		jbDir = new JComboBox(dir);
		jbDir.setBounds(130, 110, 180,50);
		jbDir.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbDir);
		
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
		for(Direct i : DB.getDictorDB().searchAllDirector()){
			jbDir.addItem(i.getName());
		}
	}
	public void delete() throws SQLException{
		for(Direct i : DB.getDictorDB().searchAllDirector()){
			if(i.getName().equals(jbDir.getSelectedItem().toString())){
				DB.getDictorDB().delete(i);
			}
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		
		case"刪除後離開":
			
			this.dispose();
			break;
		case"取消":
			this.dispose();
			break;
	}
	}

}
