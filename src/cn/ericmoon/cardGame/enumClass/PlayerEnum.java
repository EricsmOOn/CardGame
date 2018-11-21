package cn.ericmoon.cardGame.enumClass;

import lombok.Getter;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 用户资料
 */
@Getter
public enum PlayerEnum {
    PLAYER_INFO(100,2,3,2,1.0),AI_INFO(100,100,3,2,1.0);//todo

    private int maxHp;
    private int Hp;
    private int maxCardNum;
    private int getCardNum;
    private double luckNum;

    PlayerEnum(int maxHp, int hp, int maxCardNum, int getCardNum, double luckNum) {
        this.maxHp = maxHp;
        Hp = hp;
        this.maxCardNum = maxCardNum;
        this.getCardNum = getCardNum;
        this.luckNum = luckNum;
    }
}
