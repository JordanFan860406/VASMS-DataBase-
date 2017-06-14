package Object;



public class Direct {
	String name;
	time birthday;
	
	public Direct(){
		
	}
	public Direct(String name,time birthday){
		this.name=name;
		this.birthday=birthday;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setBirth(time birthday){
		this.birthday=birthday;
	}
	public String getName(){
		return name;
	}
}
