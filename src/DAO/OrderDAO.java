package DAO;

import DataAccess.ConnectionFactory;
import Model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {
    private String s ="";
    private PreparedStatement statem = null;
    private ConnectionFactory conn = new ConnectionFactory();

    /**
     * This method returns a select all from order query
     * @return query
     */

    public String SelectAllOrderQuery() {
        s = "select * from `order`";
        return s;
    }

    /**
     * This method returns an insert into order query
     * @return query
     */

    private String insertOrderQuery() {
        s = "insert into `order` (idorder, buyer, product, quantity) values (?, ?, ?, ?)";
        return s;
    }

    /**
     * This method executes the statement on the insert into order query
     * @param order order object
     * @throws SQLException
     */

    public void executionOrder(Order order) throws SQLException {
            statem = conn.createStatement(statem, insertOrderQuery());
            statem.setInt(1, order.getIdorder());
            statem.setString(2, order.getBuyer());
            statem.setString(3, order.getProduct());
            statem.setInt(4,order.getQuantity());
            conn.executeStatement(statem);
    }

    /**
     * This method verifies if the stock of a product is sufficient for an order
     * @param order order object
     * @param x quantity from database
     * @return the remaining quantity
     */

    public boolean verifyValidity(Order order, int x) {
        return x - order.getQuantity() >= 0;
    }
}
