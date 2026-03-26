package Threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class FrameAnimation extends JFrame {
    JButton b_start, b_stop;
    JPanel pn;
    JPanel pa;
    int x,xi,xf=10;
    int y,yi=200;
    int yf=200;
    boolean isRunning= false;
    double a,b;

    public FrameAnimation() {

        setTitle("Animation");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        b_start=new JButton("Start");
        b_stop=new JButton("Stop");

        pn=new JPanel();
        pn.setBackground(Color.GRAY);
        pn.add(b_start);
        pn.add(b_stop);

        this.add(pn,BorderLayout.NORTH);

        pa=new PanelAnimation();
        this.add(pa);
        Animation animation=new Animation();
        animation.start();
        b_start.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

             isRunning=true;

          }
        });
        b_stop.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              isRunning = false;

          }
        });
        pa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xf=e.getX();
                yf=e.getY();
                a=(yf-yi)/(double)(xf-xi);
                b=yi-a*xi;
                yf=(int)(a*xf+b);

                Graphics g=pa.getGraphics();
                g.drawRect(xf,yf,100,100);
            }
        });
    }
    class Animation extends Thread{
        int pas=10;
        @Override
        public void run() {
            while (true) {
                System.out.println(isRunning);
                while (isRunning && x < xf && x>=xi) {
                    x += pas;
                    y +=pas;
                    pa.repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(x<xi && pas<0){
                        isRunning=false;
                    }
                }
                if(x>=xf){
                    pas*=-1;
                    x+=pas;
                }

            }
        }
    }
    class PanelAnimation extends JPanel{
        PanelAnimation(){
            this.setBackground(new Color(40, 100, 120));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(80, 50, 100));
            g.fillOval(x,y,100,100);

            g.setColor(new Color(100,20,40));
            g.drawString(new Date().toLocaleString(),x,100);

        }
    }

    public static void main(String[] args) {
        new FrameAnimation().setVisible(true);
    }
}

//mouselistner sur panel pour transition de boule : y=equation de droit
//dans dash afficher temps reel dans desktoppane