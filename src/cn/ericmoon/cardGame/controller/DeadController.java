package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.cards.AfterCard;
import cn.ericmoon.cardGame.keys.AfterPlayerKey;
import cn.ericmoon.cardGame.player.Player;

import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/19
 * @Desc: 死亡清算
 */
public class DeadController {

    /**
     * @Description
     * 死亡前检测
     * @parameters  [player]
     * @return  boolean
     */
    public static boolean preDeadController(AfterPlayerKey apk) throws Exception {
        Player player = apk.getPlayer();
        if (player.getHp() <= 0) {
            boolean isDead = true;
            //System.out.println("死亡检测开始...");
            List<AfterCard> afterCards = apk.getAfterCards();
            for (AfterCard after : afterCards) {
                if (after.getAfterType() == 3) {
                    //System.out.println("有一次免死...");
                    player.setHp(1);
                    apk.deleteAfter(after);
                    isDead = false;
                }
            }
            if (isDead) {
                //System.out.println("确认死亡...");
                player.setHp(0);
            }
            return isDead;
        }
        return false;
    }
}
