package cn.ericmoon.cardGame.cards;

import lombok.Data;
import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/14
 * @Desc: 卡牌祖宗POJO
 *
 * cardId 卡牌id
 * cardName 卡牌名称
 * cardDesc 卡牌描述
 *
 * cardType 卡牌类型
 * 1-DamageCard
 * 2-BuffCard
 * 3-AfterCard
 *
 */
@Data
public abstract class Card {

    public int cardType;
    public String cardName;
    public String cardDesc;

}
