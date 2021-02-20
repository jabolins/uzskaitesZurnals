package lapas;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbParvaldiba;
import db.Lietotajs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ControllerPievienotLietotaju {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butHome;

    @FXML
    private AnchorPane rButLietotajs;

    @FXML
    private TextField txtEpasts;

    @FXML
    private TextField txtLietotajvards;

    @FXML
    private PasswordField txtParole;

    @FXML
    private PasswordField txtParole2;

    @FXML
    private Button butRegistret;

    @FXML
    private ToggleGroup loma;

    @FXML
    private RadioButton rButAdmin;

    @FXML
    void initialize() {


    }

    @FXML
    private void pievienotLietotaju() {
        String lietotajvards = txtLietotajvards.getText().trim();
        String parole = txtParole.getText().trim();
        String parole2 = txtParole2.getText().trim();
        String ePasts = txtEpasts.getText().trim();
        String loma;
        if (rButAdmin.isSelected()) {
            loma = "admin";
        } else {
            loma = "user";
        }

        if (parole.equals(parole2)) {
            if (lietotajvards.equals("") || parole.equals("") || ePasts.equals("")) {
                System.out.println("nav aizpildīti vajadzīgie lauki"); // vēlāk jānomaina uz loga iekrāsošanu
            } else {
                Lietotajs lietotajs = new Lietotajs(lietotajvards, parole, ePasts, loma);
                db.DbParvaldiba dbParvaldiba = new DbParvaldiba();
                dbParvaldiba.lietotajaRegistracija(lietotajs);
            }
        }
        else {
            System.out.println("ievadītās paroles nav vienādas");
            //txtParole.setBorder();  // te jāieliek lai maina krāsu ja nav ok
        }

    }
}
