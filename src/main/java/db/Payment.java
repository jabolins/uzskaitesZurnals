package db;

import java.sql.Date;
import java.time.LocalDate;

public class Payment {
    private int Nr;
    private LocalDate date;
    private String documentNr;
    private String company;
    private String descriptionOfDeal;
    private double CashReceived;
    private double CashIssued;
    private double bankReceived;
    private double bankIssued;
    private double incomeBusinessNotFarming;
    private double incomeNotForTax;
    private double totalIncome;
    private double expensesBusinessNotFarming;
    private double proportionateExpenses;
    private double expensesNotForBusiness;
    private double totalExpenses;
    private String attitudeForBusiness;


    public int getNr() {
        return Nr;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDocumentNr() {
        return documentNr;
    }

    public void setDocumentNr(String documentNr) {
        this.documentNr = documentNr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescriptionOfDeal() {
        return descriptionOfDeal;
    }

    public void setDescriptionOfDeal(String descriptionOfDeal) {
        this.descriptionOfDeal = descriptionOfDeal;
    }

    public double getCashReceived() {
        return CashReceived;
    }

    public void setCashIncomes(double cashReceived) {
        CashReceived = cashReceived;
    }

    public double getCashIssued() {
        return CashIssued;
    }

    public void setCashExpenses(double cashIssued) {
        CashIssued = cashIssued;
    }

    public double getBankReceived() {
        return bankReceived;
    }

    public void setBankIncomes(double bankReceived) {
        this.bankReceived = bankReceived;
    }

    public double getBankIssued() {
        return bankIssued;
    }

    public void setBankExpenses(double bankIssued) {
        this.bankIssued = bankIssued;
    }

    public double getIncomeBusinessNotFarming() {
        return incomeBusinessNotFarming;
    }

    public void setIncomeBusinessNotFarming(double incomeBusinessNotFarming) {
        this.incomeBusinessNotFarming = incomeBusinessNotFarming;
    }

    public double getIncomeNotForTax() {
        return incomeNotForTax;
    }

    public void setIncomeNotForTax(double incomeNotForTax) {
        this.incomeNotForTax = incomeNotForTax;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getExpensesBusinessNotFarming() {
        return expensesBusinessNotFarming;
    }

    public void setExpensesBusinessNotFarming(double expensesBusinessNotFarming) {
        this.expensesBusinessNotFarming = expensesBusinessNotFarming;
    }

    public double getProportionateExpenses() {
        return proportionateExpenses;
    }

    public void setProportionateExpenses(double proportionateExpenses) {
        this.proportionateExpenses = proportionateExpenses;
    }

    public double getExpensesNotForBusiness() {
        return expensesNotForBusiness;
    }

    public void setExpensesNotForBusiness(double expensesNotForBusiness) {
        this.expensesNotForBusiness = expensesNotForBusiness;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public String getAttitudeForBusiness() {
        return attitudeForBusiness;
    }

    public void setAttitudeForBusiness(String attitudeForBusiness) {
        this.attitudeForBusiness = attitudeForBusiness;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "Nr=" + Nr +
                ", date=" + date +
                ", documentNr='" + documentNr + '\'' +
                ", company='" + company + '\'' +
                ", descriptionOfDeal='" + descriptionOfDeal + '\'' +
                ", CashReceived=" + CashReceived +
                ", CashIssued=" + CashIssued +
                ", bankReceived=" + bankReceived +
                ", bankIssued=" + bankIssued +
                ", incomeBusinessNotFarming=" + incomeBusinessNotFarming +
                ", incomeNotForTax=" + incomeNotForTax +
                ", totalIncome=" + totalIncome +
                ", expensesBusinessNotFarming=" + expensesBusinessNotFarming +
                ", proportionateExpenses=" + proportionateExpenses +
                ", expensesNotForBusiness=" + expensesNotForBusiness +
                ", totalExpenses=" + totalExpenses +
                ", attitudeForBusiness='" + attitudeForBusiness + '\'' +
                '}';
    }
}

