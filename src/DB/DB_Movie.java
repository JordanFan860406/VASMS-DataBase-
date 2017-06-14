package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Object.Actor;
import Object.Company;
import Object.Movie;
import Object.time;

public class DB_Movie {
	DB_connect DB;
	Statement stmt;
	
	public DB_Movie(DB_connect DB){
		this.DB=DB;
		this.stmt=DB.stmt;
	}
	
	
	
	public void updateMovie(Movie movie) throws SQLException{
		String per= String.valueOf(movie.get());
		stmt.executeUpdate("UPDATE movie SET title = '"+movie.getTitle()+"',per_charge='"+per+"' WHERE movie_id='"+movie.getID()+"';");
	}
	
	
	public ArrayList<Movie> searchMovie(String name,String type,String year,String actor) throws Exception{
		ArrayList<Movie> movieArray=new ArrayList<Movie>();
		String find="SELECT * From movie natural join manufacture natural join company natural join act natural join genres ";
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
	
	
}