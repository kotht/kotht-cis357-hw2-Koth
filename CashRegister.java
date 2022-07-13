import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CashRegister {

    double dailyTotal = 0;

    /**
     * This method initializes the sale, or begins processing the sale.
     */
    public void initializeSale() {
        // Initialization(s)/Declaration(s)
        String filename;
        String itemCode;
        ArrayList<Item> items = new ArrayList<>(); // create ArrayList of Item
        Scanner sc = new Scanner(System.in); // create scanner obj
        boolean endTrans = false;
        char c;

        // Processing
        try {
            readFile(items, sc); // read items file to arrayList
        } catch (IOException e) {
            System.out.println("File import unsuccessful");
        }

        Sale sale = new Sale();
        c = sale.promptSale();

        while (c == 'Y') {
            if (c == 'Y') {
                System.out.println("--------------------");
                ArrayList<PurchaseInfo> pInfo = new ArrayList<>();

                do {
                    itemCode = acceptItemCode(sc);
                    switch (itemCode) {
                        case "0000":
                            printItemTable(items);
                            break;
                        case "-1":
                            break;
                        default:
                            int i;


                            boolean itemFound = false;
                            for (Item elem : items) {
                                if (elem.getItemCode().equals(itemCode)) {
                                    itemFound = true;
                                    i = items.indexOf(elem);
                                    printItemName(items.get(i).getItemName());
                                    int quantity;
                                    do{
                                        quantity = getItemQuantity(sc);
                                    } while (quantity <= 0);
                                    double itemTotal = calcItemTotal(items.get(i).getUnitPrice(), quantity);
                                    System.out.println("         item total: $   " + String.format("%.2f", itemTotal) + "\n");
                                    pInfo.add(new PurchaseInfo(itemTotal, quantity, elem.getItemName()));
                                }
                            }
                            if (itemFound != true) {
                                System.out.println("!!! Invalid product code\n");
                            }
                    }

                    if (itemCode.equals("-1"))
                        break;

                } while (itemCode.equals("-1") == false);

                sortItems(pInfo);
                printReceipt(pInfo, sc);

                c = sale.promptSale();
            }
        }
        if (c == 'N') {
            System.out.println("\n===================");
            System.out.println("The total sale for the day is  $  " + String.format("%.2f",dailyTotal));

            char code = '\u0000';
            while (code != 'Q') {
                System.out.print("\nDo you want to update the items data? (A/D/M/Q): ");
                code = sc.next().charAt(0);
                code = Character.toUpperCase(code);

                switch (code) {
                    case 'A':
                        addItemData(items, sc);
                        break;
                    case 'D':
                        deleteItemData(items, sc);
                        break;
                    case 'M':
                        modifyItemData(items, sc);
                        break;
                }
            }

            printItemTable(items);
            System.out.println("Thanks for using Koth cash register system. Goodbye. ");

        }
    }

    /**
     *  This method reads in the contents of the file into an arrayList of object instances
     * @param items ArrayList Item (Instances of Item)
     * @param sc    Scanner obj
     * @throws IOException
     */
    public void readFile (ArrayList<Item> items, Scanner sc) throws IOException {
        int count = 0;
        System.out.print("Input file:  ");
        String filename = sc.next();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                items.add(new Item(tokens[0], tokens[1], Double.parseDouble(tokens[2])));

            }
            System.out.println("File import successful");
        }
        catch (IOException e) {
            System.out.println("File import unsuccessful");
            readFile(items, sc);
        }
    }

    /**
     *  This method accepts the item code for each item of the sale. It then returns the itemCode for further elaboration.
     * @param sc    Scanner Obj
     * @return String itemCode
     */
    public String acceptItemCode (Scanner sc){
        String itemCode;
        System.out.print("Enter product code:  ");
        itemCode = sc.next().trim();
        return itemCode;
    }

    /**
     * This method prints the Item's name when initialized
     * @param itemName
     */
    public void printItemName(String itemName) {
        System.out.println("         item name:  " + itemName);
    }

    /**
     * This method accepts the quantity of the item.
     * @param sc    Scanner obj
     * @return int Quantity of Items
     */
    public int getItemQuantity(Scanner sc) {
        int quantity = 0;

        try {
            System.out.print("Enter quantity:      ");
            String temp = sc.next().trim();
            quantity = Integer.parseInt(temp);

        } catch (NumberFormatException e) {
            System.out.println("!!! Invalid data type");
        }
        finally {
            return quantity;
        }


    }

    /**
     * This method calculates the total for the item given its specified quantity.
     * @param price     Item's Price
     * @param quantity  Quantity of items being purchased
     * @return  double Quantity * Price
     */
    public double calcItemTotal(double price, int quantity) {
        double total;
        total = price * quantity;
        return total;
    }

    /**
     * This method is used to generate the receipt to print to the customer. It formats the output and prints it to the
     * users terminal.
     * @param pInfo ArrayList purchaseInformation (Instances of Purchase Information)
     * @param sc    Scanner obj
     */
    public void printReceipt(ArrayList<PurchaseInfo> pInfo, Scanner sc) {
        String itemsFormat = "%5s %-13s $ %7s";
        String totalsFormat = "%-19s $ %7s";

        double subtotal = 0;
        final double TAXRATE = 1.06;
        double tenderedAmt;

        System.out.println("----------------------------");
        System.out.println("Items list:");

        for(PurchaseInfo elem: pInfo){
            subtotal += elem.getPrice();
            System.out.printf(itemsFormat, elem.getQuantity(), elem.getItemName(), String.format("%.2f",elem.getPrice()) + "\n");
        }

        System.out.printf(totalsFormat, "Subtotal", String.format("%.2f",subtotal)+ "\n");
        System.out.printf(totalsFormat, "Total with Tax (6%)", String.format("%.2f",(subtotal * TAXRATE))+ "\n");
        System.out.print("Tendered amount \t$  ");
        tenderedAmt = Double.parseDouble(sc.next());

        while (tenderedAmt < subtotal * TAXRATE) {
            System.out.println("!!! Not enough. Enter again.");
            System.out.print("Tendered amount \t$  ");
            tenderedAmt = Double.parseDouble(sc.next().trim());
        }
        System.out.printf(totalsFormat, "Change", String.format("%.2f",(((subtotal * TAXRATE) - tenderedAmt) * -1))+ "\n");
        System.out.println("----------------------------\n");

        dailyTotal+=subtotal;
    }

    /**
     * This method is used to sort the arrayList values in ascending order by name.
     * @param pInfo ArrayList purchaseInformation (Instances of Purchase Information)
     */
    public void sortItems(ArrayList<PurchaseInfo> pInfo) {
        pInfo.sort(Comparator.comparing(PurchaseInfo::getItemName));

    }

    /**
     * This method is used to add an item to the items arrayList.
     * @param items ArrayList Item (Instances of Item)
     * @param sc    Scanner obj
     */
    public void addItemData(ArrayList<Item> items, Scanner sc) {
        String itemCode;
        String itemName;
        String unitPrice;
        System.out.print("item code:  ");
        itemCode = sc.next().trim();
        System.out.print("item name:  ");
        itemName = sc.next().trim();
        System.out.print("item price:  ");
        unitPrice = sc.next().trim();

        items.add(new Item(itemCode, itemName, Double.parseDouble(unitPrice))); // add new item to arrayList
        System.out.println("Item add successful!");
    }

    /**
     * This method is used to delete an item from the items arrayList.
     * @param items ArrayList Item (Instances of Item)
     * @param sc    Scanner obj
     */
    public void deleteItemData(ArrayList<Item> items, Scanner sc) {
        String itemCode;
        System.out.print("item code:  ");
        itemCode = sc.next().trim();
        int initialSize = items.size();
        items.removeIf(elem -> elem.getItemCode().equals(itemCode)); // removes element where filter is true

        if (items.size() < initialSize) { // check if any items were deleted from arrList
            System.out.println("Item delete successful!");
        }
        else{
            System.out.println("Item not found");
        }

    }

    /**
     * This method is used to modify the existing data of an element from the items arrayList.
     * @param items ArrayList Item (Instances of Item)
     * @param sc    Scanner obj
     */
    public void modifyItemData(ArrayList<Item> items, Scanner sc) {
        String itemCode;
        String itemName;
        double unitPrice;
        System.out.print("item code:  ");
        itemCode = sc.next().trim();
        System.out.print("item name:  ");
        itemName = sc.next().trim();
        System.out.print("item price:  ");
        unitPrice = sc.nextDouble();

        for(Item elem : items) {
            if(elem.getItemCode().equals(itemCode)) {
                elem.setItemName(itemName);
                elem.setUnitPrice(unitPrice);
                System.out.println("Item modify successful!");
            }
        }
    }

    /**
     * This method prints a formatted table of all the items from the items arrayList.
     * @param items ArrayList Item (Instances of Item)
     */
    public void printItemTable(ArrayList<Item> items) {
        final String FORMAT = "%-9s\t%-14s\t%-10s\n";
        System.out.printf("\n"  + FORMAT, "item code", "item name", "unit price");
        for (Item elem: items) {
            System.out.printf(FORMAT, elem.getItemCode(), elem.getItemName(), String.format("%.2f", elem.getUnitPrice()));
        }
        System.out.println();
    }
}
