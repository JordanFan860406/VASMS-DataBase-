package GUI;

import javax.swing.JPanel;
import DB.DB_connect;

public class OtherPanel extends JPanel{
	DB_connect DB;
	public OtherPanel(DB_connect DB){
		this.DB = DB;
		initialize();
	}
	
	void initialize(){
		
	}
}
