import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The class to control the Frontpage.fxml
 * Controlling the button on the front page
 */
public class FrontController implements Initializable
{
    public void initialize(URL url, ResourceBundle rb)
    {
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toOrder: go to the Clientpage
     */

    public void toOrder(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Clientpage.fxml"));
            Stage stage = new Stage();//new stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toReport: go to the report list page
     */
    public void toReport(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Reportpage.fxml"));
            Stage stage = new Stage();//new stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toDiscount: randomly choose an item for having discount and direct to show the detail of the set of new catalog
     */
    public void toDiscount(ActionEvent event) throws ClassNotFoundException {
        //the id that be chosen
        int id = 0;
        Catalog c = new Catalog();
        //the original price
        double op = 0d;
        //the max item that the system has
        int mid = c.maxID();
        //randomly choose the item to set the discount
        if(id == 0|| c.checkDiscount(id) == "Y" ){
           id = (int) (Math.random()*mid);
        }
        //update the price and the status of discount for the item
        op = c.checkPrice(id);
        c.changePrice(id,op*0.85);
        c.changeDiscount(id,"Y");

        //setting the discountController
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("Discountpage.fxml"));
            loader.load();
            DiscountController sn=loader.getController();
            sn.setCid(id);
            Parent p =loader.getRoot();
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * toAdjustInventory: go to the adjusting inventory page
     */
    public void toAdjustInventory(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdjustInventorypage.fxml"));
            Stage stage = new Stage();//new stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
