package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.cards.BuffCard;
import cn.ericmoon.cardGame.cards.DeBuffCard;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.player.Player;
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
    public static void getCards(CardPlayerKey cpk , BuffPlayerKey bpk) throws Exception {
        Player player = cpk.getPlayer();
        List<BuffCard> buffs = bpk.getBuffs();
        List<DeBuffCard> deBuffs = bpk.getDeBuffs();

        int cardNum = player.getGetCardNum();
        double luck = player.getLuckNum();

        boolean havingDebuff = false;
        boolean havingBuff = false;
        BuffCard bc = null;
        DeBuffCard dbc = null;

        if(!buffs.isEmpty()){
            for(BuffCard buff : buffs){
                if(buff.getBuffType() == 2){
                    cardNum += buff.getBuffNumber();
                    havingBuff = true;
                    bc = buff;
                    break;
                }
            }
            if(havingBuff){
                buffs.remove(bc);
            }
        }

        if(!deBuffs.isEmpty()){
            for(DeBuffCard deBuff : deBuffs){
                if(deBuff.getBuffType() == 2){
                    cardNum -= deBuff.getBuffNumber();
                    havingDebuff = true;
                    dbc = deBuff;
                    break;
                }
            }
            if(havingDebuff){
                deBuffs.remove(dbc);
            }
        }

        if(cardNum <= 0){
            cardNum = 0;
        }

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
    public static void countCards(CardPlayerKey cpk) throws Exception{

        Player player = cpk.getPlayer();
        List<Card> cards = cpk.getCards();

        int maxCardNum = player.getMaxCardNum();
        int num = cards.size() - maxCardNum;

        if(num > 0){
            for(int i = 0;i < num;i++ ){
                cards.remove(0);
            }
        }
    }


}
