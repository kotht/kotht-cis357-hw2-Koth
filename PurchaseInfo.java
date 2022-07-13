public class PurchaseInfo {
    /** Member data     */
    private double price;
    private int quantity;
    private String itemName;

    /** No-Args Constructor     */
    public PurchaseInfo(){
    }

    /**
     * Constructor
     * @param price double
     * @param quantity  int
     * @param itemName  String
     */
    public PurchaseInfo(double price, int quantity, String itemName){
        this.price = price;
        this.quantity = quantity;
        this.itemName = itemName;
    }

    /**
     * Get Method -- Price
     * @return price double
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Get Method -- Quantity
     * @return quantity int
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Get Method -- Item Name
     * @return itemName String
     */
    public String getItemName(){
        return this.itemName;
    }
}
