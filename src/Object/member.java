package Object;

public class member {
	String name;
	String ID;
	time birthday;
	int pay=0;
	
	
	
	public member(){
		
	}
	public member(String name,String ID,time birthday){
		this.name=name;
		this.ID=ID;
		this.birthday=birthday;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public void setID(String ID){
		this.ID=ID;
	}
	public void setID(time birthday){
		this.birthday=birthday;
	}
	public void setPay(int pay){
		this.pay=pay;
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
}
