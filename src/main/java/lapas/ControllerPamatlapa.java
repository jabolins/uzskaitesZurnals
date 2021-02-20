package lapas;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;


public class ControllerPamatlapa {


    @FXML
    void initialize() {

    }
    @FXML
    private void home() throws IOException {   // nospiežot pogu Home vienmēr nokļūstam pamata lapā
       // Main.showLapu("/loginPage.fxml");

    }
    private void goToPage(String page) {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource(page));
        try {
            load.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent run = load.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(run));
        stage.show();
    }

}