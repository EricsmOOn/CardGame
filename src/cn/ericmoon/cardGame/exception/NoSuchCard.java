package cn.ericmoon.cardGame.exception;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: 没有这种卡牌在列表中异常
 */
public class NoSuchCard extends Exception{

    public NoSuchCard() {
        super();
    }

    public NoSuchCard(String message) {
        super(message);
    }

    public NoSuchCard(String message, Throwable cause) {
        super(message, cause);
    }
}
