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
