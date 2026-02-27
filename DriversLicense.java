package driverslicense;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * This class represents an Ontario Driver's License.
 * It stores license details, calculates age, days to expiry, height in inches, etc.
 * All constructors are chained and use default values when necessary.
 *
 * @author Sammy Were
 * @version 1.0
 * @since 2024
 */
public class DriversLicense {
	/** The unique license number */
    private String licenseNumber;

    /** Driver's first name */
    private String firstName;

    /** Driver's last name */
    private String lastName;

    /** Driver's date of birth */
    private LocalDate dateOfBirth;

    /** Driver's address */
    private String address;

    /** Driver's city */
    private String city;

    /** Driver's province */
    private String province;

    /** Driver's postal code */
    private String postalCode;

    /** Date when license was issued */
    private LocalDate dateOfIssue;

    /** Date when license will expire */
    private LocalDate dateOfExpiry;

    /** License class (e.g., G, G1, G2) */
    private String licenseClass;

    /** Any license restrictions */
    private String restrictions;

    /** Driver's sex (M/F) */
    private char sex;

    /** Driver's height in centimeters */
    private double heightInCm;

    /** Driver's eye color */
    private String eyeColor;

    /** Driver's hair color */
    private String hairColor;

    /**
     * No-argument constructor.
     * Uses default fictional character values and current dates.
     */
    public DriversLicense() {
        this("DL000000", "Gollum", "Unknown", LocalDate.now().minusYears(30),
             "Bag End", "Middle Earth", "ON", "A1A1A1",
             LocalDate.now(), LocalDate.now().plusYears(5),
             "G", "None", 'M', 70.0, "Blue", "Grey");
    }

    /**
     * Partial constructor (chains to full constructor).
     * @param licenseNumber the license number
     * @param firstName the first name
     * @param lastName the last name
     * @param dateOfBirth the driver's date of birth
     */
    public DriversLicense(String licenseNumber, String firstName, String lastName, LocalDate dateOfBirth) {
        this(licenseNumber, firstName, lastName, dateOfBirth,
             "Unknown Address", "Unknown City", "ON", "A1A1A1",
             LocalDate.now(), LocalDate.now().plusYears(5),
             "G", "None", 'M', 175.0, "Blue", "Black");
    }

    /**
     * Full constructor for all attributes.
     * @param licenseNumber unique license number
     * @param firstName driver's first name
     * @param lastName driver's last name
     * @param dateOfBirth driver's date of birth
     * @param address address of driver
     * @param city city of residence
     * @param province province
     * @param postalCode postal code
     * @param dateOfIssue date of license issue
     * @param dateOfExpiry date of license expiry
     * @param licenseClass license class (G, G1, G2)
     * @param restrictions license restrictions
     * @param sex driver's sex
     * @param heightInCm height in centimeters
     * @param eyeColor eye color
     * @param hairColor hair color
     */
    public DriversLicense(String licenseNumber, String firstName, String lastName, LocalDate dateOfBirth,
                          String address, String city, String province, String postalCode,
                          LocalDate dateOfIssue, LocalDate dateOfExpiry,
                          String licenseClass, String restrictions, char sex,
                          double heightInCm, String eyeColor, String hairColor) {
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.licenseClass = licenseClass;
        this.restrictions = restrictions;
        this.sex = sex;
        this.heightInCm = heightInCm;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    // ----------------- Worker Methods -----------------

    /**
     * Calculates the number of days until license expiry.
     * @return number of days to expiry
     */
    public int computeDaysToExpiry() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), dateOfExpiry);
    }

    /**
     * Calculates the driver's age in years.
     * @return age in years
     */
    public int computeDriverAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Calculates the number of days since license was issued.
     * @return days since issue
     */
    public int computeDaysSinceIssue() {
        return (int) ChronoUnit.DAYS.between(dateOfIssue, LocalDate.now());
    }

    /**
     * Calculates the number of years since license was issued.
     * @return years since issue
     */
    public double computeYearsSinceIssue() {
        return computeDaysSinceIssue() / 365.25;
    }

    /**
     * Converts height from centimeters to inches.
     * @return height in inches
     */
    public double computeHeightInInches() {
        return heightInCm / 2.54;
    }

    // ----------------- Report -----------------

    /**
     * Generates a formatted license report with all details and calculated values.
     * @return formatted report string
     */
    public String printLicenseReport() {
        StringBuilder report = new StringBuilder();
        report.append("========================================\n");
        report.append("          DRIVER'S LICENSE REPORT       \n");
        report.append("========================================\n");
        report.append(String.format("Name: %s %s%n", firstName, lastName));
        report.append(String.format("License #: %s%n", licenseNumber));
        report.append(String.format("Class: %s%n", licenseClass));
        report.append(String.format("Restrictions: %s%n", restrictions));
        report.append(String.format("Sex: %c%n", sex));
        report.append(String.format("Date of Birth: %s (Age: %d)%n", dateOfBirth, computeDriverAge()));
        report.append(String.format("Date of Issue: %s%n", dateOfIssue));
        report.append(String.format("Date of Expiry: %s (Days to Expiry: %d)%n", dateOfExpiry, computeDaysToExpiry()));
        report.append(String.format("Address: %s, %s, %s, %s%n", address, city, province, postalCode));
        report.append(String.format("Height: %.1f cm (%.1f inches)%n", heightInCm, computeHeightInInches()));
        report.append(String.format("Eye Color: %s%n", eyeColor));
        report.append(String.format("Hair Color: %s%n", hairColor));
        report.append(String.format("Years Since Issue: %.2f%n", computeYearsSinceIssue()));
        report.append("========================================\n");
        return report.toString();
    }

    // ----------------- Getters & Setters (Optional) -----------------
    public String getLicenseNumber() {
        return licenseNumber;
    }
   
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    
}

