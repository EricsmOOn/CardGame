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
        if(player.getHp() <= 0){
            List<AfterCard> afterCards = apk.getAfterCards();
            for(AfterCard after : afterCards){
                if(after.getAfterType() == 3){
                    player.setHp(1);
                    apk.deleteAfter(after);
                    return false;
                }else {
                    player.setHp(0);
                    return true;
                }
            }
        }
        return false;
    }

}
