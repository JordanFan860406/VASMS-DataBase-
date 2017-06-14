package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Object.Actor;
import Object.Company;
import Object.Direct;
import Object.Movie;
import Object.time;

public class DB_Movie {
	DB_connect DB;
	Statement stmt;
	
	public DB_Movie(DB_connect DB){
		this.DB=DB;
		this.stmt=DB.stmt;
	}
	
	public void delete(Movie movie) throws SQLException{
		String sql = "DELETE FROM movie where movie_id='"+movie.getID()+"'";
		stmt.execute(sql);
		sql = "DELETE FROM genres where movie_id='"+movie.getID()+"'";
		stmt.execute(sql);
		sql = "DELETE FROM manufacture where movie_id='"+movie.getID()+"'";
		stmt.execute(sql);
		sql = "DELETE FROM direct where movie_id='"+movie.getID()+"'";
		stmt.execute(sql);
	}
	
	public void updateMovie(Movie movie) throws SQLException{
		String per= String.valueOf(movie.get());
		String time=String.valueOf(movie.getTime().getYear())+"-"+String.valueOf(movie.getTime().getMonth())+"-"+String.valueOf(movie.getTime().getDay());
		stmt.executeUpdate("UPDATE genres SET movie_genres = '"+movie.getgeners()+"' WHERE movie_id='"+movie.getID()+"';");
		stmt.executeUpdate("UPDATE manufacture SET company_name='"+movie.getCompanyName()+"' WHERE movie_id='"+movie.getID()+"';");
		stmt.executeUpdate("UPDATE movie SET title = '"+movie.getTitle()+"',per_charge='"+per+"',release_date='"+time+"' WHERE movie_id='"+movie.getID()+"';");
		stmt.executeUpdate("UPDATE direct SET director_name = '"+movie.getdirectName()+"' WHERE movie_id='"+movie.getID()+"';");
	}
	public void insertMovie(Movie movie) throws SQLException{
		String time=String.valueOf(movie.getTime().getYear())+"-"+String.valueOf(movie.getTime().getMonth())+"-"+String.valueOf(movie.getTime().getDay());
		String per= String.valueOf(movie.get());
		String sql = "INSERT INTO movie (movie_id, title,release_date, per_charge) VALUES ('"+movie.getID()+"', '"+movie.getTitle()+"', '"+time+"', '"+per+"')";
		stmt.execute(sql);
		sql = "INSERT INTO genres (movie_id, movie_genres) VALUES ('"+movie.getID()+"', '"+movie.getgeners()+"')";
		stmt.execute(sql);
		sql = "INSERT INTO manufacture (movie_id, company_name) VALUES ('"+movie.getID()+"', '"+movie.getCompanyName()+"')";
		stmt.execute(sql);
		sql = "INSERT INTO direct (movie_id, director_name) VALUES ('"+movie.getID()+"', '"+movie.getdirectName()+"')";
		stmt.execute(sql);
	}
	
	/*public  ArrayList<Movie> searchMovieNumber() throws SQLException{
		ArrayList<Movie> movieArray=new ArrayList<Movie>();
		ResultSet rs = stmt.executeQuery("SELECT * From movie");
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		int ans= Integer.parseInt(rs.getObject(0).toString());;
		while(rs.next()){
			Movie movie=new Movie();
			Company com=new Company();
			
			for(int i=1; i<=cnum; i++){
				switch(rm.getColumnName(i)){
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
	}*/
	public ArrayList<Movie> searchMovie(String name,String type,String year,String actor) throws Exception{
		ArrayList<Movie> movieArray=new ArrayList<Movie>();
		String find="SELECT * From movie natural join manufacture natural join company natural join genres natural join direct ";
		ArrayList<String> where=new ArrayList<String>();
		ArrayList<String> from=new ArrayList<String>();
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
			find+="natural join act ";
		}
		if(where.size()>0){
			find+="where ";
		}
		for(int i=0;i<where.size();i++){
			find+=where.get(i);
			if(where.size()!=i+1){
				find+="and ";
			}
				
		}
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();

		while(rs.next()){
			Movie movie=new Movie();
			Company com=new Company();
			Direct director=new Direct();
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
				case"movie_genres":
					movie.setGeners(rs.getObject(i).toString());
					break;
				case"director_name":
					director.setName(rs.getObject(i).toString());
					break;
				case"director_birthday":
					String[] str1 = rs.getObject(i).toString().split("-");
					director.setBirth(new time(Integer.valueOf(str1[0]),Integer.valueOf(str1[1]),Integer.valueOf(str1[2])));
					break;
				
				}
			
			}
			movie.setDirect(director);
			movie.setCompany(com);
			movieArray.add(movie);
		}
		
		return movieArray;
		
	}
	
	
}
