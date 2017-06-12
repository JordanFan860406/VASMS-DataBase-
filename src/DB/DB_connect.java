package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import Object.Company;
import Object.Movie;
import Object.time;

public class DB_connect {
	Statement stmt;
	
	public DB_connect() throws Exception{
		connect();
	}
	
	void connect()throws Exception{
		String CONNECTION = "jdbc:mariadb://140.127.74.210:3306/410477025";
		Class.forName("org.mariadb.jdbc.Driver");
		Properties p = new Properties();
	    p.put("user","410477025");
	    p.put("password","rua01");
		Connection testCon = DriverManager.getConnection(CONNECTION, p);
		stmt = testCon.createStatement();
	}
	
	public ArrayList<Movie> searchMovie() throws Exception{
		ArrayList<Movie> movieArray=new ArrayList<Movie>();
		ResultSet rs = stmt.executeQuery("SELECT * From movie natural join manufacture natural join company ");
		ResultSetMetaData rm = rs.getMetaData();
		
		int cnum = rm.getColumnCount();

		while(rs.next()){
			Movie movie=new Movie();
			Company com=new Company();
			
			for(int i=1; i<=cnum; i++){
				switch(rm.getColumnName(i)){
				case"company_name":
					com.setName(rs.getObject(i).toString());
					break;
				case"address":
					com.setAdress(rs.getObject(i).toString());
					break;
				case"movie_id":
					movie.setID(rs.getObject(i).toString());
					break;
				case"title":
					movie.setTitle(rs.getObject(i).toString());
					break;
				case"release_date":
					String[] str = rs.getObject(i).toString().split("-");
					movie.setTime(new time(Integer.valueOf(str[0]),Integer.valueOf(str[1]),Integer.valueOf(str[2])));
					break;
				case"per_charge":
					movie.setcharge(Integer.valueOf(rs.getObject(i).toString()));
					break;
				}
			
			}
			movie.setCompany(com);
			movieArray.add(movie);
		}
		
		return movieArray;
		
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String CONNECTION = "jdbc:mariadb://140.127.74.210:3306/410477025";
		Class.forName("org.mariadb.jdbc.Driver");
		Properties p = new Properties();
	    p.put("user","410477025");
	    p.put("password","rua01");
		Connection testCon = DriverManager.getConnection(CONNECTION, p);
		Statement stmt = testCon.createStatement();
		//stmt.executeUpdate("INSERT INTO  manufacture ('mv00004', '漫威工作室')");
//stmt.executeUpdate("INSERT INTO member VALUES ('mem00002', 'Jordan Fan', '1997-8-9', 'boy')");
		ResultSet rs = stmt.executeQuery("SELECT * From movie natural join manufacture natural join company");
		ResultSetMetaData rm = rs.getMetaData();

		int cnum = rm.getColumnCount();

		while(rs.next()){
			for(int i=1; i<=cnum; i++){
				if(rm.getColumnName(i).equals("release_date")){
					String[] str = rs.getObject(i).toString().split("-");
					System.out.print(str[0]+" "+str[1]+" "+str[2]);
				}
				System.out.print(rm.getColumnName(i)+":"+rs.getObject(i)+" ");
				
			}
			System.out.println("");
		}
		
		stmt.close();
		testCon.close();
	}
}
