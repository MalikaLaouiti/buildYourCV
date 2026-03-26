package IHM;

import IHM.CV.Components.CVFormPanel;
import IHM.CV.Components.CVFooterPanel;
import IHM.CV.Components.CVHeaderPanel;
import IHM.CV.Services.Data;
import IHM.CV.Services.Generator;
import IHM.CV.Services.Validator;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CurriculumVitae extends JFrame {
    private CVFormPanel formPanel;
    private CVHeaderPanel headerPanel;
    private CVFooterPanel footerPanel;
    private Generator generator;
    private Validator validator;

    public CurriculumVitae() {
        generator = new Generator();
        validator = new Validator();
        initUI();
    }

    private void initUI() {
        setTitle("Générateur de Curriculum Vitae");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header
        headerPanel = new CVHeaderPanel();
        mainPanel.add(headerPanel.createHeaderPanel(), BorderLayout.NORTH);

        // Center - Form
        formPanel = new CVFormPanel();
        JScrollPane scrollPane = new JScrollPane(formPanel.createFormPanel());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer - Buttons
        footerPanel = new CVFooterPanel();
        mainPanel.add(footerPanel.createFooterPanel(
                e -> genererCV(),
                e -> System.exit(0)
        ), BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void genererCV() {
        // Récupérer les données du formulaire
        Data data = formPanel.getCVData();

        // Valider les données
        Validator.ValidationResult validation = validator.validate(data);
        if (!validation.isValid()) {
            JOptionPane.showMessageDialog(this,
                    validation.getErrorMessage(),
                    "Erreur de validation",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Afficher les warnings si nécessaire
        if (!validation.getWarnings().isEmpty()) {
            int result = JOptionPane.showConfirmDialog(this,
                    "Attention:\n" + String.join("\n", validation.getWarnings()) +
                            "\n\nVoulez-vous continuer ?",
                    "Avertissement",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (result != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            // Générer le CV
            String fileName = "CV_" + data.getNom() + "_" + data.getPrenom() + ".html";
            File file = generator.generateHTML(data, fileName);

            // Message de confirmation
            String message = "CV généré avec succès!\n\n";
            message += "Fichier: " + file.getAbsolutePath() + "\n";
            message += "Taille: " + file.length() + " octets";

            if (footerPanel.isExportPDFSelected()) {
                message += "\n\nNote: L'export PDF sera disponible prochainement.";
            }

            JOptionPane.showMessageDialog(this, message, "Succès",
                    JOptionPane.INFORMATION_MESSAGE);

            // Option pour ouvrir le fichier
            int openFile = JOptionPane.showConfirmDialog(this,
                    "Voulez-vous ouvrir le fichier généré ?",
                    "Ouvrir le CV",
                    JOptionPane.YES_NO_OPTION);
            if (openFile == JOptionPane.YES_OPTION) {
                Desktop.getDesktop().open(file);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la génération du CV:\n" + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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