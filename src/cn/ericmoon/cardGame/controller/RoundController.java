package cn.ericmoon.cardGame.controller;

import cn.ericmoon.cardGame.cards.AfterCard;
import cn.ericmoon.cardGame.cards.BuffCard;
import cn.ericmoon.cardGame.cards.DeBuffCard;
import cn.ericmoon.cardGame.keys.AfterPlayerKey;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.player.Player;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.keys.CardPlayerKey;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 回合控制器
 */
public class RoundController {

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

        List<BuffCard> deleteBuffs = new ArrayList<>();
        List<DeBuffCard> deleteDes = new ArrayList<>();

        //Buff 清算
        if(!buffs.isEmpty()){
            for(BuffCard buff : buffs){
                if(buff.getBuffType() == 2){
                    cardNum += buff.getBuffNumber();
                    deleteBuffs.add(buff);
                }
                if (buff.getBuffType() == 3) {
                    //改变luck 隐式显示
                    luck += 1;
                    deleteBuffs.add(buff);
                }
            }
            if (!deleteBuffs.isEmpty()) {
                for (BuffCard b : deleteBuffs) {
                    buffs.remove(b);
                }
            }
        }

        //DeBuff 清算
        if(!deBuffs.isEmpty()){
            for(DeBuffCard deBuff : deBuffs){
                if(deBuff.getBuffType() == 2){
                    cardNum -= deBuff.getBuffNumber();
                    deleteDes.add(deBuff);
                }
                if (deBuff.getBuffType() == 3) {
                    //改变luck 隐式显示
                    luck -= 0.5;
                    deleteDes.add(deBuff);
                }
            }
            if (!deleteDes.isEmpty()) {
                for (DeBuffCard d : deleteDes) {
                    deBuffs.remove(d);
                }
            }
        }


        //影响力最终过滤
        if(cardNum <= 0){
            cardNum = 0;
        }
        if (luck <= 0) {
            luck = 0.1;
        }

        //摸牌
        CardCreaterController ccc = new CardCreaterController();

        for(int i = 0;i < cardNum;i++){
            Card card = ccc.cardCreater(luck);
            cpk.addCard(card);
        }

    }

    /**
     * @Description
     * 使用时要使用repository
     * 回合清算 -> 弃牌
     * @parameters  [cpk]
     * @return  void
     */
    public static void countCards(CardPlayerKey cpk) throws Exception{
        //弃牌
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


    /**
     * @return void
     * @Description 使用时要使用repository
     * 回合清算 -> 清算反制牌
     * @parameters [cpk]
     */
    public static void countAfters(AfterPlayerKey apk) throws Exception {
        List<AfterCard> deleteCards = new ArrayList<>();

        List<AfterCard> afterCards = apk.getAfterCards();

        if (afterCards != null && !afterCards.isEmpty()) {
            for (AfterCard ac : afterCards) {
                if (ac.getAfterType() != 3) {
                    deleteCards.add(ac);
                }
            }
            if (!deleteCards.isEmpty()) {
                for (AfterCard ac : deleteCards) {
                    apk.getAfterCards().remove(ac);
                    //System.out.println("清算反制牌" + ac.cardName);
                }
            }
        }
    }


}
