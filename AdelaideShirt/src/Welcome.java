import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Wei Huang
 * @Since 2021/11/22
 * The start of the system
 */
public class Welcome extends Application
{
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * show the Frontpage.fxml
     */
    public void start(Stage stage) throws Exception
    {
        //build the FXML
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Frontpage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @author Wei Huang
     * @Since 2021/11/22
     * launch the window page
     */
    //start the view
    public static void main(String[] args) {
        launch(args);
    }
}
