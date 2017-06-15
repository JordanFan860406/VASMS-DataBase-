package DB;
import java.sql.*;
import java.util.ArrayList;
public class DB_Download {
	DB_connect DB;
	Statement stmt;
	
	public DB_Download(DB_connect DB){
		this.DB = DB;
		this.stmt = DB.stmt;
	}
	
	public ArrayList<String> searchDownload(String year, boolean month, String type) throws Exception{
		String [] arr = {"","類型", "月份", "下載次數"};
		String [] arr2 = {"", "電影名稱", "年份", "下載次數"};
		String [] check = null;
		ArrayList<String> resultList = new ArrayList<String>();
		ArrayList<String> where = new ArrayList<String>();
		String find = "Select title, year(download_date), count(buy.movie_id) as downloadtime From movie natural join buy natural join genres ";
		String findcheck = null;
		String findMon = "Select movie_genres, month(download_date) ,count(buy.movie_id) as downloadtime From movie natural join buy natural join genres ";		
		if( month== true){
			
			if(!year.equals("所有年份") ){
				where.add("year(download_date)='" +  year + "' ");
			}
	
			if(!type.equals("所有類型")){
				where.add("movie_genres='" + type + "' ");
			}
			if(where.size()>0){
				findMon += "where ";
			}
			for(int i=0;i<where.size();i++){
				findMon+=where.get(i);
				if(where.size()!=i+1){
					findMon+="and ";
				}
			}
			findMon+="group by movie_genres, month(download_date) order by downloadtime desc";
			findcheck = findMon;
			check = arr;
		}
		else if(month == false){
			if(!year.equals("所有年份") ){
				where.add("year(download_date)='" +  year + "' ");
			}
	
			if(!type.equals("所有類型")){
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
			find+="group by movie_genres, month(download_date) order by downloadtime desc";
			findcheck = find;
			check = arr2;
		}
		
			ResultSet rs = stmt.executeQuery(findcheck);
			ResultSetMetaData rm = rs.getMetaData();
	
			int cnum = rm.getColumnCount();
	
			while(rs.next())
			{
				String resultS = "";
			for(int i=1; i<=cnum; i++)
			{	
				resultS += check[i]+" : "+rs.getObject(i) + "  ";
	//			System.out.print(resultS);
			}
			resultList.add(resultS);
	//		System.out.println("");
			}
			return resultList;
			
		}

	public ArrayList <String> downloadName(String movieTitle, String year, boolean check) throws Exception{
		ArrayList <String> resultArr = new ArrayList<String>();
		String []items = {"","會員姓名", "下載日期"};
		String find = "Select member_name, download_date From buy natural join movie natural join member natural join genres where movie_genres='"+movieTitle+"' and month(download_date)='" + year + "'";
		String findln = "Select member_name, download_date From buy natural join movie natural join member where title='"+movieTitle+"' and year(download_date)='" + year + "'";
		String findCheck = null;
		if(check == true){
			findCheck = find;
		}
		else if(check == false){
			findCheck = findln;
		}
		
		ResultSet rs = stmt.executeQuery(findCheck);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();

		while(rs.next())
		{
			String resultS = "";
		for(int i=1; i<=cnum; i++)
		{	
			resultS += items[i]+" : "+rs.getObject(i) + "  ";
			
		}
		resultArr.add(resultS);
		System.out.println("");
		}
		return resultArr;
	}
	
	public ArrayList<String> searchID(String name, String mvTitle) throws Exception{
		ArrayList<String>conList = new ArrayList<String>();
		String findId = "Select member_id From member where member_name='"+ name +"'";
//		String findMv = "Select movie_id From movie ntural join buy where download_date='"+ mvTitle +"'";
		String[]temp = {findId};
		for(int i=0 ; i<temp.length ; i++){
			ResultSet rs = stmt.executeQuery(temp[i]);
			ResultSetMetaData rm = rs.getMetaData();
			int cnum = rm.getColumnCount();
	
			while(rs.next())
			{
				String resultS = "";
			for(int j=1; j<=cnum; j++)
			{	
				conList.add(rs.getObject(j).toString());
			}
			}
		}
		return conList;
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
