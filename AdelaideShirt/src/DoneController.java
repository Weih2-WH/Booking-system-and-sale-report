import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The class to control the Donepage.fxml
 * After the user done the order, the controller create the order and show in the page
 */
public class DoneController implements Initializable {

    //the order ID that in this order
    public ArrayList<Integer> oID = new ArrayList<>();

    @FXML
    public TableView<Order> t2;
    //Order ID
    @FXML
    public TableColumn<Order, Integer> c8;
    //Order Date
    @FXML
    public TableColumn<Order, String> c9;
    //Shirt ID
    @FXML
    public TableColumn<Order, Integer> c10;
    //Order Number
    @FXML
    public TableColumn<Order, Integer> c11;
    //Amount
    @FXML
    public TableColumn<Order, Double> c12;
    //Client ID
    @FXML
    public TableColumn<Order, Integer> c13;
    //Payment ID
    @FXML
    public TableColumn<Order, Integer> c14;
    //status: whether the item will deliver immediately or need to wait
    @FXML
    public TableColumn<Order, String> c15;
    //Back to front page
    @FXML
    public Button b6;

    ////collect the data to show on the table view
    ObservableList<Order> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * setOrderID: set the order ID for the class
     */
    public void setOrderID(ArrayList<Integer> OID){
        this.oID = OID;
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * showTable: show the order and the status
     */
    public void showTable(ActionEvent event) {
        //collect the data from DB
        for (int j = 0; j < oID.size(); ++j) {
            Order o = new Order();
            Order ob = null;
            for (int i = 0; i < o.checkAll().size(); ++i) {
                ob = (Order) o.checkAll().get(i);
                int cID = ob.getID();
                int nID = oID.get(j);
                if (cID == nID) {
                    oblist.add(new Order(ob.getID(),ob.getOrderDate(), ob.getShirtID()
                            , ob.getOrderNumber(), ob.getAmount(), ob.getClientID(), ob.getPaymentID(),ob.getStatus()));
                }
            }
        }
        //pass the data to collection
        c8.setCellValueFactory(new PropertyValueFactory<>("ID"));
        c9.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        c10.setCellValueFactory(new PropertyValueFactory<>("shirtID"));
        c11.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        c12.setCellValueFactory(new PropertyValueFactory<>("amount"));
        c13.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        c14.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        c15.setCellValueFactory(new PropertyValueFactory<>("status"));
        t2.setItems(oblist);
    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toFront: click to open the front page
     */
    public void toFront(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Frontpage.fxml"));
            Stage stage = new Stage();//new stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Stage close = (Stage) b6.getScene().getWindow();
            close.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
