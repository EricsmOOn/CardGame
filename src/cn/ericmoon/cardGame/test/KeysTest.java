package cn.ericmoon.cardGame.test;

import cn.ericmoon.cardGame.Player.Player;
import cn.ericmoon.cardGame.cards.DamageCard;
import cn.ericmoon.cardGame.keys.CardPlayerKey;


//ACCESS

/**
 * @ProjectName: CardGame
 * @CoderName: Eric Wong
 * @Date: 2018/10/15
 * @Desc: Keys测试类
 */
public class KeysTest {

    public static void main(String[] str) {
        try {
            CardPlayerKey cardPlayerKey = new CardPlayerKey();
            cardPlayerKey.init(new Player());

            DamageCard damage = new DamageCard();
            damage.setDamage(1);
            damage.setDamageType(1);

            DamageCard damage2 = new DamageCard();
            damage.setDamage(1);
            damage.setDamageType(1);


            cardPlayerKey.addCard(damage);
            System.out.println(cardPlayerKey.toString());

            cardPlayerKey.deleteCard(damage);
            cardPlayerKey.deleteCard(damage2);

            System.out.println(cardPlayerKey.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
