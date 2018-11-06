package cn.ericmoon.cardGame.Event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BuffStatusEvent implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("进入Buff状态栏!");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
