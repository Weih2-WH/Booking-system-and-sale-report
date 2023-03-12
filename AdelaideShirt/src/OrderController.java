import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 *  After order, the page shows the detail of the order
 *  The class to control the Orderpage.fxml
 */
//the controller for order page
public class OrderController implements Initializable {
    int clientID;
    int paymentID;
    ArrayList<Integer> orderID = new ArrayList<Integer>();
    ListCatalog c = new ListCatalog();
    ListCatalog b = null;
    @FXML
    public TableView<ListCatalog> t1;
    //OrderNumber
    @FXML
    public TableColumn<ListCatalog,TextField> c2;
    //OrderID
    @FXML
    public TableColumn<ListCatalog,Double> c3;
    //Description
    @FXML
    public TableColumn<ListCatalog,Integer> c4;
    //Uniprice
    @FXML
    public TableColumn<ListCatalog,String> c5;
    //Inventory
    @FXML
    public TableColumn<ListCatalog, Integer> c6;
    //Discount
    @FXML
    public TableColumn<ListCatalog, String> c7;
    //finish the order and store to the DB
    @FXML
    public Button Done;

    ObservableList<ListCatalog> oblist = FXCollections.observableArrayList();
    //show the ordering item list
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get the order detail
        for(int i = 0; i < c.checkAll().size(); ++i) {
            b = c.checkAll().get(i);
            String s = String.valueOf(i);
            oblist.add(new ListCatalog("",
                    b.getID(),b.getDescription(),b.getPrice(),b.getInventory(),b.getDiscount()));

        }

        c2.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        c3.setCellValueFactory(new PropertyValueFactory<>("ID"));
        c4.setCellValueFactory(new PropertyValueFactory<>("description"));
        c5.setCellValueFactory(new PropertyValueFactory<>("price"));
        c6.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        c7.setCellValueFactory(new PropertyValueFactory<>("discount"));
        t1.setItems(oblist);
    }

    //get the client and payment ID
    public void setClientID(int Client,int Payment){
        this.clientID = Client;
        this.paymentID = Payment;
    }

    //pass the order detail to the DB
    public void order(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<ListCatalog> orderCatalog = FXCollections.observableArrayList();
        for(ListCatalog bean : oblist){
            if(! bean.getOrderNumber().getText().isEmpty()){
                Order order = new Order();
                int orderNumber = Integer.valueOf(bean.getOrderNumber().getText());
                int mID = order.newOrder(bean.getID(),orderNumber,clientID,paymentID);
                this.orderID.add(mID);
            }
        }

        //show on the table
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("Donepage.fxml"));
            loader.load();
            DoneController sn=loader.getController();
            sn.setOrderID(orderID);
            Parent p =loader.getRoot();
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.setScene(scene);
            stage.show();
            Stage close = (Stage) Done.getScene().getWindow();
            close.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
