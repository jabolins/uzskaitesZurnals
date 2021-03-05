package operativeDate;

public class OperatvieDataConstants {
    public static final String[] GROUP_OF_SERVICES = new String[]{
            "degviela", "auto rezerves daļas/remonts", "preces birojam", "datortehnika darba", "materiāli darbam",
            "kancelejas preces", "mārketinga izcevumi", "ar uzņēmējdarbību nesaistīti izdevumi"
    };

    public static String getAttentionProductToBusiness(String groupOfServices) {
        switch (groupOfServices) {
            case "degviela":
            case "auto rezerves daļas/remonts":
                return "partly";

            case "preces birojam":
            case "datortehnika darba":
            case "materiāli darbam":
            case "kancelejas preces":
            case "mārketinga izcevumi":
                return "yes";

            case "ar uzņēmējdarbību nesaistīti izdevumi":
                return "no";
        }
        return null;
    }
}
