import java.util.*;
public class Main {
	
	public static void bookTicket(Passenger p) {
		TicketBooker booker=new TicketBooker();
		if(TicketBooker.avaiWait==0) {
			System.out.println("No tickets Avilable");
			return;
		}
		if((p.berthPref.equals("L") && TicketBooker.avaiLow>0) || 
		  (p.berthPref.equals("M") && TicketBooker.avaiMid>0) || 
		  (p.berthPref.equals("U") && TicketBooker.avaiUp>0)) {
			
//			System.out.println("Your berth preference available..");
			
			//lower
			if(p.berthPref.equals("L")) {
				System.out.println("Lower Berth Given..");
				booker.bookTicket(p, TicketBooker.lbPos.get(0), "L");
				TicketBooker.lbPos.remove(0);
				TicketBooker.avaiLow--;
			}
			
			//middle
			else if(p.berthPref.equals("M")) {
				System.out.println("Middle Berth Given..");
				booker.bookTicket(p, TicketBooker.mbPos.get(0), "M");
				TicketBooker.mbPos.remove(0);
				TicketBooker.avaiMid--;
			}
			
			//upper
			else if(p.berthPref.equals("U")) {
				System.out.println("Upper Berth Given..");
				booker.bookTicket(p, TicketBooker.ubPos.get(0), "U");
				TicketBooker.ubPos.remove(0);
				TicketBooker.avaiUp--;
			}
			
		}
		//if prefered berth not available..
		else if(TicketBooker.avaiLow>0) {
//			System.out.println("Your preference not available");
			System.out.println("Lower Berth Given..");
			booker.bookTicket(p, TicketBooker.lbPos.get(0), "L");
			TicketBooker.lbPos.remove(0);
			TicketBooker.avaiLow--;
			
		}
		else if(TicketBooker.avaiMid>0) {
//			System.out.println("Your preference not available");
			System.out.println("Middle Berth Given..");
			booker.bookTicket(p, TicketBooker.mbPos.get(0), "M");
			TicketBooker.mbPos.remove(0);
			TicketBooker.avaiMid--;
			
		}
		else if(TicketBooker.avaiUp>0) {
//			System.out.println("Your preference not available");
			System.out.println("Upper Berth Given..");
			booker.bookTicket(p, TicketBooker.ubPos.get(0), "U");
			TicketBooker.ubPos.remove(0);
			TicketBooker.avaiUp--;
		}
		
		//if no berth available...book the rac ticket..
		else if(TicketBooker.avaiRac>0) {
			System.out.println("Berth Not Available");
			System.out.println("RAC Tickets Available");
			booker.bookRAC(p, TicketBooker.racPos.get(0), "RAC");
			TicketBooker.racPos.remove(0);
			TicketBooker.avaiRac--;
		}
		
		//if no berth and rac avilable ...book into waiting list
		else if(TicketBooker.avaiWait>0) {
			System.out.println("No Berth and RAC are Available..");
			System.out.println("Waiting List Available...");
			booker.addToWait(p,TicketBooker.waitPos.get(0),"WL");
			TicketBooker.waitPos.remove(0);
			TicketBooker.avaiWait--;
		}
	} 
	
	public static void cancelTicket(int id) {
		TicketBooker booker=new TicketBooker();
		if(!TicketBooker.passengers.containsKey(id)) ///checking in the map for passengers details..
			System.out.println("Invalid Passenger ID...please enter the valid ID");
		else
			booker.cancelTicket(id);
	}

	public static void main(String[] args) {
		try (Scanner s = new Scanner(System.in)) {
			boolean loop=true;
			while(loop) {
				System.out.println("1. Book Ticket\n2. Cancel Ticket\n3. Available ticket\n4. Booked Tickets\n5. Exit");
				int choice =s.nextInt();
				switch(choice) {
					case 1:
						System.out.println("Enter Passenger name,age and berthPref (L,M or U)");
						String name=s.next();
						int age=s.nextInt();
						String berthPref=s.next();
						
						Passenger p=new Passenger(name,age,berthPref); //instance of Passenger class..
						bookTicket(p);
						break;
					
					case 2:
						System.out.println("Enter the passenger ID to cancel");
						int id=s.nextInt();
						cancelTicket(id);
						break;
						
					case 3:
						System.out.println("Tickets Available..");
						System.out.println("_____________________");
						TicketBooker booker=new TicketBooker();
						booker.printAvailable();
						break;
						
					case 4:
					{
						System.out.println("Booked Tickets");
						System.out.println("_____________________");
						TicketBooker booker1=new TicketBooker();
						booker1.printBooked();
						break;
					}
						
					case 5:
						loop=false;
						break;
						
				}
			}
		}

	}

}
