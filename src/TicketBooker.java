import java.util.*;
public class TicketBooker {
	//available berths
	static int avaiLow=1;
	static int avaiMid=1;
	static int avaiUp=1;
	static int avaiRac=1;
	static int avaiWait=1;
	
	static Queue<Integer> waitList=new LinkedList<>();
	static Queue<Integer> racList=new LinkedList<>();
	static List<Integer> bookedList=new ArrayList<>();
	
	static List<Integer> lbPos=new ArrayList<>(Arrays.asList(1));
	static List<Integer> mbPos=new ArrayList<>(Arrays.asList(1));
	static List<Integer> ubPos=new ArrayList<>(Arrays.asList(1));
	static List<Integer> racPos=new ArrayList<>(Arrays.asList(1));
	static List<Integer> waitPos=new ArrayList<>(Arrays.asList(1));
	
	static Map<Integer,Passenger> passengers=new HashMap<>();//map to id and passenger in passengers class
	
	public void bookTicket(Passenger p,int berthInfo,String allotedBerth) {
		p.seatNum=berthInfo;
		p.alloted=allotedBerth;
		passengers.put(p.passId, p);
		bookedList.add(p.passId);
		System.out.println(".......Booked Successfully....");
	}
	
	public void bookRAC(Passenger p,int berthPref,String alottedBerth) {
		p.seatNum=berthPref;
		p.alloted=alottedBerth;
		passengers.put(p.passId, p);
		racList.add(p.passId);//queue
		System.out.println("...Added to RAC successfully...");
	}
	
	public void addToWait(Passenger p,int berthPref,String alottedBerth) {
		p.seatNum=berthPref;
		p.alloted=alottedBerth;
		passengers.put(p.passId, p);
		waitList.add(p.passId);
		System.out.println("...Added to Waiting List succesfully..");
	}
	
	public void cancelTicket(int id) {
		Passenger p=passengers.get(id);
		passengers.remove(Integer.valueOf(id));
		int position=p.seatNum;
		bookedList.remove(Integer.valueOf(id));
		System.out.println("Canceled successfully....");
		
		if(p.alloted.equals("L")) {
			lbPos.add(position);
			avaiLow++;
		}
		else if(p.alloted.equals("M")) {
			mbPos.add(position);
			avaiMid++;
		}
		else if(p.alloted.equals("U")) {
			ubPos.add(position);
			avaiUp++;
		}
		if(racList.size()>0) {
			Passenger racPassenger=passengers.get(racList.poll());
			int racPosition=racPassenger.seatNum;
			racList.remove(racPassenger.passId);
			racPos.add(racPosition);
			avaiRac++;
			
			if(waitList.size()>0) {
				Passenger waitPassenger =passengers.get(waitList.poll());
				int waitPosition=waitPassenger.seatNum;
				waitPos.add(waitPosition);
				waitList.remove(waitPassenger.passId);
				
				waitPassenger.seatNum=racPos.get(0);
				waitPassenger.alloted="RAC";
				avaiRac--;
				avaiWait++;
				
			}
			Main.bookTicket(racPassenger);
		}
	}
	public void printAvailable() {
		System.out.println("Available LowerBerth : "+avaiLow);
		System.out.println("Available MiddleBerth : "+avaiMid);
		System.out.println("Available UpperBerth : "+avaiUp);
		System.out.println("Available RAC Tickets : "+avaiRac);
		System.out.println("Available Waiting List : "+avaiWait);
		System.out.println();
	}
	
	public void printBooked() {
		if(passengers.size()==0) {
			System.out.println("No Booked details available");
			System.out.println("-----------------------");
		}
		for(Passenger p:passengers.values()) {
			System.out.println("ID : "+p.passId);
			System.out.println("Name : "+p.name);
			System.out.println("Age : "+p.age);
			System.out.println("SeatNo : "+p.seatNum+p.alloted);
			System.out.println("-----------------------");
		}
	}
	
}
