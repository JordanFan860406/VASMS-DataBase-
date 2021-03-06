package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Object.Actor;
import Object.Company;
import Object.time;

public class DB_Company {
	DB_connect DB;
	Statement stmt;
	
	public DB_Company(DB_connect DB){
		this.DB=DB;
		this.stmt=DB.stmt;
	}
	
	public void insertCompany(Company com)throws Exception{
		String company = com.getName();
		String address = com.getAddress();
		String sql = "INSERT INTO company (company_name, address) VALUES ('"+company+"', '"+address+"'"+")";
		stmt.execute(sql);
	}
	
	public void deleteCompany(Company com)throws Exception{
		String company = com.getName();
		String sql = "DELETE FROM company where company_name='"+company+"'";
		stmt.execute(sql);
		sql = "DELETE FROM manufacture where company_name='"+company+"'";
		stmt.execute(sql);
	}
	
	public ArrayList<Company>searchAllCom() throws SQLException{
		ArrayList<Company> companyArray=new ArrayList<Company>();
		ResultSet rs = stmt.executeQuery("SELECT * From company");
		ResultSetMetaData rm = rs.getMetaData();
		int cnum = rm.getColumnCount();
		
		while(rs.next()){
			Company company=new Company();
			
			for(int i=1; i<=cnum; i++){
				switch(rm.getColumnName(i)){
				case"company_name":
					company.setName(rs.getObject(i).toString());
					break;
				case"address":
					company.setAdress(rs.getObject(i).toString());;
					break;
				}
			
			}

			companyArray.add(company);
		}
		
		return companyArray;
	}
}
