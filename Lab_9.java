/*
** John G
** CIS 131
** Lab 9
*/

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

    // Main method
    public static void main(String args[]) throws Exception {

        // Create input file instance
        java.io.File inputFile = new java.io.File(INPUT_FILE);

        // Create scanner instance using input file
        Scanner input = new Scanner(inputFile);

        // Create output file instance
        java.io.File outputFile = new java.io.File(OUTPUT_FILE);

        // Create PrintWriter instance using output file
        java.io.PrintWriter output = new PrintWriter(outputFile);

        // Loop through file
        while(input.hasNext()) {
            
            // Get all fields from each employee
            int employeeNumber = input.nextInt();
            String lastName = input.next();
            String firstName = input.next();
            double hoursWorked = input.nextDouble();
            double payRate = input.nextDouble();
            
            double[] payArray = new double[PAY_SIZE];
            payArray = calculatePay(hoursWorked, payRate);

        }

        // Close the file
        input.close();
    }

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
            pay[REG_PAY] = FULL_WEEK * rate;
            pay[OT_PAY] = (hours - FULL_WEEK) * rate;
            pay[TOTAL_PAY] = pay[REG_PAY] + pay[OT_PAY];
        }

        return pay;
    }

    public static void writeFile() {
        
    }

}