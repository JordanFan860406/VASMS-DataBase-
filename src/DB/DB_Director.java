package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Object.Company;
import Object.Direct;
import Object.time;

public class DB_Director {
	DB_connect DB;
	Statement stmt;
	
	public DB_Director(DB_connect DB){
		this.DB=DB;
		this.stmt=DB.stmt;
	}
	public ArrayList<Direct>searchAllDirector() throws SQLException{
		ArrayList<Direct> DirectArray=new ArrayList<Direct>();
		ResultSet rs = stmt.executeQuery("SELECT * From director");
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		
		while(rs.next()){
			Direct director=new Direct();
			
			for(int i=1; i<=cnum; i++){
				switch(rm.getColumnName(i)){
				case"director_name":
					director.setName(rs.getObject(i).toString());
					break;
				case"director_birthday":
					String[] str = rs.getObject(i).toString().split("-");
					director.setBirth(new time(Integer.valueOf(str[0]),Integer.valueOf(str[1]),Integer.valueOf(str[2])));
					break;
				}
			
			}

			DirectArray.add(director);
			
		}
		
		return DirectArray;
	}
}
