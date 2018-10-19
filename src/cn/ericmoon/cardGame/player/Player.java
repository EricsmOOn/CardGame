package cn.ericmoon.cardGame.player;

import lombok.Data;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 玩家类
 */

@Data
public class Player {

    private int maxHp;
    private int Hp;
    private int maxCardNum;
    private int getCardNum;
    private double luckNum;

    public Player(int maxHp, int hp, int maxCardNum, int getCardNum, double luckNum) {
        this.maxHp = maxHp;
        Hp = hp;
        this.maxCardNum = maxCardNum;
        this.getCardNum = getCardNum;
        this.luckNum = luckNum;
    }

    public Player() { }

    public void setHp(int hp) {
        if(hp > this.getMaxHp()){
            hp = this.getMaxHp();
        }
        this.Hp = hp;
    }
}
