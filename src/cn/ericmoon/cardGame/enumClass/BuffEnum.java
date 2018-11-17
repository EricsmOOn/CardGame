package cn.ericmoon.cardGame.enumClass;

import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Buff 枚举
 */

@Getter
public enum BuffEnum {

    ATTACK_BUFF(2, "装备改良", "下一次攻击对目标伤害加成", 1, 1, "增加己方伤害"),//攻击清算 叠加
    CARD_BUFF(2, "行窃预兆", "下一回合多抽一张卡(不可叠加)", 2, 1, "下回合己方多抽卡"),//回合清算
    LUCK_BUFF(2, "圣灵祝福", "增加自己下一回合的幸运值", 3, 1, "下回合抽卡攻击加成");//回合清算

    private int cardType;
    private String cardName;
    private String cardDesc;
    private int buffType;
    private int buffNumber;
    private String statusDesc;

    BuffEnum(int cardType, String cardName, String cardDesc, int buffType, int buffNumber, String statusDesc) {
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.buffType = buffType;
        this.buffNumber = buffNumber;
        this.statusDesc = statusDesc;
    }
}
