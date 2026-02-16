package IHM;

import java.awt.event.*;

public class EcouteurTextField extends MouseAdapter {
    GestionProfile gp;

    public EcouteurTextField(GestionProfile gestionProfils) {
        this.gp = gestionProfils;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == this.gp.nomf) {
            this.gp.help.setText("saisir nom");
        }
        if (e.getSource() == this.gp.prenomf) {
            this.gp.help.setText("saisir prenom");
        }
        if (e.getSource() == this.gp.pseudof) {
            this.gp.help.setText("saisir pseudo");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.gp.help.setText("HELP");
    }
}