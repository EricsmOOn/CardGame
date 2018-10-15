package cn.ericmoon.cardGame.gameRepository;

import cn.ericmoon.cardGame.keys.AfterPlayerKey;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: After-Play 关系仓库
 */
public class ApKeySource {

    private static AfterPlayerKey apk1 = new AfterPlayerKey();
    private static AfterPlayerKey apk2 = new AfterPlayerKey();

    public static AfterPlayerKey getApk1(){
        return apk1;
    }

    public static AfterPlayerKey getApk2(){
        return apk2;
    }

}
