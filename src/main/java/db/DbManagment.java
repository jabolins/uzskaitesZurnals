package db;

import java.sql.*;
import java.util.ArrayList;

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
                "," + DateBaseConstants.COMPANY_sHORT_NAME +
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
        int countOfCompanies=0;
        String query = "SELECT * FROM " + DateBaseConstants.TABLE_UZNEMUMI + " WHERE "
                + DateBaseConstants.COMPANY_sHORT_NAME + "=" + "?";

        PreparedStatement statement = getConnectionDatabase().prepareStatement(query);
        statement.setString(1, companyShortName);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
          countOfCompanies++;
        }
        if (countOfCompanies>0){
            dataBaseConnection.close();
            return false;
        }
        dataBaseConnection.close();
        return true;
    }

    public Companies getCompanyData(String companyShortName) throws SQLException {
        Companies findingCompany = new Companies();

        String query = "SELECT * FROM " + DateBaseConstants.TABLE_UZNEMUMI + " WHERE "
                + DateBaseConstants.COMPANY_sHORT_NAME + "=" + "?";

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
            companies.setCompanyName(resultSet.getString("nosaukums"));
            companies.setCompanyShortName(resultSet.getString("saisinataisNosaukums"));
            companies.setCompanyBaseProductGroup(resultSet.getString("pamatprodukts"));
            companies.setCompanyBaseProductToBusiness(resultSet.getString("saistibaUznemejdarbibai"));
            companies.setCompanyAddress(resultSet.getString("adrese"));
            companies.setCompanyEMail(resultSet.getString("epasts"));
            companies.setCompanyRegistrationNr(resultSet.getString("regNr"));
            companies.setCompanyBankData(resultSet.getString("konts"));
            companies.setCompanyPhone(resultSet.getInt("telefons"));
            allCompanies.add(companies);
        }
        dataBaseConnection.close();
        return allCompanies;
    }
}
