package cn.ericmoon.cardGame.gameRepository;

import cn.ericmoon.cardGame.keys.CardPlayerKey;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Card-Play 关系仓库
 */
public class CpKeySource {

    private static CardPlayerKey cpk1 = new CardPlayerKey();
    private static CardPlayerKey cpk2 = new CardPlayerKey();

    public static CardPlayerKey getCpk1(){
        return cpk1;
    }

    public static CardPlayerKey getCpk2(){
        return cpk2;
    }

}
