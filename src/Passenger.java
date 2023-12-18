
public class Passenger {
	static int id=1;
	String name;
	int age;
	String berthPref;
	int passId;
	String alloted;
	int seatNum;
	Passenger(String name,int age,String berthPref){
		this.name=name;
		this.age=age;
		this.berthPref=berthPref;
		this.passId=id++;
		alloted="";
		seatNum=-1;
	}
}
