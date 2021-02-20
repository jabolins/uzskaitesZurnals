package interfaces;

import db.Companies;

import java.util.ArrayList;

public interface getOperativeData {
    ArrayList<Companies> getArrayAllCompanies();

    ArrayList<String> getArrayCompaniesShortName();

    void setArrayAllCompanies(ArrayList<Companies> arrayAllCompanies);

    void setArrayCompaniesShortName(ArrayList<String> arrayCompaniesShortName);
}
