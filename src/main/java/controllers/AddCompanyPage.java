package controllers;

import java.io.IOException;
import java.sql.SQLException;

import db.Companies;
import db.DbManagment;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import operativeDate.OperatvieDataConstants;

import static java.lang.Integer.parseInt;

public class AddCompanyPage {

    @FXML
    private Button butHome;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtShortName;

    @FXML
    private ComboBox<String> comboBoxServicesGroup;

    @FXML
    private TextField txtRegistrationNr;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEMail;

    @FXML
    private TextField txtAccount;

    @FXML
    private Button butAddCompany;

    @FXML
    private Text informationAboutSuccessful;

    @FXML
    void initialize() {
        informationAboutSuccessful.setVisible(false);
        comboBoxSetValues();

        butAddCompany.setOnAction(event -> {
            if (checkRequiredFields()) {
                if (checkPhoneNrisInteger()) {
                    try {
                        if (checkUniqueShortName()) { // jāpieliek pārbaude uz unikālu reģistrācijas nr

                            registerCompany();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        butHome.setOnAction(event -> {
            butHome.getScene().getWindow().hide();
            goToPage("/basicPage.fxml");
        });
    }

    private void comboBoxSetValues() {
        comboBoxServicesGroup.setValue(OperatvieDataConstants.GROUP_OF_SERVICES[0]);
        comboBoxServicesGroup.getItems().addAll(OperatvieDataConstants.GROUP_OF_SERVICES);
    }

    private boolean checkPhoneNrisInteger() {
        if (txtPhone.getText().trim().equals("")) {
            return true;
        }
        try {
            int phoneNr = Integer.parseInt(txtPhone.getText().trim());
            System.out.println(phoneNr); // šis ir pārbaudei vēlāk jāizdzēš
        } catch (NumberFormatException nfe) {
            alarmPopUpWindow("tālruņa Nr jābūt skaitliskai vērtībai ne vairāk kā 10 cipari");
            return false;
        }
        return true;
    }

    private boolean checkRequiredFields() {
        if (txtName.getText().equals("") || txtShortName.getText().equals("") || comboBoxServicesGroup.equals("")) { // te vēl
            // jāsakārto lai ja combobox ir tukšs lai izmet kļūdu
            alarmPopUpWindow("nav aizpildīti obligātie lauki");
            return false;
        }
        return true;
    }

    private boolean checkUniqueShortName() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        if (dbManagment.checkCompanyShortName(txtShortName.getText())) {
            return true;
        }
        alarmPopUpWindow("šāds uzņēmuma īsais nosaukums: " + txtShortName.getText() + ", jau reģistrēts");
        return false;
    }

    private void registerCompany() throws SQLException {
        Companies company = new Companies();

        company.setCompanyName(txtName.getText().trim());
        company.setCompanyShortName(txtShortName.getText().trim());
        company.setCompanyBaseProductGroup(comboBoxServicesGroup.getValue());
        company.setCompanyAddress(txtAdress.getText().trim());
        company.setCompanyEMail(txtEMail.getText().trim());
        company.setCompanyBankData(txtAccount.getText().trim());
        company.setCompanyBaseProductToBusiness(OperatvieDataConstants.getAttentionProductToBusiness(comboBoxServicesGroup.getValue()));
        if (!txtPhone.getText().equals("")) {
            company.setCompanyPhone(parseInt(txtPhone.getText().trim()));
        }
        if (!txtRegistrationNr.getText().trim().equals("")) {
            company.setCompanyBankData(txtAccount.getText().trim());
        }

        DbManagment dbManagment = new DbManagment();
        dbManagment.companyRegistration(company);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> informationAboutSuccessful.setVisible(false));
        informationAboutSuccessful.setVisible(true);
        delay.play();

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
        stage.setTitle("Uzskaites žurnāls");
        stage.setScene(new Scene(run));
        stage.show();
    }

    private void alarmPopUpWindow(String alarmText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(alarmText);
        alert.setTitle("Brīdinājums");
        //alert.setContentText(alarmText);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

}
