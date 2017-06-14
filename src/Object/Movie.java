package Object;

import java.util.ArrayList;

public class Movie {
	String title;
	String ID;
	int charge;
	ArrayList<Character> actArray=new ArrayList<Character>();
	Company company;
	Direct direct;
	time publishTime;
	
	public Movie(){
		
	}
	public void setTitle(String title){
		this.title=title;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public void setcharge(int charge){
		this.charge=charge;
	}
	public void setTime(time publishTime){
		this.publishTime=publishTime;
	}
	public void setCompany(Company company){
		this.company=company;
	}
	
	public String getID(){
		return ID;
	}
	public String getTitle(){
		return title;
	}
	public String getCompanyName(){
		return company.name;
	}
	public String getCompanyAddress(){
		return company.adress;
	}
	public time getTime(){
		return publishTime;
	}
	public int get(){
		return charge;
	}
}
