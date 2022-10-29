package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import database.BusBooking;

public class BusBookingDatabase {
	private static BusBookingDatabase busDB;
	private List<Passenger> passengerList;
	private Queue<Bookings> bookingsList;
	private List<Bus> busList;

	public BusBookingDatabase() {
		passengerList = new ArrayList<Passenger>();
		bookingsList = new LinkedList<Bookings>();
		busList = new ArrayList<Bus>();
	}

	public static BusBookingDatabase getInstance() {
		if (busDB == null) {
			busDB = new BusBookingDatabase();
		}
		return busDB;
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(Passenger passengerList) {
		this.passengerList.add(passengerList);
	}

	public Queue<Bookings> getBookingsList() {
		return bookingsList;
	}

	public void setBookingsList(Bookings bookings, String query) throws SQLException {
		query = "insert into busticket.bookings values('" + bookings.getUserName() + "','" + bookings.getBookDate() + "','"
				+ bookings.getBoarding() + "','" + bookings.getDropping() + "','" + bookings.getSeatsBooked() + "','"
				+ bookings.getPaid() + "','" + bookings.getBusId() + "')";
		BusBooking.getInstance().setData(query);
	}

	public void setBookingsList(Bookings bookingsList) {
		this.bookingsList.offer(bookingsList);
	}

	public List<Bus> getBusList() {
		return busList;
	}

	public void setBusList(Bus busList) {
		this.busList.add(busList);
	}

	public void setPassengerList(Passenger passenger, String query) throws SQLException {
		query = "insert into busticket.passenger (username,password,age) values('" + passenger.getUserName() + "','"
				+ passenger.getPassword() + "'," + passenger.getAge() + ")";
		BusBooking.getInstance().setData(query);
	}

	public void setBusList(Bus bus, String query) throws SQLException {
		query = "insert into busticket.bus values('" + bus.getBusId() + "','" + bus.getSeats() + "','" + bus.getAvailableSeats()
				+ "','" + bus.getUnavailableSeats() + "','" + bus.getBoardingPoint() + "','" + bus.getDroppingPoint()
				+ "','" + bus.getTravelDate() + "')";
		BusBooking.getInstance().setData(query);
	}

	public void updateQuery(Bus bus) throws SQLException {
		String query = "update busticket.bus set availSeats="+bus.getAvailableSeats() +",unavailSeats="+bus.getUnavailableSeats()+"where busId="+bus.getBusId();
		BusBooking.getInstance().setData(query);
	}

}
