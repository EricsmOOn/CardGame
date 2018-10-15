package cn.ericmoon.cardGame.gameRepository;

import cn.ericmoon.cardGame.keys.BuffPlayerKey;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Buff-Play 关系仓库
 */
public class BpKeySource {

    private static BuffPlayerKey bpk1 = new BuffPlayerKey();
    private static BuffPlayerKey bpk2 = new BuffPlayerKey();

    public static BuffPlayerKey getBpk1(){
        return bpk1;
    }

    public static BuffPlayerKey getBpk2(){
        return bpk2;
    }

}
