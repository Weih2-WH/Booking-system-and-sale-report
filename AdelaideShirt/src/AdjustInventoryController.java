import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 * When the user would like to add the Inventory the class control the Adjustinvestorypage.fxml
 */

public class AdjustInventoryController implements Initializable {
    public void initialize(URL url, ResourceBundle rb) {
    }
    //the shirt ID
    @FXML
    public TextField shirtID = new TextField() ;
    //the add inventory
    @FXML
    public TextField Inumber = new TextField() ;
    //show the status of the action
    @FXML
    public Label label;
/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The submit function: press the button, will add the inventory based on user input
 */
    public void submit(ActionEvent event) throws ClassNotFoundException {
        //the status for the adjustment of inventory
        String status;
        //show the status on the labal
        label.setText("");
        //use the Catalog function
        Catalog c = new Catalog();
        try {
            //get the user input ID
            int ID = Integer.valueOf(shirtID.getText());
            //check the ID is in the DB or not
            int mID = c.maxID();
            //get the user input added inventory
            int inventory = Integer.valueOf(Inumber.getText());
            //check the ID is valid or not
            if (ID > mID) {
                status = "Out off ID range";
            } else {
                //adjust the inventory
                status = c.inputInventory(Integer.valueOf(shirtID.getText()), Integer.valueOf(Inumber.getText()));
            }
            label.setText(status);
        }catch(NumberFormatException ex){
            ex.printStackTrace();
            label.setText("Please enter only Int.");
        }

    }


}
