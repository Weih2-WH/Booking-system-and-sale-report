import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The class for data structure that show in report
 */
public class ReportData {
    //connection params
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
    public static String user = "root";
    public static String password = "Well@1201";
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * getTypeInventory: make the inventory data in the pie chart
     */
    // Create PieChart Data
    public static ObservableList<PieChart.Data> getTypeInventory() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        //search from the database
        String sql = "select shirtType,sum(inventory) as inventory  from AdelaideShirt.ShirtCatalog group by shirtType";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //put into the data
            while (rs.next()) {
                data.add(new PieChart.Data(rs.getString("shirtType")+" : "+rs.getInt("inventory"), rs.getInt("inventory")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return data;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * getQSales: make the quarterly sale data in the XYChart
     */
    public static ObservableList<XYChart.Series<String, Number>> getQSales() {
        //record the data sort by shirt size
        XYChart.Series<String, Number> seriesSS = new XYChart.Series<String, Number>();
        seriesSS.setName("Shirt-S");
        XYChart.Series<String, Number> seriesSM = new XYChart.Series<String, Number>();
        seriesSM.setName("Shirt-M");
        XYChart.Series<String, Number> seriesSL = new XYChart.Series<String, Number>();
        seriesSL.setName("Shirt-L");
        //record the data sort by Trousers size
        XYChart.Series<String, Number> seriesTS = new XYChart.Series<String, Number>();
        seriesTS.setName("Trousers-S");
        XYChart.Series<String, Number> seriesTM = new XYChart.Series<String, Number>();
        seriesTM.setName("Trousers-M");
        XYChart.Series<String, Number> seriesTL = new XYChart.Series<String, Number>();
        seriesTL.setName("Trousers-L");
        //record the data sort by Jackets size
        XYChart.Series<String, Number> seriesJS = new XYChart.Series<String, Number>();
        seriesJS.setName("Jackets-S");
        XYChart.Series<String, Number> seriesJM = new XYChart.Series<String, Number>();
        seriesJM.setName("Jackets-M");
        XYChart.Series<String, Number> seriesJL = new XYChart.Series<String, Number>();
        seriesJL.setName("Jackets-L");

        //search from the database
        String sql = "select concat(o.orderYear,'-',o.orderQuarter) as orderQuarter ,concat(c.shirtType,'-',c.shirtSize) as  shirtType, sum(o.orderNumber) as orderNumber " +
                "from " +
                "(select year(orderDate) orderYear,quarter(orderDate) orderQuarter ,shirtID ,sum(orderNumber) orderNumber ,sum(amount) amount from AdelaideShirt.ShirtOrder " +
                "group by year(orderDate) ,quarter(orderDate),shirtID) o " +
                "inner join " +
                "AdelaideShirt.ShirtCatalog c " +
                "on o.shirtID = c.ID " +
                "group by concat(o.orderYear,'-',o.orderQuarter),concat(c.shirtType,'-',c.shirtSize); ";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            // get the data
            while (rs.next()) {
                String a = rs.getString("shirtType");
                System.out.println(a);
                System.out.println(a.equals("Shirts-S"));
                if (a.equals("Shirts-S")) {
                    seriesSS.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                } else if (a.equals("Shirts-M")) {
                    seriesSM.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                } else if (a.equals("Shirts-L")) {
                    seriesSL.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                } else if (a.equals("Trousers-S")) {
                    seriesTS.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                } else if (a.equals("Trousers-M")) {
                    seriesTM.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                } else if (a.equals("Trousers-L")) {
                    seriesTL.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                } else if (a.equals("Jackets-S")) {
                    seriesJS.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                } else if (a.equals("Jackets-M")) {
                    seriesJM.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                } else if (a.equals("Jackets-L")) {
                    seriesJL.getData().add(new XYChart.Data<String, Number>(rs.getString("orderQuarter"), rs.getInt("orderNumber")));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }


        //put into the data
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.<XYChart.Series<String, Number>>observableArrayList();
        data.add(seriesSS);
        data.add(seriesSM);
        data.add(seriesSL);
        data.add(seriesTS);
        data.add(seriesTM);
        data.add(seriesTL);
        data.add(seriesJS);
        data.add(seriesJM);
        data.add(seriesJL);
        return data;
    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * getClient: make the client data in the XYChart
     */

    public static ObservableList<XYChart.Series<Number, Number>> getClient() {
        //set data series name
        XYChart.Series<Number, Number> seriesM = new XYChart.Series<Number, Number>();
        seriesM.setName("Male");
        XYChart.Series<Number, Number> seriesF = new XYChart.Series<Number, Number>();
        seriesF.setName("Female");
        XYChart.Series<Number, Number> seriesN = new XYChart.Series<Number, Number>();
        seriesN.setName("Non");

        String sql = "select gender,year(createDate) as createYear ,count(ID) as clients from AdelaideShirt.Client c \n" +
                "group by gender,year(createDate)";
        // get the data
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String a = rs.getString("gender");
                if (a.equals("M")) {
                    seriesM.getData().add(new XYChart.Data<Number, Number>(rs.getInt("createYear"), rs.getInt("clients")));
                } else if (a.equals("F")) {
                    seriesF.getData().add(new XYChart.Data<Number, Number>(rs.getInt("createYear"), rs.getInt("clients")));

                } else if (a.equals("N")) {
                    seriesN.getData().add(new XYChart.Data<Number, Number>(rs.getInt("createYear"), rs.getInt("clients")));

                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        // put into data
        ObservableList<XYChart.Series<Number, Number>> data = FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
        data.add(seriesM);
        data.add(seriesF);
        data.add(seriesN);
        return data;

    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * getClient: make the client and consumption data in the XYChart
     */

    public static ObservableList<XYChart.Series<String, Number>> getClientBuy() {
        //set data series name
        XYChart.Series<String, Number> seriesCB = new XYChart.Series<String, Number>();
        seriesCB.setName("CB");


        String sql = "select clientID,sum(orderNumber) as orderNumber from AdelaideShirt.ShirtOrder \n" +
                "group by clientID order by clientID";
        // get the data
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                    seriesCB.getData().add(new XYChart.Data<String, Number>
                            (String.valueOf(rs.getInt("clientID")), rs.getInt("orderNumber")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        // put into data
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.<XYChart.Series<String, Number>>observableArrayList();
        data.add(seriesCB);
        return data;

    }

}