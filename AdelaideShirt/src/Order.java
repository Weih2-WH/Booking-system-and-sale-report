
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 *  The order params and the relevant methods
 */
//The detail of order item
public class Order {
    public int ID = 0;
    public String orderDate;
    public int shirtID;
    public int orderNumber;
    public double amount;
    public int clientID;
    public int paymentID;
    public String status;
    //connection params
    String driver = "com.mysql.cj.jdbc.Driver";  // 連結 MySQL 驅動程式
    String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
    String user = "root";  // 帳戶 root
    String password = "Well@1201";

    public Order() {

    }

    public Order(int id, String orderDate, int shirtID, int orderNumber, double amount, int clientID, int paymentID, String status) {
        this.ID = id;
        this.orderDate = orderDate;
        this.shirtID = shirtID;
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.clientID = clientID;
        this.paymentID = paymentID;
        this.status = status;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getID: Get ID
     */
    public Integer getID() {
        return ID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getOrderDate: Get order date
     */
    public String getOrderDate() {
        return orderDate;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getShirtID: Get shirt ID
     */
    public Integer getShirtID() {
        return shirtID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getOrderNumber: Get order number
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getAmount: Get amount for the ordered item
     */
    public double getAmount() {
        return amount;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getClientID: Get client ID how order
     */
    public int getClientID() {
        return clientID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getPaymentID: Get payment ID for the order
     */
    public int getPaymentID() {
        return paymentID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getStatus: Get the inventory status
     */
    public String getStatus(){
        return status;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  newOrder: create the order and record
     */
   //new order
    public int newOrder(int shirtID, int orderNumber, int clientID ,int paymentID) throws SQLException, ClassNotFoundException {
        double tAmount = 0.0d;
        //check for the Inventory
        String asql = "select price,inventory from  AdelaideShirt.ShirtCatalog where id = " + shirtID;
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(asql);
            while(rs.next()) {
                tAmount = rs.getDouble("price");
                if (orderNumber > rs.getInt("inventory")) {
                    //when the inventory is valid
                    this.status = "The order will deliver later.";
                } else {
                    //when out off inventory
                    this.status = "The order will send in three days.";
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Get current max OrderID and provide the new ID for the new order
        int mID = 0;
        String isql = "select max(ID)+1 as ID from  AdelaideShirt.ShirtOrder";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(isql);
            while(rs.next()) {
                mID = rs.getInt("ID");
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //create the Order
        String sql = "insert into  AdelaideShirt.ShirtOrder values(?,now(),?,?,?,?,?,?)";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mID);
            ps.setInt(2,shirtID);
            ps.setInt(3, orderNumber);
            ps.setDouble(4, tAmount*orderNumber);
            ps.setInt(5, clientID);
            ps.setInt(6, paymentID);
            ps.setString(7, status);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //deduct the inventory
        Catalog c = new Catalog();
        c.changeInventory(shirtID,orderNumber);
        System.out.println("New Order done");
        return mID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkAll: list all the order in the DB
     */
    //List all the order
    public ArrayList<Order> checkAll() {
        String sql = "select ID ,orderDate ,shirtID ,orderNumber ,amount ,clientID ,paymentID, status from AdelaideShirt.ShirtOrder";
        ArrayList<Order> result = new ArrayList<Order>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Order order = new Order();
                order.ID = rs.getInt("ID");
                order.orderDate = rs.getString("orderDate");
                order.shirtID = rs.getInt("shirtID");
                order.orderNumber = rs.getInt("orderNumber");
                order.amount = rs.getDouble("amount");
                order.clientID = rs.getInt("clientID");
                order.paymentID = rs.getInt("paymentID");
                order.status = rs.getString("status");
                result.add(order);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }
}
