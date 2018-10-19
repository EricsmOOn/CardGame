package cn.ericmoon.cardGame;

import cn.ericmoon.cardGame.Draw.GamePannel;
import cn.ericmoon.cardGame.controller.BattleController;
import cn.ericmoon.cardGame.controller.DeadController;
import cn.ericmoon.cardGame.gameRepository.ApKeySource;
import cn.ericmoon.cardGame.gameRepository.BpKeySource;
import cn.ericmoon.cardGame.keys.AfterPlayerKey;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.CardPlayerKey;
import cn.ericmoon.cardGame.player.Player;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.controller.HandCardController;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.AllKeyInit;

import java.util.List;
import java.util.Scanner;

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
            Scanner scn = gameInit();

            boolean playing = true;
            CardPlayerKey cpk;
            BuffPlayerKey bpk;
            int input;

            while (true){

                if(playing){
                    cpk = CpKeySource.getCpk1();
                    bpk = BpKeySource.getBpk1();
                    System.out.println("轮到玩家出牌");
                }else {
                    cpk = CpKeySource.getCpk2();
                    bpk = BpKeySource.getBpk2();
                    System.out.println("轮到电脑出牌");
                }

                //抓牌
                HandCardController.getCards(cpk,bpk);

                printer();

                if(DeadController.preDeadController(ApKeySource.getApk1()) ||
                        DeadController.preDeadController(ApKeySource.getApk2()))
                    break;

                System.out.println("按 0 结束出牌 输入对应数字 1-5 出牌...");

                int i = 1;//每回合剩余的牌是动态的 初始牌-1 因为设定了0为结束出牌

                while (!cpk.getCards().isEmpty()){

                    input = scn.nextInt();

                    if(input == 0) break;

                    Card card = cpk.getCards().get(input-i);

                    System.out.println("您出了:"+card.toString());

                    BattleController.useCard(cpk.getPlayer(),card);

                    i++;

                }

                //卡牌清算
                HandCardController.countCards(cpk);

                playing = !playing;

            }

            gameOver();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        GamePannel f = new GamePannel();
//        f.LaunchFrame();
    }


    private static Scanner gameInit() throws Exception{

        Player player = new Player(PLAYER_INFO.getMaxHp(),PLAYER_INFO.getHp(), PLAYER_INFO.getMaxCardNum(),
                PLAYER_INFO.getGetCardNum(),PLAYER_INFO.getLuckNum());

        Player ai = new Player(AI_INFO.getMaxHp(),AI_INFO.getHp(),AI_INFO.getMaxCardNum(),
                AI_INFO.getGetCardNum(), AI_INFO.getLuckNum());

        AllKeyInit.allKeyInit(player,ai);

        Scanner scn = new Scanner(System.in);

        return  scn;
    }

    private static void gameOver() throws Exception{
        if(CpKeySource.getCpk1().getPlayer().getHp() <= 0){
            System.out.println("十年生死两茫茫 大侠请重新来过");
        }else {
            System.out.println("战胜了第一牌师之后 您从此归隐江湖...");
        }
    }

    private static void printer() throws Exception{

        List<Card> cards1 = CpKeySource.getCpk1().getCards();

        List<Card> cards2 = CpKeySource.getCpk2().getCards();


        System.out.println("您的基本信息:");

        System.out.println(CpKeySource.getCpk1().getPlayer());


        System.out.println("你的手牌:");
        for (Card card:cards1) {
            System.out.println(">>>>"+card.getCardName()+"<<<<"+":"+'\n'+"伤害数值"+card.toString());
            System.out.println(card.getCardDesc());
            System.out.println();
        }

        System.out.println("您的敌人的基本信息:");

        System.out.println(CpKeySource.getCpk2().getPlayer());


        System.out.println("你的敌人的手牌:");
        for (Card card:cards2) {
            System.out.println(">>>>"+card.getCardName()+"<<<<"+":"+'\n'+":"+"伤害数值"+card.toString());
            System.out.println(card.getCardDesc());
            System.out.println();
        }
    }
}
