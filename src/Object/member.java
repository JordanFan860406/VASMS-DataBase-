package Object;

public class member {
	String name;
	String ID;
	time birthday;
	String pay="0";
	String sex;
	
	
	public member(){
		
	}
	public member(String name,String ID,time birthday){
		this.name=name;
		this.ID=ID;
		this.birthday=birthday;
	}
	
	public void setsex(String sex){
		this.sex=sex;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public void setBir(time birthday){
		this.birthday=birthday;
	}
	public void setPay(String pay){
		this.pay=pay;
	}
	public String  getSex(){
		return sex;
	}
	public String  getName(){
		return name;
	}
	public String  getID(){
		return ID;
	}
	public time  getBir(){
		return birthday;
	}
	public String  getPay(){
		return pay;
	}
}
