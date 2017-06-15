package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DB.DB_connect;

public class ActorInsert extends JFrame implements ActionListener {
	JLabel lbName = new JLabel("演員名字:");
	JLabel lbBirth = new JLabel("演員生日:");
	JLabel lbSex = new JLabel("演員性別:");
	JLabel lbFrame = new JLabel("演員資料新增");
	JTextField tfName ;
	JComboBox jbYear;
	JComboBox jbMonth;
	JComboBox jbDay;
	JComboBox jbSex;
	String [] year ={"年份"};
	String [] month = {"月份"};
	String [] day ={"日"};
	String [] sex ={"性別", "男", "女"};
	JButton btnInsert;
	JButton btnCancel;
	DB_connect DB;
	
	public ActorInsert(DB_connect DB){
		this.DB = DB;
		initialize();
		setData();
	}
	
	void initialize(){
		this.setBounds(100,100,500,700);
		this.setLayout(null);
		this.setVisible(true);
		
		lbFrame.setFont(new Font("新細明體",Font.PLAIN ,32));
		lbFrame.setBounds(135, 10,200,50);
		this.add(lbFrame);
		
		lbName.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbName.setBounds(10, 110,150,50);
		this.add(lbName);
		
		tfName = new JTextField();
		tfName.setBounds(130, 110, 250,50);
		tfName.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(tfName);
		
		lbBirth.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbBirth.setBounds(10, 210,150,50);
		this.add(lbBirth);
		
		jbYear = new JComboBox(year);
		jbYear.setBounds(120, 210, 80, 50);
		jbYear.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbYear);
		
		JLabel lbYear = new JLabel("年");
		lbYear.setBounds(210, 210, 80, 50);
		lbYear.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(lbYear);
		
		jbMonth = new JComboBox(month);
		jbMonth.setBounds(240, 210, 80, 50);
		jbMonth.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbMonth);
		
		JLabel lbMonth = new JLabel("月");
		lbMonth.setBounds(325, 210, 50, 50);
		lbMonth.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(lbMonth);
		
		jbDay = new JComboBox(day);
		jbDay.setBounds(360, 210, 80, 50);
		jbDay.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(jbDay);
		
		JLabel lbDay = new JLabel("日");
		lbDay.setBounds(450, 210, 50, 50);
		lbDay.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(lbDay);
		
		lbSex.setFont(new Font("新細明體",Font.PLAIN ,24));
		lbSex.setBounds(10, 310,150,50);
		this.add(lbSex);
		
		jbSex = new JComboBox(sex);
		jbSex.setFont(new Font("新細明體",Font.PLAIN ,24));
		jbSex.setBounds(120, 310, 80, 60);
		this.add(jbSex);
		
		btnInsert = new JButton("儲存後離開");
		btnInsert.setBounds(50, 450, 160, 100);
		btnInsert.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnInsert);
		btnInsert.addActionListener(this);
		
		btnCancel = new JButton("取消");
		btnCancel.setBounds(250, 450, 160, 100);
		btnCancel.setFont(new Font("新細明體",Font.PLAIN ,24));
		this.add(btnCancel);
		btnCancel.addActionListener(this);
	}
	
	public void setData(){
		for(int i=1911 ; i<=2017 ; i++){
			String year = "" + i;
			jbYear.addItem(year);
		}
		for(int j=1 ; j<=12 ; j++){
			String month = "" + j;
			jbMonth.addItem(month);
		}
		for(int k=1 ; k<=31 ; k++){
			String day = "" + k;
			jbDay.addItem(day);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case"儲存後離開":
				this.dispose();
				break;
			case"取消":
				this.dispose();
				break;
		}
	}

}
