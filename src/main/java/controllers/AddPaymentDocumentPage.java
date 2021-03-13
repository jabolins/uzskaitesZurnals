package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db.Companies;
import db.DbManagment;
import db.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

import operativeDate.OperatvieDataConstants;
import org.controlsfx.control.textfield.TextFields;


public class AddPaymentDocumentPage {
    private final RegisterPayment registerPayment = new RegisterPayment(this);
    ArrayList<String> shortNamesOfCompanies = new ArrayList();
    Map<String, Companies> shortNameToFullCompanyMap = new HashMap<>();
    private final PageTransition pageTransition = new PageTransition();

    public DatePicker getData() {
        return data;
    }

    public TextField getDocNr() {
        return docNr;
    }

    public TextField getCompanyShortName() {
        return companyShortName;
    }

    public TextField getCompanyFullName() {
        return companyFullName;
    }

    public ComboBox<String> getComboBoxProductGroup() {
        return comboBoxProductGroup;
    }

    public TextField getSum() {
        return sum;
    }

    public RadioButton getRadioYesBusiness() {
        return radioYesBusiness;
    }

    public RadioButton getRadioPartlyBusiness() {
        return radioPartlyBusiness;
    }

    public RadioButton getRadioNotBusiness() {
        return radioNotBusiness;
    }

    public RadioButton getRadioIncome() {
        return radioIncome;
    }

    public RadioButton getRadioExpenses() {
        return radioExpenses;
    }

    public RadioButton getRadioCash() {
        return radioCash;
    }

    public RadioButton getRadioBank() {
        return radioBank;
    }

    public RadioButton getRadioCheck() {
        return radioCheck;
    }

    public RadioButton getRadioTicket() {
        return radioTicket;
    }

    public RadioButton getRadioOtherPaymentMethod() {
        return radioOtherPaymentMethod;
    }

    @FXML
    private BorderPane basicBorderPane;

    @FXML
    private Button butHome;

    @FXML
    private ToggleButton butDb;

    @FXML
    private Button buttAdd;

    @FXML
    private DatePicker data;

    @FXML
    private TextField docNr;

    @FXML
    private TextField companyShortName;

    @FXML
    private TextField companyFullName;

    @FXML
    private ComboBox<String> comboBoxProductGroup;

    @FXML
    private TextField sum;

    @FXML
    private RadioButton radioYesBusiness;

    @FXML
    private ToggleGroup attitudeToBusiness;

    @FXML
    private RadioButton radioPartlyBusiness;

    @FXML
    private RadioButton radioNotBusiness;

    @FXML
    private RadioButton radioIncome;

    @FXML
    private ToggleGroup incomeExpenses;

    @FXML
    private RadioButton radioExpenses;

    @FXML
    private RadioButton radioCash;

    @FXML
    private ToggleGroup cashBank;

    @FXML
    private RadioButton radioBank;

    @FXML
    private RadioButton radioCheck;

    @FXML
    private RadioButton radioTicket;

    @FXML
    private RadioButton radioOtherPaymentMethod;

    @FXML
    void initialize() throws SQLException {

        updateShortNameList();
        updateShortNameToFullCompanyMap();
        TextFields.bindAutoCompletion(companyShortName, shortNamesOfCompanies);
        comboBoxProductGroup.getItems().addAll(OperatvieDataConstants.GROUP_OF_SERVICES);

        butHome.setOnAction(event -> {
            butHome.getScene().getWindow().hide();
            pageTransition.goToPage("/basicPage.fxml");
        });

        buttAdd.setOnAction(event -> {
            checkFields();
            try {
                registerPayment.registerPayment();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        companyShortName.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.TAB)) {
                Companies company = (shortNameToFullCompanyMap.get(companyShortName.getText()));

                companyFullName.setText(company.getCompanyName());
                comboBoxProductGroup.setValue(company.getCompanyBaseProductGroup());

                if (company.getCompanyBaseProductToBusiness().equals("yes")) {
                    radioYesBusiness.setSelected(true);
                } else if (company.getCompanyBaseProductToBusiness().equals("no")) {
                    radioNotBusiness.setSelected(true);
                } else if (company.getCompanyBaseProductToBusiness().equals("partly")) {
                    radioPartlyBusiness.setSelected(true);
                }
            }
        });
    }

    private void updateShortNameToFullCompanyMap() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        shortNameToFullCompanyMap.putAll(dbManagment.getShortNameToFullCompanyMap());
    }

    private void updateShortNameList() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        shortNamesOfCompanies.addAll(dbManagment.getAllCompaniesShortNames());
    }

    String radioButtonsDataCollection() {
        String radioButtonsDataCollection = null;
        if (radioCash.isSelected()) if (radioExpenses.isSelected()) {
            radioButtonsDataCollection = "cash expenses";
        } else {
            radioButtonsDataCollection = "cash incomes";
        }

        if (radioBank.isSelected()) if (radioExpenses.isSelected()) {
            radioButtonsDataCollection = "bank expenses";
        } else {
            radioButtonsDataCollection = "bank incomes";
        }
        return radioButtonsDataCollection;
    }

    String fillDocumentNr() {
        String correctDocumentNrAndType;
        if (radioCheck.isSelected()) {
            correctDocumentNrAndType = "Čeks " + docNr.getText();
        } else if (radioTicket.isSelected()) {
            correctDocumentNrAndType = "Kvīts " + docNr.getText();
        } else correctDocumentNrAndType = docNr.getText();

        return correctDocumentNrAndType;
    }

    private void calculateDates() {

    }

    private void checkFields() {

    }
}
