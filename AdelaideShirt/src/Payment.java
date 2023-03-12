import java.sql.*;
import java.util.ArrayList;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 *  The payment params and the relevant methods
 */
//the payment detail
public class Payment {
    public int ID = 0;
    public int clientID;
    public String payType;
    public String cardType;
    public String cardNumber;
    public String CSC;
    public String cardDeadline;
    //connection params
    String driver = "com.mysql.cj.jdbc.Driver";  // 連結 MySQL 驅動程式
    String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
    String user = "root";  // 帳戶 root
    String password = "Well@1201";

    public Payment() {

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
     *  getClientID: Get client ID of the Payment
     */
    public Integer getClientID() {
        return clientID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getPayType: Get pay type(Cash/Card)
     */
    public String getPayType() {
        return payType;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getCardType: Get card type(Master/Visa)
     */
    public String getCardType() {
        return cardType;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getCardNumber: Get card number
     */
    public String getCardNumber() {
        return cardNumber;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getCSC: Get CSC
     */
    public String getCSC() {
        return CSC;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getCardDeadline: Get card deadline
     */
    public String getCardDeadline() {
        return cardDeadline;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  newPayment: create the new payment and record
     */
    //create the payment
    public void newPayment(int id, int clientID, String payType, String cardType, String cardNumber, String CSC,String cardDeadline) throws SQLException, ClassNotFoundException {
        String sql = "insert into  AdelaideShirt.Payment values(?,?,?,?,?,?,?)";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, clientID);
            ps.setString(3, payType);
            ps.setString(4, cardType);
            ps.setString(5, cardNumber);
            ps.setString(6, CSC);
            ps.setString(7, cardDeadline);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("New Payment done");
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  checkAll: List all the payment in the DB
     */

    public ArrayList<Payment> checkAll() {
        String sql = "select ID ,clientID ,payType ,cardType ,cardNumber ,CSC ,cardDeadline from AdelaideShirt.Payment";
        ArrayList<Payment> result = new ArrayList<Payment>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Payment payment = new Payment();
                payment.ID = rs.getInt("ID");
                payment.clientID = rs.getInt("clientID");
                payment.payType = rs.getString("payType");
                payment.cardType = rs.getString("cardType");
                payment.cardNumber = rs.getString("cardNumber");
                payment.CSC = rs.getString("CSC");
                payment.cardDeadline = rs.getString("cardDeadline");
                result.add(payment);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  maxID: Get new ID for the new payment
     */
    public int maxID (){
        int mID = 0;
        String isql = "select max(ID)+1 as ID from  AdelaideShirt.Payment";
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
