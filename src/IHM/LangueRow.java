package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LangueRow {
    private JPanel panel;
    private JLabel langueLabel;
    private String langue;
    private JLabel[] stars;
    private JLabel niveauLabel;
    private int niveau = 0;
    private JButton btnRemove;
    private FormPanel parent;
    private ImageIcon starFilledIcon;
    private ImageIcon starEmptyIcon;

    public LangueRow(FormPanel parent, String langue) {
        this.parent = parent;
        this.langue = langue;

        try {
            java.net.URL starFilledURL = getClass().getResource("/IHM/star_filled.png");
            java.net.URL starEmptyURL = getClass().getResource("/IHM/star_empty.png");

            if (starFilledURL != null && starEmptyURL != null) {
                starFilledIcon = new ImageIcon(starFilledURL);
                starEmptyIcon = new ImageIcon(starEmptyURL);
            } else {
                createDefaultIcons();
            }
        } catch (Exception e) {
            createDefaultIcons();
        }

        initializePanel();
    }

    private void initializePanel() {
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // Label pour la langue (non éditable)
        langueLabel = new JLabel(langue);
        langueLabel.setFont(new Font("Arial", Font.BOLD, 12));
        langueLabel.setPreferredSize(new Dimension(120, 25));
        langueLabel.setOpaque(true);
        langueLabel.setBackground(new Color(236, 240, 241));
        langueLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));
        panel.add(langueLabel);

        stars = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            final int index = i;
            stars[i] = new JLabel(starEmptyIcon);
            stars[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

            stars[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setNiveau(index + 1);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Effet de survol - afficher les étoiles pleines
                    for (int j = 0; j <= index; j++) {
                        stars[j].setIcon(starFilledIcon);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurer l'état actuel
                    updateStars();
                }
            });

            panel.add(stars[i]);
        }

        // Label pour afficher le niveau en texte
        niveauLabel = new JLabel("(0/5)");
        niveauLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        niveauLabel.setForeground(Color.GRAY);
        panel.add(niveauLabel);

        btnRemove = new JButton("X");
        btnRemove.setFont(new Font("Arial", Font.BOLD, 12));
        btnRemove.setPreferredSize(new Dimension(30, 25));
        btnRemove.setBackground(new Color(231, 76, 60));
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFocusPainted(false);
        btnRemove.setBorderPainted(false);
        btnRemove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRemove.setToolTipText("Supprimer cette langue");
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.removeLangueRow(LangueRow.this);
            }
        });

        panel.add(btnRemove);
    }

    private void createDefaultIcons() {
        // Créer des icônes simples en mémoire si les fichiers ne sont pas trouvés
        starFilledIcon = createStarIcon(true);
        starEmptyIcon = createStarIcon(false);
    }

    private ImageIcon createStarIcon(boolean filled) {
        int size = 24;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
                size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessiner une étoile
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];
        double centerX = size / 2.0;
        double centerY = size / 2.0;
        double outerRadius = size * 0.4;
        double innerRadius = size * 0.16;

        for (int i = 0; i < 10; i++) {
            double angle = Math.PI / 2 + (2 * Math.PI * i / 10);
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;
            xPoints[i] = (int) (centerX + radius * Math.cos(angle));
            yPoints[i] = (int) (centerY - radius * Math.sin(angle));
        }

        if (filled) {
            g2d.setColor(new Color(255, 193, 7));
            g2d.fillPolygon(xPoints, yPoints, 10);
            g2d.setColor(new Color(218, 165, 32));
            g2d.drawPolygon(xPoints, yPoints, 10);
        } else {
            g2d.setColor(new Color(200, 200, 200));
            g2d.drawPolygon(xPoints, yPoints, 10);
        }

        g2d.dispose();
        return new ImageIcon(img);
    }

    private void setNiveau(int n) {
        niveau = n;
        updateStars();
        niveauLabel.setText("(" + niveau + "/5)");
    }

    private void updateStars() {
        for (int i = 0; i < 5; i++) {
            if (i < niveau) {
                stars[i].setIcon(starFilledIcon);
            } else {
                stars[i].setIcon(starEmptyIcon);
            }
        }
    }

    public String getLangue() {
        return langue;
    }

    public int getNiveau() {
        return niveau;
    }

    public JPanel getPanel() {
        return panel;
    }
}