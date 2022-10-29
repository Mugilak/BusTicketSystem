package model;

public class Bookings {
	private String userName;
	private String bookDate;
	private String boarding;
	private String dropping;
	private int seatsBooked;
	private int paid;
	private int busId;

	public Bookings(String userName, String bookDate, String boarding, String dropping, int seatsBooked, int paid, int busId) {
		this.userName = userName;
		this.bookDate = bookDate;
		this.boarding = boarding;
		this.dropping = dropping;
		this.seatsBooked = seatsBooked;
		this.paid = paid;
		this.busId = busId;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getBoarding() {
		return boarding;
	}

	public void setBoarding(String boarding) {
		this.boarding = boarding;
	}

	public String getDropping() {
		return dropping;
	}

	public void setDropping(String dropping) {
		this.dropping = dropping;
	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}
}
