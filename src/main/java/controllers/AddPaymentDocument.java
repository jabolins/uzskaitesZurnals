package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db.Companies;
import db.DbManagment;
import db.Payment;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import operativeDate.OperatvieDataConstants;
import org.apache.commons.math3.util.Precision;
import org.controlsfx.control.textfield.TextFields;


public class AddPaymentDocument {
    ArrayList<String> shortNamesOfCompanies = new ArrayList();
    Map<String, Companies> shortNameToFullCompanyMap = new HashMap<>();

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
            goToPage("/basicPage.fxml");
        });

        buttAdd.setOnAction(event -> {
            checkFields();
            try {
                registerPayment();
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

    private void updateShortNameToFullCompanyMap() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        shortNameToFullCompanyMap.putAll(dbManagment.getShortNameToFullCompanyMap());
    }

    private void updateShortNameList() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        shortNamesOfCompanies.addAll(dbManagment.getAllCompaniesShortNames());
    }

    private void registerPayment() throws SQLException {
        Payment payment = new Payment();

        payment.setDate(data.getValue());
        payment.setDocumentNr(fillDocumentNr());
        payment.setCompany(companyFullName.getText());
        payment.setDescriptionOfDeal(comboBoxProductGroup.getValue());

        setPaymentIncomeOrExpense(payment);
        setPaymentSumToCorrectTaxPlace(payment);


        DbManagment dbManagment = new DbManagment();
        dbManagment.paymentRegistration(payment);
        System.out.println(payment); // tas pārbaudei, vēlāk jāizdzēš
    }

    private void setPaymentIncomeOrExpense(Payment payment) {
        if (radioButtonsDataCollection().equals("cash expenses")) {
            payment.setCashExpenses(getRoundedPaymentSum());
            payment.setTotalExpenses(getRoundedPaymentSum());
        } else if (radioButtonsDataCollection().equals("bank expenses")) {
            payment.setBankExpenses(getRoundedPaymentSum());
            payment.setTotalExpenses(getRoundedPaymentSum());
        } else if (radioButtonsDataCollection().equals("cash incomes")) {
            payment.setCashIncomes(getRoundedPaymentSum());
            payment.setTotalIncome(getRoundedPaymentSum());
        } else if (radioButtonsDataCollection().equals("bank incomes")) {
            payment.setBankIncomes(getRoundedPaymentSum());
            payment.setCashIncomes(getRoundedPaymentSum());
        }
    }

    private void setPaymentSumToCorrectTaxPlace(Payment payment) {
        if (radioExpenses.isSelected()) {
            if (radioYesBusiness.isSelected()) {
                payment.setExpensesBusinessNotFarming(getRoundedPaymentSum());
            } else if (radioNotBusiness.isSelected()) {
                payment.setExpensesNotForBusiness(getRoundedPaymentSum());
            } else if (radioPartlyBusiness.isSelected()) {
                payment.setExpensesBusinessNotFarming(getRoundedPaymentSum() / 2);
                payment.setExpensesNotForBusiness(getRoundedPaymentSum() / 2);
            }
        } else if (radioIncome.isSelected()) {
            if (radioYesBusiness.isSelected()) {
                payment.setIncomeBusinessNotFarming(getRoundedPaymentSum());
            } else if (radioNotBusiness.isSelected()) {
                payment.setIncomeNotForTax(getRoundedPaymentSum());
            }
        }
    }

    private Double getRoundedPaymentSum() {
        String correctSum = sum.getText().replace(',', '.');
        return Precision.round(Double.parseDouble(correctSum), 2);
    }

    private String radioButtonsDataCollection() {
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

    private String fillDocumentNr() {
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
