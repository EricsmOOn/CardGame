package cn.ericmoon.cardGame.cards;

import lombok.Data;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/14
 * @Desc: 主动功能父类
 *
 * Type:
 * 1-攻击修改
 * 2-抽牌修改
 * 3-运气修改
 *
 */
@Data
public abstract class Buff extends Card {
    private int buffType;
}
