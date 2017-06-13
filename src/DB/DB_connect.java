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
	
	public ArrayList<Movie> searchMovie(String name,String type,String year,String actor) throws Exception{
		ArrayList<Movie> movieArray=new ArrayList<Movie>();
		String find="SELECT * From movie natural join manufacture natural join company natural join act where ";
		
		ArrayList<String> where=new ArrayList<String>();
		if(!name.equals("")){
			where.add("title='"+name+"' ");
		}
		if(!type.equals("所有類型")){
			where.add("movie_genres='"+type+"' ");
		}
		if(!year.equals("所有年代")){
			if(year.equals("2005~2015")){
				where.add("year(release_date)<='2015' and year(release_date)>='2005' ");
			}
			else{
				where.add("year(release_date)<='"+year+"' ");
			}
		}
		if(!actor.equals("所有演員")){
			where.add("actor_name='"+actor+"'");
		}
		for(int i=0;i<where.size();i++){
			find+=where.get(i);
			if(where.size()!=i+1){
				find+="and ";
			}
				
		}
		System.out.println(find);
		ResultSet rs = stmt.executeQuery(find);
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
	public ArrayList<String> searchGenres() throws Exception{
		ArrayList<String> genresArray=new ArrayList<String>();
		ResultSet rs = stmt.executeQuery("SELECT movie_genres From genres GROUP BY movie_genres HAVING count(movie_genres)>=1");
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		
		while(rs.next()){
			for(int i=1; i<=cnum; i++){
				genresArray.add(rs.getObject(i).toString());
			}
		}
		return genresArray;
	}
	public ArrayList<Actor> searchActor() throws SQLException{
		ArrayList<Actor> actorArray=new ArrayList<Actor>();
		ResultSet rs = stmt.executeQuery("SELECT * From actor");
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		
		while(rs.next()){
			Actor actor=new Actor();
			
			for(int i=1; i<=cnum; i++){
				switch(rm.getColumnName(i)){
				case"actor_sex":
					actor.setSex(rs.getObject(i).toString());
					break;
				case"actor_name":
					actor.setName(rs.getObject(i).toString());
					break;
				case"release_date":
					String[] str = rs.getObject(i).toString().split("-");
					actor.setBirthday(new time(Integer.valueOf(str[0]),Integer.valueOf(str[1]),Integer.valueOf(str[2])));
					break;
				}
			
			}

			actorArray.add(actor);
		}
		
		return actorArray;
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
