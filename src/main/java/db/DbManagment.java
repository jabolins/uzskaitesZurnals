package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DbManagment {

    Connection dataBaseConnection;

    public Connection getConnectionDatabase() throws SQLException {

        String pieslegumaCels = "jdbc:mysql://" + DateBaseConstants.DB_HOST + ":" + DateBaseConstants.DB_PORT + "/" + DateBaseConstants.DB_NAME;

        dataBaseConnection = DriverManager.getConnection(pieslegumaCels, DateBaseConstants.DB_USER, DateBaseConstants.DB_PASS);
        return dataBaseConnection;
    }

    public void userRegistration(User jaunslietotajs) throws SQLException {
        String ievade = "INSERT INTO " + DateBaseConstants.TABLE_LIETOTAJI + "(" + DateBaseConstants.LIET_LIETOTAJVARDS + ","
                + DateBaseConstants.LIET_PAROLE + "," + DateBaseConstants.LIET_E_PASTS + "," + DateBaseConstants.LIET_LOMA + ")" + " VALUES(?,?,?,?)";

        try {
            PreparedStatement inputValues = getConnectionDatabase().prepareStatement(ievade);
            inputValues.setString(1, jaunslietotajs.getLietotajvards());
            inputValues.setString(2, jaunslietotajs.getParole());
            inputValues.setString(3, jaunslietotajs.getEpasts());
            inputValues.setString(4, jaunslietotajs.getLoma());

            inputValues.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataBaseConnection.close();
    }

    public ResultSet getUserDate(String lietotajvards, String parole, String loma) throws SQLException { // šo varbūt vajadzētu pārveidot lai ir objekts lietotājas un tā laukus
        ResultSet atgriezamaVertiba = null; // sākumā pieņemam ka nekas netiks atrasts

        String atlase = "SELECT * FROM " + DateBaseConstants.TABLE_LIETOTAJI + " WHERE " + DateBaseConstants.LIET_LIETOTAJVARDS + "=? AND "
                + DateBaseConstants.LIET_PAROLE + "=? AND " + DateBaseConstants.LIET_LOMA + "=?"; // =? nozīmē ka vienāds ar kaut ko ko norādīsim vēlāk,
        // * norāda - visas vērtības

        try {
            PreparedStatement sanemtasVertibas = getConnectionDatabase().prepareStatement(atlase);

            sanemtasVertibas.setString(1, lietotajvards);
            sanemtasVertibas.setString(2, parole);
            sanemtasVertibas.setString(3, loma);
            atgriezamaVertiba = sanemtasVertibas.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataBaseConnection.close();
        return atgriezamaVertiba;
    } // atgriež no datu bāzes lietotājvārdu,
    // paroli un lomu. ir OK

    public void companyRegistration(Companies newCompany) throws SQLException {
        String input = "INSERT INTO " + DateBaseConstants.TABLE_UZNEMUMI +
                "(" + DateBaseConstants.COMPANY_NAME +
                "," + DateBaseConstants.COMPANY_SHORT_NAME +
                "," + DateBaseConstants.COMPANY_BASIC_PRODUCT_GROUP +
                "," + DateBaseConstants.PRODUCT_ATTENTION_TO_BUSINESS +
                "," + DateBaseConstants.COMPANY_REGISTRATION_NR +
                "," + DateBaseConstants.COMPANY_ADDRESS +
                "," + DateBaseConstants.COMPANY_ACCOUNT +
                "," + DateBaseConstants.COMPANY_E_MAIL +
                "," + DateBaseConstants.COMPANY_PHONE +
                ")" + " VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getConnectionDatabase().prepareStatement(input);
            preparedStatement.setString(1, newCompany.getCompanyName());
            preparedStatement.setString(2, newCompany.getCompanyShortName());
            preparedStatement.setString(3, newCompany.getCompanyBaseProductGroup());
            preparedStatement.setString(4, newCompany.getCompanyBaseProductToBusiness());
            preparedStatement.setString(5, newCompany.getCompanyRegistrationNr());
            preparedStatement.setString(6, newCompany.getCompanyAddress());
            preparedStatement.setString(7, newCompany.getCompanyBankData());
            preparedStatement.setString(8, newCompany.getCompanyEMail());
            preparedStatement.setInt(9, newCompany.getCompanyPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataBaseConnection.close();
    }

    public boolean checkCompanyShortName(String companyShortName) throws SQLException {
        int countOfCompanies = 0;
        String query = "SELECT * FROM " + DateBaseConstants.TABLE_UZNEMUMI + " WHERE "
                + DateBaseConstants.COMPANY_SHORT_NAME + "=" + "?";

        PreparedStatement statement = getConnectionDatabase().prepareStatement(query);
        statement.setString(1, companyShortName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            countOfCompanies++;
        }
        if (countOfCompanies > 0) {
            dataBaseConnection.close();
            return false;
        }
        dataBaseConnection.close();
        return true;
    }

    public Companies getCompanyData(String companyShortName) throws SQLException {
        Companies findingCompany = new Companies();

        String query = "SELECT * FROM " + DateBaseConstants.TABLE_UZNEMUMI + " WHERE "
                + DateBaseConstants.COMPANY_SHORT_NAME + "=" + "?";

        PreparedStatement statement = getConnectionDatabase().prepareStatement(query);
        statement.setString(1, companyShortName);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            findingCompany.setCompanyName(resultSet.getString("nosaukums"));
            findingCompany.setCompanyShortName(resultSet.getString("saisinataisNosaukums"));
            findingCompany.setCompanyBaseProductGroup(resultSet.getString("pamatprodukts"));
            findingCompany.setCompanyBaseProductToBusiness(resultSet.getString("saistibaUznemejdarbibai"));
            findingCompany.setCompanyAddress(resultSet.getString("adrese"));
            findingCompany.setCompanyEMail(resultSet.getString("epasts"));
            findingCompany.setCompanyRegistrationNr(resultSet.getString("regNr"));
            findingCompany.setCompanyBankData(resultSet.getString("konts"));
            findingCompany.setCompanyPhone(resultSet.getInt("telefons"));

        }
        dataBaseConnection.close();
        return findingCompany;
    }

    public ArrayList<Companies> getAllCompanies() throws SQLException {
        ArrayList<Companies> allCompanies = new ArrayList<>();
        String query = "SELECT * FROM " + DateBaseConstants.TABLE_UZNEMUMI;
        Statement statement = getConnectionDatabase().createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Companies companies = new Companies();
            companies.setCompanyName(resultSet.getString(DateBaseConstants.COMPANY_NAME));
            companies.setCompanyShortName(resultSet.getString(DateBaseConstants.COMPANY_SHORT_NAME));
            companies.setCompanyBaseProductGroup(resultSet.getString(DateBaseConstants.COMPANY_BASIC_PRODUCT_GROUP));
            companies.setCompanyBaseProductToBusiness(resultSet.getString(DateBaseConstants.PRODUCT_ATTENTION_TO_BUSINESS));
            companies.setCompanyAddress(resultSet.getString(DateBaseConstants.COMPANY_ADDRESS));
            companies.setCompanyEMail(resultSet.getString(DateBaseConstants.COMPANY_E_MAIL));
            companies.setCompanyRegistrationNr(resultSet.getString(DateBaseConstants.COMPANY_REGISTRATION_NR));
            companies.setCompanyBankData(resultSet.getString(DateBaseConstants.COMPANY_ACCOUNT));
            companies.setCompanyPhone(resultSet.getInt(DateBaseConstants.COMPANY_PHONE));
            allCompanies.add(companies);
        }
        dataBaseConnection.close();
        return allCompanies;
    }

    public ArrayList<String> getAllCompaniesShortNames() throws SQLException {
        ArrayList<String> allCompaniesShortNames = new ArrayList<>();
        String query = "SELECT " + DateBaseConstants.COMPANY_SHORT_NAME + " FROM " + DateBaseConstants.TABLE_UZNEMUMI;
        Statement statement = getConnectionDatabase().createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            allCompaniesShortNames.add(resultSet.getString(DateBaseConstants.COMPANY_SHORT_NAME));
        }
        dataBaseConnection.close();
        return allCompaniesShortNames;
    }

    public Map<String, String> getShortNameFullNameOfCompanies() throws SQLException {
        Map<String, String> shortNamesFullNamesOfCompanies = new HashMap<>();

        String query = "SELECT " + DateBaseConstants.COMPANY_SHORT_NAME + " , " + DateBaseConstants.COMPANY_NAME + " FROM " + DateBaseConstants.TABLE_UZNEMUMI;
        Statement statement = getConnectionDatabase().createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            shortNamesFullNamesOfCompanies.put(resultSet.getString(DateBaseConstants.COMPANY_SHORT_NAME), resultSet.getString(DateBaseConstants.COMPANY_NAME));
        }
        dataBaseConnection.close();
        return shortNamesFullNamesOfCompanies;
    }

    public Map<String, Companies> getShortNameToFullCompanyMap() throws SQLException {
        Map<String, Companies> ShortNameToFullCompanyMap = new HashMap<>();

        String query = "SELECT * FROM " + DateBaseConstants.TABLE_UZNEMUMI;
        Statement statement = getConnectionDatabase().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Companies company = new Companies();
            company.setCompanyName(resultSet.getString(DateBaseConstants.COMPANY_NAME));
            company.setCompanyShortName(resultSet.getString(DateBaseConstants.COMPANY_SHORT_NAME));
            company.setCompanyBaseProductGroup(resultSet.getString(DateBaseConstants.COMPANY_BASIC_PRODUCT_GROUP));
            company.setCompanyBaseProductToBusiness(resultSet.getString(DateBaseConstants.PRODUCT_ATTENTION_TO_BUSINESS));
            company.setCompanyAddress(resultSet.getString(DateBaseConstants.COMPANY_ADDRESS));
            company.setCompanyEMail(resultSet.getString(DateBaseConstants.COMPANY_E_MAIL));
            company.setCompanyRegistrationNr(resultSet.getString(DateBaseConstants.COMPANY_REGISTRATION_NR));
            company.setCompanyBankData(resultSet.getString(DateBaseConstants.COMPANY_ACCOUNT));
            company.setCompanyPhone(resultSet.getInt(DateBaseConstants.COMPANY_PHONE));

            ShortNameToFullCompanyMap.put(resultSet.getString(DateBaseConstants.COMPANY_SHORT_NAME), company);
        }
        dataBaseConnection.close();
        return ShortNameToFullCompanyMap;

    }

    public void paymentRegistration(Payment newPayment) throws SQLException {
        String input = "INSERT INTO " + DateBaseConstants.TABLE_FINDOC +
                "(" + DateBaseConstants.FINDOC_DATE +
                "," + DateBaseConstants.FINDOC_DOC_NR +
                "," + DateBaseConstants.FINDOC_COMPANY +
                "," + DateBaseConstants.FINDOC_DESCRIPTION_OF_DEAL +
                "," + DateBaseConstants.FINDOC_CASH_RECEIVED +
                "," + DateBaseConstants.FINDOC_CASH_ISSUED +
                "," + DateBaseConstants.FINDOC_BANK_RECEIVED +
                "," + DateBaseConstants.FINDOC_BANK_ISSUED +
                "," + DateBaseConstants.FINDOC_INCOME_BUSINESS_NOT_FARMING +
                "," + DateBaseConstants.FINDOC_INCOME_NOT_FOR_TAX +
                "," + DateBaseConstants.FINDOC_TOTAL_INCOME +
                "," + DateBaseConstants.FINDOC_EXPENSES_BUSINESS_NOT_FARMING +
                "," + DateBaseConstants.FINDOC_EXPENSNES_NOT_FOR_BUSINESS +
                "," + DateBaseConstants.FINDOC_TOTAL_EXPENSES +
                ")" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getConnectionDatabase().prepareStatement(input);
            preparedStatement.setDate(1, java.sql.Date.valueOf(newPayment.getDate()));
            preparedStatement.setString(2, newPayment.getDocumentNr());
            preparedStatement.setString(3, newPayment.getCompany());
            preparedStatement.setString(4, newPayment.getDescriptionOfDeal());
            preparedStatement.setDouble(5, newPayment.getCashReceived());
            preparedStatement.setDouble(6, newPayment.getCashIssued());
            preparedStatement.setDouble(7, newPayment.getBankReceived());
            preparedStatement.setDouble(8, newPayment.getBankIssued());
            preparedStatement.setDouble(9, newPayment.getIncomeBusinessNotFarming());
            preparedStatement.setDouble(10, newPayment.getIncomeNotForTax());
            preparedStatement.setDouble(11, newPayment.getTotalIncome());
            preparedStatement.setDouble(12, newPayment.getExpensesBusinessNotFarming());
            preparedStatement.setDouble(13, newPayment.getExpensesNotForBusiness());
            preparedStatement.setDouble(14, newPayment.getTotalExpenses());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataBaseConnection.close();
    }


    public ArrayList<Payment> getAllPayments() throws SQLException {
        ArrayList<Payment> allPayments = new ArrayList<>();

        String query = "SELECT * FROM " + DateBaseConstants.TABLE_FINDOC+ " ORDER BY "+DateBaseConstants.FINDOC_DATE+ " ASC" ;
        Statement statement = getConnectionDatabase().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Payment payment = new Payment();
            payment.setDate(resultSet.getDate(DateBaseConstants.FINDOC_DATE).toLocalDate());
            if (resultSet.getString(DateBaseConstants.FINDOC_DOC_NR) != null) {
                payment.setDocumentNr(resultSet.getString(DateBaseConstants.FINDOC_DOC_NR));
            }
            if (resultSet.getString(DateBaseConstants.FINDOC_COMPANY) != null) {
                payment.setCompany(resultSet.getString(DateBaseConstants.FINDOC_COMPANY));
            }
            if (resultSet.getString(DateBaseConstants.FINDOC_DESCRIPTION_OF_DEAL) != null) {
                payment.setDescriptionOfDeal(resultSet.getString(DateBaseConstants.FINDOC_DESCRIPTION_OF_DEAL));
            }
            if (resultSet.getDouble(DateBaseConstants.FINDOC_CASH_RECEIVED) != 0) {
                payment.setCashIncomes(resultSet.getDouble(DateBaseConstants.FINDOC_CASH_RECEIVED));
            }
            if (resultSet.getDouble(DateBaseConstants.FINDOC_CASH_ISSUED) != 0) {
                payment.setCashExpenses(resultSet.getDouble(DateBaseConstants.FINDOC_CASH_ISSUED));
            }
            if (resultSet.getDouble(DateBaseConstants.FINDOC_BANK_RECEIVED) != 0) {
                payment.setBankIncomes(resultSet.getDouble(DateBaseConstants.FINDOC_BANK_RECEIVED));
            }
            if (resultSet.getDouble(DateBaseConstants.FINDOC_BANK_ISSUED) != 0) {
                payment.setBankExpenses(resultSet.getDouble(DateBaseConstants.FINDOC_BANK_ISSUED));
            }
            if (resultSet.getDouble(DateBaseConstants.FINDOC_INCOME_BUSINESS_NOT_FARMING) != 0) {
                payment.setIncomeBusinessNotFarming(resultSet.getDouble(DateBaseConstants.FINDOC_INCOME_BUSINESS_NOT_FARMING));
            }
            if (resultSet.getDouble(DateBaseConstants.FINDOC_INCOME_NOT_FOR_TAX) != 0) {
                payment.setIncomeNotForTax(resultSet.getDouble(DateBaseConstants.FINDOC_INCOME_NOT_FOR_TAX));
            }

            payment.setTotalIncome(resultSet.getDouble(DateBaseConstants.FINDOC_TOTAL_INCOME));

            if (resultSet.getDouble(DateBaseConstants.FINDOC_EXPENSES_BUSINESS_NOT_FARMING) != 0) {
                payment.setExpensesBusinessNotFarming(resultSet.getDouble(DateBaseConstants.FINDOC_EXPENSES_BUSINESS_NOT_FARMING));
            }
            if (resultSet.getDouble(DateBaseConstants.FINDOC_EXPENSNES_NOT_FOR_BUSINESS) != 0) {
                payment.setExpensesNotForBusiness(resultSet.getDouble(DateBaseConstants.FINDOC_EXPENSNES_NOT_FOR_BUSINESS));
            }

            payment.setTotalExpenses(resultSet.getDouble(DateBaseConstants.FINDOC_TOTAL_EXPENSES));


            allPayments.add(payment);
        }
        dataBaseConnection.close();
        return allPayments;

    }
}
