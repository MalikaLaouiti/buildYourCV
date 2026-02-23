package IHM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EcouteurDash implements ActionListener, MouseListener {
    GestionProfile gestionProfile;
    public EcouteurDash(GestionProfile gestProfile){
        this.gestionProfile=gestProfile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==gestionProfile.save){
            gestionProfile.model.addElement(gestionProfile.pseudof.getText());
            gestionProfile.prenomf.setText(" ");
            gestionProfile.nomf.setText(" ");
            gestionProfile.pseudof.setText(" ");
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
        if (e.getSource() == gestionProfile.save) {
            gestionProfile.save.setBackground(new java.awt.Color(29, 161, 97));
            gestionProfile.save.setForeground(Color.white);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == gestionProfile.save) {
            gestionProfile.save.setBackground(null);
        }
    }
}
