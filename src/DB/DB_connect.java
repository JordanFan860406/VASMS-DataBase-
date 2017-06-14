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
	
	public DB_connect() throws Exception{
		connect();
		this.MovieDB=new DB_Movie(this);
		this.ActorDB=new DB_Actor(this);
		this.GenresDB=new DB_Geners(this);
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

	public ArrayList<String> searchDownload(String year, String month, String type) throws Exception{
		//stmt.executeUpdate("INSERT INTO member VALUES ('mem00020', 'Joker Fan', '1997-8-9', 'boy')");
		String [] arr = {"","電影名稱", "下載次數"};
		ArrayList<String> resultList = new ArrayList<String>();
		ArrayList<String> where = new ArrayList<String>();
		String find = "Select title, count(buy.movie_id) as downloadtime From movie natural join buy natural join genres ";
		if(!year.equals("選擇年份") ){
			where.add("year(download_date)='" +  year + "' ");
		}
		if(!month.equals("選擇月份")){
			where.add("month(download_date)='" + month + "' ");
		}
		if(!type.equals("選擇類型")){
			where.add("movie_genres='" + type + "' ");
		}
		if(where.size()>0){
			find += "where ";
		}
		for(int i=0;i<where.size();i++){
			find+=where.get(i);
			if(where.size()!=i+1){
				find+="and ";
			}
		}
		find +="group by title order by downloadtime desc";
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();

		int cnum = rm.getColumnCount();

		while(rs.next())
		{
			String resultS = "";
		for(int i=1; i<=cnum; i++)
		{	
			resultS += arr[i]+" : "+rs.getObject(i) + "  ";
			System.out.print(resultS);
		}
		resultList.add(resultS);
		System.out.println("");
		}
		return resultList;
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
		ResultSet rs = stmt.executeQuery("SELECT * From movie natural join manufacture natural join company natural join act natural join genres where movie_genres='科幻'");
		ResultSetMetaData rm = rs.getMetaData();
		//stmt.executeUpdate("UPDATE movie SET release_date='2000'  WHERE movie_id='mv00019';");
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
