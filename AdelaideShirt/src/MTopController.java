import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * @author Wei Huang
 * @Since 2021/11/22
 *  Show the monthly sale top 3 data in table
 *  The class to control the MToppage.fxml
 */
public class MTopController implements Initializable {

    //the element that will be used to show on the Monthly top 3 report
    @FXML
    public TableView<MTop> t;
    //order month
    @FXML
    public TableColumn<MTop,Integer> orderM;
    //shirt type
    @FXML
    public TableColumn<MTop,String> shirtT;
    //shirt ID
    @FXML
    public TableColumn<MTop, Integer> shirtID;
    //order number
    @FXML
    public TableColumn<MTop, Integer> OrderN;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MTop mtop = new MTop();
        MTop b = null;

        ObservableList<MTop> oblist = FXCollections.observableArrayList();
        //put the data
        for(int i = 0; i < mtop.MTop().size(); ++i) {
            b = mtop.MTop().get(i);
            oblist.add(new MTop(b.getOrderMonth(),b.getShirtType(),b.getShirtID(),b.getOrderNumber()));

        }

        orderM.setCellValueFactory(new PropertyValueFactory<>("orderMonth"));
        shirtT.setCellValueFactory(new PropertyValueFactory<>("shirtType"));
        shirtID.setCellValueFactory(new PropertyValueFactory<>("shirtID"));
        OrderN.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        t.setItems(oblist);
    }
}