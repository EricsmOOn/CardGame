# 数据结构课程设计实验报告

##### ——基于VRC三层结构的卡牌博弈竞技游戏

### 1、游戏名称

##### 卡牌博弈竞技游戏

### 2、需求分析

本游戏为单机游戏，其目的除了为玩家在空闲时间提供娱乐外，也同时具有锻练、提高玩家快速记忆与考虑问题、分析事件能力的功能。 

2.1  需要为玩家设计一个简约、清晰、易懂和易于操作的游戏界面。游戏界面还需要显示出电脑的当前幸运值Luck、电脑的当前生命值HP、玩家的当前幸运值Luck、玩家的当前生命值HP和玩家当前所持有的卡牌以及电脑当前所持有的卡牌。玩家可以在游戏界面进行选牌与出牌操作。

2.2  需要对电脑与玩家进行随机发牌，判断并记录下电脑与玩家每次所出的卡牌类型。

2.3  需要对电脑与玩家每次所出卡牌而引起的改变进行判断并输出到游戏界面，进行会和清算。 

2.4  需要对游戏输赢进行判断，并输出相应的标志。

2.5  语言：Java语言

### 3、系统架构图

**![img](https://github.com/EricsmOOn/CardGame/blob/master/images/CardGame%E6%9E%B6%E6%9E%84%E5%9B%BE.png)**

### 4、使用说明书

**游戏介绍：**

当玩家打开本游戏的软件时，系统将会自动生成游戏界面并即时为玩家发牌，既是当玩家打开本游戏的软件时就可以开始玩游戏了。玩家无需像打开其它游戏软件那样需要进行登录操作，这样即为玩家省去了等待登录的时间，也为玩家避免了因为忘记密码无法登录而造成不能玩游戏的麻烦。

游戏界面分为两层：底层为由系统设定的游戏背景图片；为了突出简约、清晰、易懂的特点，游戏界面上层的右边，由上而下分别显示着电脑的当前幸运值Luck、电脑的当前生命值HP、玩家的当前幸运值Luck和玩家的当前生命值HP，上层的下半部分显示着玩家当前所持有的卡牌，上半部分则显示电脑当前所持有的卡牌，中间有一个Buff小图标。当把鼠标移至卡牌内时，Buff小图标时，界面会显示出电脑和玩家所打出的BUFF牌。

本游戏所有操作都采用鼠标点击进行：如玩家出牌时，只需把鼠标移至该张卡牌内点击即可。

玩家只可以看到自己的卡牌内容，而电脑的为空白卡。当把鼠标移至某张卡牌内时，卡牌的类型、名称和功能都会显示出来，如果是攻击卡，还会显示出对应的攻击值。

玩家和电脑起始的生命值HP和幸运值Luck都分别为100和1.00。当一方的生命值HP先减为0时，则被判为输，游戏结束。而当前幸运值Luck则会影响己方每次的攻击数值。

当玩家赢得游戏，界面会显示“恭喜您获得胜利！”；当玩家输掉游戏，界面则会显示“很遗憾您输了……”。同时界面还会显示一个循环和一个叉号的小图标，点击循环小图标表示玩家重新进行游戏，点击叉号小图标则表示退出游戏软件。

**卡牌类型：**

本游戏的卡牌分三大类：红色的攻击卡、蓝色的Buff/Debuff卡和黄色的反制卡。

攻击卡又细分为三类：普通攻击卡、全力攻击卡和神圣攻击卡。普通攻击卡命名“试探”，它的功能是对目标进行一次普通攻击。全力攻击卡命名“突破”，它的功能是对目标进行全力攻击,无视DeBuff削弱效果。神圣攻击卡命名“惩戒”，它的功能是对目标发起惩戒，减少目标的幸运值Luck。

Buff卡分为三类：攻击修改、抽牌修改和运气修改。攻击修改既是下一次攻击对方伤害加成，卡牌命名“装备改良”。抽牌修改既是己方下一回合多抽一张牌（不可叠加），卡牌命名“行窃预兆”。运气修改既是增加自己下一回合的幸运值，卡牌命名“圣灵祝福”。

Debuff卡也分为三类：攻击修改、抽牌修改和运气修改，但与Buff卡略有不同。攻击修改是减弱对方下一次对己方的攻击，卡牌命名“蛛网束缚”。抽牌修改既是使对方下一回合少抽一张牌（不可叠加），卡牌命名“钓鱼执法”。运气修改既是减少对方下一回合的幸运值，卡牌命名“隔壁老王”。

反制卡分为三类：反弹攻击伤害、阻止一次Buff和阻止一次死亡伤害。反弹攻击伤害既是下一回合反弹对方的攻击，卡牌命名“反伤刺甲”。 阻止一次Buff既是使下一回合对方所打的Buff卡失效，卡牌命名“科学领域”。阻止一次死亡伤害既是打出该卡牌后当己方受到对方攻击生命值HP减为0时，此卡可以使得己方的生命值HP变为1，但不可以叠加使用，卡牌命名“先祖之力”。

**游戏规则：**

（1）一般系统每次随机给玩家发两张卡牌，但会受到Buff卡和Debuff卡的影响作相应的改变。

（2）每一回合玩家只能且必须出一张卡牌，每一回合后玩家手中最多能保留三张卡牌，系统会随机去掉多余的卡牌。

（3）当一方的生命值HP先减为0时，并且他未曾打出“先祖之力”这张卡牌又或者这张卡牌已经失效，则被判为输，游戏结束。
