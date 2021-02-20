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
    public static final String DATUMS = "2datums";
    public static final String DOK_VEIDS = "3dokVeidsNr";
    public static final String DOK_AUTORS = "4dokAutors";
    public static final String DARIJUMA_APRAKSTS = "5darijumaApraksts";
    public static final String KASE_SANEMTS = "7kaseSanemts";
    public static final String KASE_IZDOTS = "8kaseIzdots";
    public static final String BANKA_SANEMTS = "9bankaSanemts";
    public static final String BANKA_IZDOTS = "10bankaIzdots";
    public static final String IENEMUMI_SAIMN = "14ienemumiSaimnDarb";
    public static final String IENEMUMI_NOT_SAIMN = "17ienemumiNotSaimnDarb";
    public static final String IENEMUMI_KOPA = "18ienemumiKopa";
    public static final String IZDEVUMI_SAIMN = "20izdevumiSaimnDarb";
    public static final String IZDEVUMI_PROPORC = "21izdevumiPropDalami";
    public static final String IZDEVUMI_NOT_SAIMN = "22izdevumiNotSaimnDarb";
    public static final String IZDEVUMI_KOPA = "24izdevumiKopa";
    public static final String SAISTIBA_UZNEMEJD = "saistibaArUznemejdarbibu";

    // tabula lietotāji

    public static final String TABLE_LIETOTAJI = "lietotaji";

    public static final String LIET_LIETOTAJVARDS = "lietotajvards";
    public static final String LIET_PAROLE = "parole";
    public static final String LIET_E_PASTS = "epasts";
    public static final String LIET_LOMA = "loma";

    // tabula uzņēmumi

    public static final String TABLE_UZNEMUMI = "uznemumi";

    public static final String UZN_NOSAUKUMS = "nosaukums";
    public static final String UZN_SAISINATAIS_NOSAUKUMS = "saisinataisNosaukums";
    public static final String UZN_REG_NR = "regNr";
    public static final String UZN_ADRESE = "adrese";
    public static final String UZN_KONTS = "konts";
    public static final String UZN_TELEFONS = "telefons";
    public static final String UZN_E_PASTS = "epasts";
    public static final String UZN_PAMATPRODUKTS = "pamatprodukts";
    public static final String UZN_PRODUKTA_ATTIECIBA_UZNEMEJDARBIBAI = "saistibaUznemejdarbibai";


}
