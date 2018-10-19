package cn.ericmoon.cardGame.keys;

import cn.ericmoon.cardGame.player.Player;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.exception.NoSuchCard;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Player绑定Cards关系 --> 手牌
 */
@Data
public class CardPlayerKey extends Key {

    private Player player;
    private List<Card> cards;

    public void init(Player player){

        this.player = player;
        this.cards = new ArrayList<>();

    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void deleteCard(Card card) throws NoSuchCard {
        if(!this.cards.contains(card)) throw new NoSuchCard("没有这张手牌");
        this.cards.remove(card);
    }

}
