import java.sql.*;
import java.util.ArrayList;

public class QSales {
    public int orderQuarter;
    public int shirtID;
    public String color;
    public String shirtSize;
    public String description;
    public double unitPrice;
    public int orderNumber;
    public double amount;

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
    String user = "root";
    String password = "Well@1201";

    public int getOrderQuarter() {
        return orderQuarter;
    }

    public int getShirtID() {
        return shirtID;
    }

    public String getColor() {
        return color;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public String  getDescription() {
        return description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public double getAmount() {
        return amount;
    }

    public ArrayList<QSales> QSales(int year) {
        String sql = "select o.orderQuarter,o.shirtID,c.shirtType ,c.color ,c.shirtSize ,c.description ,c.uniprice, o.orderNumber,o.amount\n" +
                "from \n" +
                "(select quarter(oderDate) orderQuarter ,shirtID ,sum(orderNumber) orderNumber ,sum(amount) amount from AdelaideShirt.ShirtOrder \n" +
                "where year(oderDate) = "+year+"\n" +
                "group by quarter(orderDate),shirtID) o\n" +
                "inner join \n" +
                "AdelaideShirt.ShirtCatalog c\n" +
                "on o.shirtID = c.ID;";
        ArrayList<QSales> result = new ArrayList<QSales>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                QSales qSales = new QSales();
                qSales.orderQuarter = rs.getInt("orderQuarter");
                qSales.shirtID = rs.getInt("shirtID");
                qSales.color = rs.getString("color");
                qSales.shirtSize = rs.getString("shirtSize");
                qSales.description = rs.getString("description");
                qSales.unitPrice = rs.getDouble("unitPrice");
                qSales.orderNumber = rs.getInt("orderNumber");
                qSales.amount = rs.getDouble("amount");
                result.add(qSales);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }
}
