package IHM;

public class Profil {
    private String nom;
    private String prenom;
    private String pseudo;
    private String langue;
    private String cycle;
    private int annee;

    public Profil(String nom, String prenom, String pseudo, String langue, String cycle, int annee) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.langue = langue;
        this.cycle = cycle;
        this.annee = annee;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getLangue() {
        return langue;
    }

    public String getCycle() {
        return cycle;
    }

    public int getAnnee() {
        return annee;
    }

    @Override
    public String toString() {
        return pseudo +" "+ nom + " " + prenom ;
    }
}