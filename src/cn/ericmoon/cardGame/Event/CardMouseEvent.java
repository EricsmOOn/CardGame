package cn.ericmoon.cardGame.Event;


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardMouseEvent implements MouseListener {

    private int index;
    private int chosenIndex = -1;

    public CardMouseEvent(int index) {
        this.index = index;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.chosenIndex = index;
        System.out.println("Button" + index + "被选中");
        System.out.println("它的index: " + index + " chosenIndex:" + chosenIndex);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
}
