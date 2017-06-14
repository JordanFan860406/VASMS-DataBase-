package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Object.Direct;
import Object.Movie;
import Object.member;
import Object.time;

public class DB_Member {
	DB_connect DB;
	Statement stmt;
	
	public DB_Member(DB_connect DB){
		this.DB=DB;
		this.stmt=DB.stmt;
	}
	public void updateMember(member member) throws SQLException{
		
		String time=String.valueOf(member.getBir().getYear())+"-"+String.valueOf(member.getBir().getMonth())+"-"+String.valueOf(member.getBir().getDay());
		stmt.executeUpdate("UPDATE member SET member_name = '"+member.getName()+"',member_birthday='"+time+"',member_sex='"+member.getSex()+"' WHERE member_id='"+member.getID()+"';");
	}
	public void insertMember(member member) throws SQLException{
		String time=String.valueOf(member.getBir().getYear())+"-"+String.valueOf(member.getBir().getMonth())+"-"+String.valueOf(member.getBir().getDay());
		String sql = "INSERT INTO member (member_id, member_name,member_birthday, member_sex) VALUES ('"+member.getID()+"', '"+member.getName()+"', '"+time+"', '"+member.getSex()+"')";
		stmt.execute(sql);
	}
	public void delete(member member) throws SQLException{
		String sql = "DELETE FROM member where member_id='"+member.getID()+"'";
		stmt.execute(sql);
		sql = "DELETE FROM buy where member_id='"+member.getID()+"'";
		stmt.execute(sql);
	}
	public ArrayList<member>searchAllMember(String name) throws SQLException{
		ArrayList<member> memberArray=new ArrayList<member>();
		String find="SELECT * From member ";
		if(!name.equals("")){
			find+="where member_name='"+name+"'";
		}
		ResultSet rs = stmt.executeQuery(find);
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		while(rs.next()){
			member member=new member();
			
			for(int i=1; i<=cnum; i++){
				switch(rm.getColumnName(i)){
				case"member_name":
					member.setName(rs.getObject(i).toString());
					break;
				case"member_birthday":
					String[] str = rs.getObject(i).toString().split("-");
					member.setBir(new time(Integer.valueOf(str[0]),Integer.valueOf(str[1]),Integer.valueOf(str[2])));
					break;
				case"member_sex":
					member.setsex(rs.getObject(i).toString());
					break;
				case"member_id":
					member.setID(rs.getObject(i).toString());
					break;
				}
			
			}

			memberArray.add(member);
			
		}
		
		return searchAllMember(memberArray);
	}
	public ArrayList<member>searchAllMember(ArrayList<member> memberArray) throws SQLException{
	
		ResultSet rs = stmt.executeQuery("SELECT member_name,sum(per_charge) From movie natural join buy natural join member group by member_id");
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		
		while(rs.next()){
			
			for(int i=1; i<=cnum; i+=2){
				if(rm.getColumnName(i).equals("member_name")){
					for(member j:memberArray){
						if(j.getName().equals(rs.getObject(i).toString())){
							j.setPay(rs.getObject(i+1).toString());
						}
					}
				}
	
			}
	
		}
		
		return memberArray;
	}
}
