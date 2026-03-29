package IHM;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DataAnimation extends JPanel {

    int x = 0, y = 0;
    int directionY = 1;
    int directionX = 1;
    int pasY = 5;
    int pasX = 100;

    public DataAnimation() {
        this.setBackground(new Color(200, 150, 220));
        this.setPreferredSize(new Dimension(800, 200));

        Thread animation = new Thread(() -> {
            while (true) {
                int panelWidth  = getWidth()  > 0 ? getWidth()  : 800;
                int panelHeight = getHeight() > 0 ? getHeight() : 200;

                y += pasY * directionY;

                if (y >= panelHeight - 20) {
                    y = panelHeight - 20;
                    directionY = -1;
                    x += pasX*directionX;
                }
                else if (y <= 0) {
                    y = 0;
                    directionY = 1;
                    x += pasX*directionX;
                }

                if (x >= panelWidth - 20) {
                    directionX=-1;
                    x+=pasX*directionX;
                    directionY = 1;
                }
                else if (x<=0){
                    x=0;
                    directionX=1;
                    y+=pasY*directionY;
                }

                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        animation.setDaemon(true);
        animation.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 220, 80));
        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        g.drawString(new Date().toLocaleString(), x, y + 16);
    }
}