package Object;

import java.util.ArrayList;

public class Movie {
	String title;
	String ID;
	int charge;
	ArrayList<Character1> actArray=new ArrayList<Character1>();
	Company company;
	Direct direct;
	time publishTime;
	String geners;
	
	public Movie(){
		
	}
	public void setTitle(String title){
		this.title=title;
	}
	public void setGeners(String geners){
		this.geners=geners;
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
	public void setDirect(Direct direct){
		this.direct=direct;
	}
	public void setCharacter(Character1 Character){
		
		actArray.add(Character);
	}
	
	
	public String getID(){
		return ID;
	}
	public String getgeners(){
		return geners;
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
	public String getdirectName(){
		return direct.getName();
	}
	public time getdirectBir(){
		return direct.birthday;
	}
	public ArrayList<Character1> getCharacter(){
		return actArray;
	}
}
