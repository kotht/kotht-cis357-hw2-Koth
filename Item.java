public class Item {
    /** Member Data     */
    private String itemCode;
    private String itemName;
    private double unitPrice;

    /** No-Args Constructor     */
    public Item(){
    }

    /**
     * Constructor
     * @param itemCode  String
     * @param itemName  String
     * @param unitPrice Double
     */
    public Item(String itemCode, String itemName, double unitPrice){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }

    /**
     * Set Item Name Method
     * @param itemName  Name of Specified Item
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Set Unit Price Method
     * @param unitPrice Price of one Unit
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Get Item Code Method
     * @return itemCode String
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * Get Item Name Method
     * @return itemName String
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Get Unit Price Method
     * @return unitPrice Double
     */
    public double getUnitPrice() {
        return this.unitPrice;
    }

    /**
     *  This method returns a string of itemcode, itemname, and unitprice.
     * @return String (itemCode itemName unitPrice)
     */
    public String toString() {
        return itemCode + " " + itemName + " " + unitPrice;
    }
}
