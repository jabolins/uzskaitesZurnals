package operativeDate;

import db.Companies;
import interfaces.getOperativeData;

import java.util.ArrayList;

public class operativeDate implements getOperativeData {
    private ArrayList<Companies> ArrayAllCompanies;
    private ArrayList<String> ArrayCompaniesShortName;

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



}
