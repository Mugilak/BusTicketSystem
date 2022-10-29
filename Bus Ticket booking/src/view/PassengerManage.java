package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import controller.PassengerController;
import model.Bus;

public class PassengerManage extends BusTicket {
	private String boarding, dropping;
	private Scanner input = new Scanner(System.in);
	private PassengerController control;
	private String busId, seats, paid;

	public PassengerManage() {
		control = new PassengerController();
	}

	public void start(String userName) {
		chooseLocations(userName);
	}

	private void chooseLocations(String userName) {
		System.out.println("Choose the boarding point and dropping point :");
		System.out.println("""
				----- Choose a corresponding Locations-----
				 A
				 B
				 C
				 D
				 E
				 F
				""");
		System.out.println("Enter Boarding Point :");
		try {
			boarding = input.nextLine();
			boarding = super.check("^[A-F]{1}$", boarding, "number", "ex : A TO F");
			System.out.println("Enter Dropping Point :");
			dropping = input.nextLine();
			dropping = super.check("^[A-F]{1}$", dropping, "number", "ex : A TO F");
			if (!boarding.equals(dropping)) {
				List<Bus> busList = control.getBuses(boarding, dropping);
				chooseBus(boarding, dropping, busList, userName);
			} else {
				System.out
						.println("---You have wrongly chose same place for both Boarding and Dropping --- Try Again!");
				chooseLocations(userName);
			}
		} catch (NumberFormatException e) {
			System.out.println("wrong input format !");
		} catch (SQLException e) {
			System.out.println("Cannot process ... exited");
			e.printStackTrace();
		} finally {
			System.out.println("Thankyou !");
		}
	}

	private void chooseBus(String boarding, String dropping, List<Bus> busList, String userName)
			throws NumberFormatException, SQLException {
		int count = viewBuses(boarding, dropping, busList);
		if (count != 0) {
			System.out.println("Enter busId");
			busId = input.nextLine();
			busId = super.check("^[1-3]{1}$", busId, "busId", "ex : 1 or 2 or 3");
			Bus eachBus = control.isBusExists(Integer.valueOf(busId));
			if (eachBus != null) {
				System.out.println("Enter no. of Seats :");
				seats = input.nextLine();
				seats = super.check("^[0-9]{2}$", seats, "number", "ex : 1 to 36");
				int amount = 1000 * Integer.valueOf(seats);
				System.out.println("each person Rs.1000  \n       enter Rs." + amount + " to pay :");
				paid = input.nextLine();
				if (Integer.valueOf(paid) == amount) {
					control.bookTicket(userName, boarding, dropping, Integer.valueOf(seats), Integer.valueOf(paid),
							Integer.valueOf(busId), eachBus);
					System.out.println("\nSuccessfully booked !");
				} else {
					System.out.println("Invalid amount to proceed !");
					chooseBus(boarding, dropping, busList, userName);
				}
			}
		} else
			System.out.println("No buses available for the location ! Exited");
	}

	private int viewBuses(String boarding, String dropping, List<Bus> busList) {
		int count = 0;
		System.out.println("Bus ID     | Total seats | Available Seats  | Used Seats");
		for (int index = 0; index < busList.size(); index++) {
			Bus bus = busList.get(index);
			if (bus.getBoardingPoint().equals(boarding) && bus.getDroppingPoint().equals(dropping)) {
				System.out.printf("|%10d|", bus.getBusId());
				System.out.printf("%11d|", bus.getSeats());
				System.out.printf("%15d|", bus.getAvailableSeats());
				System.out.printf("%15d|", bus.getUnavailableSeats());
				count++;
			}
			System.out.println();
		}
		if (count == 0)
			System.out.println("No buses available for the location ! Exited");
		return count;
	}
}
