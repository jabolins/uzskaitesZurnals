package db;

public class User {


    public String getLietotajvards() {
        return lietotajvards;
    }

    public void setLietotajvards(String lietotajvards) {
        this.lietotajvards = lietotajvards;
    }

    public String getParole() {
        return parole;
    }

    public void setParole(String parole) {
        this.parole = parole;
    }

    public String getEpasts() {
        return epasts;
    }

    public void setEpasts(String epasts) {
        this.epasts = epasts;
    }
    public String getLoma() {
        return loma;
    }

    public void setLoma(String loma) {
        this.loma = loma;
    }

    private String lietotajvards;
    private String parole;
    private String epasts;
    private String loma;

    public User(String lietotajvards, String parole, String epasts, String loma){
        this.lietotajvards= lietotajvards;
        this.parole= parole;
        this.epasts= epasts;
        this.loma= loma;
    }

}
