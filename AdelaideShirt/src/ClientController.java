import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 *  Before order, the user needs to enter the client's detail
 *  The class to control the Clientpage.fxml and ClientAlarm.fxml
 */
public class ClientController implements Initializable {
    public void initialize(URL url, ResourceBundle rb) {
    }
    //name
    @FXML
    public TextField te1 = new TextField();
    //Address
    @FXML
    public TextField te2= new TextField();
    //phone
    @FXML
    public TextField te3= new TextField();
    //gender: M(Male)/F(Female)/N(Non)
    @FXML
    public TextField te4= new TextField();
    //type of payment: card or cash
    @FXML
    public TextField te5= new TextField();
    //type of card: Master/ Visa
    @FXML
    public TextField te6= new TextField();
    //card number
    @FXML
    public TextField te7= new TextField();
    //CSC
    @FXML
    public TextField te8= new TextField();
    //card deadline
    @FXML
    public TextField te9= new TextField();
    //to close the alarm page
    @FXML
    public Button b5;
    //to submit the client detail
    @FXML
    public Button submit;
    //get the client ID and payment ID to the order page
    int clientID;
    int paymentID;
    Client client = new Client();
    Payment payment = new Payment();
    //check whether the client or payment is existed
    boolean clientNew = true;
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * submit: check whether the client is new and record the payment
     */
    public void submit(ActionEvent event) {

        //check the client exist or not,compare the input client to the database
            for (int i = 0; i < client.checkAll().size(); i++) {
                Client b = client.checkAll().get(i);
                String cName = b.getName();
                String nName = te1.getText();
                String cAddress = b.getAddress();
                String nAddress = te2.getText();
                String cPhone = b.getPhone();
                String nPhone = te3.getText();
                String cCompany = b.getGender();
                String nCompany = te4.getText();
                System.out.println(clientNew);
                System.out.println(clientID);
                if (cName.equals(nName)&& cAddress.equals(nAddress)&& cPhone.equals(nPhone)&& cCompany.equals(nCompany) ) {
                    clientID = b.getID();
                    clientNew = false;
                    break;
                }
            }

        //if  client is new, pop-up the alarm page
        if ( clientNew == true) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ClientAlarm.fxml"));
                Stage stage = new Stage();//new stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                //store the payment
                Payment payment = new Payment();
                int ID = payment.maxID();
                System.out.println(ID);
                payment.newPayment(ID,clientID,te5.getText(),te6.getText(),te7.getText(),te8.getText(),te9.getText());
                //go to the order page
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Orderpage.fxml"));
                loader.load();
                OrderController sn = loader.getController();
                sn.setClientID(clientID, ID);
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                Scene scene = new Scene(p);
                stage.setScene(scene);
                stage.show();
                Stage close = (Stage) submit.getScene().getWindow();
                close.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * OK: click to close the windows
     */
    public void OK(ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) b5.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    }