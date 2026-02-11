package IHM;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class CurriculumVitae extends JFrame {
    // Components
    private JPanel mainPanel;
    private JTextField tfNom, tfPrenom, tfEmail, tfTelephone, tfAdresse, tfGithub;
    private JDateChooser dateChooser;
    private JList<String> listLangue, listCompetences, listLangages, listDatabases, listOutils;
    private JTextArea taExperience, taEducation, taProjet;
    private JRadioButton rbMasculin, rbFeminin;
    private ButtonGroup bgSexe;
    private JFileChooser fileChooser;
    private JCheckBox cbExportPDF;

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

    public CurriculumVitae() {
        initUI();
        setupListeners();
    }

    private void initUI() {
        setTitle("Générateur de Curriculum Vitae");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Header
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center - Form
        JPanel formPanel = createFormPanel();
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer - Buttons
        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(44, 62, 80));

        JLabel title = new JLabel("GÉNÉRATEUR DE CURRICULUM VITAE");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Créez votre CV professionnel en quelques clics");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(236, 240, 241));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(20));
        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(20));

        return panel;
    }

    private JPanel createFormPanel() {
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
        JPanel expEduPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        expEduPanel.setBorder(new TitledBorder("Expérience et Formation"));

        // Expérience
        JPanel expPanel = new JPanel(new BorderLayout());
        expPanel.setBorder(new TitledBorder("Expérience Professionnelle"));
        taExperience = new JTextArea(5, 20);
        taExperience.setLineWrap(true);
        taExperience.setWrapStyleWord(true);
        expPanel.add(new JScrollPane(taExperience), BorderLayout.CENTER);
        expEduPanel.add(expPanel);

        // Éducation
        JPanel eduPanel = new JPanel(new BorderLayout());
        eduPanel.setBorder(new TitledBorder("Formation"));
        taEducation = new JTextArea(5, 20);
        taEducation.setLineWrap(true);
        taEducation.setWrapStyleWord(true);
        eduPanel.add(new JScrollPane(taEducation), BorderLayout.CENTER);
        expEduPanel.add(eduPanel);

        gbc.gridy = row++;
        panel.add(expEduPanel, gbc);

        // Section: Projet
        JPanel projectPanel = new JPanel(new BorderLayout());
        projectPanel.setBorder(new TitledBorder("Projet de Fin d'Études / Projets Importants"));
        taProjet = new JTextArea(4, 20);
        taProjet.setLineWrap(true);
        taProjet.setWrapStyleWord(true);
        projectPanel.add(new JScrollPane(taProjet), BorderLayout.CENTER);

        gbc.gridy = row++;
        panel.add(projectPanel, gbc);

        return panel;
    }

    private JPanel createPersonalInfoPanel() {
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
        tfNom = new JTextField(20);
        panel.add(tfNom, gbc);

        gbc.gridx = 2; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        tfPrenom = new JTextField(20);
        panel.add(tfPrenom, gbc);
        row++;

        // Sexe et Date de Naissance
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Sexe:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMasculin = new JRadioButton("Masculin");
        rbFeminin = new JRadioButton("Féminin");
        rbMasculin.setSelected(true);
        bgSexe = new ButtonGroup();
        bgSexe.add(rbMasculin);
        bgSexe.add(rbFeminin);
        sexPanel.add(rbMasculin);
        sexPanel.add(rbFeminin);
        panel.add(sexPanel, gbc);

        gbc.gridx = 2; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Date de naissance:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setDate(new Date());
        panel.add(dateChooser, gbc);
        row++;

        // Contact Information
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 1.0;
        tfEmail = new JTextField();
        panel.add(tfEmail, gbc);
        gbc.gridwidth = 1;
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Téléphone:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        tfTelephone = new JTextField();
        panel.add(tfTelephone, gbc);

        gbc.gridx = 2; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("Adresse:"), gbc);
        gbc.gridx = 3; gbc.weightx = 1.0;
        tfAdresse = new JTextField();
        panel.add(tfAdresse, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        panel.add(new JLabel("GitHub:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 1.0;
        tfGithub = new JTextField();
        panel.add(tfGithub, gbc);

        return panel;
    }

    private JPanel createSkillsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 5, 10, 0));
        panel.setBorder(new TitledBorder("Compétences Techniques"));

        // Initialize lists before creating panels
        listLangue = new JList<>(langues);
        listCompetences = new JList<>(competences);
        listLangages = new JList<>(langages);
        listDatabases = new JList<>(databases);
        listOutils = new JList<>(outils);

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

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(new Color(236, 240, 241));

        JButton btnGenerer = new JButton("Générer CV HTML");
        btnGenerer.setBackground(new Color(46, 204, 113));
        btnGenerer.setForeground(Color.WHITE);
        btnGenerer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGenerer.setPreferredSize(new Dimension(180, 40));

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setBackground(new Color(231, 76, 60));
        btnQuitter.setForeground(Color.WHITE);
        btnQuitter.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnQuitter.setPreferredSize(new Dimension(120, 40));

        cbExportPDF = new JCheckBox("Exporter également en PDF");

        panel.add(btnGenerer);
        panel.add(btnQuitter);
        panel.add(cbExportPDF);

        // Assign buttons to class variables for event handling
        btnGenerer.addActionListener(e -> genererCV());
        btnQuitter.addActionListener(e -> System.exit(0));

        return panel;
    }

    private void setupListeners() {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() ||
                        f.getName().toLowerCase().matches(".*\\.(jpg|jpeg|png|gif)$");
            }

            @Override
            public String getDescription() {
                return "Images (JPG, PNG, GIF)";
            }
        });
    }

    private void genererCV() {
        // Validation des champs requis
        if (tfNom.getText().trim().isEmpty() || tfPrenom.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez saisir au moins le nom et le prénom.",
                    "Champs requis",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Collecte des données
            String nom = tfNom.getText().trim();
            String prenom = tfPrenom.getText().trim();
            String email = tfEmail.getText().trim();
            String telephone = tfTelephone.getText().trim();
            String adresse = tfAdresse.getText().trim();
            String github = tfGithub.getText().trim();
            String sexe = rbMasculin.isSelected() ? "Masculin" : "Féminin";

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateNaissance = dateChooser.getDate() != null ?
                    sdf.format(dateChooser.getDate()) : "Non spécifiée";

            // Génération du HTML
            String html = generateHTML(nom, prenom, email, telephone, adresse,
                    github, sexe, dateNaissance);

            // Sauvegarde du fichier
            File file = new File("CV_" + nom + "_" + prenom + ".html");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(html);
            }

            // Message de confirmation
            String message = "CV généré avec succès!\n\n";
            message += "Fichier: " + file.getAbsolutePath() + "\n";

            if (cbExportPDF.isSelected()) {
                message += "\nNote: L'export PDF nécessite l'installation de bibliothèques supplémentaires.";
            }

            JOptionPane.showMessageDialog(this, message, "Succès",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la génération du CV: " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private String generateHTML(String nom, String prenom, String email, String telephone,
                                String adresse, String github, String sexe, String dateNaissance) {

        // Récupération des sélections multiples avec vérification null
        String languesSelected = listLangue.getSelectedValuesList().isEmpty() ?
                "Aucune sélection" : String.join(", ", listLangue.getSelectedValuesList());
        String competencesSelected = listCompetences.getSelectedValuesList().isEmpty() ?
                "Aucune sélection" : String.join(", ", listCompetences.getSelectedValuesList());
        String langagesSelected = listLangages.getSelectedValuesList().isEmpty() ?
                "Aucune sélection" : String.join(", ", listLangages.getSelectedValuesList());
        String databasesSelected = listDatabases.getSelectedValuesList().isEmpty() ?
                "Aucune sélection" : String.join(", ", listDatabases.getSelectedValuesList());
        String outilsSelected = listOutils.getSelectedValuesList().isEmpty() ?
                "Aucune sélection" : String.join(", ", listOutils.getSelectedValuesList());

        // Escape HTML characters
        email = escapeHtml(email);
        telephone = escapeHtml(telephone);
        adresse = escapeHtml(adresse);
        github = escapeHtml(github);

        return "<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>CV - " + nom + " " + prenom + "</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "        }\n" +
                "        \n" +
                "        body {\n" +
                "            background-color: #f5f5f5;\n" +
                "            color: #333;\n" +
                "            line-height: 1.6;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        \n" +
                "        .cv-container {\n" +
                "            max-width: 1000px;\n" +
                "            margin: 0 auto;\n" +
                "            background: white;\n" +
                "            box-shadow: 0 0 20px rgba(0,0,0,0.1);\n" +
                "            border-radius: 10px;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        \n" +
                "        .header {\n" +
                "            background: linear-gradient(135deg, #2c3e50 0%, #3498db 100%);\n" +
                "            color: white;\n" +
                "            padding: 40px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        \n" +
                "        .name {\n" +
                "            font-size: 2.5em;\n" +
                "            font-weight: 700;\n" +
                "            margin-bottom: 10px;\n" +
                "            letter-spacing: 1px;\n" +
                "        }\n" +
                "        \n" +
                "        .title {\n" +
                "            font-size: 1.2em;\n" +
                "            opacity: 0.9;\n" +
                "            font-weight: 300;\n" +
                "        }\n" +
                "        \n" +
                "        .contact-info {\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            flex-wrap: wrap;\n" +
                "            gap: 20px;\n" +
                "            margin-top: 20px;\n" +
                "            font-size: 0.95em;\n" +
                "        }\n" +
                "        \n" +
                "        .contact-item {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            gap: 8px;\n" +
                "        }\n" +
                "        \n" +
                "        .section {\n" +
                "            padding: 30px 40px;\n" +
                "            border-bottom: 1px solid #eee;\n" +
                "        }\n" +
                "        \n" +
                "        .section:last-child {\n" +
                "            border-bottom: none;\n" +
                "        }\n" +
                "        \n" +
                "        .section-title {\n" +
                "            color: #2c3e50;\n" +
                "            font-size: 1.4em;\n" +
                "            margin-bottom: 20px;\n" +
                "            padding-bottom: 10px;\n" +
                "            border-bottom: 2px solid #3498db;\n" +
                "            font-weight: 600;\n" +
                "        }\n" +
                "        \n" +
                "        .grid-2col {\n" +
                "            display: grid;\n" +
                "            grid-template-columns: repeat(2, 1fr);\n" +
                "            gap: 30px;\n" +
                "        }\n" +
                "        \n" +
                "        .info-group {\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "        \n" +
                "        .info-label {\n" +
                "            font-weight: 600;\n" +
                "            color: #2c3e50;\n" +
                "            margin-bottom: 5px;\n" +
                "        }\n" +
                "        \n" +
                "        .info-value {\n" +
                "            color: #555;\n" +
                "        }\n" +
                "        \n" +
                "        .skills-list {\n" +
                "            list-style: none;\n" +
                "            display: flex;\n" +
                "            flex-wrap: wrap;\n" +
                "            gap: 10px;\n" +
                "        }\n" +
                "        \n" +
                "        .skill-item {\n" +
                "            background: #e8f4fc;\n" +
                "            padding: 8px 15px;\n" +
                "            border-radius: 20px;\n" +
                "            font-size: 0.9em;\n" +
                "            color: #2c3e50;\n" +
                "            border: 1px solid #3498db;\n" +
                "        }\n" +
                "        \n" +
                "        .experience-item, .education-item {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        \n" +
                "        .item-title {\n" +
                "            font-weight: 600;\n" +
                "            color: #2c3e50;\n" +
                "            margin-bottom: 5px;\n" +
                "        }\n" +
                "        \n" +
                "        .item-date {\n" +
                "            color: #7f8c8d;\n" +
                "            font-size: 0.9em;\n" +
                "            margin-bottom: 5px;\n" +
                "        }\n" +
                "        \n" +
                "        .item-description {\n" +
                "            color: #555;\n" +
                "        }\n" +
                "        \n" +
                "        @media print {\n" +
                "            body {\n" +
                "                background: white;\n" +
                "                padding: 0;\n" +
                "            }\n" +
                "            \n" +
                "            .cv-container {\n" +
                "                box-shadow: none;\n" +
                "                border-radius: 0;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"cv-container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1 class=\"name\">" + prenom + " " + nom + "</h1>\n" +
                "            <div class=\"title\">Développeur Logiciel & Architecte Système</div>\n" +
                "            <div class=\"contact-info\">\n" +
                "                <div class=\"contact-item\">📧 " + email + "</div>\n" +
                "                <div class=\"contact-item\">📱 " + telephone + "</div>\n" +
                "                <div class=\"contact-item\">📍 " + adresse + "</div>\n" +
                "                <div class=\"contact-item\">🐱 " + github + "</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"section\">\n" +
                "            <h2 class=\"section-title\">RÉSUMÉ PROFESSIONNEL</h2>\n" +
                "            <p>Développeur motivé avec une expertise dans le développement logiciel et l'architecture système. \n" +
                "            Passionné par la création de solutions web et logicielles innovantes.</p>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"section\">\n" +
                "            <h2 class=\"section-title\">INFORMATIONS PERSONNELLES</h2>\n" +
                "            <div class=\"grid-2col\">\n" +
                "                <div class=\"info-group\">\n" +
                "                    <div class=\"info-label\">Date de naissance</div>\n" +
                "                    <div class=\"info-value\">" + dateNaissance + "</div>\n" +
                "                </div>\n" +
                "                <div class=\"info-group\">\n" +
                "                    <div class=\"info-label\">Sexe</div>\n" +
                "                    <div class=\"info-value\">" + sexe + "</div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"section\">\n" +
                "            <h2 class=\"section-title\">COMPÉTENCES TECHNIQUES</h2>\n" +
                "            <div class=\"grid-2col\">\n" +
                "                <div>\n" +
                "                    <div class=\"info-group\">\n" +
                "                        <div class=\"info-label\">Langues parlées</div>\n" +
                "                        <div class=\"info-value\">" + languesSelected + "</div>\n" +
                "                    </div>\n" +
                "                    <div class=\"info-group\">\n" +
                "                        <div class=\"info-label\">Compétences</div>\n" +
                "                        <ul class=\"skills-list\">\n" +
                generateSkillItems(competencesSelected) +
                "                        </ul>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div>\n" +
                "                    <div class=\"info-group\">\n" +
                "                        <div class=\"info-label\">Langages de programmation</div>\n" +
                "                        <ul class=\"skills-list\">\n" +
                generateSkillItems(langagesSelected) +
                "                        </ul>\n" +
                "                    </div>\n" +
                "                    <div class=\"info-group\">\n" +
                "                        <div class=\"info-label\">Bases de données</div>\n" +
                "                        <ul class=\"skills-list\">\n" +
                generateSkillItems(databasesSelected) +
                "                        </ul>\n" +
                "                    </div>\n" +
                "                    <div class=\"info-group\">\n" +
                "                        <div class=\"info-label\">Outils & Technologies</div>\n" +
                "                        <ul class=\"skills-list\">\n" +
                generateSkillItems(outilsSelected) +
                "                        </ul>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"section\">\n" +
                "            <h2 class=\"section-title\">EXPÉRIENCE PROFESSIONNELLE</h2>\n" +
                generateExperienceSection() +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"section\">\n" +
                "            <h2 class=\"section-title\">FORMATION</h2>\n" +
                generateEducationSection() +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"section\">\n" +
                "            <h2 class=\"section-title\">PROJETS</h2>\n" +
                generateProjectSection() +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }

    private String escapeHtml(String text) {
        if (text == null || text.isEmpty()) return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    private String generateSkillItems(String skills) {
        if (skills.trim().isEmpty() || skills.equals("Aucune sélection")) {
            return "            <li class='skill-item'>Aucune sélection</li>\n";
        }

        StringBuilder sb = new StringBuilder();
        String[] skillArray = skills.split(", ");
        for (String skill : skillArray) {
            sb.append("            <li class='skill-item'>")
                    .append(escapeHtml(skill))
                    .append("</li>\n");
        }
        return sb.toString();
    }

    private String generateExperienceSection() {
        String text = taExperience.getText().trim();
        if (text.isEmpty()) {
            return "            <div class=\"experience-item\">\n" +
                    "                <div class=\"item-title\">Développeur FullStack (Projet de Fin d'Études)</div>\n" +
                    "                <div class=\"item-date\">Février 2025 - Présent</div>\n" +
                    "                <div class=\"item-description\">\n" +
                    "                    Conception et implémentation d'un langage de programmation FullStack personnalisé pour le web.\n" +
                    "                    Développement d'un transpiler utilisant Flex et Bison pour la création d'AST et la génération de code.\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"experience-item\">\n" +
                    "                <div class=\"item-title\">Plateforme de Gestion des Congés (Stage à l'ISIMM)</div>\n" +
                    "                <div class=\"item-date\">Août 2024 - Présent</div>\n" +
                    "                <div class=\"item-description\">\n" +
                    "                    Développement de RHFlex, une plateforme dynamique de gestion des congés utilisant Next.js \n" +
                    "                    pour automatiser et rationaliser les processus RH.\n" +
                    "                </div>\n" +
                    "            </div>\n";
        }

        StringBuilder sb = new StringBuilder();
        String[] experiences = text.split("\n");
        for (String exp : experiences) {
            if (!exp.trim().isEmpty()) {
                sb.append("            <div class='experience-item'>\n")
                        .append("                <div class='item-description'>")
                        .append(escapeHtml(exp))
                        .append("</div>\n")
                        .append("            </div>\n");
            }
        }
        return sb.toString();
    }

    private String generateEducationSection() {
        String text = taEducation.getText().trim();
        if (text.isEmpty()) {
            return "            <div class=\"education-item\">\n" +
                    "                <div class=\"item-title\">Licence en Génie Logiciel</div>\n" +
                    "                <div class=\"item-date\">Juin 2025</div>\n" +
                    "                <div class=\"item-description\">\n" +
                    "                    Institut Supérieur d'Informatique et de Mathématiques de Monastir (ISIMM)\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"education-item\">\n" +
                    "                <div class=\"item-title\">Baccalauréat Technologique</div>\n" +
                    "                <div class=\"item-date\">Juin 2022</div>\n" +
                    "                <div class=\"item-description\">\n" +
                    "                    Lycée Ibn Khaldoun Jemmel - Mention: 15.73/20\n" +
                    "                </div>\n" +
                    "            </div>\n";
        }

        StringBuilder sb = new StringBuilder();
        String[] educations = text.split("\n");
        for (String edu : educations) {
            if (!edu.trim().isEmpty()) {
                sb.append("            <div class='education-item'>\n")
                        .append("                <div class='item-description'>")
                        .append(escapeHtml(edu))
                        .append("</div>\n")
                        .append("            </div>\n");
            }
        }
        return sb.toString();
    }

    private String generateProjectSection() {
        String text = taProjet.getText().trim();
        if (text.isEmpty()) {
            return "            <div class=\"experience-item\">\n" +
                    "                <div class=\"item-title\">Développement d'un Langage de Programmation FullStack</div>\n" +
                    "                <div class=\"item-description\">\n" +
                    "                    Conception et implémentation complète d'un langage de programmation FullStack pour le web\n" +
                    "                    avec transpiler personnalisé utilisant Flex et Bison.\n" +
                    "                </div>\n" +
                    "            </div>\n";
        }

        StringBuilder sb = new StringBuilder();
        String[] projects = text.split("\n");
        for (String project : projects) {
            if (!project.trim().isEmpty()) {
                sb.append("            <div class='experience-item'>\n")
                        .append("                <div class='item-description'>")
                        .append(escapeHtml(project))
                        .append("</div>\n")
                        .append("            </div>\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CurriculumVitae().setVisible(true);
        });
    }
}