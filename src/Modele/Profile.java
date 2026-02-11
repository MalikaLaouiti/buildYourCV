package Modele;

public class Profile {
    String nom ,prenom ,pseudo;

    public Profile(String prenom, String nom, String pseudo) {
        this.prenom = prenom;
        this.nom = nom;
        this.pseudo = pseudo;
    }

    public Profile() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                '}';
    }
}
