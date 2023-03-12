import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
/**
 * @author Wei Huang
 * @Since 2021/11/22
 *  The client params and the relevant methods
 */
public class Client {
    //The params that will be used for checking client.
    public int ID = 0;
    public String address;
    public String phone;
    public String name;
    public String gender;
    public Date createDate = new Date();

    //The params for connecting to the database
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
    String user = "root";  // 帳戶 root
    String password = "Well@1201";

    public Client() {

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
     *  getAddress: Get Address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getPhone: Get Phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getName: Get Name
     */
    public String getName() {
        return name;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getGender: Get Gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getCreateDate: Get create date
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  newClient: Create new client
     */
    public void newClient(int id, String address, String phone, String name, String gender) throws SQLException, ClassNotFoundException {
        String sql = "insert into  AdelaideShirt.ShirtClient values(?,?,?,?,?,now())";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setString(4, name);
            ps.setString(5, gender);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("New Client done");
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkAll: List all client
     */
    public ArrayList<Client> checkAll() {
        String sql = "select ID ,address ,phone ,name,gender,createDate from AdelaideShirt.Client";
        ArrayList<Client> result = new ArrayList<Client>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client();
                client.ID = rs.getInt("ID");
                client.address = rs.getString("address");
                client.phone = rs.getString("phone");
                client.name = rs.getString("name");
                client.gender = rs.getString("gender");
                client.createDate = rs.getDate("createDate");
                result.add(client);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkClient: List specific client
     */
    public boolean checkClient (Client check) {
        Client a = new Client();
        Client b = null;
        boolean result = true;
        for (int i = 0; i < a.checkAll().size(); i++) {
            if (check.address==b.getAddress()&&check.phone==b.getPhone()&&
                    check.name==b.getName()&&check.gender==b.getGender()) {
                ID = b.getID();
            }else{
                result = false;
            }
        }
        return result;
    }
}
