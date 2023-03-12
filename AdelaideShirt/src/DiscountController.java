import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The class to control the Discountpage.fxml
 * Showing a random pick item that set the discount and a set of item for the new catalog
 */
public class DiscountController implements Initializable {

    //the element that will be used to set the discount for the random item
    //the ID that the item will be chosen for discount
    public int cid;
    Catalog c = new Catalog();
    Catalog b = null;
    Catalog p = null;
    HashSet<Integer>  pickID = new HashSet<>();

    @FXML
   public TableView<Catalog> t3;
    //Shirt ID
    @FXML
    public TableColumn<Catalog,Integer> id;
    //Shirt Type
    @FXML
    public TableColumn<Catalog,String> shiT;
    //Shirt color
    @FXML
    public TableColumn<Catalog,String> color;
    //Shirt Size
    @FXML
    public TableColumn<Catalog,String> shiS;
    //Description of the shirt
    @FXML
    public TableColumn<Catalog,String> des;
    //price
    @FXML
    public TableColumn<Catalog,Double> pr;
    //inventory
    @FXML
    public TableColumn<Catalog,Integer> inv;
    //under discount or not: Y/N
    @FXML
    public TableColumn<Catalog,String> dis;

    @FXML
    public TableView<Catalog> t4;
    //Shirt ID
    @FXML
    public TableColumn<Catalog,Integer> id1;
    //Shirt Type
    @FXML
    public TableColumn<Catalog,String> shiT1;
    //Shirt color
    @FXML
    public TableColumn<Catalog,String> color1;
    //Shirt Size
    @FXML
    public TableColumn<Catalog,String> shiS1;
    //Description of the shirt
    @FXML
    public TableColumn<Catalog,String> des1;
    //price
    @FXML
    public TableColumn<Catalog,Double> pr1;
    //inventory
    @FXML
    public TableColumn<Catalog,Integer> inv1;
    //under discount or not: Y/N
    @FXML
    public TableColumn<Catalog,String> dis1;

    //collect the data to show on the table view
    ObservableList<Catalog> oblist = FXCollections.observableArrayList();
    ObservableList<Catalog> cataloglist = FXCollections.observableArrayList();
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * setCid: set the ID for the class
     */
    public void setCid (int CID){
        this.cid = CID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * showTable: show the item that be seted the discount and the set of new catalog
     */
    //press the button to show which item is going to have discount
    public void showTable(ActionEvent event){
        //collect the data from DB
        for(int i = 0; i < c.checkOne(cid).size(); ++i) {
            b = (Catalog)c.checkOne(cid).get(i);
            oblist.add(new Catalog(b.getID(),b.getShirtType(),b.getColor(),b.getShirtSize()
                    ,b.getDescription(),b.getPrice(),b.getInventory(),b.getDiscount()));
        }
        //pass the data to collection
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        shiT.setCellValueFactory(new PropertyValueFactory<>("shirtType"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        shiS.setCellValueFactory(new PropertyValueFactory<>("shirtSize"));
        des.setCellValueFactory(new PropertyValueFactory<>("description"));
        pr.setCellValueFactory(new PropertyValueFactory<>("price"));
        inv.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        dis.setCellValueFactory(new PropertyValueFactory<>("discount"));
        t3.setItems(oblist);

        //pick another 5 random item ID to the set
        while(pickID.size() != 5){
        int nid;
        int mid = c.maxID();
        nid = (int) (Math.random()*mid);
        p = (Catalog)c.checkOne(nid).get(0);
            if(!(nid == cid) || !(p.getDiscount() == "Y")){
                pickID.add(nid);
            }
        }
        //List the picked item
        Iterator iterator = pickID.iterator();
        while(iterator.hasNext()) {
            b = c.checkOne((Integer) iterator.next()).get(0);
            cataloglist.add(new Catalog(b.getID(),b.getShirtType(),b.getColor(),b.getShirtSize()
                    ,b.getDescription(),b.getPrice(),b.getInventory(),b.getDiscount()));
        }

        //pass the data to collection
        id1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        shiT1.setCellValueFactory(new PropertyValueFactory<>("shirtType"));
        color1.setCellValueFactory(new PropertyValueFactory<>("color"));
        shiS1.setCellValueFactory(new PropertyValueFactory<>("shirtSize"));
        des1.setCellValueFactory(new PropertyValueFactory<>("description"));
        pr1.setCellValueFactory(new PropertyValueFactory<>("price"));
        inv1.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        dis1.setCellValueFactory(new PropertyValueFactory<>("discount"));
        t4.setItems(cataloglist);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}