package cn.ericmoon.cardGame.Enum;

import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Buff 枚举
 */

@Getter
public enum BuffEnum {

    ATTACK_BUFF(2,"装上刺刀","对目标伤害加成",1,1),
    CARD_BUFF(2,"行窃预兆","下回合多抽卡",2,1),
    LUCK_BUFF(2,"Eric的祝福","增加自己的幸运值",3,1);

    private int cardType;
    private String cardName;
    private String cardDesc;
    private int buffType;
    private int buffNumber;

    BuffEnum(int cardType, String cardName, String cardDesc, int buffType, int buffNumber) {
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.buffType = buffType;
        this.buffNumber = buffNumber;
    }
}
