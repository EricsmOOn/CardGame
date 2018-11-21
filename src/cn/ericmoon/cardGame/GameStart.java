package cn.ericmoon.cardGame;

import cn.ericmoon.cardGame.cards.AfterCard;
import cn.ericmoon.cardGame.cards.DamageCard;
import cn.ericmoon.cardGame.draw.GameClient;
import cn.ericmoon.cardGame.draw.Helper;
import cn.ericmoon.cardGame.controller.BattleController;
import cn.ericmoon.cardGame.controller.DeadController;
import cn.ericmoon.cardGame.gameRepository.ApKeySource;
import cn.ericmoon.cardGame.gameRepository.BpKeySource;
import cn.ericmoon.cardGame.keys.AfterPlayerKey;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.CardPlayerKey;
import cn.ericmoon.cardGame.player.Player;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.controller.RoundController;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.AllKeyInit;

import java.util.Scanner;

import static cn.ericmoon.cardGame.enumClass.AfterEnum.AFTER_ATTACK;
import static cn.ericmoon.cardGame.enumClass.AfterEnum.AFTER_DEAD;
import static cn.ericmoon.cardGame.enumClass.DamageEnum.COMMON_ATTACK;
import static cn.ericmoon.cardGame.enumClass.PlayerEnum.*;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 游戏入口
 */
public class GameStart {

    public static boolean continuePlaying = true;

    public static void main(String[] str) {

        GameClient f = new GameClient();
        f.LaunchFrame();

        try {
            while(true) {
                Thread.sleep(2);
                if(continuePlaying) {
//                    System.out.println("开始了一次游戏");
                    startGame(f);
                    gameOver(f);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void startGame(GameClient f) throws Exception{


        //System.out.println("进入start");
        gameInit();
        boolean playing = true;
        AfterPlayerKey apk;
        CardPlayerKey cpk;
        BuffPlayerKey bpk;

        while (true) {

            //System.out.println("-------------------------------------------------------------------------");

            f.cardPlayerKeySelf = CpKeySource.getCpk1();
            f.cardPlayerKeyEnemy = CpKeySource.getCpk2();
            f.buffPlayerkeySelf = BpKeySource.getBpk1();
            f.buffPlayerKeyEnemy = BpKeySource.getBpk2();
            f.afterPlayerKeySelf = ApKeySource.getApk1();
            f.playing = playing;

            if(playing){
                apk = ApKeySource.getApk2();//注意 !!! 逆清算
                cpk = CpKeySource.getCpk1();
                bpk = BpKeySource.getBpk1();
                f.playerDesciption = "己方玩家出牌";

//                AfterCard afterCard = new AfterCard();
//
//                afterCard.setCardType(AFTER_DEAD.getCardType());
//                afterCard.setCardName(AFTER_DEAD.getCardName());
//                afterCard.setCardDesc(AFTER_DEAD.getCardDesc());
//                afterCard.setAfterType(AFTER_DEAD.getAfterType());
//                afterCard.setStatusDesc(AFTER_DEAD.getStatusDesc());
//
//                cpk.addCard(afterCard);

                //System.out.println("轮到玩家出牌");
            }else {
                apk = ApKeySource.getApk1();//注意 !!! 逆清算
                cpk = CpKeySource.getCpk2();
                bpk = BpKeySource.getBpk2();
                f.playerDesciption = "AI正在出牌..";
                //System.out.println("轮到电脑出牌");
            }


            //抓牌
            RoundController.getCards(cpk, bpk);



//                printer(cpk);

            f.refreshAll(); //刷新画面

            //System.out.println("repaint执行完毕!");
            Helper.printWait();


            //System.out.println("按 0 结束出牌 输入对应数字 1-5 出牌...");
            int chosenIndex = 0;
            int sleepTime = 0;
            if (playing) {
                if (f.cardPlayerKeySelf != null) {
                    while (f.chosenIndexOfButton == -1) {
//                         wait for the chosenCard
//                        System.out.println("尝试获取index");
                        f.configureIndex();
                    }
                    chosenIndex = f.chosenIndexOfButton;
                    sleepTime = 700;
                }
            } else {
                chosenIndex = (int)(Math.random() * cpk.getCards().size());
                sleepTime = 1300;
            }

            //System.out.println("选中了一张牌,index: " + chosenIndex);

            //input = scn.nextInt();

            //if(input == 0) break;

            Card card = cpk.getCards().get(chosenIndex);//测试注意 get(input - 1)

            //System.out.println("您出了:"+card.toString());

            Thread.sleep(700);
            f.drawUsedCard(card);
            Thread.sleep(sleepTime);
            BattleController.useCard(cpk.getPlayer(),card);
            //System.out.println("HP: " + cpk.getPlayer().getHp());

            if(DeadController.preDeadController(ApKeySource.getApk1()) ||
                    DeadController.preDeadController(ApKeySource.getApk2())) {
                break;
            }

            //回合清算
            RoundController.countCards(cpk);
            RoundController.countAfters(apk);

            playing = !playing;

            //System.out.println("完成一次出牌");

        }


    }


    private static void gameInit() throws Exception {

        Player player = new Player(1,PLAYER_INFO.getMaxHp(),PLAYER_INFO.getHp(), PLAYER_INFO.getMaxCardNum(),
                PLAYER_INFO.getGetCardNum(),PLAYER_INFO.getLuckNum());

        Player ai = new Player(2,AI_INFO.getMaxHp(),AI_INFO.getHp(),AI_INFO.getMaxCardNum(),
                AI_INFO.getGetCardNum(), AI_INFO.getLuckNum());

        AllKeyInit.allKeyInit(player,ai);

    }

    private static void gameOver(GameClient f) throws Exception{
        if(CpKeySource.getCpk1().getPlayer().getHp() <= 0){
            System.out.println("游戏失败...");
            f.exitGame();
        }else {
            System.out.println("游戏胜利...");
            f.exitGame();
        }
    }

//    private static void printer(CardPlayerKey cpk) throws Exception{
//
//        Player player = CpKeySource.getCpk1().getPlayer();
//
//        Player ai = CpKeySource.getCpk2().getPlayer();
//
//        System.out.print("PLAYER______:");
//        System.out.println("HP::"+player.getHp()+"//LUCK::"+player.getLuckNum());
//        System.out.println();
//        System.out.println("BUFF::"+BpKeySource.getBpk1().getBuffs()+"//DEBUFF::"+BpKeySource.getBpk1().getDeBuffs());
//        System.out.println("AFTER::"+ApKeySource.getApk1().getAfterCards());
//        System.out.println();
//
//        System.out.print("AI__________:");
//        System.out.println("HP::"+ai.getHp()+"//LUCK::"+ai.getLuckNum());
//        System.out.println();
//        System.out.println("BUFF::"+BpKeySource.getBpk2().getBuffs()+"//DEBUFF::"+BpKeySource.getBpk2().getDeBuffs());
//        System.out.println("AFTER::"+ApKeySource.getApk2().getAfterCards());
//        System.out.println();
//
//        System.out.println("当前玩家手牌:");
//        for (Card card:cpk.getCards()) {
//            System.out.println(">>>>"+card.getCardName()+"<<<<"+":"+'\n'+"伤害数值"+card.toString());
//            System.out.println("card.getCardDesc()" + card.getCardDesc());
//            System.out.println();
//        }
//    }
}
