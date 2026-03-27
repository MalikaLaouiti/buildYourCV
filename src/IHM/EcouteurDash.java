package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EcouteurDash implements ActionListener, MouseListener {
    GestionProfile gestionProfile=null;
    FormPanel formulaire=null;
    public EcouteurDash(GestionProfile gestProfile){
        this.gestionProfile=gestProfile;

    }
    public EcouteurDash(FormPanel form){
        this.formulaire=form;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gestionProfile != null && e.getSource()==gestionProfile.save){
                SaveProfile();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String selectedPseudo = gestionProfile.jl.getSelectedValue();
            if (selectedPseudo == null) return;

            // Extraire le pseudo brut si le modèle affiche "pseudo (infos...)"
//            String pseudoKey = selectedPseudo.contains(" (")
//                    ? selectedPseudo.substring(0, selectedPseudo.indexOf(" ("))
//                    : selectedPseudo;

            // Vérifier si l'onglet est déjà ouvert
            for (int i = 0; i < gestionProfile.jtp.getTabCount(); i++) {
                Component comp = gestionProfile.jtp.getComponentAt(i);
                if (comp instanceof FormPanel) {
                    FormPanel fp = (FormPanel) comp;
                    if (fp.pseudo.equals(selectedPseudo)) {
                        gestionProfile.jtp.setSelectedIndex(i); // juste le mettre au premier plan
                        return;
                    }
                }
            }

            // Sinon, ouvrir un nouvel onglet
            FormPanel form = new FormPanel(gestionProfile, selectedPseudo);
            gestionProfile.jtp.addTab(selectedPseudo, form);
            int tabIndex = gestionProfile.jtp.indexOfComponent(form);
            gestionProfile.jtp.setSelectedIndex(tabIndex);

            // Ajouter un bouton de fermeture sur l'onglet
            JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            tabHeader.setOpaque(false);
            JLabel tabTitle = new JLabel(selectedPseudo + "  ");
            JButton closeBtn = new JButton("✕");
            closeBtn.setFont(new Font("Arial", Font.BOLD, 10));
            closeBtn.setPreferredSize(new Dimension(18, 18));
            closeBtn.setFocusPainted(false);
            closeBtn.setBorderPainted(false);
            closeBtn.setContentAreaFilled(false);
            closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            closeBtn.addActionListener(ev -> {
                int idx = gestionProfile.jtp.indexOfComponent(form);
                if (idx != -1) gestionProfile.jtp.remove(idx);
            });
            tabHeader.add(tabTitle);
            tabHeader.add(closeBtn);
            gestionProfile.jtp.setTabComponentAt(tabIndex, tabHeader);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            MyPopUpMenu popup = new MyPopUpMenu(gestionProfile.jl, gestionProfile.model, gestionProfile.jtp);
            popup.show(gestionProfile.jl, e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (gestionProfile != null && e.getSource() == gestionProfile.save) {
            gestionProfile.save.setBackground(new java.awt.Color(29, 161, 97));
            gestionProfile.save.setForeground(Color.white);
        }
        if (formulaire != null &&e.getSource() == formulaire.btnSave){
            formulaire.btnSave.setBackground(new Color(39, 174, 96));
        }
        if (formulaire != null &&e.getSource() == formulaire.btnClose){
            formulaire.btnClose.setBackground(new Color(192, 57, 43));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (gestionProfile != null && e.getSource() == gestionProfile.save) {
            gestionProfile.save.setBackground(Color.GRAY);
        }
        if (formulaire != null && e.getSource() == formulaire.btnSave){
            formulaire.btnSave.setBackground(new Color(46, 204, 113));
        }
        if (formulaire != null && e.getSource() == formulaire.btnClose){
            formulaire.btnClose.setBackground(new Color(231, 76, 60));
        }
    }
    public void SaveProfile(){
        String nom = gestionProfile.nomf.getText();
        String prenom= gestionProfile.prenomf.getText();
        String pseudo= gestionProfile.pseudof.getText();
        Profil nouveauProfil = new Profil(nom, prenom, pseudo);
        DataProfil.addProfil(nouveauProfil);
        gestionProfile.model.addElement(gestionProfile.pseudof.getText());
        gestionProfile.prenomf.setText(" ");
        gestionProfile.nomf.setText(" ");
        gestionProfile.pseudof.setText(" ");
    }


}
