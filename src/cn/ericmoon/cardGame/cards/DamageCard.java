package cn.ericmoon.cardGame.cards;

import lombok.Data;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/14
 * @Desc: 伤害牌
 * damage 基础伤害值
 *
 * damageType
 * 1-普通攻击
 * 2-全力攻击 -> 无视减攻击Debuff
 * 3-神圣攻击 -> 减少对方运气
 *
 */

@Data
public class DamageCard extends Card {

    private int damage;
    private int damageType;

}
