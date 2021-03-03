package db;

public class DateBaseConstants {

    // datu bāze un piekļuves
    public static final String DB_HOST = "127.0.0.1";
    public static final String DB_PORT = "3306";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "Rootroot1$";
    public static final String DB_NAME = "uzskaiteszurnals";

    // tabula finanšu dokumenti
    public static final String TABLE_FINANSUDOK = "finansudokumenti";

    public static final String NR = "1kartasNr";
    public static final String DATE = "2datums";
    public static final String DOKUMENT_NR = "3dokVeidsNr";
    public static final String COMPANY = "4dokAutors";
    public static final String DESCRIPTION_OF_DEAL = "5darijumaApraksts";
    public static final String CASH_RECEIVED = "7kaseSanemts";
    public static final String CASH_ISSUED = "8kaseIzdots";
    public static final String BANK_RECEIVED = "9bankaSanemts";
    public static final String BANK_ISSUED = "10bankaIzdots";
    public static final String INCOME_BUSINESS_NOT_FARMING = "14ienemumiSaimnDarb";
    public static final String INCOME_NOT_FOR_TAX= "17ienemumiNotSaimnDarb";
    public static final String TOTAL_INCOME = "18ienemumiKopa";
    public static final String EXPENSES_BUSINESS_NOT_FARMING = "20izdevumiSaimnDarb";
    public static final String PROPORTIONATE_EXPENSES = "21izdevumiPropDalami";
    public static final String EXPENSNES_NOT_FOR_BUSINESS = "22izdevumiNotSaimnDarb";
    public static final String TOTAL_EXPENSES = "24izdevumiKopa";
    public static final String ATTITUDE_FOR_BUSINESS = "saistibaArUznemejdarbibu";

    // tabula lietotāji

    public static final String TABLE_LIETOTAJI = "lietotaji";

    public static final String LIET_LIETOTAJVARDS = "lietotajvards";
    public static final String LIET_PAROLE = "parole";
    public static final String LIET_E_PASTS = "epasts";
    public static final String LIET_LOMA = "loma";

    // tabula uzņēmumi

    public static final String TABLE_UZNEMUMI = "uznemumi";

    public static final String COMPANY_NAME = "nosaukums";
    public static final String COMPANY_sHORT_NAME = "saisinataisNosaukums";
    public static final String COMPANY_REGISTRATION_NR = "regNr";
    public static final String COMPANY_ADDRESS = "adrese";
    public static final String COMPANY_ACCOUNT = "konts";
    public static final String COMPANY_PHONE = "telefons";
    public static final String COMPANY_E_MAIL = "epasts";
    public static final String COMPANY_BASIC_PRODUCT_GROUP = "pamatprodukts";
    public static final String PRODUCT_ATTENTION_TO_BUSINESS = "saistibaUznemejdarbibai";


}
