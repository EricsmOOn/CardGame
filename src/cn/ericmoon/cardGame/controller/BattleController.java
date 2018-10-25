package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.player.Player;
import cn.ericmoon.cardGame.cards.*;
import cn.ericmoon.cardGame.gameRepository.ApKeySource;
import cn.ericmoon.cardGame.gameRepository.BpKeySource;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.CardPlayerKey;
import cn.ericmoon.cardGame.keys.KeyBag;

import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 战斗控制器
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

        }else if(cpk2.getPlayer().equals(player)){
            kb.setApkme(ApKeySource.getApk2());
            kb.setBpkme(BpKeySource.getBpk2());
            kb.setCpkme(cpk2);

            kb.setApken(ApKeySource.getApk1());
            kb.setBpken(BpKeySource.getBpk1());
            kb.setCpken(cpk1);

        }else throw new Exception("userCard找不到该用户");
        
        useCardCore(kb);
    }

    public static void useCardCore(KeyBag keyBag) throws Exception {
        //先删除手牌中的这张卡
        keyBag.getCpkme().deleteCard(keyBag.getCard());

        //卡牌种类分流
        outer : switch (keyBag.getCard().cardType) {
                //Damage
            case 1:
                useDamageCardCore(keyBag);
            break;

            //Buff
            case 2:
                Buff buff = (Buff) keyBag.getCard();

                //反制牌 直接阻断Buff
                List<AfterCard> afterCards = keyBag.getApken().getAfterCards();

                for(AfterCard after : afterCards){
                    if(after.getAfterType() == 2){
                        keyBag.getApken().deleteAfter(after);
                        break outer;
                    }
                }

                if(buff.isDebuff()) {useDeBuffCardCore(keyBag);}
                else if(!buff.isDebuff()){useBuffCardCore(keyBag);}

                break;

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
            case 1:UseDamageCard.commonAttack(keyBag);break;
                //全力攻击
            case 2:UseDamageCard.fullAttack(keyBag);break;
                //神圣攻击
            case 3:UseDamageCard.holyAttack(keyBag);break;
        }
    }

    public static void useBuffCardCore(KeyBag keyBag){
        //强制类型转换
        BuffCard buffCard = (BuffCard) keyBag.getCard();

        //Buff卡牌种类分流
        switch (buffCard.getBuffType()){
                //AttackUp
            case 1:UseBuffCard.attackUpBuff(keyBag);break;
                //CardNumUp
            case 2:UseBuffCard.cardNumUpBuff(keyBag);break;
                //LuckUp
            case 3:UseBuffCard.luckUpBuff(keyBag);break;
        }
    }


    public static void useDeBuffCardCore(KeyBag keyBag){
        //强制类型转换
        DeBuffCard deBuffCard = (DeBuffCard) keyBag.getCard();

        //DeBuff卡牌种类分流
        switch (deBuffCard.getBuffType()){
                //AttackDown
            case 1:UseDeBuffCard.attackDownDeBuff(keyBag);break;
                //CardNumDown
            case 2:UseDeBuffCard.cardNumDownDeBuff(keyBag);break;
                //LuckDown
            case 3:UseDeBuffCard.luckDownDeBuff(keyBag);break;
        }
    }


    public static void useAfterCardCore(KeyBag keyBag){
        //强制类型转换  记得删除
        AfterCard afterCard = (AfterCard) keyBag.getCard();
        keyBag.getApkme().addAfter(afterCard);
    }


}
