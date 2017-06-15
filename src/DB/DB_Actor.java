package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Object.Actor;
import Object.time;

public class DB_Actor {
	DB_connect DB;
	Statement stmt;
	public DB_Actor(DB_connect DB){
		this.DB=DB;
		this.stmt=DB.stmt;
	}
	
	public void insertActor(String name, String birth, String sex) throws Exception{
		String sql = "INSERT INTO actor (actor_name, actor_birthday, actor_sex) VALUES ('"+name+"', '"+birth+"', "+"'"+sex+"'"+")";
		stmt.execute(sql);
	}
	
	public void deleteActor(String name)throws Exception{
		String sql = "DELETE FROM actor where actor_name='"+name+"'";
		stmt.execute(sql);
		sql = "DELETE FROM act where actor_name='"+name+"'";
		stmt.execute(sql);
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
}
