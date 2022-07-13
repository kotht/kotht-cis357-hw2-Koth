// Homework 2: Updated Sales Register Program
// Course: CIS357
// Due date: July 12, 2022
// Name: Tanner J Koth
// GitHub:
// Instructor: Il-Hyung Cho
// Program description:This program emulates a cash register at a grocery store. A .txt file is read to an array of
// object instances each containing product code, item name, and unit price. We then prompt the user for input (y/n),
// which determines if a sale is to be conducted. The process of the sale involves prompting the user for more input,
// and calling on various methods to process that input. When the user has finished their purchase they will enter (-1)
// as a product code, which will then call on various methods to generate a receipt in ascending order by item name. The
// program will then prompt the user with the decision to begin another sale. If true, repeat instructions above; if
// false, the program prints the total sale for the day, and thanks the user for using the system.

/*
Program features:
Change the item code type to String: Y
Provide the input in CSV format. Ask the user to enter the input file name: Y
Implement exception handling for
    File input: Y
    Checking wrong input data type: Y
    Checking invalid data value: Y
    Tendered amount less than the total amount: P
Use ArrayList for the items data: Y
Adding item data: Y
Deleting item data: Y
Modifying item data: Y
*/


public class HW2_Koth {
    /**
     * Main driver. Shouldn't contain code for processing the sale; -setup for input and output
     * @param args
     */
    public static void main(String[] args){

        System.out.println("Welcome to Koth cash register system!\n");

        CashRegister register = new CashRegister(); // register object created
        register.initializeSale();

    }
}
