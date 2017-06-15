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
		String CONNECTION = "jdbc:mariadb://140.127.74.226:3306/410477025";
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

}
