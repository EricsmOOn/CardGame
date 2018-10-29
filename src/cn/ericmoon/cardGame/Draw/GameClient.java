package cn.ericmoon.cardGame.Draw;

import cn.ericmoon.cardGame.CONSTANT;
import cn.ericmoon.cardGame.GameUtil.GameUtil;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.gameRepository.AllKeySource;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.CardPlayerKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GameClient extends JFrame {

    Toolkit toolkit = this.getToolkit();
    Image backgroundImage = toolkit.getImage("images/background.png");
    Container container = this.getContentPane();
    public CardPlayerKey cardPlayerKey;
    public String playerDesciption = "";
    private JLabel label;
    private JLabel labelHP;
    ArrayList<JButton> buttons = new ArrayList<>();


    private Image offScreenImage = null;

    public void update(Graphics g){
        if(offScreenImage == null)
            offScreenImage = this.createImage(CONSTANT.frameWidth,CONSTANT.frameHeight);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }


    public void LaunchFrame() {
        configureFrame();
        System.out.println("width: " + backgroundImage.getWidth(this) + " height: " + backgroundImage.getHeight(this));
        //new CardThread().start();
    }

    public void configureFrame() {
        this.setTitle("Created by EricWong & CarterWang");
        this.setVisible(true);
        this.setSize(CONSTANT.frameWidth, CONSTANT.frameHeight);
        this.setLocation(CONSTANT.frameLocationx, CONSTANT.frmaeLocationy);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.container.setLayout(null);
        this.setResizable(true);
        this.
        new CardThread().start();
        System.out.println("成功设置窗口！");
    }

    public JButton getButton(String text, int x, int y, int width, int height) {
        JButton jButton = new JButton(text);
        jButton.setBounds(x,y,width,height);
        return jButton;
    }

    public void removeAllComponents() {
        for(int i=0;i<buttons.size();i++) {
            JButton button = buttons.get(i);
            container.remove(button);
        }
        if(this.label != null)
            container.remove(label);
        buttons.clear();
        if (this.labelHP != null)
            container.remove(labelHP);
    }

    public void setLabel(int labelX,int y) {
        JLabel label = new JLabel();
        label.setVisible(true);
        label.setBounds(labelX, y - CONSTANT.contentSpace, 300, 30);
        label.setText(playerDesciption);
        this.label = label;
        container.add(label);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        removeAllComponents();
        drawButtons();
        drawINFO(g);
    }

    class CardThread extends Thread {
        @Override
        public void run() {
            super.run();
            while(true) {
                repaint();
                try {
                    Thread.sleep(16);
                } catch(InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void drawButtons() {

        //System.out.println("进入函数");
        //System.out.println("清屏");
        container.setVisible(true);
        this.setVisible(true);

        if (cardPlayerKey != null && !cardPlayerKey.getCards().isEmpty()) {
            //System.out.println("自己还剩" + cardPlayerKey.getCards().size() + "张手牌");
            int x = (CONSTANT.frameWidth - cardPlayerKey.getCards().size() * CONSTANT.cardWidth) / 2;
            int labelX = x;
            int y = CONSTANT.frameHeight - CONSTANT.cardDistantFromBottom - CONSTANT.cardHeight - 50;
            setLabel(labelX,y);
            for (int i = 0; i < cardPlayerKey.getCards().size(); i++) {
                String text = cardPlayerKey.getCards().get(i).getCardName();
                JButton button = getButton(text, x, y, CONSTANT.cardWidth, CONSTANT.cardHeight);
                container.add(button);
                buttons.add(button);
                //System.out.println("Button" + String.valueOf(i + 1) + "添加成功！");
                x += CONSTANT.cardWidth + 10;
            }
        }
    }

    private void drawINFO(Graphics g) {

        int x = CONSTANT.frameWidth - 50;
        int y = CONSTANT.frameHeight - 100;

        if (cardPlayerKey != null) {
            System.out.println("HP函数");
            JLabel labelHP = new JLabel();
            labelHP.setText("HP:" + cardPlayerKey.getPlayer().getHp());
            labelHP.setBounds(x,y,50,100);
            container.add(labelHP);
            this.labelHP = labelHP;
            System.out.println(cardPlayerKey.getPlayer().getHp());

        }
    }


}