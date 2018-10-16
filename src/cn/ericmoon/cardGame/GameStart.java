package cn.ericmoon.cardGame;

import cn.ericmoon.cardGame.Draw.GamePannel;
import cn.ericmoon.cardGame.Player.Player;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.controller.HandCardController;
import cn.ericmoon.cardGame.gameRepository.ApKeySource;
import cn.ericmoon.cardGame.gameRepository.BpKeySource;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.AllKeyInit;

import java.util.List;

import static cn.ericmoon.cardGame.Enum.PlayerEnum.*;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 游戏入口
 */
public class GameStart {

    public static void main(String[] str){
        try {

            //游戏初始化
            gameInit();


            List<Card> cards1 = CpKeySource.getCpk1().getCards();

            List<Card> cards2 = CpKeySource.getCpk2().getCards();

            System.out.println("你抽到了:");
            for (Card card:cards1) {
                System.out.println(card.getCardName()+"\n"+":"+card.toString());
                System.out.println(card.getCardDesc()+"\n"+":"+card.toString());
                System.out.println();
            }

            System.out.println("你的敌人抽到了:");
            for (Card card:cards2) {
                System.out.println(card.getCardName()+"\n"+":"+card.toString());
                System.out.println(card.getCardDesc()+"\n"+":"+card.toString());
                System.out.println();
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        GamePannel f = new GamePannel();
        f.LaunchFrame();

    }


    private static void gameInit() throws Exception{

        Player player = new Player(PLAYER_INFO.getMaxHp(),PLAYER_INFO.getHp(), PLAYER_INFO.getMaxCardNum(),
                PLAYER_INFO.getGetCardNum(),PLAYER_INFO.getGetCardNum());

        Player ai = new Player(AI_INFO.getMaxHp(),AI_INFO.getHp(),AI_INFO.getMaxCardNum(),
                AI_INFO.getGetCardNum(), AI_INFO.getLuckNum());

        AllKeyInit.allKeyInit(player,ai);

        //开局抽牌
        HandCardController hcc = new HandCardController();
        for(int i = 0;i < 3;i++){
            hcc.getCards(CpKeySource.getCpk1());
            hcc.getCards(CpKeySource.getCpk2());
        }
    }
}
