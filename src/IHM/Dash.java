package IHM;

import javax.swing.*;

public class Dash extends JFrame {
    JMenuBar menuBar;
    JMenu menuTP1,menuTP2;
    JMenuItem itemFlow, itemGrid,itemBorder, itemCurri, itemGestPro;
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

        this.menuTP1.add(itemFlow);
        this.menuTP1.add(itemBorder);
        this.menuTP1.add(itemGrid);
        this.menuTP2.add(itemCurri);
        this.menuTP2.add(itemGestPro);

        this.menuBar.add(menuTP1);
        this.menuBar.add(menuTP2);
        this.setJMenuBar(menuBar);
        itemFlow.addActionListener(new EcouteurMenu(this));
        itemGrid.addActionListener(new EcouteurMenu(this));
        itemBorder.addActionListener(new EcouteurMenu(this));
        itemGestPro.addActionListener(new EcouteurMenu(this));

    }





}
//utiliser plusieurs class pour ecouteur evenement