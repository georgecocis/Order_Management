package DAO;

import DataAccess.ConnectionFactory;
import Model.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAO {
    private String s ="";
    private PreparedStatement statem = null;
    private ConnectionFactory conn = new ConnectionFactory();

    /**
     * This method returns a select from client query
     * @return query
     */

    public String SelectAllClientQuery() {
        s = "select * from client";
        return s;
    }

    /**
     * This method returns an insertion into client query
     * @return query
     */

    private String insertClientQuery() {
        s = "insert into client (idclient, name, location) values (?, ?, ?)";
        return s;
    }

    /**
     * This method returns a delete from client query
     * @return query
     */
    private String deleteClientQuery() {
        s = "delete from client where (name=? AND location=?)";
        return s;
    }

    /**
     * This method executes the statement on the insert into client query
     * @param client client object
     * @throws SQLException
     */
    public void executionInsertClient(Client client) throws SQLException {
            statem = conn.createStatement(statem, insertClientQuery());
            statem.setInt(1,client.getIdclient());
            statem.setString(2, client.getName());
            statem.setString(3, client.getLocation());
            conn.executeStatement(statem);
    }

    /**
     * This method executes the statement on the delete from client query
     * @param s1 string for delete query
     * @param s2 string for delete query
     * @throws SQLException
     */

    public void executionDeleteClient(String s1, String s2) throws SQLException {
            statem = conn.createStatement(statem, deleteClientQuery());
            statem.setString(1,s1);
            statem.setString(2, s2);
            conn.executeStatement(statem);
    }
}
