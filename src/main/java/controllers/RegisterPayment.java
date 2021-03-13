package controllers;

import db.DbManagment;
import db.Payment;
import org.apache.commons.math3.util.Precision;

import java.sql.SQLException;

public class RegisterPayment {
    private final AddPaymentDocumentPage addPaymentDocumentPage;

    public RegisterPayment(AddPaymentDocumentPage addPaymentDocumentPage) {
        this.addPaymentDocumentPage = addPaymentDocumentPage;
    }

    void registerPayment() throws SQLException {
        Payment payment = new Payment();

        payment.setDate(addPaymentDocumentPage.getData().getValue());
        payment.setDocumentNr(addPaymentDocumentPage.fillDocumentNr());
        payment.setCompany(addPaymentDocumentPage.getCompanyFullName().getText());
        payment.setDescriptionOfDeal(addPaymentDocumentPage.getComboBoxProductGroup().getValue());

        setPaymentIncomeOrExpense(payment);
        setPaymentSumToCorrectTaxPlace(payment);


        DbManagment dbManagment = new DbManagment();
        dbManagment.paymentRegistration(payment);
        System.out.println(payment); // tas pārbaudei, vēlāk jāizdzēš
    }

    void setPaymentIncomeOrExpense(Payment payment) {
        if (addPaymentDocumentPage.radioButtonsDataCollection().equals("cash expenses")) {
            payment.setCashExpenses(getRoundedPaymentSum());
            payment.setTotalExpenses(getRoundedPaymentSum());
        } else if (addPaymentDocumentPage.radioButtonsDataCollection().equals("bank expenses")) {
            payment.setBankExpenses(getRoundedPaymentSum());
            payment.setTotalExpenses(getRoundedPaymentSum());
        } else if (addPaymentDocumentPage.radioButtonsDataCollection().equals("cash incomes")) {
            payment.setCashIncomes(getRoundedPaymentSum());
            payment.setTotalIncome(getRoundedPaymentSum());
        } else if (addPaymentDocumentPage.radioButtonsDataCollection().equals("bank incomes")) {
            payment.setBankIncomes(getRoundedPaymentSum());
            payment.setTotalIncome(getRoundedPaymentSum());
        }
    }

    void setPaymentSumToCorrectTaxPlace(Payment payment) {
        if (addPaymentDocumentPage.getRadioExpenses().isSelected()) {
            if (addPaymentDocumentPage.getRadioYesBusiness().isSelected()) {
                payment.setExpensesBusinessNotFarming(getRoundedPaymentSum());
            } else if (addPaymentDocumentPage.getRadioNotBusiness().isSelected()) {
                payment.setExpensesNotForBusiness(getRoundedPaymentSum());
            } else if (addPaymentDocumentPage.getRadioPartlyBusiness().isSelected()) {
                payment.setExpensesBusinessNotFarming(getRoundedPaymentSum() / 2);
                payment.setExpensesNotForBusiness(getRoundedPaymentSum() / 2);
            }
        } else if (addPaymentDocumentPage.getRadioIncome().isSelected()) {
            if (addPaymentDocumentPage.getRadioYesBusiness().isSelected()) {
                payment.setIncomeBusinessNotFarming(getRoundedPaymentSum());
            } else if (addPaymentDocumentPage.getRadioNotBusiness().isSelected()) {
                payment.setIncomeNotForTax(getRoundedPaymentSum());
            }
        }
    }

    Double getRoundedPaymentSum() {
        String correctSum = addPaymentDocumentPage.getSum().getText().replace(',', '.');
        return Precision.round(Double.parseDouble(correctSum), 2);
    }
}