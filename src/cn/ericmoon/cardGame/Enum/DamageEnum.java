package cn.ericmoon.cardGame.Enum;

import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Damage 枚举
 */

@Getter
public enum DamageEnum {

    COMMON_ATTACK(1,"试探","对目标进行一次普通攻击",1,1),
    FULL_ATTACK(1,"突破","对目标进行全力攻击,无视Debff削弱效果",2,2),
    HOLY_ATTACK(1,"惩戒","对目标惩戒，减少目标的幸运值",3,1);

    private int cardType;
    private String cardName;
    private String cardDesc;
    private int damageType;
    private int damage;

    DamageEnum(int cardType, String cardName, String cardDesc, int damageType, int damage) {
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.damageType = damageType;
        this.damage = damage;
    }
}
