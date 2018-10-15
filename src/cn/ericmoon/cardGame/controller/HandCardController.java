package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.Player.Player;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.keys.CardPlayerKey;

import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 手牌控制器
 */
public class HandCardController {

    /**
     * @Description
     * 使用时要使用repository
     * @parameters  [cpk]
     * @return  void
     */
    public void getCards(CardPlayerKey cpk) throws Exception {
        Player player = cpk.getPlayer();
        List<Card> cards = cpk.getCards();

        int cardNum = player.getGetCardNum();
        double luck = player.getLuckNum();

        CardCreaterController ccc = new CardCreaterController();

        for(int i = 0;i < cardNum;i++){
            Card card = ccc.cardCreater(luck);
            cpk.addCard(card);
        }
    }

    /**
     * @Description
     * 使用时要使用repository
     * @parameters  [cpk]
     * @return  void
     */
    public void countCards(CardPlayerKey cpk)throws Exception{
        Player player = cpk.getPlayer();
        List<Card> cards = cpk.getCards();

        int maxCardNum = player.getMaxCardNum();
        int num = cards.size() - maxCardNum;

        if(num > 0){
            for(int i = 1;i < num;i++ ){
                cards.remove(0);
            }
        }
    }


}
