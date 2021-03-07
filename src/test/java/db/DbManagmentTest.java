package db;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DbManagmentTest {


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
        Companies company = dbParvaldiba.getCompanyData("Depo");
        Assert.assertEquals("uzņēmuma nosaukums", company.getCompanyName(), "Depo DIY, SIA");
        Assert.assertEquals("uzņēmuma saīsinātais nosaukums", company.getCompanyShortName(), "Depo");
        Assert.assertEquals("uzņēmuma produkts", company.getCompanyBaseProductGroup(), "preces birojam");
    }

    @Test
    public void shouldGetAllCompanies() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        ArrayList<Companies> allCompanies = dbManagment.getAllCompanies();
        for (Companies companies : allCompanies) {
            System.out.println("Nosaukums: " + companies.getCompanyName());
            System.out.println("Saīsinātais nosaukums: " + companies.getCompanyShortName());
            System.out.println("Produkts: " + companies.getCompanyBaseProductGroup());
            System.out.println("Produkta attiecība: " + companies.getCompanyBaseProductToBusiness());
            System.out.println("Telefons: " + companies.getCompanyPhone());
            System.out.println("Reģistrāciajas nr: " + companies.getCompanyRegistrationNr());
        }
    }

    @Test
    public void shoulCompanyNameBeUnique() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        boolean actualResult = dbManagment.checkCompanyShortName("Nepareizais");
        Assert.assertTrue(dbManagment.checkCompanyShortName("Nepareizais"));
    }

    @Test
    public void shouldntCompanyNameBeUnique() throws SQLException {
        DbManagment dbManagment = new DbManagment();
        boolean actualResult = dbManagment.checkCompanyShortName("Depo");
        Assert.assertFalse(dbManagment.checkCompanyShortName("Depo"));
    }

    @Test
    public void getAllCompaniesShortNames() throws SQLException {
        ArrayList<String> allComaniesShortName= new ArrayList<>();
        DbManagment dbManagment= new DbManagment();
        allComaniesShortName.addAll(dbManagment.getAllCompaniesShortNames());
        for (String name: allComaniesShortName){
            System.out.println(name);
        }
    }

    @Test
    public void getShortNameFullNameOfCompanies() throws SQLException {
        DbManagment dbManagment =new DbManagment();
        System.out.println(dbManagment.getShortNameFullNameOfCompanies());
    }

    @Test
    public void ShouldFindFullNameFromShortName() throws SQLException {
        DbManagment dbManagment =new DbManagment();
        Map<String, String> fullNameShortNameMap= new HashMap<>();
        fullNameShortNameMap.putAll(dbManagment.getShortNameFullNameOfCompanies());
        String expectedValue= "Depo DIY, SIA";
        Assert.assertEquals(expectedValue,fullNameShortNameMap.get("Depo"));
    }

    @Test
    public void ShouldFindAllCompaniesGetShortNameToFullCompanyMap() throws SQLException {
        DbManagment dbManagment =new DbManagment();
        System.out.println(dbManagment.getShortNameToFullCompanyMap());
    }

    @Test
    public void ShouldFindFullCompanyFromShortName() throws SQLException {
        DbManagment dbManagment =new DbManagment();
        Map<String, Companies> ShortNameToFullCompanyMap= new HashMap<>();
        ShortNameToFullCompanyMap.putAll(dbManagment.getShortNameToFullCompanyMap());
        String expectedValue= "Depo DIY, SIA";
        Assert.assertEquals(expectedValue,ShortNameToFullCompanyMap.get("Depo").getCompanyName());
    }

    @Test
    public void paymentRegistration() throws SQLException {
        Payment payment = new Payment();
        payment.setDate(LocalDate.now());
        payment.setCompany("Testa1");
        payment.setCashExpenses(2.345);
        payment.setExpensesBusinessNotFarming(2.345);
        payment.setTotalExpenses(2.345);

        DbManagment dbManagment= new DbManagment();
        dbManagment.paymentRegistration(payment);
    }
}
