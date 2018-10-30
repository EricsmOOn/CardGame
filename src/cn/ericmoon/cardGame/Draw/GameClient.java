package cn.ericmoon.cardGame.Draw;

import cn.ericmoon.cardGame.CONSTANT;
import cn.ericmoon.cardGame.Event.CardMouseEvent;
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
    Container container = this.getContentPane();

    public CardPlayerKey cardPlayerKeySelf;
    public CardPlayerKey cardPlayerKeyEnemy;
    public String playerDesciption = "";
    public int chosenIndexOfButton = -1;

    private JLabel label;
    private JLabel labelHP;

    public boolean playing = false;

    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<CardMouseEvent> cardMouseEventsSelf = new ArrayList<>();
    ArrayList<CardMouseEvent> cardMouseEventsEnemy = new ArrayList<>();

    /**
     * 双缓冲
     */
/*
    private Image offScreenImage = null;

    public void update(Graphics g){
        if(offScreenImage == null)
            offScreenImage = this.createImage(CONSTANT.frameWidth,CONSTANT.frameHeight);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }
*/

    public void LaunchFrame() {
        configureFrame();
       // new CardThread().start();

    }

    public void configureFrame() {
        this.setTitle("Created by EricWong & CarterWang");
        this.setVisible(true);
        this.setSize(CONSTANT.frameWidth, CONSTANT.frameHeight);
        this.setLocation(CONSTANT.frameLocationx, CONSTANT.frmaeLocationy);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.container.setLayout(null);
        this.setResizable(false);
        this.container.setBackground(Color.BLACK);
        System.out.println("成功设置窗口！");
    }

    public JButton getButton(String text, int x, int y, int width, int height) {
        //System.out.println("getButton1");
        JButton jButton = new JButton(text);
        //System.out.println("getButton2");
        jButton.setBounds(x,y,width,height);
        //System.out.println("getButton3");
        return jButton;
    }

    public void removeAllComponents() {
        if(cardPlayerKeySelf != null) {

            if(buttons !=null){
                for (int i = 0; i < buttons.size(); i++) {
                    JButton button = buttons.get(i);
                    container.remove(button);
                }
                buttons.clear();
            }
            //System.out.println("清理卡牌完毕！");

            if (this.label != null)
                container.remove(label);
            //System.out.println("清理玩家标签！");
            if (this.labelHP != null)
                container.remove(labelHP);
            //System.out.println("清理血量！");

            this.cardMouseEventsSelf.clear();
            this.cardMouseEventsEnemy.clear();
            //System.out.println("清理鼠标事件！");
            this.chosenIndexOfButton = -1;
            System.out.println("chosenIndexOfButton被初始化为：" + chosenIndexOfButton);
        }
    }

    public void setLabel(int labelX,int y) {
        JLabel label = new JLabel();
        label.setVisible(true);
        label.setForeground(Color.white);
        label.setBounds(labelX, y - CONSTANT.contentSpace, 100, 30);
        label.setText(playerDesciption);
        this.label = label;
        container.add(label);
    }
/*
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("执行paint");
        removeAllComponents();
        drawButtons();
        d
*/

    public void drawButtons() {

        //System.out.println("进入函数");
        //System.out.println("清屏");

        drawButtonSelf(cardPlayerKeySelf);
        drawButtonEnemy(cardPlayerKeyEnemy);

    }

    public void drawButtonSelf(CardPlayerKey cardPlayerKey) {

        if (cardPlayerKey != null && cardPlayerKey.getCards()!=null &&!cardPlayerKey.getCards().isEmpty()) {
            //System.out.println("自己还剩" + cardPlayerKey.getCards().size() + "张手牌");

            int x = (CONSTANT.frameWidth - cardPlayerKey.getCards().size() * CONSTANT.cardWidth) / 2;
            int labelX = x;
            int y = CONSTANT.frameHeight - CONSTANT.cardDistantFromBottom - CONSTANT.cardHeight - 50;
            setLabel(labelX,y);

            //System.out.println("drawButtons1");
            for (int i = 0; i < cardPlayerKey.getCards().size(); i++) {
                // System.out.println("drawButtons2");
                String text = cardPlayerKey.getCards().get(i).getCardName();
                //System.out.println("drawButtons3");

                JButton button = getButton(text, x, y, CONSTANT.cardWidth, CONSTANT.cardHeight);
                CardMouseEvent cardMouseEvent = new CardMouseEvent(i);
                cardMouseEventsSelf.add(cardMouseEvent);
                button.addMouseListener(cardMouseEvent);
                container.add(button);
                buttons.add(button);
                System.out.println("Button" + i + " 的Event里的index值:" + cardMouseEvent.getIndex() + "\nchosenIndex: " + cardMouseEvent.getChosenIndex());
                x += CONSTANT.cardWidth + 10;
            }
        }
    }

    public void drawButtonEnemy(CardPlayerKey cardPlayerKey) {
        if (cardPlayerKey != null && cardPlayerKey.getCards()!=null &&!cardPlayerKey.getCards().isEmpty()) {

            int x = (CONSTANT.frameWidth - cardPlayerKey.getCards().size() * CONSTANT.cardWidth) / 2;
            int labelX = x;
            int y = CONSTANT.getCardDistantFromUp;

            for (int i = 0; i < cardPlayerKey.getCards().size(); i++) {
                String text = cardPlayerKey.getCards().get(i).getCardName();

                JButton button = getButton(text, x, y, CONSTANT.cardWidth, CONSTANT.cardHeight);
                CardMouseEvent cardMouseEvent = new CardMouseEvent(i);
                cardMouseEventsEnemy.add(cardMouseEvent);
                button.addMouseListener(cardMouseEvent);
                container.add(button);
                buttons.add(button);

                System.out.println("Button" + i + " 的Event里的index值:" + cardMouseEvent.getIndex() + "\nchosenIndex: " + cardMouseEvent.getChosenIndex());
                x += CONSTANT.cardWidth + 10;
            }
        }
    }

    public void configureIndex() {

        if(cardMouseEventsSelf != null){
            for(CardMouseEvent cardMouseEvent : cardMouseEventsSelf) {
                if(cardMouseEvent.getChosenIndex() == cardMouseEvent.getIndex()) {
                    System.out.println("找到了！");
                    this.chosenIndexOfButton = cardMouseEvent.getChosenIndex();
                    break;
                }
            }
        }
    }

    public void drawInfoSelf() {

        int x = CONSTANT.frameWidth - 100;
        int y = CONSTANT.frameHeight - 100;

        if (cardPlayerKeySelf != null) {
            //System.out.println("HP函数");
            JLabel labelHP = new JLabel();
            labelHP.setText("HP:" + cardPlayerKeySelf.getPlayer().getHp());
            labelHP.setBounds(x,y,100,100);
            labelHP.setFont(new Font("宋体",1,20));
            labelHP.setForeground(Color.white);
            container.add(labelHP);
            this.labelHP = labelHP;
            //System.out.println(cardPlayerKey.getPlayer().getHp());

        }
    }


}