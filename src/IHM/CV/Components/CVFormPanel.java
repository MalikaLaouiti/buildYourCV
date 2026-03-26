package IHM.CV.Components;

import IHM.CV.Services.Data;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Date;

public class CVFormPanel {

    // Data arrays
    private String[] langues = {"Arabe", "Français", "Anglais", "Espagnol", "Allemand"};
    private String[] competences = {
            "Web Development", "Desktop Application Development",
            "Database Management", "Project Management",
            "Software Architecture", "UI/UX Design"
    };
    private String[] langages = {
            "Python", "C", "Java", "SQL", "PLSQL", ".NET C#",
            "JavaScript", "HTML/CSS", "TypeScript", "PHP"
    };
    private String[] databases = {"MySQL", "PostgreSQL", "SQLite", "MongoDB", "Oracle"};
    private String[] outils = {
            "Flex", "Bison", "PyQt5", "Azure AI", "Next.js",
            "Tailwind CSS", "React", "Node.js", "Git", "Docker"
    };

    // Composants d'interface (propres à ce panel)
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfEmail;
    private JTextField tfTelephone;
    private JTextField tfAdresse;
    private JTextField tfGithub;
    private JRadioButton rbMasculin;
    private JRadioButton rbFeminin;
    private ButtonGroup bgSexe;
    private JDateChooser dateChooser;

    private JTextArea taExperience;
    private JTextArea taEducation;
    private JTextArea taProjet;

    private JList<String> listLangue;
    private JList<String> listCompetences;
    private JList<String> listLangages;
    private JList<String> listDatabases;
    private JList<String> listOutils;


    public CVFormPanel() {
        initComponents();
    }

    private void initComponents() {
        // Initialisation des composants
        tfNom = new JTextField(20);
        tfPrenom = new JTextField(20);
        tfEmail = new JTextField();
        tfTelephone = new JTextField();
        tfAdresse = new JTextField();
        tfGithub = new JTextField();

        rbMasculin = new JRadioButton("Masculin");
        rbFeminin = new JRadioButton("Féminin");
        rbMasculin.setSelected(true);
        bgSexe = new ButtonGroup();
        bgSexe.add(rbMasculin);
        bgSexe.add(rbFeminin);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(new Date());

        taExperience = new JTextArea(5, 20);
        taExperience.setLineWrap(true);
        taExperience.setWrapStyleWord(true);

        taEducation = new JTextArea(5, 20);
        taEducation.setLineWrap(true);
        taEducation.setWrapStyleWord(true);

        taProjet = new JTextArea(4, 20);
        taProjet.setLineWrap(true);
        taProjet.setWrapStyleWord(true);

        // Initialisation des listes
        listLangue = new JList<>(langues);
        listCompetences = new JList<>(competences);
        listLangages = new JList<>(langages);
        listDatabases = new JList<>(databases);
        listOutils = new JList<>(outils);
    }

    // Ajoutez cette méthode à votre CVFormPanel existant
    public Data getCVData() {
        Data data = new Data();

        // Remplir les données personnelles
        data.setNom(tfNom.getText());
        data.setPrenom(tfPrenom.getText());
        data.setEmail(tfEmail.getText());
        data.setTelephone(tfTelephone.getText());
        data.setAdresse(tfAdresse.getText());
        data.setGithub(tfGithub.getText());
        data.setSexe(rbMasculin.isSelected() ? "Masculin" : "Féminin");
        data.setDateNaissance(dateChooser.getDate());

        // Remplir les compétences
        data.setLangues(listLangue.getSelectedValuesList());
        data.setCompetences(listCompetences.getSelectedValuesList());
        data.setLangages(listLangages.getSelectedValuesList());
        data.setDatabases(listDatabases.getSelectedValuesList());
        data.setOutils(listOutils.getSelectedValuesList());

        // Remplir les textes
        data.setExperience(taExperience.getText());
        data.setFormation(taEducation.getText());
        data.setProjet(taProjet.getText());

        return data;
    }

    public JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;

        int row = 0;

        // Section: Informations Personnelles
        JPanel personalPanel = createPersonalInfoPanel();
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        panel.add(personalPanel, gbc);

        // Section: Compétences
        JPanel skillsPanel = createSkillsPanel();
        gbc.gridy = row++;
        panel.add(skillsPanel, gbc);

        // Section: Expérience et Formation
        JPanel expEduPanel = createExpEduPanel();
        gbc.gridy = row++;
        panel.add(expEduPanel, gbc);

        // Section: Projet
        JPanel projectPanel = createProjectPanel();
        gbc.gridy = row++;
        panel.add(projectPanel, gbc);

        return panel;
    }

    public JPanel createPersonalInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Informations Personnelles"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);

        int row = 0;

        // Nom et Prénom
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(tfNom, gbc);

        gbc.gridx = 2; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        panel.add(tfPrenom, gbc);
        row++;

        // Sexe et Date de Naissance
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Sexe:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sexPanel.add(rbMasculin);
        sexPanel.add(rbFeminin);
        panel.add(sexPanel, gbc);

        gbc.gridx = 2; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Date de naissance:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        panel.add(dateChooser, gbc);
        row++;

        // Contact Information
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 1.0;
        panel.add(tfEmail, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Téléphone:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(tfTelephone, gbc);

        gbc.gridx = 2; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Adresse:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        panel.add(tfAdresse, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("GitHub:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 1.0;
        panel.add(tfGithub, gbc);

        return panel;
    }

    public JPanel createSkillsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 5, 10, 0));
        panel.setBorder(new TitledBorder("Compétences Techniques"));

        // Langues
        panel.add(createListPanel("Langues Parlées", langues, listLangue));

        // Compétences
        panel.add(createListPanel("Compétences", competences, listCompetences));

        // Langages de programmation
        panel.add(createListPanel("Langages", langages, listLangages));

        // Bases de données
        panel.add(createListPanel("Bases de Données", databases, listDatabases));

        // Outils & Technologies
        panel.add(createListPanel("Outils & Technologies", outils, listOutils));

        return panel;
    }

    private JPanel createExpEduPanel() {
        JPanel expEduPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        expEduPanel.setBorder(new TitledBorder("Expérience et Formation"));

        // Expérience
        JPanel expPanel = new JPanel(new BorderLayout());
        expPanel.setBorder(new TitledBorder("Expérience Professionnelle"));
        expPanel.add(new JScrollPane(taExperience), BorderLayout.CENTER);
        expEduPanel.add(expPanel);

        // Éducation
        JPanel eduPanel = new JPanel(new BorderLayout());
        eduPanel.setBorder(new TitledBorder("Formation"));
        eduPanel.add(new JScrollPane(taEducation), BorderLayout.CENTER);
        expEduPanel.add(eduPanel);

        return expEduPanel;
    }

    private JPanel createProjectPanel() {
        JPanel projectPanel = new JPanel(new BorderLayout());
        projectPanel.setBorder(new TitledBorder("Projet de Fin d'Études / Projets Importants"));
        projectPanel.add(new JScrollPane(taProjet), BorderLayout.CENTER);
        return projectPanel;
    }

    private JPanel createListPanel(String title, String[] items, JList<String> list) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder(title));

        list.setVisibleRowCount(5);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(150, 120));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public String[] getLangues() {
        return langues;
    }

    public void setLangues(String[] langues) {
        this.langues = langues;
    }

    public String[] getCompetences() {
        return competences;
    }

    public void setCompetences(String[] competences) {
        this.competences = competences;
    }

    public String[] getLangages() {
        return langages;
    }

    public void setLangages(String[] langages) {
        this.langages = langages;
    }

    public String[] getDatabases() {
        return databases;
    }

    public void setDatabases(String[] databases) {
        this.databases = databases;
    }

    public String[] getOutils() {
        return outils;
    }

    public void setOutils(String[] outils) {
        this.outils = outils;
    }

    public JTextField getTfNom() {
        return tfNom;
    }

    public void setTfNom(JTextField tfNom) {
        this.tfNom = tfNom;
    }

    public JTextField getTfPrenom() {
        return tfPrenom;
    }

    public void setTfPrenom(JTextField tfPrenom) {
        this.tfPrenom = tfPrenom;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public void setTfEmail(JTextField tfEmail) {
        this.tfEmail = tfEmail;
    }

    public JTextField getTfTelephone() {
        return tfTelephone;
    }

    public void setTfTelephone(JTextField tfTelephone) {
        this.tfTelephone = tfTelephone;
    }

    public JTextField getTfAdresse() {
        return tfAdresse;
    }

    public void setTfAdresse(JTextField tfAdresse) {
        this.tfAdresse = tfAdresse;
    }

    public JTextField getTfGithub() {
        return tfGithub;
    }

    public void setTfGithub(JTextField tfGithub) {
        this.tfGithub = tfGithub;
    }

    public JRadioButton getRbMasculin() {
        return rbMasculin;
    }

    public void setRbMasculin(JRadioButton rbMasculin) {
        this.rbMasculin = rbMasculin;
    }

    public JRadioButton getRbFeminin() {
        return rbFeminin;
    }

    public void setRbFeminin(JRadioButton rbFeminin) {
        this.rbFeminin = rbFeminin;
    }

    public ButtonGroup getBgSexe() {
        return bgSexe;
    }

    public void setBgSexe(ButtonGroup bgSexe) {
        this.bgSexe = bgSexe;
    }

    public JDateChooser getDateChooser() {
        return dateChooser;
    }

    public void setDateChooser(JDateChooser dateChooser) {
        this.dateChooser = dateChooser;
    }

    public JTextArea getTaExperience() {
        return taExperience;
    }

    public void setTaExperience(JTextArea taExperience) {
        this.taExperience = taExperience;
    }

    public JTextArea getTaEducation() {
        return taEducation;
    }

    public void setTaEducation(JTextArea taEducation) {
        this.taEducation = taEducation;
    }

    public JTextArea getTaProjet() {
        return taProjet;
    }

    public void setTaProjet(JTextArea taProjet) {
        this.taProjet = taProjet;
    }

    public JList<String> getListLangue() {
        return listLangue;
    }

    public void setListLangue(JList<String> listLangue) {
        this.listLangue = listLangue;
    }

    public JList<String> getListCompetences() {
        return listCompetences;
    }

    public void setListCompetences(JList<String> listCompetences) {
        this.listCompetences = listCompetences;
    }

    public JList<String> getListLangages() {
        return listLangages;
    }

    public void setListLangages(JList<String> listLangages) {
        this.listLangages = listLangages;
    }

    public JList<String> getListDatabases() {
        return listDatabases;
    }

    public void setListDatabases(JList<String> listDatabases) {
        this.listDatabases = listDatabases;
    }

    public JList<String> getListOutils() {
        return listOutils;
    }

    public void setListOutils(JList<String> listOutils) {
        this.listOutils = listOutils;
    }
}