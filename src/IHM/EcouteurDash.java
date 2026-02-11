package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurDash implements ActionListener {
    GestionProfile gestionProfile;
    public EcouteurDash(GestionProfile gestProfile){
        this.gestionProfile=gestProfile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==gestionProfile.save){
            gestionProfile.model.addElement(gestionProfile.pseudof.getText());
        }
    }



}
