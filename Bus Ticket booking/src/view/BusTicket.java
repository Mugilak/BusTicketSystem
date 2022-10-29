package view;

import java.util.Scanner;

import controller.LoginController;

public class BusTicket {
	private Scanner input = new Scanner(System.in);
	private LoginManage manage;
	private LoginController control;

	BusTicket() {
		control = new LoginController();
	}

	public static void main(String[] args) {
		new BusTicket().start();
	}

	private void start() {
		System.out.println("           ----- Bus Ticket Booking Management System -----");
		System.out.println("select ->  1  to LOGIN\n       ->  2  to SIGN UP");
		String choice = input.nextLine();
		choice = check("^[1-2]+$", choice, "choice", "(ex: 1 or 2)");
		manage = new LoginManage();
		switch (choice) {
		case "1":
			manage.doLogin(0);
			break;
		case "2":
			manage.doSignup();
			break;
		}
	}

	protected String check(String regex, String word, String print, String example) {
		if ((control.isValid(regex, word)) == false) {
			while ((control.isValid(regex, word)) == false) {
				System.out.println("Invalid " + print + " !  ----  Enter " + print + " again " + example);
				word = input.nextLine();
			}
		}
		return word;
	}

}
