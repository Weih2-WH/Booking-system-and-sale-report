import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The class to control Reportpage.fxml and each report page
 * Use the data in the ReportData to show the chart in the windows
 */
public class ReportController implements Initializable {

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toStockTake: show the inventory chart page
     */
    //Inventory for each type
    public void toStockTake(ActionEvent event) {
        // Create the PieChart
        PieChart chart = new PieChart();
        // Set the Title of the Chart
        chart.setTitle("Inventory for each type");
        // Place the legend on the left side
        chart.setLegendSide(Side.LEFT);
        // Set the Data for the Chart
        ObservableList<PieChart.Data> pieData = ReportData.getTypeInventory();
        chart.setData(pieData);

        var root = new HBox();

        var scene = new Scene(root, 450, 330);

        root.getChildren().add(chart);

        //new stage
        Stage stage = new Stage();
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Display the Stage
        stage.show();

    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toQSales: show the quarterly sale report page
     */
    //Quarterly sales of Shirts Size
    public void toQSales(ActionEvent event) {

        // Create the X-Axis
        CategoryAxis xAxis = new CategoryAxis();
        // Set the Label for the Axis
        xAxis.setLabel("Shirt Type");

          String driver = "com.mysql.cj.jdbc.Driver";
          String url = "jdbc:mysql://localhost:3306/AdelaideShirt";
          String user = "root";
          String password = "Well@1201";
        String sql = "select concat(year(orderDate) ,'-',quarter(orderDate) ) as orderQuarter from AdelaideShirt.ShirtOrder group by concat(year(orderDate) ,'-',quarter(orderDate) )";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                // Add the Categories to the Axis
                xAxis.getCategories().add(rs.getString("orderQuarter"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }


        // Create the Y-Axis
        NumberAxis yAxis = new NumberAxis();
        // Set the Label for the Axis
        yAxis.setLabel("Dollar");
        // Create the Chart
        StackedBarChart<String, Number> stackedBar = new StackedBarChart<>(xAxis, yAxis);
        // Set the Title for the Chart
        stackedBar.setTitle("Quarterly sales of Shirts Size");
        // Set the data for the chart
        ObservableList<XYChart.Series<String, Number>> stackedData = ReportData.getQSales();
        stackedBar.setData(stackedData);

        var root = new HBox();

        var scene = new Scene(root, 450, 330);

        root.getChildren().add(stackedBar);

        // Create the Scene
        //new stage
        Stage stage = new Stage();
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Display the Stage
        stage.show();

    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toCustomer: show the client report chart page
     */
    //Number of new client by gender by subscribe year
    public void toCustomer(ActionEvent event) {
            //First chart
            // Create the X-Axis
            NumberAxis xAxis = new NumberAxis();
            xAxis.setLabel("Year");
            // Customize the X-Axis, so points are scattered uniformly
            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(2018);
            xAxis.setUpperBound(2025);
            xAxis.setTickUnit(1);

            // Create the Y-Axis
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Number of client");

            // Create the LineChart
            LineChart<Number,Number> LineChart = new LineChart<>(xAxis, yAxis);
            // Set the Title for the Chart
            LineChart.setTitle("Number of new client by gender by subscribe year");
            // Set the Data for the Chart
            ObservableList<XYChart.Series<Number,Number>> chartData = ReportData.getClient();
            LineChart.setData(chartData);

            //Second chart
        // Create the X-Axis
        CategoryAxis xAxis2 = new CategoryAxis();
        xAxis2.setLabel("Client ID");

        // Create the Y-Axis
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Number of order");

        // Create the LineChart
        BarChart<String,Number> LineChart2 = new BarChart<>(xAxis2,yAxis2);
        // Set the Title for the Chart
        LineChart2.setTitle("Number of order by client");
        // Set the Data for the Chart
        ObservableList<XYChart.Series<String,Number>> chartData2 = ReportData.getClientBuy();
        LineChart2.setData(chartData2);


            var root = new HBox();

            var scene = new Scene(root, 450, 330);

            root.getChildren().add(LineChart);
            root.getChildren().add(LineChart2);

            //new stage
            Stage stage = new Stage();
            // Add the Scene to the Stage
            stage.setScene(scene);
            // Display the Stage
            stage.show();

    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toMTop: go to the MToppage.fxml
     */

    public void toMTop(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MToppage.fxml"));
            Stage stage = new Stage();//new stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
