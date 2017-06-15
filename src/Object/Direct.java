package Object;



public class Direct {
	String name;
	time birthday;
	String sex;
	public Direct(){
		
	}
	public Direct(String name,time birthday){
		this.name=name;
		this.birthday=birthday;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public void setBirth(time birthday){
		this.birthday=birthday;
	}
	public String getName(){
		return name;
	}
	public time getTime(){
		return birthday ;
	}
	public String getSex(){
		return sex;
	}
}
