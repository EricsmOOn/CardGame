package cn.ericmoon.cardGame.Enum;

import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Damage 枚举
 */

@Getter
public enum AfterEnum {

    AFTER_ATTACK(3,"瓮中捉鳖","反弹对方的攻击",1),
    AFTER_BUFF(3,"科学领域","阻止目标的Buff效果",2),
    AFTER_DEAD(3,"不可思议","阻止一次致命伤害",3);

    private int cardType;
    private String cardName;
    private String cardDesc;
    private int afterType;

    AfterEnum(int cardType, String cardName, String cardDesc, int afterType) {
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.afterType = afterType;
    }

}
