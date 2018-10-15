package cn.ericmoon.cardGame.cards;

import lombok.Data;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/14
 * @Desc: 虚弱效果
 *
 * buffNumber -> buff加成效果的基础数值
 *
 */

@Data
public class DeBuffCard extends Buff {

    private int buffNumber;

}
