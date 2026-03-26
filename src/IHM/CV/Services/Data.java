package IHM.CV.Services;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Data {

        // Informations personnelles
        private String nom;
        private String prenom;
        private String email;
        private String telephone;
        private String adresse;
        private String github;
        private String sexe;
        private Date dateNaissance;

        // Compétences
        private List<String> langues;
        private List<String> competences;
        private List<String> langages;
        private List<String> databases;
        private List<String> outils;

        // Textes
        private String experience;
        private String formation;
        private String projet;

        public Data() {
            this.langues = new ArrayList<>();
            this.competences = new ArrayList<>();
            this.langages = new ArrayList<>();
            this.databases = new ArrayList<>();
            this.outils = new ArrayList<>();
            this.experience = "";
            this.formation = "";
            this.projet = "";
        }

        // Getters et Setters
        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }

        public String getPrenom() { return prenom; }
        public void setPrenom(String prenom) { this.prenom = prenom; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getTelephone() { return telephone; }
        public void setTelephone(String telephone) { this.telephone = telephone; }

        public String getAdresse() { return adresse; }
        public void setAdresse(String adresse) { this.adresse = adresse; }

        public String getGithub() { return github; }
        public void setGithub(String github) { this.github = github; }

        public String getSexe() { return sexe; }
        public void setSexe(String sexe) { this.sexe = sexe; }

        public Date getDateNaissance() { return dateNaissance; }
        public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

        public List<String> getLangues() { return langues; }
        public void setLangues(List<String> langues) { this.langues = langues; }

        public List<String> getCompetences() { return competences; }
        public void setCompetences(List<String> competences) { this.competences = competences; }

        public List<String> getLangages() { return langages; }
        public void setLangages(List<String> langages) { this.langages = langages; }

        public List<String> getDatabases() { return databases; }
        public void setDatabases(List<String> databases) { this.databases = databases; }

        public List<String> getOutils() { return outils; }
        public void setOutils(List<String> outils) { this.outils = outils; }

        public String getExperience() { return experience; }
        public void setExperience(String experience) { this.experience = experience; }

        public String getFormation() { return formation; }
        public void setFormation(String formation) { this.formation = formation; }

        public String getProjet() { return projet; }
        public void setProjet(String projet) { this.projet = projet; }

        // Validation
        public boolean isValid() {
            return nom != null && !nom.trim().isEmpty()
                    && prenom != null && !prenom.trim().isEmpty();
        }

        @Override
        public String toString() {
            return prenom + " " + nom;
        }
    }
