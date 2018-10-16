package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.Player.Player;
import cn.ericmoon.cardGame.cards.*;
import cn.ericmoon.cardGame.tools.RandomNumTools;


import static cn.ericmoon.cardGame.Enum.AfterEnum.*;
import static cn.ericmoon.cardGame.Enum.BuffEnum.*;
import static cn.ericmoon.cardGame.Enum.DamageEnum.*;
import static cn.ericmoon.cardGame.Enum.DeBuffEnum.*;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 卡牌控制器
 */
public class CardCreaterController {

    /**
     * @Description
     * 卡牌大类生成控制器
     * @parameters  [player]
     * @return  cn.ericmoon.cardGame.cards.Card
     */
    public Card cardCreater(double luck) throws Exception {

        switch (RandomNumTools.cardChoose()){
            case 1:
                return damageCardCreater(luck);
            case 2:
                if(Math.random() > 0.4)
                return deBuffCardCreater(luck);
                else
                    return buffCardCreater(luck);
            case 3:
                return afterCardCreater(luck);

                default:throw new Exception("卡牌生成逻辑错误");
        }
    }

    private DamageCard damageCardCreater(double luck) throws Exception {

        DamageCard damageCard = new DamageCard();

        switch (RandomNumTools.cardChoose()){
            case 1:
                damageCard.setCardType(COMMON_ATTACK.getCardType());
                damageCard.setCardName(COMMON_ATTACK.getCardName());
                damageCard.setCardDesc(COMMON_ATTACK.getCardDesc());
                damageCard.setDamageType(COMMON_ATTACK.getDamageType());
                damageCard.setDamage(COMMON_ATTACK.getDamage()*(int)(5*luck));

                return damageCard;

            case 2:
                damageCard.setCardType(FULL_ATTACK.getCardType());
                damageCard.setCardName(FULL_ATTACK.getCardName());
                damageCard.setCardDesc(FULL_ATTACK.getCardDesc());
                damageCard.setDamageType(FULL_ATTACK.getDamageType());
                damageCard.setDamage(FULL_ATTACK.getDamage()*(int)(5*luck));

                return damageCard;


            case 3:
                damageCard.setCardType(HOLY_ATTACK.getCardType());
                damageCard.setCardName(HOLY_ATTACK.getCardName());
                damageCard.setCardDesc(HOLY_ATTACK.getCardDesc());
                damageCard.setDamageType(HOLY_ATTACK.getDamageType());
                damageCard.setDamage(HOLY_ATTACK.getDamage());

                return damageCard;

                default:throw new Exception("攻击卡牌生成逻辑错误");
        }
    }

    private BuffCard buffCardCreater(double luck) throws Exception {

        BuffCard buffCard = new BuffCard();
        buffCard.setDebuff(false);

        switch (RandomNumTools.cardChoose()){
            case 1:
                buffCard.setCardType(ATTACK_BUFF.getCardType());
                buffCard.setCardName(ATTACK_BUFF.getCardName());
                buffCard.setCardDesc(ATTACK_BUFF.getCardDesc());
                buffCard.setBuffNumber(ATTACK_BUFF.getBuffNumber()*(int)(luck*2));
                buffCard.setBuffType(ATTACK_BUFF.getBuffType());

                return buffCard;

            case 2:
                buffCard.setCardType(CARD_BUFF.getCardType());
                buffCard.setCardName(CARD_BUFF.getCardName());
                buffCard.setCardDesc(CARD_BUFF.getCardDesc());
                buffCard.setBuffNumber(CARD_BUFF.getBuffNumber());
                buffCard.setBuffType(CARD_BUFF.getBuffType());

                return buffCard;


            case 3:
                buffCard.setCardType(LUCK_BUFF.getCardType());
                buffCard.setCardName(LUCK_BUFF.getCardName());
                buffCard.setCardDesc(LUCK_BUFF.getCardDesc());
                buffCard.setBuffNumber(LUCK_BUFF.getBuffNumber());
                buffCard.setBuffType(LUCK_BUFF.getBuffType());

                return buffCard;

            default:throw new Exception("功能卡牌生成逻辑错误");
        }
    }

    private DeBuffCard deBuffCardCreater(double luck) throws Exception {

        DeBuffCard deBuffCard = new DeBuffCard();
        deBuffCard.setDebuff(true);

        switch (RandomNumTools.cardChoose()){
            case 1:
                deBuffCard.setCardType(ATTACK_DEBUFF.getCardType());
                deBuffCard.setCardName(ATTACK_DEBUFF.getCardName());
                deBuffCard.setCardDesc(ATTACK_DEBUFF.getCardDesc());
                deBuffCard.setBuffNumber(ATTACK_DEBUFF.getDeBuffNumber()*(int)(luck*2));
                deBuffCard.setBuffType(ATTACK_DEBUFF.getDeBuffType());

                return deBuffCard;

            case 2:
                deBuffCard.setCardType(CARD_DEBUFF.getCardType());
                deBuffCard.setCardName(CARD_DEBUFF.getCardName());
                deBuffCard.setCardDesc(CARD_DEBUFF.getCardDesc());
                deBuffCard.setBuffNumber(CARD_DEBUFF.getDeBuffNumber());
                deBuffCard.setBuffType(CARD_DEBUFF.getDeBuffType());

                return deBuffCard;


            case 3:
                deBuffCard.setCardType(LUCK_DEBUFF.getCardType());
                deBuffCard.setCardName(LUCK_DEBUFF.getCardName());
                deBuffCard.setCardDesc(LUCK_DEBUFF.getCardDesc());
                deBuffCard.setBuffNumber(LUCK_DEBUFF.getDeBuffNumber());
                deBuffCard.setBuffType(LUCK_DEBUFF.getDeBuffType());

                return deBuffCard;

            default:throw new Exception("功能卡牌生成逻辑错误");
        }
    }

    private AfterCard afterCardCreater(double luck) throws Exception {

        AfterCard afterCard = new AfterCard();

        switch (RandomNumTools.cardChoose()){
            case 1:
                afterCard.setCardType(AFTER_ATTACK.getCardType());
                afterCard.setCardName(AFTER_ATTACK.getCardName());
                afterCard.setCardDesc(AFTER_ATTACK.getCardDesc());
                afterCard.setAfterType(AFTER_ATTACK.getAfterType());

                return afterCard;

            case 2:
                afterCard.setCardType(AFTER_BUFF.getCardType());
                afterCard.setCardName(AFTER_BUFF.getCardName());
                afterCard.setCardDesc(AFTER_BUFF.getCardDesc());
                afterCard.setAfterType(AFTER_BUFF.getAfterType());

                return afterCard;

            case 3:
                afterCard.setCardType(AFTER_DEAD.getCardType());
                afterCard.setCardName(AFTER_DEAD.getCardName());
                afterCard.setCardDesc(AFTER_DEAD.getCardDesc());
                afterCard.setAfterType(AFTER_DEAD.getAfterType());

                return afterCard;
            default:throw new Exception("反制卡牌生成逻辑错误");
        }
    }

}
