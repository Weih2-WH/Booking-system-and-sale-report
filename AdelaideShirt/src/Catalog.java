import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The class for catalog and provide the relative function
 */
public class Catalog {
    //The params that will be used for checking sale item.
    public int ID = 0;
    public String shirtType;
    public String color;
    public String shirtSize;
    public String description;
    public double price;
    public int inventory;
    public String discount;
    //The params for connecting to the database
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
    String user = "root";
    String password = "Well@1201";

    public Catalog() {

    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * Create the Catalog by each params
     */
    public Catalog(int id, String shirtType, String color, String shirtSize, String description, double price, int inventory, String discount) {
        this.ID = id;
        this.shirtType = shirtType;
        this.color = color;
        this.shirtSize = shirtSize;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.discount = discount;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getID: Get ID from the class
     */

    public Integer getID() {
        return ID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getShirtType: Get ShirtType from the class
     */
    public String getShirtType() {
        return shirtType;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getColor: Get Color from the class
     */
    public String getColor() {
        return color;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getShirtSize: Get ShirtSize from the class
     */
    public String getShirtSize() {
        return shirtSize;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getDescription: Get Description from the class
     */
    public String getDescription() {
        return description;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getPrice: Get Price from the class
     */
    public Double getPrice() {
        return price;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getInventory: Get Inventory from the class
     */
    public Integer getInventory() {
        return inventory;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getDiscount: Get Discount status from the class
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  newCatalog: Create the new sale item
     */

    public void newCatalog(int id, String shirtType, String color, String shirtSize, String description, double price, int inventory, String discount) throws SQLException, ClassNotFoundException {
        //SQL
        String sql = "insert into  AdelaideShirt.ShirtCatalog values(?,?,?,?,?,?,?,?)";
        try {
            //Connect to DB
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            //Set params
            ps.setInt(1, id);
            ps.setString(2, shirtType);
            ps.setString(3, color);
            ps.setString(4, shirtSize);
            ps.setString(5, description);
            ps.setDouble(6, price);
            ps.setInt(7, inventory);
            ps.setString(8, discount);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Get status
        System.out.println("New Catalog done");
    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  delete: Delete the sale item
     */
    public void delete(int id) throws ClassNotFoundException {
        String sql = "delete from  AdelaideShirt.ShirtCatalog where id = ?";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("delete Catalog " + id + " done");
    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  changePrice: Change the price for specific sale item
     */
    public void changePrice(int id, double price) throws ClassNotFoundException {
        String sql = "update  AdelaideShirt.ShirtCatalog set price = ? where id = ?";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Change " + id + " price done");
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  changeDiscount: Change the status of Discount for specific sale item
     */
    public void changeDiscount(int id, String status) throws ClassNotFoundException {
        String sql = "update  AdelaideShirt.ShirtCatalog set discount = ? where id = ?";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Change " + id + "discount status done");
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  changeInventory: deduct the Inventory for specific sale item
     */
    public String changeInventory(int id, int inventory) throws ClassNotFoundException {
        String status = "Change " + id + " inventory done";
        String sql = "update  AdelaideShirt.ShirtCatalog set inventory = inventory - ? where id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, inventory);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            status = "Fail";
        }
        return status;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  inputInventory: Add the Inventory for specific sale item
     */
    public String inputInventory(int id, int inventory) throws ClassNotFoundException {
        String status = "Change " + id + " inventory done";
        String sql = "update  AdelaideShirt.ShirtCatalog set inventory = inventory + ? where id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, inventory);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            status = "Fail";
        }
        return status;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkInventory: Change the Inventory for specific sale item
     */
    public int checkInventory(int id) throws ClassNotFoundException {
        String sql = "select inventory from AdelaideShirt.ShirtCatalog where id = ? ";
        int cInventory = 0;
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            cInventory = rs.getInt("inventory");
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cInventory;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkPrice: Check the price for specific sale item
     */
    public double checkPrice(int id) throws ClassNotFoundException {
        String sql = "select price from AdelaideShirt.ShirtCatalog where id = ? ";
        double cPrice = 0;
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cPrice = rs.getDouble("price");
            }
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cPrice;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkDiscount: Check the status of Discount for specific sale item
     */
    public String checkDiscount(int id) throws ClassNotFoundException {
        String sql = "select discount from AdelaideShirt.ShirtCatalog where id = ? ";
        String discount = "Y";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                discount = rs.getString("discount");
            }
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return discount;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkAll: List all sale item
     */
    public ArrayList<Catalog> checkAll() {
        String sql = "select ID ,shirtType ,color ,shirtSize ,description ,price ,inventory ,discount from AdelaideShirt.ShirtCatalog";
        ArrayList<Catalog> result = new ArrayList<Catalog>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.ID = rs.getInt("ID");
                catalog.shirtType = rs.getString("shirtType");
                catalog.color = rs.getString("color");
                catalog.shirtSize = rs.getString("shirtSize");
                catalog.description = rs.getString("description");
                catalog.price = rs.getDouble("price");
                catalog.inventory = rs.getInt("inventory");
                catalog.discount = rs.getString("discount");
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
     *  checkOne: List specific sale item
     */
    public ArrayList<Catalog> checkOne(int id) {
        String sql = "select ID ,shirtType ,color ,shirtSize ,description ,price ,inventory ,discount from AdelaideShirt.ShirtCatalog where id = ?";
        ArrayList<Catalog> result = new ArrayList<Catalog>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.ID = rs.getInt("ID");
                catalog.shirtType = rs.getString("shirtType");
                catalog.color = rs.getString("color");
                catalog.shirtSize = rs.getString("shirtSize");
                catalog.description = rs.getString("description");
                catalog.price = rs.getDouble("price");
                catalog.inventory = rs.getInt("inventory");
                catalog.discount = rs.getString("discount");
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
     *  maxID: check the max ID for the catalog list
     */
    public int maxID (){
        int mID = 0;
        String isql = "select max(ID) as ID from  AdelaideShirt.ShirtCatalog";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(isql);
            while (rs.next()) {
                mID = rs.getInt("ID");
            }

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return mID;
    }

}

