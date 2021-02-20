package db;

import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class DbParvaldibaTest {


//    @Test
//    public void IsRegisterOfCompanySucessfull() {
//        DbParvaldiba dbParvaldiba = new DbParvaldiba();
//     Uznemums uznemums= new Uznemums();
//     uznemums.setUznemumaNosaukums(" SIA, Bla bla");
//     uznemums.setUznemumaSaisinataisNosaukums("bla");
//     uznemums.setUznemumaPamatprodukts("degviela");
//     dbParvaldiba.uznemumaRegistracija(uznemums);
//
//    }
    @Test
    public void shouldGetCorrectCompanyDateFromDatabase() throws SQLException {
        DbParvaldiba dbParvaldiba = new DbParvaldiba();
        Companies companies =dbParvaldiba.getCompanyData("bla");
        System.out.println("Nosaukums: "+ companies.getCompanyName());
        System.out.println("Saīsinātais nosaukums: "+ companies.getCompanyShortName());
        System.out.println("Produkts: "+ companies.getCompanyBaseProductGroup());
        System.out.println("Produkta attiecība: "+ companies.getCompanyBaseProductToBusiness());
        System.out.println("Telefons: "+ companies.getCompanyPhone());
        System.out.println("Reģistrāciajas nr: "+ companies.getCompanyRegistrationNr());
    }

    @Test
    public void shouldGetAllCompanies() throws SQLException {
        DbParvaldiba dbParvaldiba = new DbParvaldiba();
        ArrayList<Companies> allCompanies = dbParvaldiba.getAllCompanies();
        for ( Companies companies : allCompanies) {
            System.out.println("Nosaukums: " + companies.getCompanyName());
            System.out.println("Saīsinātais nosaukums: " + companies.getCompanyShortName());
            System.out.println("Produkts: " + companies.getCompanyBaseProductGroup());
            System.out.println("Produkta attiecība: " + companies.getCompanyBaseProductToBusiness());
            System.out.println("Telefons: " + companies.getCompanyPhone());
            System.out.println("Reģistrāciajas nr: " + companies.getCompanyRegistrationNr());
        }
    }
}