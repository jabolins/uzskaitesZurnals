package operativeDate;

import db.Companies;
import interfaces.getOperativeData;

import java.util.ArrayList;

public class OperativeDate implements getOperativeData {

    private ArrayList<Companies> ArrayAllCompanies;
    private ArrayList<String> ArrayCompaniesShortName;

    public OperativeDate(ArrayList<String> groupOfServices) {
        this.groupOfServices = groupOfServices;
        groupOfServices.add("degviela");
        groupOfServices.add("preces birojam");
        groupOfServices.add("datortehnika darbam");
        groupOfServices.add("materiāli darbam");
        groupOfServices.add("kancelejas preces");
        groupOfServices.add("mārketinga izdevumi");
        groupOfServices.add("ar uzņējdarbību nesaisīts");
    }

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
