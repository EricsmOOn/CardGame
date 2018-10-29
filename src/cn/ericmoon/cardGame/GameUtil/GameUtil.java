package cn.ericmoon.cardGame.GameUtil;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.IOException;

/**
 * 加载图片代码
 */
public class GameUtil {

    private GameUtil(){
    }

    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch(IOException e){
            e.printStackTrace();
        }
        return bi;
    }
}
