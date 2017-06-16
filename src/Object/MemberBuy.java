package Object;

public class MemberBuy {
	String memName;
	String Year;
	String month;
	String day;
	String strDate;
	
	public MemberBuy(String memberName, String date){
		this.strDate = date;
		this.memName = memberName;
	}
	
	public String getStrDate(){
		return strDate;
	}
	
	
	public void setMember(String memName, String date){
		this.memName = memName;
	}
	
	public String getMember(){
		return memName;
	}
	
	public void setDate(String year, String month, String day){
		this.Year = year;
		this.month = month;
		this.day = day;
	}
	
	public String getDate(){
		return Year +"-" + month + "-" + day;
	}
}
