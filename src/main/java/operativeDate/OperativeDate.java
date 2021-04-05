package operativeDate;

import db.Companies;

import java.util.ArrayList;

public class OperativeDate {

    private ArrayList<Companies> ArrayAllCompanies;
    private ArrayList<String> ArrayCompaniesShortName;

    private ArrayList<String> groupOfServices;

    public ArrayList<Companies> getArrayAllCompanies() {
        return ArrayAllCompanies;
    }

    public void setArrayAllCompanies(ArrayList<Companies> arrayAllCompanies) {
        ArrayAllCompanies = arrayAllCompanies;
    }

    public ArrayList<String> getArrayCompaniesShortName() {
        return ArrayCompaniesShortName;
    }

    public void setArrayCompaniesShortName(ArrayList<String> arrayCompaniesShortName) {
        ArrayCompaniesShortName = arrayCompaniesShortName;
    }

    public ArrayList<String> getGroupOfServices() {
        return groupOfServices;
    }

    public void setGroupOfServices(ArrayList<String> groupOfServices) {
        this.groupOfServices = groupOfServices;
    }




}
