package db;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class DbParvaldibaTest {


//    @Test
//    public void IsRegisterOfCompanySucessfull() {
//        DbManagment dbManagment = new DbManagment();
//        Companies company = new Companies();
//        company.setCompanyName("Testa uzņēmums");
//        company.setCompanyShortName("Testa");
//        company.setCompanyRegistrationNr("LV11223344556677");
//        company.setCompanyAddress("Rīga, Brīvības iela 2");
//        company.setCompanyPhone(22334455);
//        company.setCompanyEMail("testa@gmail.com");
//        company.setCompanyBaseProductGroup("degviela");
//        company.setCompanyBaseProductToBusiness("yes");
//
//        dbManagment.companyRegistration(company);
//
//    }

    @Test
    public void shouldGetCorrectCompanyDateFromDatabase() throws SQLException {
        DbManagment dbParvaldiba = new DbManagment();
        Companies companies = dbParvaldiba.getCompanyData("Testa");
        Assert.assertEquals("uzņēmuma nosaukums", companies.getCompanyName(), "Testa uzņēmums");
        Assert.assertEquals("uzņēmuma saīsinātais nosaukums", companies.getCompanyShortName(), "Testa");
        Assert.assertEquals("uzņēmuma produkts", companies.getCompanyBaseProductGroup(), "degviela");
        Assert.assertEquals("uzņēmuma telefons", companies.getCompanyPhone(), 22334455);
        Assert.assertNull("uzņēmuma bankas dati", companies.getCompanyBankData());
    }

    @Test
    public void shouldGetAllCompanies() throws SQLException {
        DbManagment dbParvaldiba = new DbManagment();
        ArrayList<Companies> allCompanies = dbParvaldiba.getAllCompanies();
        for (Companies companies : allCompanies) {
            System.out.println("Nosaukums: " + companies.getCompanyName());
            System.out.println("Saīsinātais nosaukums: " + companies.getCompanyShortName());
            System.out.println("Produkts: " + companies.getCompanyBaseProductGroup());
            System.out.println("Produkta attiecība: " + companies.getCompanyBaseProductToBusiness());
            System.out.println("Telefons: " + companies.getCompanyPhone());
            System.out.println("Reģistrāciajas nr: " + companies.getCompanyRegistrationNr());
        }
    }
}