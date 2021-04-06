package Start;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;

import DataAccess.ConnectionFactory;
import Presentation.FileParser;
import com.itextpdf.text.DocumentException;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException, DocumentException {
        ConnectionFactory conn = new ConnectionFactory();
        try {
            Connection newConn = conn.createConnection();
            System.out.println("Connection open");
        } catch (Exception ex) {
            System.out.println("Error");
        }
        File input = new File("input.txt");
        FileParser fp = new FileParser();
        fp.readFile(input);
    }
}
