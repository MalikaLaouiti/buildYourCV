package IHM;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FormPanel extends JPanel {
    GestionProfile gp;
    JLabel labelMessage;
    JComboBox<String> comboCycle;
    JSpinner spinnerAnnee;
    JButton btnSave, btnClose, btnAddLangue;
    String[] cycles = {"1er Cycle", "2ème Cycle", "3ème Cycle"};
    String pseudo;
    Profil p;

    JPanel languesPanel;
    ArrayList<LangueRow> langueRows;

    JPanel anneesPanel;
    ButtonGroup anneesGroup;
    HashMap<String, String[]> cycleAnnees;

    public FormPanel(GestionProfile gp, String pseudo) {
        this.gp = gp;
        this.pseudo = pseudo;
        this.langueRows = new ArrayList<LangueRow>();
        this.p= DataProfil.getProfilByPseudo(pseudo);

        if (this.p == null) {
            System.err.println("Attention: Profil non trouvé pour le pseudo: " + pseudo);
            this.p = new Profil("Inconnu", "Inconnu", pseudo);
        }

        // Définir les années pour chaque cycle
        cycleAnnees = new HashMap<String, String[]>();
        cycleAnnees.put("1er Cycle", new String[]{"1ère année", "2ème année" });
        cycleAnnees.put("2ème Cycle", new String[]{"3ème année","4ème année", "5ème année"});
        cycleAnnees.put("3ème Cycle", new String[]{"6ème année", "7ème année", "8ème année"});

        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(240, 245, 250));

        // Panel supérieur avec message de bienvenue
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(240, 245, 250));
        labelMessage = new JLabel("Bonjour " + p.getNom() + " " + p.getPrenom());
        labelMessage.setFont(new Font("Arial", Font.BOLD, 16));
        labelMessage.setForeground(new Color(41, 128, 185));
        topPanel.add(labelMessage);

        // Panel central avec le formulaire (utilisant BoxLayout vertical)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                        "Informations du Profil",
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 12),
                        new Color(52, 73, 94)),
                new EmptyBorder(15, 15, 15, 15)
        ));

        // Section Pseudo
        JPanel pseudoPanel = createFieldPanel();
        JLabel labelPseudo = new JLabel("Pseudo :");
        labelPseudo.setFont(new Font("Arial", Font.BOLD, 12));
        labelPseudo.setPreferredSize(new Dimension(100, 30));

        JTextField pseudoField = new JTextField(pseudo);
        pseudoField.setEditable(false);
        pseudoField.setBackground(new Color(236, 240, 241));
        pseudoField.setFont(new Font("Arial", Font.PLAIN, 12));
        pseudoField.setPreferredSize(new Dimension(300, 30));

        pseudoPanel.add(labelPseudo);
        pseudoPanel.add(pseudoField);
        centerPanel.add(pseudoPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Section Langues avec étoiles
        JPanel languesSectionPanel = new JPanel();
        languesSectionPanel.setLayout(new BoxLayout(languesSectionPanel, BoxLayout.Y_AXIS));
        languesSectionPanel.setBackground(Color.WHITE);
        languesSectionPanel.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                        "Langues et Niveaux",
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 12),
                        new Color(52, 152, 219)),
                new EmptyBorder(10, 10, 10, 10)
        ));

        languesPanel = new JPanel();
        languesPanel.setLayout(new BoxLayout(languesPanel, BoxLayout.Y_AXIS));
        languesPanel.setBackground(Color.WHITE);


        LangueRow firstRow = new LangueRow(this, "Arabe");
        langueRows.add(firstRow);
        languesPanel.add(firstRow.getPanel());

        languesSectionPanel.add(languesPanel);

        // Bouton pour ajouter une langue
        JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButtonPanel.setBackground(Color.WHITE);
        btnAddLangue = new JButton("+ Ajouter une langue");
        btnAddLangue.setFont(new Font("Arial", Font.BOLD, 11));
        btnAddLangue.setBackground(new Color(52, 152, 219));
        btnAddLangue.setForeground(Color.WHITE);
        btnAddLangue.setFocusPainted(false);
        btnAddLangue.setBorderPainted(false);
        btnAddLangue.setCursor(new Cursor(Cursor.HAND_CURSOR));


        btnAddLangue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLangueRow();
            }
        });

        addButtonPanel.add(btnAddLangue);

        languesSectionPanel.add(addButtonPanel);
        centerPanel.add(languesSectionPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Section Cycle
        JPanel cycleSectionPanel = new JPanel();
        cycleSectionPanel.setLayout(new BoxLayout(cycleSectionPanel, BoxLayout.Y_AXIS));
        cycleSectionPanel.setBackground(Color.WHITE);
        cycleSectionPanel.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder(BorderFactory.createLineBorder(new Color(155, 89, 182), 2),
                        "Niveau d'Études",
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 12),
                        new Color(155, 89, 182)),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JPanel cyclePanel = createFieldPanel();
        JLabel labelCycle = new JLabel("Cycle :");
        labelCycle.setFont(new Font("Arial", Font.BOLD, 12));
        labelCycle.setPreferredSize(new Dimension(100, 30));

        comboCycle = new JComboBox<String>(cycles);
        comboCycle.setFont(new Font("Arial", Font.PLAIN, 12));
        comboCycle.setPreferredSize(new Dimension(300, 30));
        comboCycle.setBackground(Color.WHITE);

        cyclePanel.add(labelCycle);
        cyclePanel.add(comboCycle);
        cycleSectionPanel.add(cyclePanel);
        cycleSectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Panel pour les radio buttons des années
        anneesPanel = new JPanel();
        anneesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        anneesPanel.setBackground(Color.WHITE);
        anneesGroup = new ButtonGroup();

        // Initialiser avec le premier cycle
        updateAnneesPanel((String) comboCycle.getSelectedItem());

        cycleSectionPanel.add(anneesPanel);
        centerPanel.add(cycleSectionPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Listener pour changer les années selon le cycle
        comboCycle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCycle = (String) comboCycle.getSelectedItem();
                updateAnneesPanel(selectedCycle);
            }
        });

        // Section Année académique
        JPanel anneeAcadPanel = createFieldPanel();
        JLabel labelAnnee = new JLabel("Année :");
        labelAnnee.setFont(new Font("Arial", Font.BOLD, 12));
        labelAnnee.setPreferredSize(new Dimension(100, 30));

        spinnerAnnee = new JSpinner(new SpinnerNumberModel(2024, 2000, 2100, 1));
        spinnerAnnee.setFont(new Font("Arial", Font.PLAIN, 12));
        spinnerAnnee.setPreferredSize(new Dimension(120, 30));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerAnnee.getEditor();
        editor.getTextField().setBackground(Color.WHITE);

        anneeAcadPanel.add(labelAnnee);
        anneeAcadPanel.add(spinnerAnnee);
        centerPanel.add(anneeAcadPanel);

        // Wrapper pour le scroll
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Panel inférieur avec les boutons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(new Color(240, 245, 250));

        btnSave = new JButton("Enregistrer");
        btnSave.setFont(new Font("Arial", Font.BOLD, 12));
        btnSave.setPreferredSize(new Dimension(130, 35));
        btnSave.setBackground(new Color(46, 204, 113));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnClose = new JButton("Annuler");
        btnClose.setFont(new Font("Arial", Font.BOLD, 12));
        btnClose.setPreferredSize(new Dimension(130, 35));
        btnClose.setBackground(new Color(231, 76, 60));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setBorderPainted(false);
        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSave.addMouseListener(new EcouteurDash(this));
        btnClose.addMouseListener(new EcouteurDash(this));

        bottomPanel.add(btnSave);
        bottomPanel.add(btnClose);

        // Ajouter les panels au layout principal
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Action listeners - utiliser des classes anonymes
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = gp.jtp.indexOfComponent(FormPanel.this);
                if (index != -1) {
                    gp.jtp.remove(index);
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveAction();
            }
        });
    }

    private void handleSaveAction() {


        String cycleSelectionne = (String) comboCycle.getSelectedItem();
        int anneeSelectionnee = (int) spinnerAnnee.getValue();


        String anneeEtude = getSelectedAnnee();
        if (anneeEtude == null) {
            JOptionPane.showMessageDialog(FormPanel.this,
                    "Veuillez sélectionner une année d'étude!",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Récupérer les langues et niveaux
        StringBuilder languesInfo = new StringBuilder();
        for (int i = 0; i < langueRows.size(); i++) {
            LangueRow row = langueRows.get(i);
            String langue = row.getLangue();
            int niveau = row.getNiveau();
            if (langue != null && !langue.isEmpty() && niveau > 0) {
                languesInfo.append(langue).append(" (").append(niveau).append(" étoiles), ");
            }
        }

        if (languesInfo.length() == 0) {
            JOptionPane.showMessageDialog(FormPanel.this,
                    "Veuillez ajouter au moins une langue avec un niveau!",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String languesStr = languesInfo.substring(0, languesInfo.length() - 2);


        Profil nouveauProfil = new Profil(p.getNom(), p.getPrenom(), pseudo, languesStr,
                cycleSelectionne, anneeEtude, anneeSelectionnee);
        DataProfil.updateProfil(nouveauProfil);


        for (int i = 0; i < gp.model.getSize(); i++) {
            String element = gp.model.getElementAt(i);
            if (element.equals(pseudo) || element.startsWith(pseudo + " (")) {
                gp.model.set(i, nouveauProfil.toString());
                break;
            }
        }

        // Message de confirmation
        JOptionPane.showMessageDialog(FormPanel.this,
                "Profil sauvegardé avec succès!\n\n" +
                        "Nom: " + p.getNom() + "\n" +
                        "Prénom: " + p.getPrenom()+ "\n" +
                        "Pseudo: " + pseudo + "\n" +
                        "Langues: " + languesStr + "\n" +
                        "Cycle: " + cycleSelectionne + "\n" +
                        "Année d'étude: " + anneeEtude + "\n" +
                        "Année académique: " + anneeSelectionnee,
                "Confirmation",
                JOptionPane.INFORMATION_MESSAGE);

        // Fermer l'onglet
        int index = gp.jtp.indexOfComponent(FormPanel.this);
        if (index != -1) {
            gp.jtp.remove(index);
        }
    }

    private JPanel createFieldPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return panel;
    }

    private void addLangueRow() {
        String[] languesDisponibles = {"Arabe", "Français", "Anglais", "Allemand",
                "Espagnol", "Italien", "Chinois"};

        ArrayList<String> languesAjoutees = new ArrayList<String>();
        for (int i = 0; i < langueRows.size(); i++) {
            LangueRow row = langueRows.get(i);
            String langue = row.getLangue();
            if (langue != null && !langue.isEmpty()) {
                languesAjoutees.add(langue);
            }
        }

        ArrayList<String> languesRestantes = new ArrayList<String>();
        for (int i = 0; i < languesDisponibles.length; i++) {
            String langue = languesDisponibles[i];
            if (!languesAjoutees.contains(langue)) {
                languesRestantes.add(langue);
            }
        }

        if (languesRestantes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Toutes les langues disponibles ont déjà été ajoutées!",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Afficher une boîte de dialogue pour choisir la langue
        String langueChoisie = (String) JOptionPane.showInputDialog(
                this,
                "Choisissez une langue à ajouter:",
                "Ajouter une langue",
                JOptionPane.QUESTION_MESSAGE,
                null,
                languesRestantes.toArray(),
                languesRestantes.get(0));

        // Si l'utilisateur a choisi une langue
        if (langueChoisie != null && !langueChoisie.isEmpty()) {
            LangueRow row = new LangueRow(this, langueChoisie);
            langueRows.add(row);
            languesPanel.add(row.getPanel());
            languesPanel.revalidate();
            languesPanel.repaint();
        }
    }

    public void removeLangueRow(LangueRow row) {
        if (langueRows.size() > 1) {
            langueRows.remove(row);
            languesPanel.remove(row.getPanel());
            languesPanel.revalidate();
            languesPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Vous devez garder au moins une langue!",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateAnneesPanel(String cycle) {
        anneesPanel.removeAll();
        anneesGroup = new ButtonGroup();

        String[] annees = cycleAnnees.get(cycle);
        for (int i = 0; i < annees.length; i++) {
            String annee = annees[i];
            JRadioButton radioButton = new JRadioButton(annee);
            radioButton.setFont(new Font("Arial", Font.PLAIN, 11));
            radioButton.setBackground(Color.WHITE);
            anneesGroup.add(radioButton);
            anneesPanel.add(radioButton);
        }

        // Sélectionner le premier par défaut
        if (annees.length > 0) {
            ((JRadioButton) anneesPanel.getComponent(0)).setSelected(true);
        }

        anneesPanel.revalidate();
        anneesPanel.repaint();
    }

    private String getSelectedAnnee() {
        Component[] components = anneesPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component comp = components[i];
            if (comp instanceof JRadioButton) {
                JRadioButton radio = (JRadioButton) comp;
                if (radio.isSelected()) {
                    return radio.getText();
                }
            }
        }
        return null;
    }
}