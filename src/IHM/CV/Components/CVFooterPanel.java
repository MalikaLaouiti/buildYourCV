package IHM.CV.Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CVFooterPanel {
        private JCheckBox cbExportPDF;

        public JPanel createFooterPanel(ActionListener generateListener, ActionListener exitListener) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            panel.setBackground(new Color(236, 240, 241));

            JButton btnGenerer = createButton("Générer CV HTML", new Color(46, 204, 113), 180, 40);
            JButton btnQuitter = createButton("Quitter", new Color(231, 76, 60), 120, 40);

            cbExportPDF = new JCheckBox("Exporter également en PDF");

            btnGenerer.addActionListener(generateListener);
            btnQuitter.addActionListener(exitListener);

            panel.add(btnGenerer);
            panel.add(btnQuitter);
            panel.add(cbExportPDF);

            return panel;
        }

        private JButton createButton(String text, Color bgColor, int width, int height) {
            JButton button = new JButton(text);
            button.setBackground(bgColor);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setPreferredSize(new Dimension(width, height));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Effet hover
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(bgColor.darker());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(bgColor);
                }
            });

            return button;
        }

        public boolean isExportPDFSelected() {
            return cbExportPDF != null && cbExportPDF.isSelected();
        }
}
