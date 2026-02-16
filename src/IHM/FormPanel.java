package IHM;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    GestionProfile gp;
    JLabel labelMessage;
    JComboBox<String> comboLangue;
    JComboBox<String> comboCycle;
    JSpinner spinnerAnnee;
    JButton btnSave, btnClose;
    String[] langues = {"Arabe", "Français", "Anglais", "Allemand"};
    String[] cycles = {"1er Cycle", "2ème Cycle", "3ème Cycle"};
    String pseudo;

    public FormPanel(GestionProfile gp, String pseudo) {
        this.gp = gp;
        this.pseudo = pseudo;
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(240, 245, 250));


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(240, 245, 250));
        labelMessage = new JLabel("Bonjour " + gp.nomf.getText() + " " + gp.prenomf.getText());
        labelMessage.setFont(new Font("Arial", Font.BOLD, 16));
        labelMessage.setForeground(new Color(41, 128, 185));
        topPanel.add(labelMessage);


        JPanel centerPanel = new JPanel(new GridBagLayout());
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel labelPseudo = new JLabel("Pseudo :");
        labelPseudo.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(labelPseudo, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField pseudoField = new JTextField(pseudo);
        pseudoField.setEditable(false);
        pseudoField.setBackground(new Color(236, 240, 241));
        pseudoField.setFont(new Font("Arial", Font.PLAIN, 12));
        pseudoField.setPreferredSize(new Dimension(200, 30));
        centerPanel.add(pseudoField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel labelLangue = new JLabel("Langue :");
        labelLangue.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(labelLangue, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        comboLangue = new JComboBox<>(langues);
        comboLangue.setFont(new Font("Arial", Font.PLAIN, 12));
        comboLangue.setPreferredSize(new Dimension(200, 30));
        comboLangue.setBackground(Color.WHITE);
        centerPanel.add(comboLangue, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel labelCycle = new JLabel("Cycle :");
        labelCycle.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(labelCycle, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        comboCycle = new JComboBox<>(cycles);
        comboCycle.setFont(new Font("Arial", Font.PLAIN, 12));
        comboCycle.setPreferredSize(new Dimension(200, 30));
        comboCycle.setBackground(Color.WHITE);
        centerPanel.add(comboCycle, gbc);

        // Année
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        JLabel labelAnnee = new JLabel("Année :");
        labelAnnee.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(labelAnnee, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        spinnerAnnee = new JSpinner(new SpinnerNumberModel(2024, 2000, 2100, 1));
        spinnerAnnee.setFont(new Font("Arial", Font.PLAIN, 12));
        spinnerAnnee.setPreferredSize(new Dimension(200, 30));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerAnnee.getEditor();
        editor.getTextField().setBackground(Color.WHITE);
        centerPanel.add(spinnerAnnee, gbc);

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

        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSave.setBackground(new Color(39, 174, 96));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSave.setBackground(new Color(46, 204, 113));
            }
        });

        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClose.setBackground(new Color(192, 57, 43));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClose.setBackground(new Color(231, 76, 60));
            }
        });

        bottomPanel.add(btnSave);
        bottomPanel.add(btnClose);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        btnClose.addActionListener(e -> {
            int index = gp.jtp.indexOfComponent(this);
            if (index != -1) {
                gp.jtp.remove(index);
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = gp.nomf.getText();
                String prenom = gp.prenomf.getText();
                String langueSelectionnee = (String) comboLangue.getSelectedItem();
                String cycleSelectionne = (String) comboCycle.getSelectedItem();
                int anneeSelectionnee = (int) spinnerAnnee.getValue();

                Profil nouveauProfil = new Profil(nom, prenom, pseudo, langueSelectionnee, cycleSelectionne, anneeSelectionnee);
                DataProfil.addProfil(nouveauProfil);

                JOptionPane.showMessageDialog(FormPanel.this,
                        "Profil sauvegardé avec succès!\n\n" +
                                "Nom: " + nom + "\n" +
                                "Prénom: " + prenom + "\n" +
                                "Pseudo: " + pseudo + "\n" +
                                "Langue: " + langueSelectionnee + "\n" +
                                "Cycle: " + cycleSelectionne + "\n" +
                                "Année: " + anneeSelectionnee,
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);

                // Fermer l'onglet
                int index = gp.jtp.indexOfComponent(FormPanel.this);
                if (index != -1) {
                    gp.jtp.remove(index);
                }
            }
        });
    }
}