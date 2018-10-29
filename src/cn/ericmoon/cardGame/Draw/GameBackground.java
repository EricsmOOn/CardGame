package cn.ericmoon.cardGame.Draw;

import cn.ericmoon.cardGame.CONSTANT;
import cn.ericmoon.cardGame.GameUtil.GameUtil;

import javax.swing.*;
import java.awt.*;

public class GameBackground extends JPanel {

    Image backgroundImage;


    public GameBackground(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        JButton button = new JButton("测试");
        button.setBounds(150,150,100,100);
        this.add(button);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Panel paint accessed!");
        g.setColor(Color.PINK);
        setVisible(true);
        g.fillOval(CONSTANT.frameHeight/2,CONSTANT.frameHeight/2,100,100);
        g.drawImage(backgroundImage,0,0,this.getWidth(),this.getHeight(),this);
    }

}
