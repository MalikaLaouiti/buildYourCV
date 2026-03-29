package IHM;

import Threads.FrameAnimation;

import javax.swing.*;
import java.awt.*;

public class Dash extends JFrame {
    JMenuBar menuBar;
    JMenu menuTP1,menuTP2;
    JMenuItem itemFlow, itemGrid,itemBorder, itemCurri, itemGestPro;
    JPanel centerPanel;
    DataAnimation  dataAnimation;
    public Dash(){
        this.setTitle("TP Java");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar=new JMenuBar();
        menuTP1=new JMenu("TP1");
        menuTP2=new JMenu("TP2");

        itemFlow=new JMenuItem("Flow");
        itemBorder=new JMenuItem("Border");
        itemGrid=new JMenuItem("Grid");
        itemCurri=new JMenuItem("Curriculum Vitae");
        itemGestPro=new JMenuItem("Gestion Profile");

        centerPanel = new JPanel(new BorderLayout());

        dataAnimation = new DataAnimation();
        dataAnimation.setPreferredSize(new Dimension(800, 500));
        centerPanel.add(dataAnimation, BorderLayout.CENTER);

        this.menuTP1.add(itemFlow);
        this.menuTP1.add(itemBorder);
        this.menuTP1.add(itemGrid);
        this.menuTP2.add(itemCurri);
        this.menuTP2.add(itemGestPro);

        this.menuBar.add(menuTP1);
        this.menuBar.add(menuTP2);
        this.setJMenuBar(menuBar);
        this.add(centerPanel);

        itemFlow.addActionListener(new EcouteurMenu(this));
        itemGrid.addActionListener(new EcouteurMenu(this));
        itemBorder.addActionListener(new EcouteurMenu(this));
        itemCurri.addActionListener(new EcouteurMenu(this));
        itemGestPro.addActionListener(new EcouteurMenu(this));



    }
}
//utiliser plusieurs class pour ecouteur evenement