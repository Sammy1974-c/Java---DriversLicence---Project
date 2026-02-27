package driverslicense;
//annotation for marking test methods
import org.junit.Test;
//runs before each test method
import org.junit.Before;
//Asserts method for verification
import static org.junit.Assert.*;
// Java time API for handling dates
import java.time.LocalDate;
//calculates differences between two dates or times
import java.time.temporal.ChronoUnit;
// represents data based amount of time in years,months and days
import java.time.Period;

/**
 * JUnit tests for DriversLicense class.
 * Tests all 6 worker methods with boundary cases.
 * Contains at least 18 assert statements as required.
 *
 * @author Sammy Were
 * @version 1.0
 */
public class DriversLicenseTest {
	private DriversLicense standardLicense;
    private DriversLicense licenseExpiringToday;
   
    /**
     * Setup method that runs before each test.
     * Creates license objects for testing.
     */
    @Before
    public void setUp() {
        // Standard license
        standardLicense = new DriversLicense(
            "A123456789", "Christiano", "Ronaldo",
            LocalDate.of(1990, 6, 15),
            "123 Main St", "Toronto", "ON", "M1A 1A1",
            LocalDate.of(2020, 1, 15),
            LocalDate.of(2025, 12, 31),
            "G", "None", 'M',
            180.0, "Brown", "Black"
        );
       
        // License expiring today
        licenseExpiringToday = new DriversLicense(
            "B987654321", "Jane", "Smith",
            LocalDate.of(1985, 3, 10),
            "456 Oak Ave", "Ottawa", "ON", "K1A 0B2",
            LocalDate.of(2019, 6, 1),
            LocalDate.now(),  // EXPIRES TODAY
            "G2", "Glasses", 'F',
            165.0, "Blue", "Blonde"
        );
    }
   
    // ===== Test 1: computeDaysToExpiry() =====
   // checks the correct calculation of days to expiry
    @Test
    public void testComputeDaysToExpiry_StandardCase() {
    	//standard test case: checks correct calculation of days between today's date and the licence expiry date(Dec 31,2025)
        long expectedDays = ChronoUnit.DAYS.between(
            LocalDate.now(), LocalDate.of(2025, 12, 31));
        assertEquals("Days to expiry should match calculation",
                     expectedDays, standardLicense.computeDaysToExpiry());
    }
     // expiry date is today, expected result =0
    @Test
    public void testComputeDaysToExpiry_ExpiringToday() {
    	// Boundary test:expiry date is today, expected days remaining =0
        assertEquals("License expiring today should return 0",
                     0, licenseExpiringToday.computeDaysToExpiry());
    }
   // if expire date is tomorrow, it returns 1
    @Test
    public void testComputeDaysToExpiry_ExpiringTomorrow() {
    	// verifies that a license expiring tomorrow returns 1 day remaining.
        DriversLicense expiringTomorrow = new DriversLicense(
            "C555555555", "Bob", "Johnson",
            LocalDate.of(1975, 11, 25),
            "789 Pine Rd", "Hamilton", "ON", "L8P 1A1",
            LocalDate.of(2021, 3, 15),
            LocalDate.now().plusDays(1),
            "G1", "Daylight Only", 'M',
            175.5, "Green", "Brown"
        );
        assertEquals("License expiring tomorrow should return 1",
                     1, expiringTomorrow.computeDaysToExpiry());
    }
   
    // ===== Test 2: computeDriverAge() =====
 // This section tests computeDriverAge() using:
 // 1) Normal case: verifies correct age calculation
 // 2) Boundary case (birthday today)
 // 3) Edge case (newborn age = 0)
    @Test
    public void testComputeDriverAge_StandardCase() {
        int expectedAge = Period.between(
            LocalDate.of(1990, 6, 15), LocalDate.now()).getYears();
        assertEquals("Age should be calculated correctly",
                     expectedAge, standardLicense.computeDriverAge());
    }
   // birthday is today
    @Test
    public void testComputeDriverAge_BirthdayToday() {
        LocalDate today = LocalDate.now();
        DriversLicense birthdayLicense = new DriversLicense(
            "D111111111", "Birthday", "Person",
            LocalDate.of(today.getYear() - 25, today.getMonth(), today.getDayOfMonth()),
            "", "", "ON", "",
            LocalDate.now(), LocalDate.now().plusYears(5),
            "G", "", 'M', 175.0, "Brown", "Black");
       
        assertEquals("Person born 25 years ago today should be 25",
                     25, birthdayLicense.computeDriverAge());
    }
   // newborn if expected age = 0
    @Test
    public void testComputeDriverAge_BornToday() {
        DriversLicense newborn = new DriversLicense(
            "E222222222", "Baby", "Newborn",
            LocalDate.now(),
            "", "", "ON", "",
            LocalDate.now(), LocalDate.now().plusYears(5),
            "G1", "Learner", 'X',
            50.0, "Blue", "Bald"
        );
        assertEquals("Person born today should be 0",
                     0, newborn.computeDriverAge());
    }
   
    // ===== Test 3: computeDaysSinceIssue() =====
   // checks correct calculation of days since issue date
    @Test
    public void testComputeDaysSinceIssue_StandardCase() {
        long expectedDays = ChronoUnit.DAYS.between(
            LocalDate.of(2020, 1, 15), LocalDate.now());
        assertEquals("Days since issue should match calculation",
                     expectedDays, standardLicense.computeDaysSinceIssue());
    }
    // Boundary case: if issue date is today, days since issue should be 0
    @Test
    public void testComputeDaysSinceIssue_IssuedToday() {
        DriversLicense issuedToday = new DriversLicense(
            "F333333333", "New", "License",
            LocalDate.of(1995, 8, 20),
            "", "", "ON", "",
            LocalDate.now(),  // ISSUED TODAY
            LocalDate.now().plusYears(5),
            "G", "None", 'F',
            170.0, "Hazel", "Red"
        );
        assertEquals("License issued today should have 0 days",
                     0, issuedToday.computeDaysSinceIssue());
    }
    
 // Near-boundary case: issue date is 365 days ago, expected days should be ~365 
    @Test
    public void testComputeDaysSinceIssue_IssuedOneYearAgo() {
        DriversLicense oneYearOld = new DriversLicense(
            "G444444444", "One", "Year",
            LocalDate.of(1980, 4, 5),
            "", "", "ON", "",
            LocalDate.now().minusDays(365),  // 1 year ago (approx)
            LocalDate.now().plusYears(4),
            "G", "None", 'M',
            185.0, "Gray", "Gray"
        );
        long expectedDays = ChronoUnit.DAYS.between(
            LocalDate.now().minusDays(365), LocalDate.now());
        assertEquals("License issued 365 days ago should return 365",
                     expectedDays, oneYearOld.computeDaysSinceIssue());
    }
   
    // ===== Test 4: computeYearsSinceIssue() =====
 // Tests years since issue using normal, boundary (issued today),
 // and near-boundary (issued 365 days ago) cases.
    
 // Normal case: convert days since issue into years using 365.25 (leap-year average)   
    @Test
    public void testComputeYearsSinceIssue_StandardCase() {
        double expectedYears = ChronoUnit.DAYS.between(
            LocalDate.of(2020, 1, 15), LocalDate.now()) / 365.25;
        assertEquals("Years since issue should match calculation",
                     expectedYears, standardLicense.computeYearsSinceIssue(), 0.01);
    }
    
 // Boundary case: if issued today, years since issue should be 0.0
    @Test
    public void testComputeYearsSinceIssue_IssuedToday() {
        DriversLicense issuedToday = new DriversLicense(
            "H555555555", "Today", "Issued",
            LocalDate.of(1995, 8, 20),
            "", "", "ON", "",
            LocalDate.now(),
            LocalDate.now().plusYears(5),
            "G", "None", 'F',
            170.0, "Hazel", "Red"
        );
        assertEquals("License issued today should be 0.0 years",
                     0.0, issuedToday.computeYearsSinceIssue(), 0.001);
    }
    
 // Near-boundary case: 365 days ago should be approximately 1 year (365/365.25)
    @Test
    public void testComputeYearsSinceIssue_IssuedOneYearAgo() {
        DriversLicense oneYearOld = new DriversLicense(
            "I666666666", "One", "Year",
            LocalDate.of(1980, 4, 5),
            "", "", "ON", "",
            LocalDate.now().minusDays(365),
            LocalDate.now().plusYears(4),
            "G", "None", 'M',
            185.0, "Gray", "Gray"
        );
        double expectedYears = 365.0 / 365.25;
        assertEquals("License issued 365 days ago should be ~1.0 years",
                     expectedYears, oneYearOld.computeYearsSinceIssue(), 0.01);
    }
   
    // ===== Test 5: computeHeightInInches() =====
 // Tests height conversion from centimeters to inches (cm / 2.54)
 // using normal, zero, and exact-conversion cases.
    
 // Normal case: verify conversion from 180 cm to inches
    @Test
    public void testComputeHeightInInches_StandardCase() {
        double expectedInches = 70.87; // hard-coded as expected
        assertEquals("Height in inches should convert correctly",
                     expectedInches, standardLicense.computeHeightInInches(), 0.01);
    }
    
 // Edge case: 0 cm should convert to 0 inches 
    @Test
    public void testComputeHeightInInches_ZeroHeight() {
        DriversLicense zeroHeight = new DriversLicense(
            "J777777777", "Zero", "Height",
            LocalDate.of(1990, 1, 1),
            "", "", "ON", "",
            LocalDate.now(), LocalDate.now().plusYears(5),
            "G", "", 'M',
            0.0, "Brown", "Black"
        );
        assertEquals("Zero cm should be 0 inches",
                     0.0, zeroHeight.computeHeightInInches(), 0.001);
    }
    
 // Exact conversion: 2.54 cm should convert to exactly 1 inch 
    @Test
    public void testComputeHeightInInches_OneInchExactly() {
        DriversLicense oneInch = new DriversLicense(
            "K888888888", "One", "Inch",
            LocalDate.of(1990, 1, 1),
            "", "", "ON", "",
            LocalDate.now(), LocalDate.now().plusYears(5),
            "G", "", 'M',
            2.54, "Brown", "Black"
        );
        assertEquals("2.54 cm should be exactly 1 inch",
                     1.0, oneInch.computeHeightInInches(), 0.001);
    }
   
    // ===== Test 6: printLicenseReport() =====
 // Validates that the generated report string contains key required fields
 // and basic formatting markers (header and separators).
    
 // Report content check: should include the license number
    @Test
    public void testPrintLicenseReport_ContainsLicenseNumber() {
        String report = standardLicense.printLicenseReport();
        boolean containsLicense = report.contains("A123456789");
        assertTrue("Report should contain license number", containsLicense);
    }
   
 // Report content check: should include computed labels like Days to Expiry and Age
    @Test
    public void testPrintLicenseReport_ContainsCalculatedValues() {
        String report = standardLicense.printLicenseReport();
        boolean hasDaysToExpiry = report.contains("Days to Expiry");
        boolean hasAge = report.contains("Age");
        assertTrue("Report should contain 'Days to Expiry'", hasDaysToExpiry);
        assertTrue("Report should contain 'Age'", hasAge);
    }
   
 // Formatting check: should include a report header and separator lines
    @Test
    public void testPrintLicenseReport_FormattedTable() {
        String report = standardLicense.printLicenseReport();
        boolean hasHeader = report.contains("DRIVER'S LICENSE REPORT");
        boolean hasSeparator = report.contains("=======");
        assertTrue("Report should have header", hasHeader);
        assertTrue("Report should have separators", hasSeparator);
    }
   
    // ===== Extra Edge Case Tests =====
   
    @Test
    public void testExpiredLicense() {
        DriversLicense expired = new DriversLicense(
            "L999999999", "Expired", "License",
            LocalDate.of(1980, 1, 1),
            "", "", "ON", "",
            LocalDate.of(2018, 1, 1),
            LocalDate.now().minusDays(10),
            "G", "None", 'M', 175.0, "Brown", "Black"
        );
        long days = expired.computeDaysToExpiry();
        assertTrue("Expired license should have negative days", days < 0);
    }
}



