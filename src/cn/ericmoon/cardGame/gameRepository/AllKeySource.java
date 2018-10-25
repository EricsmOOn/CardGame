package cn.ericmoon.cardGame.gameRepository;

import cn.ericmoon.cardGame.keys.AfterPlayerKey;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.CardPlayerKey;
import lombok.Data;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/23
 * @Desc: 总仓库
 */
@Data
public class AllKeySource {

     AfterPlayerKey apk1 = ApKeySource.getApk1();
     AfterPlayerKey apk2 = ApKeySource.getApk2();

     BuffPlayerKey bpk1 = BpKeySource.getBpk1();
     BuffPlayerKey bpk2 = BpKeySource.getBpk2();

     CardPlayerKey cpk1 = CpKeySource.getCpk1();
     CardPlayerKey cpk2 = CpKeySource.getCpk2();
    
}
