package cn.ericmoon.cardGame.Draw;


import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    Image bg;

    public BackgroundPanel(Image bg) {
        this.bg = bg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg,0,0,this);
    }

}
