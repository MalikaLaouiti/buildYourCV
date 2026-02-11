package IHM;

import javax.swing.*;
import java.awt.*;

public class FrameGrid extends JFrame{
    JButton b1,b2,b3,b4,b5;
    public FrameGrid(){
        b1=new JButton("1");
        b2=new JButton("2");
        b3=new JButton("3");
        b4=new JButton("4");
        b5=new JButton("5");

        this.setLayout(new GridLayout(2,3));
        this.setSize(800,400);

        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(b5);

    }
}


