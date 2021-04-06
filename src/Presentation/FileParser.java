package Presentation;

import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Client;
import Model.Order;
import Model.Product;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FileParser {
    private int clientIndex = 3;
    private int productIndex = 4;
    private int orderIndex = 2;
    private int clientReport = 0;
    private int productReport = 0;
    private int orderReport = 0;
    private int billNumber = 0;
    private ResultSet rs = null;

    private ClientDAO cdao = new ClientDAO();
    private ProductDAO pdao = new ProductDAO();
    private OrderDAO odao = new OrderDAO();
    private PdfGenerator gen = new PdfGenerator();

    /**
     * This method returns the first name & the second name as a unique string
     * @param firstName First name of client
     * @param lastName Last name of client
     * @return Full name of client
     */

    private String createCLientName(String firstName, String lastName) {
        String fullName ="";
        fullName = firstName + " " + lastName;
        fullName = fullName.substring(0, fullName.length() - 1);
        return fullName;
    }

    /**
     * This method parses the input file and executes the required operations
     * @param input input file
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws DocumentException
     */

    public void readFile(File input) throws SQLException, FileNotFoundException, DocumentException {
        Scanner scan;
        scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String[] s = scan.nextLine().split(" ");
            switch (s[0].toLowerCase()) {
                case "insert":
                    if (s[1].toLowerCase().equals("client:")) {
                        Client client = new Client(++clientIndex, createCLientName(s[2], s[3]), s[4]);
                        cdao.executionInsertClient(client);
                    }
                    else if (s[1].toLowerCase().equals("product:")) {
                        Product product = new Product(productIndex, s[2].substring(0, s[2].length() - 1), Integer.parseInt(s[3].substring(0, s[3].length() - 1)), Float.parseFloat(s[4]));
                        if (!pdao.selectGivenProduct(product, rs)) {
                            pdao.executionInsertProduct(product);
                            ++productIndex;
                        }
                        else
                        pdao.insertExistingProduct(product, pdao.getExistingQuant(product.getName(), rs));
                    }
                    break;
                case "order:":
                    Order order = new Order(++orderIndex, createCLientName(s[1], s[2]), s[3].substring(0, s[3].length() - 1), Integer.parseInt(s[4]));
                    if (odao.verifyValidity(order, pdao.getExistingQuant(order.getProduct(), rs))) {
                        odao.executionOrder(order);
                        pdao.decreaseExistingProduct(order, pdao.getExistingQuant(order.getProduct(), rs));
                        gen.createBill(order, ++billNumber, true, pdao.getExistingPrice(order.getProduct(), rs), pdao.getExistingQuant(order.getProduct(),rs));
                    }
                    else
                        gen.createBill(order, ++billNumber, false, pdao.getExistingPrice(order.getProduct(), rs), pdao.getExistingQuant(order.getProduct(),rs));
                    break;
                case "delete":
                    if (s[1].toLowerCase().equals("client:"))
                        cdao.executionDeleteClient(createCLientName(s[2], s[3]), s[4]);
                    else if (s[1].toLowerCase().equals("product:"))
                        pdao.executionDeleteProduct(s[2]);
                    break;
                case "report":
                    if (s[1].toLowerCase().equals("client"))
                        gen.createClientReport(++clientReport);
                    else if (s[1].toLowerCase().equals("product"))
                        gen.createProductReport(++productReport);
                    else
                        gen.createOrderReport(++orderReport);
                    break;
                default:
                    throw new RuntimeException("Error with input format");
            }
        }
    }
}
