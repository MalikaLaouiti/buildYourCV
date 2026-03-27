package Threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class FrameAnimation extends JFrame {
    JButton b_start, b_stop;
    JPanel pn;
    JPanel pa;

    int x = 10, y = 200;
    int xi = 10, yi = 200;
    int xf = 10, yf = 200;
    double a, b;

    boolean isRunning = false;
    boolean isMovingToClick = false;

    int pasBounce = 5;
    int directionBounce = 1;
    public FrameAnimation() {
        setTitle("Animation");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        b_start = new JButton("Start");
        b_stop  = new JButton("Stop");

        pn = new JPanel();
        pn.setBackground(Color.GRAY);
        pn.add(b_start);
        pn.add(b_stop);
        this.add(pn, BorderLayout.NORTH);

        pa = new PanelAnimation();
        this.add(pa);

        Animation animation = new Animation();
        animation.start();

        b_start.addActionListener(e -> {
            isMovingToClick = false;
            isRunning = true;
        });

        b_stop.addActionListener(e -> {
            isRunning = false;
            isMovingToClick = false;
        });

        pa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Stoppe le bounce et démarre le mouvement vers le click
                isRunning = false;
                isMovingToClick = true;

                xi = x;
                yi = y;
                xf = e.getX();
                yf = e.getY();

                if (xf != xi) {
                    a = (yf - yi) / (double)(xf - xi);
                    b = yi - a * xi;
                } else {
                    // cas vertical : x ne bouge pas, y avance
                    a = 0;
                    b = yi;
                }
            }
        });
    }

    class Animation extends Thread {
        int pas = 5;

        @Override
        public void run() {
            while (true) {

                if (isMovingToClick) {
                    if (xf != xi) {
                        int direction = (xf > xi) ? pas : -pas;
                        boolean notReached = (direction > 0) ? (x < xf) : (x > xf);

                        if (notReached) {
                            if (Math.abs(xf - x) <= Math.abs(pas)) {
                                x = xf;
                                y = yf;
                                isMovingToClick = false;
                            } else {
                                x += direction;
                                y = (int)(a * x + b);
                            }
                        } else {
                            x = xf;
                            y = yf;
                            isMovingToClick = false;
                        }

                    } else {
                        int dirY = (yf > yi) ? pas : -pas;
                        boolean notReachedY = (dirY > 0) ? (y < yf) : (y > yf);

                        if (notReachedY) {
                            if (Math.abs(yf - y) <= Math.abs(pas)) {
                                y = yf;
                                isMovingToClick = false;
                            } else {
                                y += dirY;
                            }
                        } else {
                            y = yf;
                            isMovingToClick = false;
                        }
                    }
                    pa.repaint();
                }

                if (isRunning) {
                    int panelWidth = pa.getWidth();
                    if (x >= panelWidth) {
                        x = panelWidth;
                        directionBounce = -1;
                    } else if (x <= 0) {
                        x = 0;
                        directionBounce = 1;
                    }
                    x += pasBounce * directionBounce;
                    pa.repaint();
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    class PanelAnimation extends JPanel {
        PanelAnimation() {
            this.setBackground(new Color(40, 100, 120));
        }
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(80, 50, 100));
            g.fillOval(x - 50, y - 50, 100, 100);

            g.setColor(new Color(100, 20, 40));
            g.drawString(new Date().toLocaleString(), x, 100);
        }
    }
    public static void main(String[] args) {
        new FrameAnimation().setVisible(true);
    }
}