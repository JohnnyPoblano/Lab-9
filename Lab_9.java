/*
** John G
** CIS 131
** Lab 9
*/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Lab_9 {

    // Constants
    final static String INPUT_FILE = "payroll.txt";
    final static String OUTPUT_FILE = "Lab9Output.txt";

    final static int REG_PAY = 0;
    final static int OT_PAY = 1;
    final static int TOTAL_PAY = 2;
    final static int PAY_SIZE = 3;
    final static double FULL_WEEK = 40.0;
    final static double OVERTIME_RATE = 1.75;

    final static String HEADER = 
    "------------------------------ PAYROLL REPORT ------------------------------------------\n" +
    "Employee      Employee         Pay         Hours       Regular     Overtime       Total\n" +
    " Number         Name           Rate        Worked        Pay          Pay          Pay\n" +
    "--------   ---------------    ------      --------    ---------   ----------     ------\n";

    //--------------------------- Main ---------------------------//
    public static void main(String args[]) throws Exception {

        // Create input file instance
        java.io.File inputFile = new java.io.File(INPUT_FILE);

        // Create output file and PrintWriter instance using output file
        java.io.File outputFile = new java.io.File(OUTPUT_FILE);
        PrintWriter output = new PrintWriter(outputFile);

        // Create output file header
        output.print(HEADER);
        System.out.print(HEADER);

        // Create scanner instance using input file
        try {
            Scanner input = new Scanner(inputFile);

            // Loop through file
            while(input.hasNext()) {
            
            // Get all fields from each employee
            int employeeNumber = input.nextInt();
            String lastName = input.next();
            String firstName = input.next();
            double hoursWorked = input.nextDouble();
            double payRate = input.nextDouble();
            
            // Pay calculations
            double[] payArray = new double[PAY_SIZE];
            payArray = calculatePay(hoursWorked, payRate);

            // Save data to file
            writeEmployee(output, employeeNumber, lastName, firstName, hoursWorked, payRate, payArray);

            }

            // Close the files
            input.close();
            output.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File Not Found: " + e.getMessage());
            System.exit(-1);
        }
        catch(InputMismatchException e) {
            System.out.println("Error. Unexpected input data type.");
            System.exit(-1);
        }

    } //------------------------- End Main -------------------------//

    // Method returns an array of 3 doubles for pay values
    public static double[] calculatePay(double hours, double rate) {
        double[] pay = new double[PAY_SIZE];

        // < 40.00 hours
        if(hours < FULL_WEEK) {
            pay[REG_PAY] = hours * rate;
            pay[OT_PAY] = 0;
            pay[TOTAL_PAY] = pay[REG_PAY];
        }
        // > 40.00 hours
        else {
            pay[REG_PAY] = hours * rate;
            pay[OT_PAY] = ((hours - FULL_WEEK) * rate) * OVERTIME_RATE;
            pay[TOTAL_PAY] = pay[REG_PAY] + pay[OT_PAY];
        }

        return pay;
    }

    // Method writes data to file
    public static void writeEmployee(PrintWriter file, int empNum, String lName, String fName, double hours, double rate, double[] payArray) {
        file.printf("  %-9d%-19s%-5.2f%13.2f%13.2f%13.2f%13.2f\n", empNum, fName + " " + lName, rate, hours, payArray[REG_PAY], payArray[OT_PAY], payArray[TOTAL_PAY]);
        System.out.printf("  %-9d%-19s%-5.2f%13.2f%13.2f%13.2f%13.2f\n", empNum, fName + " " + lName, rate, hours, payArray[REG_PAY], payArray[OT_PAY], payArray[TOTAL_PAY]);
    }

}