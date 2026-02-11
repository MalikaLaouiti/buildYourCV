package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EcouteurMenu implements ActionListener {

    Dash dashboard;
    GestionProfile gestionProfile;
    public EcouteurMenu(Dash d1){
        this.dashboard=d1;
    }
    public EcouteurMenu(GestionProfile gestProfile){
        this.gestionProfile=gestProfile;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==dashboard.itemFlow){
            FrameFlow flow=new FrameFlow();
            flow.setVisible(true);
        }
        if (e.getSource()==dashboard.itemGrid){
            FrameGrid grid=new FrameGrid();
            grid.setVisible(true);
        }
        if (e.getSource()==dashboard.itemBorder){
            FrameBorder border=new FrameBorder();
            border.setVisible(true);
        }
        if (e.getSource()==dashboard.itemGestPro){
            GestionProfile profile=new GestionProfile();
            profile.setVisible(true);
        }

    }
}