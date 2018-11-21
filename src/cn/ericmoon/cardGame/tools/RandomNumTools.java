package cn.ericmoon.cardGame.tools;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 随机数生成器
 */
public class RandomNumTools {

    /**
     * @Description
     * 卡牌选择概率为:
     *                        DamageCard    BuffCard      DeBuffCard      AfterCard
     * 1 -> 40% -> Damage ->   Common       AttackUp      AttackDown      AttackBack
     * 2 -> 30% -> Buff   ->    Full        CardNumUp     CardNumDown      BuffBan
     * 3 -> 30% -> After  ->    Holy         LuckUp        LuckDown        DeadFree
     *
     * @parameters  []
     * @return  int
     */
    public static int cardChoose(){
        double random = Math.random()*10;
//        double random = Math.random()*4;

        if (random < 4) return 1;
        else if (random < 7) return 2;
        else return 3;
    }

}
