package cn.ericmoon.cardGame.Draw;

import cn.ericmoon.cardGame.GameUtil.GameUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GamePannel extends Frame{


    /**
     *
     * 双缓冲
     */

    private Image offScreenImage = null;

    public void update(Graphics g){
        if(offScreenImage == null)
            offScreenImage = this.createImage(700,700);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);

        g.fillRect(350,350,50,70);
        g.setColor(Color.BLUE);
        g.fillRect(385,350,50,70);

        g.setColor(c);
    }


    //反复重画窗口，线程
    class PaintThread extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //定义键盘监听内部类


    public void LaunchFrame() {
        this.setTitle("Created by CarterWang");
        this.setVisible(true);
        this.setSize(700,700);
        this.setLocation(300,50);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start();//启动重画窗口的线程


    }

}