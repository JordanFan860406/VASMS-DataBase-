package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class DB_Geners {
	DB_connect DB;
	Statement stmt;
	public DB_Geners(DB_connect DB){
		this.DB=DB;
		this.stmt=DB.stmt;
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
}
