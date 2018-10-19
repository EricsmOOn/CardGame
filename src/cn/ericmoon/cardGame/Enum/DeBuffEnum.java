package cn.ericmoon.cardGame.Enum;

import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: DeBuff 枚举
 */

@Getter
public enum DeBuffEnum {

    ATTACK_DEBUFF(2,"美人计","虚弱对方减少对方攻击",1,1),
    CARD_DEBUFF(2,"钓鱼执法","使对方下回合减少抽牌",2,1),
    LUCK_DEBUFF(2,"隔壁老王","减少对方的幸运值",3,1);

    private int cardType;
    private String cardName;
    private String cardDesc;
    private int DeBuffType;
    private int DeBuffNumber;

    DeBuffEnum(int cardType, String cardName, String cardDesc, int deBuffType, int deBuffNumber) {
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        DeBuffType = deBuffType;
        DeBuffNumber = deBuffNumber;
    }
}
