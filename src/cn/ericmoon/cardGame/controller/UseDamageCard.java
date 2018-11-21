package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.cards.*;
import cn.ericmoon.cardGame.keys.KeyBag;
import cn.ericmoon.cardGame.player.Player;

import java.util.ArrayList;
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


        boolean havingBuff = false;
        DeBuffCard dbc = null;

        damage = buffCount(keyBag, damage);

        damage = deBuffCount(keyBag, damage);

        afterCount(keyBag,damage);
    }

    public static void fullAttack(KeyBag keyBag) {
        //转型
        DamageCard damageCard = (DamageCard) keyBag.getCard();

        //初始攻击力
        int damage = damageCard.getDamage();

        damage = buffCount(keyBag, damage);

        //反制卡牌清算
        afterCount(keyBag,damage);
    }

    public static void holyAttack(KeyBag keyBag) {
        //转型
        DamageCard damageCard = (DamageCard) keyBag.getCard();

        //初始攻击力
        double damage = damageCard.getDamage();

        damage = Math.pow(0.9,damage);

        //反制卡牌清算
        List<AfterCard> afterCards = keyBag.getApken().getAfterCards();
        AfterCard toBeDeleted  = null;
        boolean havingType1 = false;

        if(afterCards.isEmpty()){
            Player player = keyBag.getApken().getPlayer();
            player.setLuckNum(player.getLuckNum() * damage);
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
                player.setLuckNum(player.getLuckNum() * damage);
            }
            else {
                Player player = keyBag.getApken().getPlayer();
                player.setLuckNum(player.getLuckNum() * damage);
            }
            if(toBeDeleted != null)
                afterCards.remove(toBeDeleted);
        }
    }

    public static void afterCount(KeyBag keyBag,int damage){
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

    public static int buffCount(KeyBag keyBag, int damage) {
        //Buff卡牌清算
        List<BuffCard> deleteCards = new ArrayList<>();

        //BuffCard加成
        List<BuffCard> buffs = keyBag.getBpkme().getBuffs();
        if (!buffs.isEmpty()) {
            for (BuffCard buff : buffs) {
                if (buff.getBuffType() == 1) {
                    damage += buff.getBuffNumber();
                    deleteCards.add(buff);
                }
            }
            if (!deleteCards.isEmpty()) {
                for (BuffCard b : deleteCards)
                    buffs.remove(b);
            }
        }
        return damage;
    }

    public static int deBuffCount(KeyBag keyBag, int damage) {
        //DeBuff卡牌清算
        List<DeBuffCard> deleteCards = new ArrayList<>();

        //DeBuffCard虚弱
        List<DeBuffCard> deBuffs = keyBag.getBpkme().getDeBuffs();
        if (!deBuffs.isEmpty()) {
            for (DeBuffCard deBuff : deBuffs) {
                if (deBuff.getBuffType() == 1) {
                    damage -= deBuff.getBuffNumber();
                    if (damage < 0) {
                        damage = 0;
                    }
                    deleteCards.add(deBuff);
                }
            }
            if (!deleteCards.isEmpty()) {
                for (DeBuffCard d : deleteCards)
                    deBuffs.remove(d);
            }
        }
        return damage;
    }
}
