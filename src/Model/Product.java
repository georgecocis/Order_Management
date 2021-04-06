package Model;

public class Product {
    private int idproduct;
    private String name;
    private int quantity;
    private float price;

    /**
     * This is the product class constructor
     * @param ID  product's iD
     * @param name  product's name
     * @param quantity  product's quantity
     * @param price product's price
     */

    public Product(int ID, String name, int quantity, float price)
    {
        this.idproduct = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * ID setter
     * @param ID ID of product
     */

    public void setIdproduct (int ID) {this.idproduct = ID;}

    /**
     * ID getter
     * @return ID of product
     */

    public int getIdproduct() {return this.idproduct;}

    /**
     * Name setter
     * @param name name of product
     */

    public void setName (String name) {this.name = name;}

    /**
     * Name getter
     * @return name of product
     */

    public String getName() {return this.name;}

    /**
     * Price setter
     * @param price price of product
     */

    public void setPrice (int price) {this.price = price;}

    /**
     * Price getter
     * @return price of product
     */

    public float getPrice() {return this.price;}


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
