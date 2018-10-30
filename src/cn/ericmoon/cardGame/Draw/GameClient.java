package cn.ericmoon.cardGame.Draw;

import cn.ericmoon.cardGame.CONSTANT;
import cn.ericmoon.cardGame.Event.CardMouseEvent;
import cn.ericmoon.cardGame.GameUtil.GameUtil;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.gameRepository.AllKeySource;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.CardPlayerKey;

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

    public void refreshAll() {
        removeAllComponents();
        drawButtons();
        drawInfo();
        repaint();
    }

    public void removeAllComponents() {
        if(cardPlayerKeySelf != null) {
            removeAllLabels();
            resetProperties();
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

    private void resetProperties() {
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
                String text = cardPlayerKey.getCards().get(i).getCardName();
                String cardDescription = cardPlayerKey.getCards().get(i).getCardDesc();

                JButton button = getButton(text, cardDescription, x, y, CONSTANT.cardWidth, CONSTANT.cardHeight);
                cardPlayerKey.getCards().get(i).setX(x);
                cardPlayerKey.getCards().get(i).setY(y);
                CardMouseEvent cardMouseEvent = new CardMouseEvent(i);
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
                String cardName = cardPlayerKey.getCards().get(i).getCardName();
                String cardDescription = cardPlayerKey.getCards().get(i).getCardDesc();

                JButton button = getButton(cardName, cardDescription, x, y, CONSTANT.cardWidth, CONSTANT.cardHeight);
                CardMouseEvent cardMouseEvent = new CardMouseEvent(i);
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
                    updateButton(button,card.getCardName(),card.getCardDesc(),x,y,index);
                    this.chosenIndexOfButton = cardMouseEvent.getChosenIndex();
                    break;
                }
            }
        }
    }


    /**
     * Helper methods
     */

    public JButton getButton(String name, String description, int x, int y, int width, int height) {

        JButton jButton = new JButton();
        jButton.setBounds(x,y,width,height);
        jButton.setLayout(null);

        JLabel nameLabel = getCardNameLabel(name);
        JLabel desLabel = getCardDesLabel(description);

        jButton.add(nameLabel);
        jButton.add(desLabel);

        return jButton;
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
        return label;
    }

    private JTextArea getCardDescriptionTextArea(String description) {
        JTextArea textArea = new JTextArea(description);
        textArea.setForeground(Color.black);
        textArea.setBounds(CONSTANT.textAreaSpace,80,CONSTANT.cardWidth - 2*CONSTANT.textAreaSpace,CONSTANT.textAreaHeight);
        textArea.setColumns(5);
        textArea.setRows(5);
        textArea.setAlignmentX(CONSTANT.cardWidth/2);

        return textArea;
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

    public void updateButton(JButton button,String cardName,String cardDescription, int x, int y,int index) {
        JButton button1 = getButton(cardName,cardDescription,x,y,CONSTANT.cardWidth,CONSTANT.cardHeight);
        container.remove(button);
        container.add(button1);
        buttons.set(index,button1);
        repaint();
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