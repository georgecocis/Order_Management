package Model;

public class Order {
    private int idorder;
    private String buyer;
    private String product;
    private int quantity;

    /**
     * This is the order class constructor
     * @param ID order's ID
     * @param buyer product buyer
     * @param product bought product
     * @param quantity quantity of product
     */

    public Order(int ID, String buyer, String product, int quantity)
    {
        this.idorder = ID;
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * ID setter
     * @param ID ID of order
     */

    public void setIdorder (int ID) {this.idorder = ID;}

    /**
     * ID getter
     * @return ID of order
     */

    public int getIdorder() {return this.idorder;}

    /**
     * Buyer setter
     * @param buyer buyer of product
     */

    public void setBuyer (String buyer) {this.buyer = buyer;}

    /**
     * Buyer getter
     * @return buyer of product
     */

    public String getBuyer() {return this.buyer;}

    /**
     * Product name setter
     * @param product bought product
     */

    public void setProduct (String product) {this.product = product;}

    /**
     * Product name getter
     * @return bought product
     */

    public String getProduct() {return this.product;}

    /**
     * Quantity setter
     * @param quantity quantity of product
     */

    public void setQuantity (int quantity) {this.quantity = quantity;}

    /**
     * Quantity getter
     * @return quantity of product
     */

    public int getQuantity() {return this.quantity;}

}
