package db;

public class Companies {
    private String companyName;
    private String companyShortName;
    private String companyRegistrationNr;
    private String companyAddress;
    private String companyBankData;
    private String companyPhone;
    private String companyEMail;
    private String companyBaseProductGroup;
    private String companyBaseProductToBusiness; // šis jāizdomā labāks nosaukums

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyRegistrationNr() {
        return companyRegistrationNr;
    }

    public void setCompanyRegistrationNr(String companyRegistrationNr) {
        this.companyRegistrationNr = companyRegistrationNr;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyBankData() {
        return companyBankData;
    }

    public void setCompanyBankData(String companyBankData) {
        this.companyBankData = companyBankData;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyEMail() {
        return companyEMail;
    }

    public void setCompanyEMail(String companyEMail) {
        this.companyEMail = companyEMail;
    }

    public String getCompanyBaseProductGroup() {
        return companyBaseProductGroup;
    }

    public void setCompanyBaseProductGroup(String companyBaseProductGroup) {
        this.companyBaseProductGroup = companyBaseProductGroup;
    }

    public String getCompanyBaseProductToBusiness() {
        return companyBaseProductToBusiness;
    }

    public void setCompanyBaseProductToBusiness(String companyBaseProductToBusiness) {
        this.companyBaseProductToBusiness = companyBaseProductToBusiness;
    }


}

