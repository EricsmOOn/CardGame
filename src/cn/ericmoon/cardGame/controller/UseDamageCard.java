package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.cards.*;
import cn.ericmoon.cardGame.keys.KeyBag;
import cn.ericmoon.cardGame.player.Player;

import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/16
 * @Desc: damage出牌控制
 */
public class UseDamageCard {

    public static void commonAttack(KeyBag keyBag) {
        //转型
        DamageCard damageCard = (DamageCard) keyBag.getCard();

        //初始攻击力
        int damage = damageCard.getDamage();

        //BuffCard加成
        List<BuffCard> buffs = keyBag.getBpkme().getBuffs();
        for (BuffCard buff : buffs) {
            if (buff.getBuffType() == 1) {
                damage += buff.getBuffNumber();
                buffs.remove(buff);
            }
        }

        //DeBuff虚弱效果
        List<DeBuffCard> deBuffs = keyBag.getBpkme().getDeBuffs();
        for(DeBuffCard deBuff : deBuffs){
            if(deBuff.getBuffType() == 1){
                damage -= deBuff.getBuffNumber();
                if(damage < 0){
                    damage = 0;
                }
                deBuffs.remove(deBuff);
            }
        }

        //反制卡牌清算
        List<AfterCard> afterCards = keyBag.getApken().getAfterCards();
        AfterCard toBeDeleted  = null;
        boolean havingType1 = false;

        if(afterCards.isEmpty()){
            Player player = keyBag.getApken().getPlayer();
            player.setHp(player.getHp() - damage);
        } else {
            for (AfterCard after : afterCards) {
                if (after.getAfterType() == 1) {
                    havingType1 = true;
                    toBeDeleted = after;
                    break;
                }
            }
            if(havingType1) {
                Player player = keyBag.getApkme().getPlayer();
                player.setHp(player.getHp() - damage);
            }
            else {
                Player player = keyBag.getApken().getPlayer();
                player.setHp(player.getHp() - damage);
            }
            if(toBeDeleted != null)
                afterCards.remove(toBeDeleted);

        }

    }

    public static void fullAttack(KeyBag keyBag) {
        //转型
        DamageCard damageCard = (DamageCard) keyBag.getCard();

        //初始攻击力
        int damage = damageCard.getDamage();

        //BuffCard加成
        List<BuffCard> buffs = keyBag.getBpkme().getBuffs();
        for (BuffCard buff : buffs) {
            if (buff.getBuffType() == 1) {
                damage += buff.getBuffNumber();
                buffs.remove(buff);
            }
        }

        //反制卡牌清算
        List<AfterCard> afterCards = keyBag.getApken().getAfterCards();
        if(afterCards.isEmpty()){
            Player player = keyBag.getApken().getPlayer();
            player.setHp(player.getHp() - damage);
        }
        for(AfterCard after : afterCards){
            if(after.getAfterType() == 1){
                Player player = keyBag.getApkme().getPlayer();
                player.setHp(player.getHp() - damage);
            }else {
                Player player = keyBag.getApken().getPlayer();
                player.setHp(player.getHp() - damage);
            }
            afterCards.remove(after);
        }
    }

    public static void holyAttack(KeyBag keyBag) {
        //转型
        DamageCard damageCard = (DamageCard) keyBag.getCard();

        //初始攻击力
        double damage = damageCard.getDamage();

        damage = Math.pow(0.9,damage);

        //反制卡牌清算
        List<AfterCard> afterCards = keyBag.getApken().getAfterCards();
        if(afterCards.isEmpty()){
            Player player = keyBag.getApken().getPlayer();
            player.setLuckNum(player.getLuckNum() * damage);
        }
        for(AfterCard after : afterCards){
            if(after.getAfterType() == 1){
                Player player = keyBag.getApkme().getPlayer();
                player.setLuckNum(player.getLuckNum() * damage);
            }else {
                Player player = keyBag.getApken().getPlayer();
                player.setLuckNum(player.getLuckNum() * damage);
            }
            afterCards.remove(after);
        }
    }
}
