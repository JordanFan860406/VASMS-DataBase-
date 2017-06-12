package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

public class DB_connect {
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String CONNECTION = "jdbc:mariadb://140.127.74.210:3306/410477025";
		Class.forName("org.mariadb.jdbc.Driver");
		Properties p = new Properties();
	    p.put("user","410477025");
	    p.put("password","rua01");
		Connection testCon = DriverManager.getConnection(CONNECTION, p);
		Statement stmt = testCon.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT company_name, address From movie natural join manufacture natural join company where title = '海角七號'");
		ResultSetMetaData rm = rs.getMetaData();

		int cnum = rm.getColumnCount();

		while(rs.next())
		{
		for(int i=1; i<=cnum; i++)
		{
		System.out.print(rm.getColumnName(i)+":"+rs.getObject(i)+" ");
		}
		System.out.println("");
		}
		//stmt.executeUpdate("INSERT INTO manufacture VALUES ('mv00004', '漫威工作室')");
		//stmt.executeUpdate("INSERT INTO member VALUES ('mem00002', 'Jordan Fan', '1997-8-9', 'boy')");
		
	}
}
