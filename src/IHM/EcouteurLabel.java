package IHM;

import java.awt.*;
import java.awt.event.*;

public class EcouteurLabel extends MouseAdapter {
    GestionProfile gp;

    public EcouteurLabel(GestionProfile gestionProfils) {
        this.gp = gestionProfils;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == this.gp.nom) {
            this.gp.nom.setForeground(Color.red);
        }
        if (e.getSource() == this.gp.prenom) {
            this.gp.prenom.setForeground(Color.red);
        }
        if (e.getSource() == this.gp.pseudo) {
            this.gp.pseudo.setForeground(Color.red);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == this.gp.nom) {
            this.gp.nom.setForeground(Color.BLACK);
        }
        if (e.getSource() == this.gp.prenom) {
            this.gp.prenom.setForeground(Color.BLACK);
        }
        if (e.getSource() == this.gp.pseudo) {
            this.gp.pseudo.setForeground(Color.BLACK);
        }
    }
}