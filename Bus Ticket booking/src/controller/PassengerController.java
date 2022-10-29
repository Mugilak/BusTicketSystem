package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Bookings;
import model.Bus;
import model.BusBookingDatabase;

public class PassengerController {
	private BusBookingDatabase busDB = BusBookingDatabase.getInstance();
	private String query;

	public List<Bus> getBuses(String boarding, String dropping) {
		List<Bus> availableBuses = new ArrayList<>();
		List<Bus> busList = busDB.getBusList();
		for (int index = 0; index < busList.size(); index++) {
			Bus eachBus = busList.get(index);
			if (eachBus.getBoardingPoint().equals(boarding) && eachBus.getDroppingPoint().equals(dropping)) {
				availableBuses.add(eachBus);
			}
		}
		return availableBuses;
	}

	public Bus isBusExists(int busId) {
		List<Bus> busList = busDB.getBusList();
		for (int index = 0; index < busList.size(); index++) {
			Bus eachBus = busList.get(index);
			if (eachBus.getBusId() == busId) {
				return eachBus;
			}
		}
		return null;
	}

	public void bookTicket(String userName, String boarding, String dropping, int seats, int paid, int busId, Bus bus)
			throws SQLException {
		String entry = new SimpleDateFormat("EEE / dd-MMM-YYYY / hh:mm:ss aa").format(Calendar.getInstance().getTime());
		Bookings bookings = new Bookings(userName, entry, boarding, dropping, seats, paid, busId);
		int availableSeats = Math.abs(bus.getAvailableSeats() - seats);
		int unavailableSeats = bus.getUnavailableSeats() + seats;
		bus.setAvailableSeats(availableSeats);
		bus.setUnavailableSeats(unavailableSeats);
		busDB.updateQuery(bus);
		busDB.setBookingsList(bookings);
		busDB.setBookingsList(bookings, query);
	}

}
