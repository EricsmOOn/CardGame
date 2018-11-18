package cn.ericmoon.cardGame.enumClass;

import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: DeBuff 枚举
 */

@Getter
public enum DeBuffEnum {

    ATTACK_DEBUFF(2, "蛛网束缚", "虚弱对方减少下一次对方攻击", 1, 1, "己方攻击力降低"),//攻击清算 叠加
    CARD_DEBUFF(2, "钓鱼执法", "使对方下一回合减少一张抽牌(不可叠加)", 2, 1, "己方减少抽牌"),//回合清算
    LUCK_DEBUFF(2, "隔壁老王", "减少对方下一回合的幸运值", 3, 1, "下回合抽卡攻击减少");//回合清算

    private int cardType;
    private String cardName;
    private String cardDesc;
    private int DeBuffType;
    private int DeBuffNumber;
    private String statusDesc;

    DeBuffEnum(int cardType, String cardName, String cardDesc, int deBuffType, int deBuffNumber, String statusDesc) {
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardDesc = cardDesc;
        this.DeBuffType = deBuffType;
        this.DeBuffNumber = deBuffNumber;
        this.statusDesc = statusDesc;
    }
}
