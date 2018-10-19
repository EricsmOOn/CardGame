package cn.ericmoon.cardGame.keys;

import cn.ericmoon.cardGame.player.Player;
import cn.ericmoon.cardGame.cards.BuffCard;
import cn.ericmoon.cardGame.cards.DeBuffCard;
import cn.ericmoon.cardGame.exception.NoSuchCard;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Player绑定Buffs关系
 */
@Data
public class BuffPlayerKey extends Key{

    private Player player;
    private List<BuffCard> buffs;
    private List<DeBuffCard> deBuffs;

    public void init(Player player){

        this.player = player;
        this.buffs = new ArrayList<>();
        this.deBuffs = new ArrayList<>();

    }

    public void addBuff(BuffCard buff){
        this.buffs.add(buff);
    }

    public void deleteBuff(BuffCard buff)throws Exception{
        if(!this.buffs.contains(buff)) throw new NoSuchCard("删除Buff失败");
        this.buffs.remove(buff);
    }

    public void addDeBuff(DeBuffCard deBuff){
        this.deBuffs.add(deBuff);
    }

    public void deleteDeBuff(DeBuffCard deBuff)throws Exception{
        if(!this.deBuffs.contains(deBuff)) throw new NoSuchCard("删除DeBuff失败");
        this.deBuffs.remove(deBuff);
    }

}
