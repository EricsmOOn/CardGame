package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.Player.Player;
import cn.ericmoon.cardGame.cards.*;
import cn.ericmoon.cardGame.gameRepository.ApKeySource;
import cn.ericmoon.cardGame.gameRepository.BpKeySource;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.AfterPlayerKey;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.CardPlayerKey;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 战斗控制器 todo
 */
public class BattleController {

    /**
     * @Description
     * 面向前端分流
     * @parameters  [player, card]
     * @return  void
     */
    public static void useCard(Player player,Card card) throws Exception {

        CardPlayerKey cpk1 = CpKeySource.getCpk1();
        CardPlayerKey cpk2 = CpKeySource.getCpk2();

        if(cpk1.getPlayer().equals(player)){
            AfterPlayerKey apk1 = ApKeySource.getApk1();
            BuffPlayerKey bpk1 = BpKeySource.getBpk1();
            useCardCore(cpk1,apk1,bpk1,card);
        }else if(cpk2.getPlayer().equals(player)){
            AfterPlayerKey apk2 = ApKeySource.getApk2();
            BuffPlayerKey bpk2 = BpKeySource.getBpk2();
            useCardCore(cpk2,apk2,bpk2,card);
        }else throw new Exception("userCard找不到该用户");

    }

    public static void useCardCore(CardPlayerKey cpk, AfterPlayerKey apk, BuffPlayerKey bpk,Card card) throws Exception {

        //先删除手牌中的这张卡
        cpk.deleteCard(card);

        //卡牌种类分流
        switch (card.cardType){
                //Damage
            case 1:
                useDamageCardCore(cpk, apk, bpk, card);
            break;

            //Buff
            case 2:
                Buff buff = (Buff)card;
                if(buff.isDebuff())useDeBuffCardCore(cpk, apk, bpk, card);
                else if(!buff.isDebuff())useBuffCardCore(cpk, apk, bpk, card);
                default : throw new Exception("Buff分流错误");

                //After
            case 3:useAfterCardCore(cpk, apk, bpk, card);
            break;
        }
    }

    public static void useDamageCardCore(CardPlayerKey cpk, AfterPlayerKey apk, BuffPlayerKey bpk,Card card){
        //强制类型转换
        DamageCard damageCard = (DamageCard) card;

        //攻击卡牌种类分流
        switch (damageCard.getDamageType()){
                //普通攻击
            case 1:
                //全力攻击
            case 2:
                //神圣攻击
            case 3:
        }
    }

    public static void useBuffCardCore(CardPlayerKey cpk, AfterPlayerKey apk, BuffPlayerKey bpk,Card card){
        //强制类型转换
        BuffCard buffCard = (BuffCard) card;

        //BUff卡牌种类分流
        switch (buffCard.getBuffType()){
                //AttackUp
            case 1:
                //CardNumUp
            case 2:
                //LuckUp
            case 3:
        }
    }


    public static void useDeBuffCardCore(CardPlayerKey cpk, AfterPlayerKey apk, BuffPlayerKey bpk,Card card){
        //强制类型转换
        DeBuffCard deBuffCard = (DeBuffCard) card;

        //DeBuff卡牌种类分流
        switch (deBuffCard.getBuffType()){
                //AttackDown
            case 1:
                //CardNumDown
            case 2:
                //LuckDown
            case 3:
        }
    }


    public static void useAfterCardCore(CardPlayerKey cpk, AfterPlayerKey apk, BuffPlayerKey bpk,Card card){
        //强制类型转换
        AfterCard afterCard = (AfterCard) card;

        //攻击卡牌种类分流
        switch (afterCard.getAfterType()){
                //AttackBack
            case 1:
                //BuffBan
            case 2:
                //DeadFree
            case 3:
        }
    }

}
