package cn.ericmoon.cardGame.Draw;

import cn.ericmoon.cardGame.CONSTANT;
import cn.ericmoon.cardGame.Event.CardMouseEvent;
import cn.ericmoon.cardGame.GameUtil.GameUtil;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.cards.DamageCard;
import cn.ericmoon.cardGame.gameRepository.AllKeySource;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.CardPlayerKey;
import com.sun.tools.classfile.ConstantPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

@SuppressWarnings("all")

public class GameClient extends JFrame {

    Toolkit toolkit = this.getToolkit();
    Container container = this.getContentPane();

    public CardPlayerKey cardPlayerKeySelf;
    public CardPlayerKey cardPlayerKeyEnemy;
    public BuffPlayerKey buffPlayerkeySelf;
    public BuffPlayerKey buffPlayerKeyEnemy;

    public String playerDesciption = "";
    public int chosenIndexOfButton = -1;

    private JLabel labelStatus;
    private JLabel labelHPSelf;
    private JLabel labelHPEnemy;
    private JLabel labelLuckSelf;
    private JLabel labelLuckEnemy;
    private JTextArea cardDescriptionTextArea;

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

/*
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        refreshAll();
    }
*/

    public void refreshAll() {
        removeAllComponents();
        drawButtons();
        drawInfo();
        repaint();
    }

    public void removeAllComponents() {
        if(cardPlayerKeySelf != null) {
            removeAllLabels();
            removeAllButtons();
            System.out.println("chosenIndexOfButton被初始化为：" + chosenIndexOfButton);
        }
    }

    private void removeAllLabels() {
        if (this.labelStatus != null)
            container.remove(labelStatus);
        if (this.labelHPSelf != null)
            container.remove(labelHPSelf);
        if(this.labelHPEnemy != null)
            container.remove(labelHPEnemy);
        if(this.labelLuckSelf != null)
            container.remove(labelLuckSelf);
        if(this.labelLuckEnemy != null)
            container.remove(labelLuckEnemy);
    }

    private void removeAllButtons() {
        if(buttons !=null){
            for (int i = 0; i < buttons.size(); i++) {
                JButton button = buttons.get(i);
                container.remove(button);
            }
            buttons.clear();
        }

        this.cardMouseEventsSelf.clear();
        this.cardMouseEventsEnemy.clear();
        //System.out.println("清理鼠标事件！");
        this.chosenIndexOfButton = -1;
    }

    public void drawButtons() {

        //System.out.println("进入函数");
        //System.out.println("清屏");

        drawButtonSelf(cardPlayerKeySelf);
        drawButtonEnemy(cardPlayerKeyEnemy);

    }

    public void drawInfo() {
        drawLabelHP();
        drawLabelLuck();
    }


    public void drawButtonSelf(CardPlayerKey cardPlayerKey) {

        if (cardPlayerKey != null && cardPlayerKey.getCards()!=null &&!cardPlayerKey.getCards().isEmpty()) {
            //System.out.println("自己还剩" + cardPlayerKey.getCards().size() + "张手牌");

            int x = (CONSTANT.frameWidth - cardPlayerKey.getCards().size() * CONSTANT.cardWidth) / 2;
            int y = CONSTANT.frameHeight - CONSTANT.cardDistantFromBottom - CONSTANT.cardHeight - 50;
            int labelX = CONSTANT.frameWidth/2 - 30;

            setLabel(labelX,y);

            for (int i = 0; i < cardPlayerKey.getCards().size(); i++) {
                Card card = cardPlayerKeySelf.getCards().get(i);

                String text = card.getCardName();
                String cardDescription = card.getCardDesc();
                boolean beingCovered = card.isBeingCovered();

                CardMouseEvent cardMouseEvent = new CardMouseEvent(i,this,beingCovered);

                JButton button = getButton(text,cardDescription,x,y,CONSTANT.cardWidth,CONSTANT.cardHeight);
                card.setX(x);
                card.setY(y);

                if(card.getCardType() == 1) {
                    DamageCard damageCard = (DamageCard) card;
                    JLabel label = getDamageLabel(String.valueOf(damageCard.getDamage()));
                    button.add(label);
                }

                cardMouseEventsSelf.add(cardMouseEvent);
                button.addMouseListener(cardMouseEvent);
                container.add(button);
                buttons.add(button);
                //System.out.println("Button" + i + " 的Event里的index值:" + cardMouseEvent.getIndex() + "\nchosenIndex: " + cardMouseEvent.getChosenIndex());
                x += CONSTANT.cardWidth + 10;
            }
        }
    }

    public void drawButtonEnemy(CardPlayerKey cardPlayerKey) {
        if (cardPlayerKey != null && cardPlayerKey.getCards()!=null &&!cardPlayerKey.getCards().isEmpty()) {

            int x = (CONSTANT.frameWidth - cardPlayerKey.getCards().size() * CONSTANT.cardWidth) / 2;
            int y = CONSTANT.getCardDistantFromUp;

            for (int i = 0; i < cardPlayerKey.getCards().size(); i++) {
                Card card = cardPlayerKeyEnemy.getCards().get(i);

                String text = card.getCardName();
                String cardDescription = card.getCardDesc();
                boolean beingCovered = card.isBeingCovered();

                JButton button = getButton(text,cardDescription,x,y,CONSTANT.cardWidth,CONSTANT.cardHeight);
                card.setX(x);
                card.setY(y);
                CardMouseEvent cardMouseEvent = new CardMouseEvent(i,this,beingCovered);
                cardMouseEventsEnemy.add(cardMouseEvent);
                button.addMouseListener(cardMouseEvent);
                container.add(button);
                buttons.add(button);

                //System.out.println("Button" + i + " 的Event里的index值:" + cardMouseEvent.getIndex() + "\nchosenIndex: " + cardMouseEvent.getChosenIndex());
                x += CONSTANT.cardWidth + 10;
            }
            Helper.printLine();
        }
    }

    public void configureIndex() {

        if(cardMouseEventsSelf != null){
            for(CardMouseEvent cardMouseEvent : cardMouseEventsSelf) {
                if(cardMouseEvent.getChosenIndex() == cardMouseEvent.getIndex()) {
                    System.out.println("找到了！");
                    int index = cardMouseEventsSelf.indexOf(cardMouseEvent);
                    Card card = cardPlayerKeySelf.getCards().get(index);
                    JButton button = buttons.get(index);
                    int x = card.getX();
                    int y = card.getY() - 30;
                    //updateButton(button,card.getCardName(),card.getCardDesc(),x,y,index);
                    this.chosenIndexOfButton = cardMouseEvent.getChosenIndex();
                    break;
                }
            }
        }
    }


    /**
     * Helper methods
     */

    private JButton getButton(String name, String description, int x, int y, int width, int height) {

        JButton jButton = new JButton();
        jButton.setBounds(x,y,width,height);
        jButton.setLayout(null);

        JLabel nameLabel = getCardNameLabel(name);
        JLabel desLabel = getCardDesLabel(description);

        jButton.add(nameLabel);
        jButton.add(desLabel);

        return jButton;
    }

    private JLabel getDamageLabel(String text) {
        text = "Damage: " + text;

        JLabel label = new JLabel(text);
        label.setVisible(true);
        label.setForeground(Color.black);
        label.setBounds(CONSTANT.cardWidth/2 - 35,CONSTANT.cardHeight - 25,100,20);
        return label;
    }

    private JLabel getCardNameLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.black);
        label.setFont(new Font("楷体", Font.PLAIN,13));
        label.setBounds(CONSTANT.cardWidth/2 - (text.length() * 12)/2,2,100,50);

        return label;
    }

    private JLabel getCardDesLabel(String text) {
        text = "<html>" + text + "</html>";
        JLabel label = new JLabel(text);
        label.setForeground(Color.black);
        label.setFont(new Font("楷体", Font.PLAIN,13));
        label.setBounds(CONSTANT.textAreaSpace,25,CONSTANT.cardWidth - 2*CONSTANT.textAreaSpace,CONSTANT.textAreaHeight);

        label.setVisible(false);
        return label;
    }

    public void setLabel(int labelX,int y) {
        JLabel label = new JLabel();
        label.setVisible(true);
        label.setForeground(Color.white);
        label.setBounds(labelX, y - CONSTANT.contentSpace, 100, 30);
        label.setText(playerDesciption);
        this.labelStatus = label;
        container.add(label);
    }

    public void updateButton(int index) {

        if (cardPlayerKeySelf != null && cardPlayerKeySelf.getCards()!=null && !cardPlayerKeySelf.getCards().isEmpty()) {

            Card card = cardPlayerKeySelf.getCards().get(index);

            JButton button = getButton(card.getCardName(),card.getCardDesc(),card.getX(),card.getY(),CONSTANT.cardWidth,CONSTANT.cardHeight);
            CardMouseEvent cardMouseEvent = new CardMouseEvent(index,this,card.isBeingCovered());
            button.addMouseListener(cardMouseEvent);

            if(card.getCardType() == 1) {
                //ok
                DamageCard damageCard = (DamageCard) card;
                JLabel label = getDamageLabel(String.valueOf(damageCard.getDamage()));
                button.add(label);
            }

            cardMouseEventsSelf.set(index,cardMouseEvent);
            JButton buttonToBeRemoved = buttons.get(index);
            container.remove(buttonToBeRemoved);
            buttons.set(index,button);
            container.add(buttons.get(index));

            repaint();
        }
    }

    public void setDesVisible() {
        if(cardMouseEventsSelf != null) {
            for(CardMouseEvent cardMouseEvent : cardMouseEventsSelf) {
                if(cardMouseEvent.isBeingCovered()) {
                    int index = cardMouseEvent.getIndex();
                    Card card = cardPlayerKeySelf.getCards().get(index);
                    card.setX(card.getX());
                    card.setY(card.getY() - 30);
                    card.setBeingCovered(true);
                    updateButton(index);
                    JButton button = buttons.get(index);
                    JLabel label = (JLabel) button.getComponent(1);
                    label.setVisible(true);
                    break;
                }
            }
        }
    }

    public void setDesUnVisible() {
        if(cardMouseEventsSelf != null){
            for(CardMouseEvent cardMouseEvent : cardMouseEventsSelf) {
                if(cardMouseEvent.isBeingCovered()) {
                    int index = cardMouseEvent.getIndex();
                    Card card = cardPlayerKeySelf.getCards().get(index);
                    card.setX(card.getX());
                    card.setY(card.getY() + 30);
                    card.setBeingCovered(false);
                    updateButton(index);
                    JButton button = buttons.get(index);
                    JLabel label = (JLabel) button.getComponent(1);
                    label.setVisible(false);
                    break;
                }
            }
        }
    }

    public void drawLabelHP() {

        //Self
        int x = CONSTANT.hpLabelSelfX;
        int y = CONSTANT.hpLabelSelfY;

        if (cardPlayerKeySelf != null) {
            //System.out.println("HP函数");
            JLabel labelHP = new JLabel();
            labelHP.setText("HP:" + cardPlayerKeySelf.getPlayer().getHp());
            labelHP.setBounds(x,y,100,100);
            labelHP.setFont(new Font("宋体",1,20));
            labelHP.setForeground(Color.white);
            container.add(labelHP);
            this.labelHPSelf = labelHP;
            //System.out.println(cardPlayerKey.getPlayer().getHp());

        }

        //Enemy
        y = 100;

        if(cardPlayerKeyEnemy != null) {
            //System.out.println("HP函数");
            JLabel labelHP = new JLabel();
            labelHP.setText("HP:" + cardPlayerKeyEnemy.getPlayer().getHp());
            labelHP.setBounds(x,y,100,100);
            labelHP.setFont(new Font("宋体",1,20));
            labelHP.setForeground(Color.white);
            container.add(labelHP);
            this.labelHPEnemy = labelHP;
            //System.out.println(cardPlayerKey.getPlayer().getHp());
        }
    }

    public void drawLabelLuck() {

        //Self
        int x = CONSTANT.luckLabelSelfX;
        int y = CONSTANT.luckLabelSelfY;

        if(cardPlayerKeySelf != null) {
            JLabel labelLuck = new JLabel();
            labelLuck.setText("Luck:" + cardPlayerKeySelf.getPlayer().getLuckNum());
            labelLuck.setBounds(x,y,100,100);
            labelLuck.setFont(new Font("宋体",1,19));
            labelLuck.setForeground(Color.white);
            container.add(labelLuck);
            this.labelLuckSelf = labelLuck;
        }

        //Eenemy
        y = 50;

        if(cardPlayerKeyEnemy != null) {
            JLabel labelLuck = new JLabel();
            labelLuck.setText("Luck:" + cardPlayerKeyEnemy.getPlayer().getLuckNum());
            labelLuck.setBounds(x,y,100,100);
            labelLuck.setFont(new Font("宋体",1,19));
            labelLuck.setForeground(Color.white);
            container.add(labelLuck);
            this.labelLuckEnemy = labelLuck;
        }
    }



}