package cn.ericmoon.cardGame;

import cn.ericmoon.cardGame.Draw.GameClient;
import cn.ericmoon.cardGame.Draw.Helper;
import cn.ericmoon.cardGame.controller.BattleController;
import cn.ericmoon.cardGame.controller.DeadController;
import cn.ericmoon.cardGame.gameRepository.ApKeySource;
import cn.ericmoon.cardGame.gameRepository.BpKeySource;
import cn.ericmoon.cardGame.keys.BuffPlayerKey;
import cn.ericmoon.cardGame.keys.CardPlayerKey;
import cn.ericmoon.cardGame.player.Player;
import cn.ericmoon.cardGame.cards.Card;
import cn.ericmoon.cardGame.controller.HandCardController;
import cn.ericmoon.cardGame.gameRepository.CpKeySource;
import cn.ericmoon.cardGame.keys.AllKeyInit;

import java.util.Scanner;

import static cn.ericmoon.cardGame.Enum.PlayerEnum.*;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 游戏入口
 */
public class GameStart {

    public static void main(String[] str) {

        try {

            //游戏初始化
            Scanner scn = gameInit();

            GameClient f = new GameClient();
            f.LaunchFrame();


            boolean playing = true;
            CardPlayerKey cpk;
            BuffPlayerKey bpk;
            int input;

            while (true) {

                System.out.println("-------------------------------------------------------------------------");

                f.cardPlayerKeySelf = CpKeySource.getCpk1();
                f.cardPlayerKeyEnemy = CpKeySource.getCpk2();
                f.playing = playing;

                if(playing){
                    cpk = CpKeySource.getCpk1();
                    bpk = BpKeySource.getBpk1();
                    f.playerDesciption = "己方玩家出牌";
                    System.out.println("轮到玩家出牌");
                }else {
                    cpk = CpKeySource.getCpk2();
                    bpk = BpKeySource.getBpk2();
                    f.playerDesciption = "AI出牌";
                    System.out.println("轮到电脑出牌");
                }


                //抓牌
                HandCardController.getCards(cpk,bpk);

                printer(cpk);

                f.removeAllComponents();
                f.drawButtons();
                f.drawInfoSelf();
                f.repaint();

                System.out.println("repaint执行完毕!");
                Helper.printLine();


                if(DeadController.preDeadController(ApKeySource.getApk1()) ||
                        DeadController.preDeadController(ApKeySource.getApk2())) {
                    break;
                }


                //System.out.println("按 0 结束出牌 输入对应数字 1-5 出牌...");
                int chosenIndex = 0;
                if (playing) {
                    if (f.cardPlayerKeySelf != null) {
                        while (f.chosenIndexOfButton == -1) {
                            // wait for the chosenCard
                            //System.out.println("尝试获取index");
                            f.configureIndex();
                        }
                        chosenIndex = f.chosenIndexOfButton;
                    }
                } else {
                    chosenIndex = 0;
                }

                System.out.println("选中了一张牌,index: " + chosenIndex);

                //input = scn.nextInt();

                //if(input == 0) break;

                Card card = cpk.getCards().get(chosenIndex);//测试注意 get(input - 1)

                System.out.println("您出了:"+card.toString());

                BattleController.useCard(cpk.getPlayer(),card);
                //System.out.println("HP: " + cpk.getPlayer().getHp());

                //卡牌清算
                HandCardController.countCards(cpk);

                playing = !playing;

                System.out.println("完成一次出牌");

            }

            gameOver();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private static Scanner gameInit() throws Exception{

        Player player = new Player(1,PLAYER_INFO.getMaxHp(),PLAYER_INFO.getHp(), PLAYER_INFO.getMaxCardNum(),
                PLAYER_INFO.getGetCardNum(),PLAYER_INFO.getLuckNum());

        Player ai = new Player(2,AI_INFO.getMaxHp(),AI_INFO.getHp(),AI_INFO.getMaxCardNum(),
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

    private static void printer(CardPlayerKey cpk) throws Exception{

        Player player = CpKeySource.getCpk1().getPlayer();

        Player ai = CpKeySource.getCpk2().getPlayer();

        System.out.print("PLAYER______:");
        System.out.println("HP::"+player.getHp()+"//LUCK::"+player.getLuckNum());
        System.out.println();
        System.out.println("BUFF::"+BpKeySource.getBpk1().getBuffs()+"//DEBUFF::"+BpKeySource.getBpk1().getDeBuffs());
        System.out.println("AFTER::"+ApKeySource.getApk1().getAfterCards());
        System.out.println();

        System.out.print("AI__________:");
        System.out.println("HP::"+ai.getHp()+"//LUCK::"+ai.getLuckNum());
        System.out.println();
        System.out.println("BUFF::"+BpKeySource.getBpk2().getBuffs()+"//DEBUFF::"+BpKeySource.getBpk2().getDeBuffs());
        System.out.println("AFTER::"+ApKeySource.getApk2().getAfterCards());
        System.out.println();

        System.out.println("当前玩家手牌:");
        for (Card card:cpk.getCards()) {
            System.out.println(">>>>"+card.getCardName()+"<<<<"+":"+'\n'+"伤害数值"+card.toString());
            System.out.println(card.getCardDesc());
            System.out.println();
        }

    }
}
