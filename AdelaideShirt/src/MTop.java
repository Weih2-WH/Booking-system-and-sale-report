import java.sql.*;
import java.util.ArrayList;
/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The class for list of Monthly Top 3 sales for each ShirtType
 */

public class MTop {

    public String orderMonth;
    public String shirtType;
    public int shirtID;
    public int orderNumber;

    //connect to the DB
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
    String user = "root";  // 帳戶 root
    String password = "Well@1201";

    public MTop(){

    }


    public MTop(String orderM, String orderT, int shirtID, int orderNumber) {
        this.orderMonth = orderM;
        this.shirtType = orderT;
        this.shirtID = shirtID;
        this.orderNumber = orderNumber;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getOrderMonth: Get order month
     */
    public String getOrderMonth() {
        return orderMonth;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getShirtType: Get shirt type
     */
    public String getShirtType() {
        return shirtType;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getShirtID: Get shirt ID
     */
    public int getShirtID() {
        return shirtID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  getOrderNumber: Get order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     *  MTop: Get the data for the monthly top 3
     */
    public ArrayList<MTop> MTop() {
        String sql = "select s1.* from " +
                "(select date_format(orderDate,'%Y-%m') orderMonth ,c.shirtType ,o.shirtID,sum(orderNumber) orderNumber  from AdelaideShirt.ShirtOrder  o " +
                "inner join " +
                "AdelaideShirt.ShirtCatalog c " +
                "on o.shirtID = c.ID where orderDate > '2021-06-30' " +
                "group by date_format(orderDate,'%Y-%m'),o.shirtID,c.shirtType) s1 " +
                "where " +
                "(select count(1) " +
                "from (select date_format(orderDate,'%Y-%m') orderMonth ,c.shirtType ,o.shirtID,sum(orderNumber) orderNumber  from AdelaideShirt.ShirtOrder  o " +
                "inner join " +
                "AdelaideShirt.ShirtCatalog c " +
                "on o.shirtID = c.ID where orderDate > '2021-06-30' " +
                "group by date_format(orderDate,'%Y-%m'),o.shirtID,c.shirtType) s2 " +
                "where s1.orderMonth=s2.orderMonth " +
                "and s1.shirtType=s2.shirtType " +
                "and s1.orderNumber<s2.orderNumber)<3 " +
                "order by s1.orderMonth,s1.shirtType,s1.orderNumber desc;";
        //The list show in table view
        ArrayList<MTop> result = new ArrayList<MTop>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //put the data in the list
            while (rs.next()) {
                MTop mTop = new MTop();
                mTop.orderMonth = rs.getString("orderMonth");
                mTop.shirtType = rs.getString("shirtType");
                mTop.shirtID = rs.getInt("shirtID");
                mTop.orderNumber = rs.getInt("orderNumber");
                result.add(mTop);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return result;
    }
}
