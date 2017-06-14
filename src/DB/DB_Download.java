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
//			System.out.print(resultS);
		}
		resultList.add(resultS);
//		System.out.println("");
		}
		return resultList;
		}

	public ArrayList <String> downloadName(String movieTitle) throws Exception{
		ArrayList <String> resultArr = new ArrayList<String>();
		String []items = {"","會員姓名", "下載日期"};
		String find = "Select member_name, download_date From buy natural join movie natural join member where title='"+movieTitle+"'";
		ResultSet rs = stmt.executeQuery(find);
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
}
