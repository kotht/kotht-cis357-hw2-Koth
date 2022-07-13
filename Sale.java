import java.util.Scanner;

public class Sale {

    /** No-Args Constructor     */
    public Sale() {

    }

    /**
     * This method prompts the user for a sale. The method will only return the value of char when the user had entered
     * a valid value (Y/N)
     * @return char (Y/N) Character
     */
    public char promptSale() {
        Scanner sc = new Scanner(System.in);
        char c;
        do {
            System.out.print("Beginning a new sale (Y/N) ");
            c = sc.next().charAt(0);
            c = Character.toUpperCase(c);
            if (c == 'Y' || c == 'N')
                break;
            System.out.println("Please enter a valid value (Y/N)");

        } while (c != 'Y' || c != 'N');

        return c;
    }
}