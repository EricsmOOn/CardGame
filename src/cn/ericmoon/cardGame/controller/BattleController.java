package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.Player.Player;
import cn.ericmoon.cardGame.cards.*;
import cn.ericmoon.cardGame.gameRepository.ApKeySource;
import cn.ericmoon.cardGame.gameRepository.BpKeySource;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.AfterPlayerKey;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.CardPlayerKey;
import cn.ericmoon.cardGame.keys.KeyBag;

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

        KeyBag kb = new KeyBag();
        kb.setCard(card);

        CardPlayerKey cpk1 = CpKeySource.getCpk1();
        CardPlayerKey cpk2 = CpKeySource.getCpk2();

        if(cpk1.getPlayer().equals(player)){
            kb.setApkme(ApKeySource.getApk1());
            kb.setBpkme(BpKeySource.getBpk1());
            kb.setCpkme(cpk1);

            kb.setApken(ApKeySource.getApk2());
            kb.setBpken(BpKeySource.getBpk2());
            kb.setCpken(cpk2);

            useCardCore(kb);
        }else if(cpk2.getPlayer().equals(player)){
            kb.setApkme(ApKeySource.getApk2());
            kb.setBpkme(BpKeySource.getBpk2());
            kb.setCpkme(cpk2);

            kb.setApken(ApKeySource.getApk1());
            kb.setBpken(BpKeySource.getBpk1());
            kb.setCpken(cpk1);

            useCardCore(kb);
        }else throw new Exception("userCard找不到该用户");

    }

    public static void useCardCore(KeyBag keyBag) throws Exception {

        //先删除手牌中的这张卡
        keyBag.getCpkme().deleteCard(keyBag.getCard());

        //卡牌种类分流
        switch (keyBag.getCard().cardType){
                //Damage
            case 1:
                useDamageCardCore(keyBag);
            break;

            //Buff
            case 2:
                Buff buff = (Buff) keyBag.getCard();
                if(buff.isDebuff())useDeBuffCardCore(keyBag);
                else if(!buff.isDebuff())useBuffCardCore(keyBag);
                default : throw new Exception("Buff分流错误");

                //After
            case 3:useAfterCardCore(keyBag);
            break;
        }
    }

    public static void useDamageCardCore(KeyBag keyBag){
        //强制类型转换
        DamageCard damageCard = (DamageCard) keyBag.getCard();

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

    public static void useBuffCardCore(KeyBag keyBag){
        //强制类型转换
        BuffCard buffCard = (BuffCard) keyBag.getCard();

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


    public static void useDeBuffCardCore(KeyBag keyBag){
        //强制类型转换
        DeBuffCard deBuffCard = (DeBuffCard) keyBag.getCard();

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


    public static void useAfterCardCore(KeyBag keyBag){
        //强制类型转换
        AfterCard afterCard = (AfterCard) keyBag.getCard();

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
