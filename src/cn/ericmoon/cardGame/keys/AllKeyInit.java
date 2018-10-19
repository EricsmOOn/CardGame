package cn.ericmoon.cardGame.keys;

import cn.ericmoon.cardGame.player.Player;
import cn.ericmoon.cardGame.gameRepository.ApKeySource;
import cn.ericmoon.cardGame.gameRepository.BpKeySource;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/16
 * @Desc: 所有Key值初始化生成器，防止12号对应不同key
 */
public class AllKeyInit {

    public static void allKeyInit(Player player,Player ai){

        //AP,BP,CP必须对应好Player Ai

        ApKeySource.getApk1().init(player);
        ApKeySource.getApk2().init(ai);

        BpKeySource.getBpk1().init(player);
        BpKeySource.getBpk2().init(ai);

        CpKeySource.getCpk1().init(player);
        CpKeySource.getCpk2().init(ai);

    }
}
