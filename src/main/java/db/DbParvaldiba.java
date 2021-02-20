package db;

import java.sql.*;
import java.util.ArrayList;

public class DbParvaldiba {

    Connection dbPieslegums;

    public Connection getDbPieslegums() throws SQLException {

        String pieslegumaCels = "jdbc:mysql://" + DateBaseConstants.DB_HOST + ":" + DateBaseConstants.DB_PORT + "/" + DateBaseConstants.DB_NAME;

        dbPieslegums = DriverManager.getConnection(pieslegumaCels, DateBaseConstants.DB_USER, DateBaseConstants.DB_PASS);
        return dbPieslegums;
    }

    public void lietotajaRegistracija(Lietotajs jaunslietotajs) {
        String ievade = "INSERT INTO " + DateBaseConstants.TABLE_LIETOTAJI + "(" + DateBaseConstants.LIET_LIETOTAJVARDS + ","
                + DateBaseConstants.LIET_PAROLE + "," + DateBaseConstants.LIET_E_PASTS + "," + DateBaseConstants.LIET_LOMA + ")" + " VALUES(?,?,?,?)";

        try {
            PreparedStatement ievadamieDati = getDbPieslegums().prepareStatement(ievade);
            ievadamieDati.setString(1, jaunslietotajs.getLietotajvards());
            ievadamieDati.setString(2, jaunslietotajs.getParole());
            ievadamieDati.setString(3, jaunslietotajs.getEpasts());
            ievadamieDati.setString(4, jaunslietotajs.getLoma());

            ievadamieDati.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    } // reģistrē jaunu lietototāju. ir OK

    public ResultSet getLietotajaDati(String lietotajvards, String parole, String loma) {
        ResultSet atgriezamaVertiba = null; // sākumā pieņemam ka nekas netiks atrasts

        String atlase = "SELECT * FROM " + DateBaseConstants.TABLE_LIETOTAJI + " WHERE " + DateBaseConstants.LIET_LIETOTAJVARDS + "=? AND "
                + DateBaseConstants.LIET_PAROLE + "=? AND " + DateBaseConstants.LIET_LOMA + "=?"; // =? nozīmē ka vienāds ar kaut ko ko norādīsim vēlāk,
        // * norāda - visas vērtības

        try {
            PreparedStatement sanemtasVertibas = getDbPieslegums().prepareStatement(atlase);

            sanemtasVertibas.setString(1, lietotajvards);
            sanemtasVertibas.setString(2, parole);
            sanemtasVertibas.setString(3, loma);
            atgriezamaVertiba = sanemtasVertibas.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return atgriezamaVertiba;
    } // atgriež no datu bāzes lietotājvārdu,
    // paroli un lomu. ir OK

    public void uznemumaRegistracija(Companies jaunsCompanies) {
        String ievade = "INSERT INTO " + DateBaseConstants.TABLE_UZNEMUMI +
                "(" + DateBaseConstants.UZN_NOSAUKUMS +
                "," + DateBaseConstants.UZN_SAISINATAIS_NOSAUKUMS +
                "," + DateBaseConstants.UZN_PAMATPRODUKTS +
                "," + DateBaseConstants.UZN_PRODUKTA_ATTIECIBA_UZNEMEJDARBIBAI +
                "," + DateBaseConstants.UZN_REG_NR +
                "," + DateBaseConstants.UZN_ADRESE +
                "," + DateBaseConstants.UZN_KONTS +
                "," + DateBaseConstants.UZN_E_PASTS +
                "," + DateBaseConstants.UZN_TELEFONS +
                ")" + " VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ievadamieDati = getDbPieslegums().prepareStatement(ievade);
            ievadamieDati.setString(1, jaunsCompanies.getCompanyName());
            ievadamieDati.setString(2, jaunsCompanies.getCompanyShortName());
            ievadamieDati.setString(3, jaunsCompanies.getCompanyBaseProductGroup());
            ievadamieDati.setString(4, jaunsCompanies.getCompanyBaseProductToBusiness());
            ievadamieDati.setString(5, jaunsCompanies.getCompanyRegistrationNr());
            ievadamieDati.setString(6, jaunsCompanies.getCompanyAddress());
            ievadamieDati.setString(7, jaunsCompanies.getCompanyBankData());
            ievadamieDati.setString(8, jaunsCompanies.getCompanyEMail());
            ievadamieDati.setString(9, jaunsCompanies.getCompanyPhone());

            ievadamieDati.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    } // reģistrē jaunu lietototāju. ir OK

    public Companies getCompanyData(String saisinataisUznemumaNosaukums) throws SQLException {
        Companies meklejamaisCompanies = new Companies();

        String query = "SELECT * FROM " + DateBaseConstants.TABLE_UZNEMUMI + " WHERE "
                + DateBaseConstants.UZN_SAISINATAIS_NOSAUKUMS + "=" + "?";

        PreparedStatement statement = getDbPieslegums().prepareStatement(query);
        statement.setString(1, saisinataisUznemumaNosaukums);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            meklejamaisCompanies.setCompanyName(resultSet.getString("nosaukums"));
            meklejamaisCompanies.setCompanyShortName(resultSet.getString("saisinataisNosaukums"));
            meklejamaisCompanies.setCompanyBaseProductGroup(resultSet.getString("pamatprodukts"));
            meklejamaisCompanies.setCompanyBaseProductToBusiness(resultSet.getString("saistibaUznemejdarbibai"));
            meklejamaisCompanies.setCompanyAddress(resultSet.getString("adrese"));
            meklejamaisCompanies.setCompanyEMail(resultSet.getString("epasts"));
            meklejamaisCompanies.setCompanyRegistrationNr(resultSet.getString("regNr"));
            meklejamaisCompanies.setCompanyBankData(resultSet.getString("konts"));
            meklejamaisCompanies.setCompanyPhone(resultSet.getString("telefons"));

        }

        return meklejamaisCompanies;
    }

    public ArrayList<Companies> getAllCompanies() throws SQLException {
        ArrayList<Companies> allCompanies = new ArrayList<>();
        String query = "SELECT * FROM " + DateBaseConstants.TABLE_UZNEMUMI;
        Statement statement = getDbPieslegums().createStatement();

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
            companies.setCompanyPhone(resultSet.getString("telefons"));
            allCompanies.add(companies);
        }
        return allCompanies;
    }
}
