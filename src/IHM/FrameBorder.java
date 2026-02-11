package IHM;

import javax.swing.*;
import java.awt.*;

public class FrameBorder extends JFrame {
    JButton b1,b2,b3,b4,b5,b6;
    JPanel ps;

    public FrameBorder() {
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");

        ps = new JPanel();

        this.setLayout(new BorderLayout());
        ps.setLayout(new FlowLayout());
        this.setSize(800, 400);

        ps.add(b2);
        ps.add(b6);

        this.add(b1, BorderLayout.NORTH);
        this.add(ps, BorderLayout.SOUTH);
        this.add(b3, BorderLayout.WEST);
        this.add(b4, BorderLayout.EAST);
        this.add(b5, BorderLayout.CENTER);
    }
}