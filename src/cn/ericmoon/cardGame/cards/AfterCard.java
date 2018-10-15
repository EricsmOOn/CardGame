package cn.ericmoon.cardGame.cards;

import lombok.Data;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 反制效果
 *
 * afterType
 * 1-反弹攻击伤害
 * 2-阻止一次Buff
 * 3-死亡后复活
 *
 */
@Data
public class AfterCard extends Card{

    private int afterType;

}
