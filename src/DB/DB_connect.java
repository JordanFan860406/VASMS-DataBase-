package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import Object.Actor;
import Object.Company;
import Object.Movie;
import Object.time;

public class DB_connect {
	Statement stmt;
	DB_Movie MovieDB;
	DB_Actor ActorDB;
	DB_Geners GenresDB;
	DB_Download DownloadDB;
	DB_Company ComanyDB;
	DB_Director DictorDB;
	DB_Member MemberDB;
	public DB_connect() throws Exception{
		connect();
		this.MovieDB=new DB_Movie(this);
		this.ActorDB=new DB_Actor(this);
		this.GenresDB=new DB_Geners(this);
		this.ComanyDB=new DB_Company(this);
		this.DownloadDB = new DB_Download(this);
		this.DictorDB = new DB_Director(this);
		MemberDB=new DB_Member(this);
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
	public DB_Movie getMovieDB(){
		
		return MovieDB;
	}
	public DB_Actor getActorDB(){
		
		return ActorDB;
	}
	public DB_Geners getGenresDB(){
		
		return GenresDB;
	}
	public DB_Company getCompanyDB(){
		
		return ComanyDB;
	}
	
	public DB_Download getDownloadDB(){
		
		return DownloadDB;
	}
	public DB_Director getDictorDB(){
		
		return DictorDB;
	}
	public DB_Member getMemberDB(){
		
		return MemberDB;
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
		String a="where ";
		String b="Lady Gaga";
		ResultSet rs = stmt.executeQuery("SELECT member_name,sum(per_charge) From movie natural join buy natural join member group by member_id");
		ResultSetMetaData rm = rs.getMetaData();
		//String sql = "INSERT INTO manufacture (movie_id, company_name) VALUES ('mv00020', 'Jang')";
		//stmt.execute(sql);
		//stmt.executeUpdate("UPDATE movie SET release_date='2000'  WHERE movie_id='mv00019';");
		int cnum = rm.getColumnCount();
		//stmt.executeUpdate("UPDATE movie SET release_date='"+time+"' WHERE movie_id='"+movie.getID()+"'");
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
