package Object;

import java.util.ArrayList;

public class Buy {
	Movie mv;
	String month;
	String year;
	String type;
	String count;
	String member;
	String title;
	ArrayList<MemberBuy>buyList = new ArrayList<MemberBuy>();
	
	
	
	public Buy(String title, String year, String count, ArrayList<MemberBuy>buyList){
		this.title = title;
		this.year = year;
		this.count = count;
		this.buyList = buyList;
	}
	
	public Buy(String type, String month, ArrayList<MemberBuy>buyList, String count){
		this.type = type;
		this.month = month;
		this.count = count;
		this.buyList = buyList;
	}
	
	public void setBuyList(MemberBuy buy){
		buyList.add(buy);
	}
	
	
	
	public ArrayList<MemberBuy> getBuyList(){
		return buyList;
	}
	
	
	public String getType(){
		return type;
	}
	public String getmemName(){
		return member;
	}
	
	public String getmvName(){
		return title;
	}
	

	public void setCount(String time){
		this.count = time;
	}
	
	public String getCount(){
		return count;
		
	}
	
	public String getMonth(){
		return month;
	}
	
	public String getYear(){
		return year;
	}
	
	
}
