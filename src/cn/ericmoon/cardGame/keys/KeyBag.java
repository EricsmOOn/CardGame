package cn.ericmoon.cardGame.keys;

import cn.ericmoon.cardGame.cards.Card;
import lombok.Data;

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/17
 * @Desc: key组封装
 */

@Data
public class KeyBag {

    private CardPlayerKey cpkme;
    private AfterPlayerKey apkme;
    private BuffPlayerKey bpkme;

    private CardPlayerKey cpken;
    private AfterPlayerKey apken;
    private BuffPlayerKey bpken;

    private Card card;
}
