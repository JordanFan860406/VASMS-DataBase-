package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Object.Actor;
import Object.Character1;
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
	
	public void updateMovie(Movie movie,boolean d,boolean c) throws SQLException{
		String per= String.valueOf(movie.get());
		String time=String.valueOf(movie.getTime().getYear())+"-"+String.valueOf(movie.getTime().getMonth())+"-"+String.valueOf(movie.getTime().getDay());
		stmt.executeUpdate("UPDATE genres SET movie_genres = '"+movie.getgeners()+"' WHERE movie_id='"+movie.getID()+"';");
		if(c){
			stmt.executeUpdate("UPDATE manufacture SET company_name='"+movie.getCompanyName()+"' WHERE movie_id='"+movie.getID()+"';");
		}
		else{
			stmt.execute("INSERT INTO manufacture (movie_id, company_name) VALUES ('"+movie.getID()+"', '"+movie.getCompanyName()+"')");
		}
		if(d){
			stmt.executeUpdate("UPDATE direct SET director_name = '"+movie.getdirectName()+"' WHERE movie_id='"+movie.getID()+"';");
		}
		else{
			stmt.execute("INSERT INTO direct (movie_id, director_name) VALUES ('"+movie.getID()+"', '"+movie.getdirectName()+"')");
		}
		stmt.executeUpdate("UPDATE movie SET title = '"+movie.getTitle()+"',per_charge='"+per+"',release_date='"+time+"' WHERE movie_id='"+movie.getID()+"';");
		
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
	//natural join manufacture natural join company
	public int countMovie() throws SQLException{
		String find="SELECT count(movie_id) From movie ";
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		return Integer.valueOf(rs.getObject(1).toString());
	}
	public ArrayList<Movie> searchMovie(String name,String type,String year,String actor) throws Exception{
		ArrayList<Movie> movieArray=new ArrayList<Movie>();
		String find="SELECT * From movie  natural join genres ";
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
			Direct director=new Direct();
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
				case"movie_genres":
					movie.setGeners(rs.getObject(i).toString());
					break;
				}
			
			}
			movie.setDirect(director);
			movieArray.add(searchAct(movie));
		}
		find="SELECT movie_id,company_name,address From manufacture natural join company ";
		rs = stmt.executeQuery(find);
		rm = rs.getMetaData();
		cnum = rm.getColumnCount();
		ArrayList<Movie> movieArray1=new ArrayList<Movie>();
		while(rs.next()){
			Company com=new Company();
			for(Movie i:movieArray){
				if(i.getID().equals(rs.getObject(1).toString())){
						com.setName(rs.getObject(2).toString());
						com.setAdress(rs.getObject(3).toString());
						i.setCompany(com);
						movieArray1.add(i);
						movieArray.remove(i);
						//System.out.println(i.getTitle()+":"+rs.getObject(2).toString());
						break;
				}
			
			}
			
		}
		for(Movie i:movieArray){
			Company com=new Company();
			i.setCompany(com);
			movieArray1.add(i);
		}
		return searchDirect(movieArray1);
	}
	public ArrayList<Movie> searchDirect(ArrayList<Movie> movieArray) throws SQLException{
		String find="SELECT movie_id,director_name From direct ";
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		ArrayList<Movie> movieArray1=new ArrayList<Movie>();
		while(rs.next()){
			Direct Direct=new Direct();
			for(Movie i:movieArray){
				if(i.getID().equals(rs.getObject(1).toString())){
					Direct.setName(rs.getObject(2).toString());
						i.setDirect(Direct);
						movieArray1.add(i);
						movieArray.remove(i);
						break;
				}
			
			}
			
		}
		for(Movie i:movieArray){
			Direct direct=new Direct();
			i.setDirect(direct);;
			movieArray1.add(i);
		}
		return movieArray1;
	}
	public Movie searchAct(Movie movie) throws SQLException{
		String find="SELECT actor_name,role From act natural join actor where movie_id='"+movie.getID()+"'";
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		while(rs.next()){
			Actor actor=new Actor();
			actor.setName(rs.getObject(1).toString());
			Character1 Character=new Character1(rs.getObject(2).toString(),actor);
			movie.setCharacter(Character);
			
		}
		return movie;
	}
	
	
}
