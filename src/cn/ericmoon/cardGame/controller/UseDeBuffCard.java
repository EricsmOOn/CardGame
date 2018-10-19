package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.cards.DeBuffCard;
import cn.ericmoon.cardGame.keys.KeyBag;
import cn.ericmoon.cardGame.player.Player;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/16
 * @Desc: DeBuff出牌控制
 */
public class UseDeBuffCard {

    public static void attackDownDeBuff(KeyBag keyBag) {
        //转型
        DeBuffCard deBuffCard = (DeBuffCard) keyBag.getCard();

        keyBag.getBpken().addDeBuff(deBuffCard);

    }

    public static void cardNumDownDeBuff(KeyBag keyBag) {
        //转型
        DeBuffCard deBuffCard = (DeBuffCard) keyBag.getCard();

        keyBag.getBpken().addDeBuff(deBuffCard);

    }

    public static void luckDownDeBuff(KeyBag keyBag) {
        //转型
        DeBuffCard deBuffCard = (DeBuffCard) keyBag.getCard();

        double number = deBuffCard.getBuffNumber();

        number = Math.pow(0.9,number);

        Player player = keyBag.getBpken().getPlayer();

        player.setLuckNum(player.getLuckNum() * number);
    }
}
