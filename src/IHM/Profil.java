package IHM;

public class Profil {
    private String nom;
    private String prenom;
    private String pseudo;
    private String langues; // Format: "Français (5 étoiles), Anglais (3 étoiles)"
    private String cycle;
    private String anneeEtude; // Ex: "1ère année", "4ème année"
    private int anneeAcademique; // Ex: 2024

    public Profil(String nom, String prenom, String pseudo) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
    }

    public Profil(String nom, String prenom, String pseudo, String langues,
                         String cycle, String anneeEtude, int anneeAcademique) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.langues = langues;
        this.cycle = cycle;
        this.anneeEtude = anneeEtude;
        this.anneeAcademique = anneeAcademique;
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

    public String getLangues() {
        return langues;
    }

    public String getCycle() {
        return cycle;
    }

    public String getAnneeEtude() {
        return anneeEtude;
    }

    public int getAnneeAcademique() {
        return anneeAcademique;
    }

    @Override
    public String toString() {
        return pseudo + " (" + nom + " " + prenom + ")";
    }

    public String toDetailedString() {
        return "Pseudo: " + pseudo + "\n" +
                "Nom: " + nom + " " + prenom + "\n" +
                "Langues: " + langues + "\n" +
                "Cycle: " + cycle + " - " + anneeEtude + "\n" +
                "Année académique: " + anneeAcademique;
    }
}