package DB;
import java.sql.*;
import java.util.ArrayList;

import Object.Buy;
import Object.MemberBuy;
public class DB_Download {
	DB_connect DB;
	Statement stmt;
	
	public DB_Download(DB_connect DB){
		this.DB = DB;
		this.stmt = DB.stmt;
	}
	
	public ArrayList<Buy> searchDownloadYear(String year, String type) throws Exception{
		ArrayList<Buy>buyList = new ArrayList<Buy>();
		String find = "SELECT title, year(download_date), count(buy.movie_id) From movie natural join buy natural join member natural join genres ";
		ArrayList<String> where=new ArrayList<String>();
		ArrayList<String> from=new ArrayList<String>();
		if(!year.equals("所有年份")){
			where.add("year(download_date)='"+year+"' ");
		}
		if(!type.equals("所有類型")){
			where.add("movie_genres='"+type+"' ");
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
		find += "group by title, year(download_date)  Order by count(buy.movie_id) desc";
		System.out.println(find);
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();

		while(rs.next())
		{
			String resultS = "";
		for(int i=1; i<=cnum; i+=3)
		{	
			ArrayList <MemberBuy>list = downloadName(rs.getObject(i).toString(), rs.getObject(i+1).toString());
			buyList.add(new Buy(rs.getObject(i).toString(), rs.getObject(i+1).toString(), rs.getObject(i+2).toString(), list));
		}
		System.out.println("");
		}
		
		return buyList;
	
	}
	
	public ArrayList<Buy> searchDownloadMon(String year, String type) throws Exception{
		ArrayList<Buy>buyList = new ArrayList<Buy>();
		String find = "SELECT movie_genres, month(download_date) as month, count(buy.movie_id) From movie natural join buy natural join member natural join genres ";
		ArrayList<String> where=new ArrayList<String>();
		ArrayList<String> from=new ArrayList<String>();
		if(!year.equals("所有年份")){
			where.add("year(download_date)='"+year+"' ");
		}
		if(!type.equals("所有類型")){
			where.add("movie_genres='"+type+"' ");
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
		find += "group by movie_genres , month Order by count(buy.movie_id) desc";
		System.out.println(find);
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();

		while(rs.next())
		{
			String resultS = "";
		for(int i=1; i<=cnum; i+=3)
		{	
			ArrayList <MemberBuy>list = downloadName2(rs.getObject(i).toString(), rs.getObject(i+1).toString(), year);
			buyList.add(new Buy(rs.getObject(i).toString(), rs.getObject(i+1).toString(), list, rs.getObject(i+2).toString()));
		}
		System.out.println("");
		}
		
		return buyList;
	
	}
	
	public ArrayList <MemberBuy> downloadName(String movieTitle, String year) throws Exception{
		ArrayList <MemberBuy> resultArr = new ArrayList<MemberBuy>();
		String find = "SELECT member_name, download_date From movie natural join buy natural join member natural join genres where title='"+movieTitle+"' and year(download_date)='"+year+"'";
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();

		while(rs.next())
		{
			String resultS = "";
		for(int i=1; i<=cnum; i+=2)
		{	
			resultArr.add(new MemberBuy(rs.getObject(i).toString(), rs.getObject(i+1).toString()));
			
		}
		System.out.println("");
		}
		return resultArr;
	}
	
	public ArrayList <MemberBuy> downloadName2(String type, String month, String year) throws Exception{
		ArrayList <MemberBuy> resultArr = new ArrayList<MemberBuy>();
		String find = "SELECT member_name, download_date From movie natural join buy natural join member natural join genres where movie_genres='"+type+"' and month(download_date)='"+month+"' ";
		if(!year.equals("所有年份"))
			find +="and year(download_date)='"+year+"'";
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();

		while(rs.next())
		{
			String resultS = "";
		for(int i=1; i<=cnum; i+=2)
		{	
			resultArr.add(new MemberBuy(rs.getObject(i).toString(), rs.getObject(i+1).toString()));
			
		}
		System.out.println("");
		}
		return resultArr;
	}
	
	public void insertDownload(String memId, String mvId, String date) throws Exception{
		String sql = "INSERT INTO buy (member_id, movie_id, download_date) VALUES ('"+memId+"', '"+mvId+"', '"+ date +"')";
		stmt.executeUpdate(sql);
	}
	
	public void deleteDownLoad(String memId, String Date)throws Exception{
		String sql = "DELETE FROM buy where member_id='" + memId +"' and download_date='" + Date +"'";
		stmt.executeQuery(sql);
	}
}
