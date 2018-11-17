package cn.ericmoon.cardGame.enumClass;

import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Damage 枚举
 */

@Getter
public enum AfterEnum {

    AFTER_ATTACK(3, "反伤刺甲", "下一回合反弹对方的攻击", 1, "反弹自己受到的伤害"),//回合清算
    AFTER_BUFF(3, "科学领域", "阻止下一回合目标的Buff效果", 2, "使得对方buff卡失效"),//回合清算
    AFTER_DEAD(3, "先祖之力", "阻止一次致命伤害(不可叠加)", 3, "抵挡一次致命伤害");//回合保留 不可叠加

    private int cardType;
    private String cardName;
    private String cardDesc;
    private int afterType;
    private String statusDesc;

    AfterEnum(int cardType, String cardName, String cardDesc, int afterType, String statusDesc) {
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.afterType = afterType;
        this.statusDesc = statusDesc;
    }

}
