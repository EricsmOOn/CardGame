package cn.ericmoon.cardGame.Event;

import cn.ericmoon.cardGame.Draw.GameClient;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BuffStatusEvent implements MouseListener {

    GameClient f;

    public BuffStatusEvent(GameClient f) {
        this.f = f;
    }

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
        f.setBuffStatusVisible();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("离开Buff状态栏！");
        f.setBuffStatusUnvisible();
    }
}
