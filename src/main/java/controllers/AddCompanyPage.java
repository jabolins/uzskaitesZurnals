package controllers;

import java.sql.SQLException;

import db.DbManagment;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import operativeDate.OperatvieDataConstants;

public class AddCompanyPage {

    private final AlarmMessages alarmMessages = new AlarmMessages();
    private final PageTransition pageTransition = new PageTransition();
    private final RegisterCompany registerCompany = new RegisterCompany(this);
    @FXML
    private Button butHome;

    public TextField getTxtName() {
        return txtName;
    }

    public TextField getTxtShortName() {
        return txtShortName;
    }

    public ComboBox<String> getComboBoxServicesGroup() {
        return comboBoxServicesGroup;
    }

    public TextField getTxtRegistrationNr() {
        return txtRegistrationNr;
    }

    public TextField getTxtAdress() {
        return txtAdress;
    }

    public TextField getTxtPhone() {
        return txtPhone;
    }

    public TextField getTxtEMail() {
        return txtEMail;
    }

    public TextField getTxtAccount() {
        return txtAccount;
    }

    public Text getInformationAboutSuccessful() {
        return informationAboutSuccessful;
    }

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
                if (checkPhoneNrIsInteger()) {
                    try {
                        if (checkUniqueShortName()) { // TODO jāpieliek pārbaude uz unikālu reģistrācijas nr

                            registerCompany.registerCompany();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        butHome.setOnAction(event -> {
            butHome.getScene().getWindow().hide();
            pageTransition.goToPage("/basicPage.fxml");
        });
    }

    private void comboBoxSetValues() {
        comboBoxServicesGroup.setValue(OperatvieDataConstants.GROUP_OF_SERVICES[0]);
        comboBoxServicesGroup.getItems().addAll(OperatvieDataConstants.GROUP_OF_SERVICES);
    }

    private boolean checkRequiredFields() {
        if (txtName.getText().equals("") || txtShortName.getText().equals("") || comboBoxServicesGroup.getValue().equals("")) {
            alarmMessages.alarmPopUpWindow("nav aizpildīti obligātie lauki");
            return false;
        }
        return true;
    }
    private boolean checkUniqueShortName() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        if (dbManagment.checkCompanyShortName(txtShortName.getText())) {
            return true;
        }
        alarmMessages.alarmPopUpWindow("šāds uzņēmuma īsais nosaukums: " + txtShortName.getText() + ", jau reģistrēts");
        return false;
    }

    private boolean checkPhoneNrIsInteger() {
        if (txtPhone.getText().trim().equals("")) {
            return true;
        }
        try {
            int phoneNr = Integer.parseInt(txtPhone.getText().trim());
        } catch (NumberFormatException nfe) {
            alarmMessages.alarmPopUpWindow("tālruņa Nr jābūt skaitliskai vērtībai ne vairāk kā 10 cipari");
            return false;
        }
        return true;
    }
}
