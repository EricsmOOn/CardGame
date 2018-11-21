package cn.ericmoon.cardGame.event;


import cn.ericmoon.cardGame.draw.GameClient;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardMouseEvent implements MouseListener {

    private int index;
    private int chosenIndex = -1;
    private boolean beingCovered = false;
    public GameClient f;

    public CardMouseEvent(int index, GameClient f, boolean beingCovered) {
        this.index = index;
        this.f = f;
        this.beingCovered = beingCovered;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.chosenIndex = index;
        //System.out.println("Button" + index + "被选中");
        //System.out.println("它的index: " + index + " chosenIndex:" + chosenIndex);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!beingCovered) {
            //System.out.println("进入卡牌Button " + index);
            beingCovered = true;
            f.setDesVisible();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("离开卡牌Button " + index);
        f.setDesUnVisible();
        beingCovered = false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getChosenIndex() {
        return chosenIndex;
    }

    public void setChosenIndex(int chosenIndex) {
        this.chosenIndex = chosenIndex;
    }

    public boolean isBeingCovered() {
        return beingCovered;
    }

    public void setBeingCovered(boolean beingCovered) {
        this.beingCovered = beingCovered;
    }

}
