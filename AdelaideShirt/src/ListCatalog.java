import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The class to control the list of the catalog information
 */

public class ListCatalog {

    //the detail of the item
    public TextField orderNumber;
    public int ID = 0;
    public String description;
    public double price;
    public int inventory;
    public String discount;

    //the detail for connecting to the DB
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
    String user = "root";  // 帳戶 root
    String password = "Well@1201";

    public ListCatalog(){}

    public ListCatalog(String OrderNumber, int ID, String Description,double Price,int Inventory,String Discount) {
        this.orderNumber = new TextField(OrderNumber);
        this.ID = ID;
        this.description = Description;
        this.price = Price;
        this.inventory = Inventory;
        this.discount = Discount;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkAll: List all catalog
     */
    //get all the item from the DB
    public ArrayList<ListCatalog> checkAll() {
        String sql = "select ID ,description ,price ,inventory ,discount from AdelaideShirt.ShirtCatalog";
        ArrayList<ListCatalog> result = new ArrayList<ListCatalog>();
        OrderController BControl = new OrderController();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ListCatalog catalog = new ListCatalog("",rs.getInt("ID"),rs.getString("description"),
                        rs.getDouble("price"),rs.getInt("inventory"),rs.getString("discount"));
                result.add(catalog);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  setOrderNumber: set the order number
     */
    public void setOrderNumber(TextField orderNumber) {
        this.orderNumber = orderNumber;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getOrderNumber: get the order number
     */
    public TextField getOrderNumber(){
        return orderNumber;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getID: get ID
     */
    public int getID() {
        return ID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getDescription: get description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getPrice: get price
     */
    public double getPrice() {
        return price;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getInventory: get inventory
     */
    public int getInventory() {
        return inventory;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getDiscount: get discount
     */
    public String getDiscount() {
        return discount;
    }
}

