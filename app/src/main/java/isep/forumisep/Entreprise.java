package isep.forumisep;

/**
 * Created by linfengwang on 21/12/2017.
 */

public class Entreprise {

    private String date;
    private String name;
    private String etat;

    public Entreprise(){
    }

    public Entreprise(String name,String etat,String date){
        this.name=name;
        this.date=date;
        this.etat=etat;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getEtat() {
        return etat;
    }
}
