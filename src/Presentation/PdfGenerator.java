package Presentation;

import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DataAccess.ConnectionFactory;
import Model.Order;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.stream.Stream;

class PdfGenerator {
    private ClientDAO cdao = new ClientDAO();
    private ProductDAO pdao = new ProductDAO();
    private OrderDAO odao = new OrderDAO();

    /**
     * This method creates a PDF file with data about the clients
     * @param index index of report
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws SQLException
     */

    void createClientReport(int index) throws FileNotFoundException, DocumentException, SQLException {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("ClientReport" + index + ".pdf"));
            doc.open();

            ConnectionFactory conn = new ConnectionFactory();
            Connection connection = conn.createConnection();

            PdfPTable table = new PdfPTable(3);
            Stream.of("idclient", "name", "location").forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setPhrase(new Phrase(columnTitle));
                table.addCell(header);
            });

            PreparedStatement statem = connection.prepareStatement(cdao.SelectAllClientQuery());
            ResultSet rs = statem.executeQuery();

            while (rs.next()){
                table.addCell(String.valueOf(rs.getInt("idclient")));
                table.addCell(String.valueOf(rs.getString("name")));
                table.addCell(String.valueOf(rs.getString("location")));
            }

            doc.add(table);
            doc.close();
    }

    /**
     * This method creates a PDF file with data about the products
     * @param index index of report
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws SQLException
     */

    void createProductReport(int index) throws FileNotFoundException, DocumentException, SQLException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("ProductReport" + index + ".pdf"));
        doc.open();

        ConnectionFactory conn = new ConnectionFactory();
        Connection connection = conn.createConnection();

        PdfPTable table = new PdfPTable(4);
        Stream.of("idproduct", "name", "quantity", "price").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });

        PreparedStatement statem = connection.prepareStatement(pdao.SelectAllProductQuery());
        ResultSet rs = statem.executeQuery();

        while (rs.next()){
            table.addCell(String.valueOf(rs.getInt("idproduct")));
            table.addCell(String.valueOf(rs.getString("name")));
            table.addCell(String.valueOf(rs.getInt("quantity")));
            table.addCell(String.valueOf(rs.getFloat("price")));
        }

        doc.add(table);
        doc.close();
    }

    /**
     * This method creates a PDF with data about the order
     * @param index index of report
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws SQLException
     */

    void createOrderReport(int index) throws FileNotFoundException, DocumentException, SQLException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("OrderReport" + index + ".pdf"));
        doc.open();

        ConnectionFactory conn = new ConnectionFactory();
        Connection connection = conn.createConnection();

        PdfPTable table = new PdfPTable(4);
        Stream.of("idorder", "buyer", "product", "quantity").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });

        PreparedStatement statem = connection.prepareStatement(odao.SelectAllOrderQuery());
        ResultSet rs = statem.executeQuery();

        while (rs.next()){
            table.addCell(String.valueOf(rs.getInt("idorder")));
            table.addCell(String.valueOf(rs.getString("buyer")));
            table.addCell(String.valueOf(rs.getString("product")));
            table.addCell(String.valueOf(rs.getInt("quantity")));
        }

        doc.add(table);
        doc.close();
    }

    /**
     * This method creates a PDF file with billing information, after an order is executed
     * If the order cannot be placed, the method creates a PDF file with an error message
     * @param order an order object
     * @param index index of bill
     * @param state boolean for deciding wether we need a bill/error pdf
     * @param price price of product
     * @param quant quantity of product
     * @throws FileNotFoundException
     * @throws DocumentException
     */

    void createBill(Order order, int index, boolean state, float price, int quant) throws FileNotFoundException, DocumentException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("OrderBill" + index + ".pdf"));
        doc.open();

        Paragraph message = new Paragraph("Bill no. " + index + "\n");
        doc.add(message);

        if (!state){
            message = new Paragraph("Order could not be placed: product out of stock");
            doc.add(message);
            message = new Paragraph("Ordered quantity: " + order.getQuantity() + "\n");
            doc.add(message);
            message = new Paragraph("Stock: " + quant + "\n");
            doc.add(message);
            doc.close();
        }
        else {
            message = new Paragraph("Order id: " + order.getIdorder() + "\n");
            doc.add(message);
            message = new Paragraph("Order recipient: " + order.getBuyer() + "\n");
            doc.add(message);
            message = new Paragraph("Item bought: " + order.getProduct() + "\n");
            doc.add(message);
            message = new Paragraph("Quantity bought: " + order.getQuantity() + "\n");
            doc.add(message);
            message = new Paragraph("Price: " + order.getQuantity() * price + "\n");
            doc.add(message);
            doc.close();
        }
    }
}
