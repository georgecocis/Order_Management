package DataAccess;

import java.sql.*;
public class ConnectionFactory {
    private Connection conn = null;

    /**
     * This method creates a statement on a given query
     * @param statement an empty statement
     * @param s query for statement
     * @return a statement
     */

    public PreparedStatement createStatement(PreparedStatement statement, String s) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldb?autoReconnect=true&useSSL=false","root","tsmtheoddone1");
            statement = conn.prepareStatement(s);
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        return statement;
    }

    /**
     * This method executes the given statement
     * @param statement a statement
     */

    public void executeStatement(PreparedStatement statement) {
        try {
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This method creates a connection to the database
     * @return the connection
     */

    public Connection createConnection() {
        try {
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldb?autoReconnect=true&useSSL=false","root","tsmtheoddone1");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return conn;
    }

    /**
     * This method closes the connection to the database
     * @param connection connection
     * @throws SQLException
     */

    public static void close(Connection connection) throws SQLException {
        connection.close();
        System.out.println("Connection closed.");
    }
}
