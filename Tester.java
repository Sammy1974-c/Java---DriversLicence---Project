package driverslicense;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Tester class for DriversLicense.
 * @author Sammy Were
 * @version 1.0
 */
public class Tester {
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	       
	        // Header with your name
	        System.out.println("========================================");
	        System.out.println("Assignment 1 - Driver's License System");
	        System.out.println("Created by: Sammy Were");
	        System.out.println("Student ID: 041178609");
	        System.out.println("Date: " + LocalDate.now());
	        System.out.println("========================================\n");
	       
	        // Get input from user
	        System.out.print("Enter license number: ");
	        String licenseNumber = scanner.nextLine();
	       
	        System.out.print("Enter first name: ");
	        String firstName = scanner.nextLine();
	       
	        System.out.print("Enter last name: ");
	        String lastName = scanner.nextLine();
	       
	        System.out.print("Enter birth year (YYYY): ");
	        int year = scanner.nextInt();
	        System.out.print("Enter birth month (1-12): ");
	        int month = scanner.nextInt();
	        System.out.print("Enter birth day (1-31): ");
	        int day = scanner.nextInt();
	        LocalDate dateOfBirth = LocalDate.of(year, month, day);
	       
	        scanner.nextLine(); // Consume newline
	       
	        // Create license object using partial constructor
	        DriversLicense license = new DriversLicense(licenseNumber,
	                                                    firstName,
	                                                    lastName,
	                                                    dateOfBirth);
	       
	        // Print the report
	        System.out.println("\n" + license.printLicenseReport());
	       
	        scanner.close();
	    }
	}


	
