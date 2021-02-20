package lapas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Main;

public class ControllerPamataMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butPievMaksDok;

    @FXML
    private Button butPievienotUzn;

    @FXML
    private Button butJaunsLietotajs;

    @FXML
    void ietPievienotJaunuLietotaju(ActionEvent event) throws IOException {
       // Main.showLapu("/userManagmentPage.fxml");
    }

    @FXML
    void ietPievienotMaksDok(ActionEvent event) throws IOException {
       // Main.showLapu("/paymentDocumentManagmentPage.fxml");
    }

    @FXML
    void initialize() {

    }
}
