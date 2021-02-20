package lapas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ControllerAddCompany {


    @FXML
    private Button butHome;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtCompanyShortname;

    @FXML
    private RadioButton rButRelatedToBusiness;

    @FXML
    private ToggleGroup relationToBusiness;

    @FXML
    private RadioButton rButPartRelatedToBusiness;

    @FXML
    private RadioButton rButNotRelatedToBusiness;

    @FXML
    private Button butRegistret;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhoneNr;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtRegistrationNr;

    @FXML
    private TextField txtBankAccount;


    @FXML
    void initialize() {


    }
}
