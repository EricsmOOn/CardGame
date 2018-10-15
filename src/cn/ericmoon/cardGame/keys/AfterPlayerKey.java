package cn.ericmoon.cardGame.keys;

import cn.ericmoon.cardGame.Player.Player;
import cn.ericmoon.cardGame.cards.AfterCard;
import cn.ericmoon.cardGame.exception.NoSuchCard;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Player绑定Afters关系
 */
@Data
public class AfterPlayerKey extends Key {

    private Player player;
    private List<AfterCard> afterCards;

    public void init(Player player){

        this.player = player;
        this.afterCards = new ArrayList<>();

    }

    public void addAfter(AfterCard after){
        this.afterCards.add(after);
    }

    public void deleteAfter(AfterCard after)throws Exception{
        if(!this.afterCards.contains(after)) throw new NoSuchCard("删除After失败");
        this.afterCards.remove(after);
    }

}
