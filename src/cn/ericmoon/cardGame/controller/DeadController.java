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
     * @return boolean
     * @Description 死亡前检测
     * @parameters [player]
     */
    public static boolean preDeadController(AfterPlayerKey apk) throws Exception {
        AfterCard deleteAfter = null;
        Player player = apk.getPlayer();
        if (player.getHp() <= 0) {
            boolean isDead = true;
            List<AfterCard> afterCards = apk.getAfterCards();
            if (afterCards != null && !afterCards.isEmpty()) {
                for (AfterCard after : afterCards) {
                    if (after.getAfterType() == 3) {
                        player.setHp(1);
                        deleteAfter = after;
                        isDead = false;
                    }
                }
                if(deleteAfter != null)
                    apk.deleteAfter(deleteAfter);
                if (isDead) {
                    //System.out.println("确认死亡...");
                    player.setHp(0);
                }
            }
            return isDead;
        }
        return false;
    }
}
