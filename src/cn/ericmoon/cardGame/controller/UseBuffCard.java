package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.cards.AfterCard;
import cn.ericmoon.cardGame.cards.BuffCard;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.KeyBag;
import cn.ericmoon.cardGame.player.Player;

import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/16
 * @Desc: buff出牌控制
 */
public class UseBuffCard {

    public static void attackUpBuff(KeyBag keyBag) {
        //转型
        BuffCard buffCard = (BuffCard) keyBag.getCard();

        keyBag.getBpkme().addBuff(buffCard);

    }

    public static void cardNumUpBuff(KeyBag keyBag) {
        //转型
        BuffCard buffCard = (BuffCard) keyBag.getCard();

        keyBag.getBpkme().addBuff(buffCard);

    }

    public static void luckUpBuff(KeyBag keyBag) {
        //转型
        BuffCard buffCard = (BuffCard) keyBag.getCard();
        //在摸牌前判断最终Luck值 并删除
        keyBag.getBpkme().addBuff(buffCard);
    }
}
