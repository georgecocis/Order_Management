package DAO;

import DataAccess.ConnectionFactory;
import Model.Order;
import Model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    private String s ="";
    private PreparedStatement statem = null;
    private ConnectionFactory conn = new ConnectionFactory();

    /**
     * This mehods returns a select all from product query
     * @return query
     */

    public String SelectAllProductQuery() {
        s = "select * from product";
        return s;
    }

    /**
     * This method returns an insert into product query
     * @return query
     */

    private String insertProductQuery() {
        s = "insert into `product` (idproduct, name, quantity, price) values (?, ?, ?, ?)";
        return s;
    }

    /**
     * This method returns a delete from product query
     * @return query
     */

    private String deleteProductQuery() {
        s = "delete from product where (name=?)";
        return s;
    }

    /**
     * This method returns an update client query
     * @return query
     */

    private String updateProductQuery() {
        s = "update `product` set quantity=? where name=?";
        return s;
    }

    /**
     * This method returns a select all from product query
     * @return query
     */

    private String selectGivenProductQuery() {
        s = "select * from product where (name=? AND price=?)";
        return s;
    }

    /**
     * This method returns a select quantity from product query
     * @return query
     */

    private String selectQuantFromProductQuery() {
        s = "select quantity from product where name=?";
        return s;
    }

    /**
     * This method returns a select price from product query
     * @return query
     */

    private String selectPriceFromProductQuery(){
        s = "select price from product where name=?";
        return s;
    }

    /**
     * This method executes the statement on the insert into product query
     * @param product product object
     * @throws SQLException
     */

    public void executionInsertProduct(Product product)throws SQLException {
            statem = conn.createStatement(statem, insertProductQuery());
            statem.setInt(1, product.getIdproduct());
            statem.setString(2, product.getName());
            statem.setInt(3, product.getQuantity());
            statem.setFloat(4, product.getPrice());
            conn.executeStatement(statem);
    }

    /**
     * This method checks if the given product already exists in the database
     * @param product product object
     * @param rs resultset object
     * @return true/false, depending if the field exists
     * @throws SQLException
     */

    public boolean selectGivenProduct(Product product, ResultSet rs) throws SQLException {
        statem = conn.createStatement(statem, selectGivenProductQuery());
        statem.setString(1,product.getName());
        statem.setFloat(2,product.getPrice());
        rs = statem.executeQuery();
        return rs.next();
    }

    /**
     * This method executes the statement on the insert into product query for a product already existing in the database
     * @param product product object
     * @param x quantity
     * @throws SQLException
     */

    public void insertExistingProduct(Product product, int x) throws SQLException {
        statem = conn.createStatement(statem, updateProductQuery());
        statem.setInt(1, product.getQuantity() + x);
        statem.setString(2, product.getName());
        conn.executeStatement(statem);
    }

    /**
     * This method returns the quantity of an object already existing in the database
     * @param s name of product
     * @param rs resultset object
     * @return quantity of product
     * @throws SQLException
     */

    public int getExistingQuant(String s, ResultSet rs) throws SQLException {
        statem = conn.createStatement(statem, selectQuantFromProductQuery());
        statem.setString(1,s);
        rs = statem.executeQuery();
        rs.next();
        return rs.getInt("quantity");
    }

    /**
     * This method decrements the quantity of an object after placing an order
     * @param order order object
     * @param x quantity from database
     * @throws SQLException
     */

    public void decreaseExistingProduct(Order order, int x) throws SQLException {
            statem = conn.createStatement(statem, updateProductQuery());
            statem.setInt(1, x - order.getQuantity());
            statem.setString(2, order.getProduct());
            conn.executeStatement(statem);
    }

    /**
     * This method executes the statement on the delete from product query
     * @param s1 product name
     * @throws SQLException
     */

    public void executionDeleteProduct(String s1) throws SQLException {
        statem = conn.createStatement(statem, deleteProductQuery());
        statem.setString(1,s1);
        conn.executeStatement(statem);
    }

    /**
     * This method returns the price of an object from the product table
     * @param s product name
     * @param rs resulset query
     * @return price of product
     * @throws SQLException
     */

    public float getExistingPrice(String s, ResultSet rs) throws SQLException {
        statem = conn.createStatement(statem, selectPriceFromProductQuery());
        statem.setString(1,s);
        rs = statem.executeQuery();
        rs.next();
        return rs.getFloat("price");
    }
}
