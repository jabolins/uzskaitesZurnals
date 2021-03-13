package controllers;

import db.Companies;
import db.DbManagment;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import operativeDate.OperatvieDataConstants;

import java.sql.SQLException;

public class RegisterCompany {
    private final AddCompanyPage addCompanyPage;

    public RegisterCompany(AddCompanyPage addCompanyPage) {
        this.addCompanyPage = addCompanyPage;
    }

    void registerCompany() throws SQLException {
        Companies company = new Companies();

        company.setCompanyName(addCompanyPage.getTxtName().getText().trim());
        company.setCompanyShortName(addCompanyPage.getTxtShortName().getText().trim());
        company.setCompanyBaseProductGroup(addCompanyPage.getComboBoxServicesGroup().getValue());
        company.setCompanyAddress(addCompanyPage.getTxtAdress().getText().trim());
        company.setCompanyEMail(addCompanyPage.getTxtEMail().getText().trim());
        company.setCompanyBankData(addCompanyPage.getTxtAccount().getText().trim());
        company.setCompanyBaseProductToBusiness(OperatvieDataConstants.getAttentionProductToBusiness(addCompanyPage.getComboBoxServicesGroup().getValue()));
        if (!addCompanyPage.getTxtPhone().getText().equals("")) {
            company.setCompanyPhone(Integer.parseInt(addCompanyPage.getTxtPhone().getText().trim()));
        }
        if (!addCompanyPage.getTxtRegistrationNr().getText().trim().equals("")) {
            company.setCompanyBankData(addCompanyPage.getTxtAccount().getText().trim());
        }

        DbManagment dbManagment = new DbManagment();
        dbManagment.companyRegistration(company);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> addCompanyPage.getInformationAboutSuccessful().setVisible(false));
        addCompanyPage.getInformationAboutSuccessful().setVisible(true);
        delay.play();

    }
}