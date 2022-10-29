package view;

import java.sql.SQLException;
import java.util.Scanner;

import model.Bus;
import model.BusBookingDatabase;

public class AdminManage extends BusTicket{
	private Bus bus;
	Scanner input = new Scanner(System.in);
	private Character board = 'A', drop = 'F';
	String buses, travelDate,query="";
	private BusBookingDatabase busDB = BusBookingDatabase.getInstance();

	public void start() throws SQLException {
		createBus();
		return;
	}

	private void createBus() throws SQLException {
		System.out.println("Enter No. of Busses to add");
		buses = input.nextLine();
		buses = check("^[0-9]+$", buses, "bus count", "(ex: 1 or 2)");
		int id = 0, seats = 36, i = 0, j = 0;
		for (int index = 1; index <= Integer.valueOf(buses); index++) {
			bus = new Bus(++id, seats);
			bus.setAvailableSeats(seats);
			bus.setUnavailableSeats(0);
			board = (char) ((char) board + i++);
			bus.setBoardingPoint(board.toString());
			drop = (char) ((char) drop - j++);
			bus.setDroppingPoint(drop.toString());
			System.out.println("Enter Travel Date for bus ID "+id );
			travelDate = input.nextLine();
			travelDate = check("^[1-2]+$", travelDate, "travel date", "(ex: 1 or 2)");
			bus.setTravelDate(travelDate);
			busDB.setBusList(bus);
			busDB.setBusList(bus,query);
		}
		System.out.println(buses+" buses created Successfully !");
	}

}
