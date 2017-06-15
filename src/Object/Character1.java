package Object;

public class Character1 {
	String name;
	Actor actor;
	
	public Character1(String name,Actor actor){
		this.name=name;
		this.actor=actor;
	}
	public Character1(){
		
	}
	public Actor getActor(){
		return actor;
	}
	
	public String getRole(){
		return name;
	}
	
}
