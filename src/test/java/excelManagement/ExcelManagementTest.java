package excelManagement;

import db.Payment;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExcelManagementTest {

    @Test
    public void createExcelFile() throws IOException, SQLException {
        ExcelManagement excelManagement = new ExcelManagement();
        excelManagement.createExcelFile();
    }

    @Test
    public void createSheetsForExcelFile() {
    }

    @Test
    public void addInformationToCell() {
    }

    @Test
    public void addPaymentInformationToExcel() throws IOException {

        ExcelManagement excelManagement = new ExcelManagement();
        Payment payment = new Payment();
        System.out.println(LocalDate.now());
        payment.setDate(LocalDate.of(2021,01,03));
        payment.setDocumentNr("Čeks 11111111");
        excelManagement.addOnePaymentInformationToExcel(payment);
    }

}