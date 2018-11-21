package cn.ericmoon.cardGame.event;

import cn.ericmoon.cardGame.GameStart;
import cn.ericmoon.cardGame.draw.GameClient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RestartEvent implements MouseListener {

    private GameClient f;

    public RestartEvent(GameClient f) {
        this.f = f;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
       try {
           GameStart.continuePlaying = true;
           System.out.println(GameStart.continuePlaying);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
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
}
