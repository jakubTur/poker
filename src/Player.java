import javax.swing.*;

public class Player {
    String name;
    int money;
    int table;
    Hand[] hands;
    Hand visible_hand;
    int turn;
    int go;
    Actions outcome;
    Boolean active;
    Boolean dealt;
    Boolean raised;
    Boolean folded;
    public Player(int go, String name)
    {
        money = 2000;
        table = 0;
        turn = 1;
        active = false;
        dealt = false;
        raised = false;
        folded = false;
        this.go = go;
        outcome = Actions.NONE;
        this.name = name;
    }
    public void buttons(JButton[] actionButtons, int max, boolean check_possible, boolean call) { }
    public void setHands(Hand hand)
    {
        hands = new Hand[4];
        Card[] cards = hand.getCards();
        visible_hand = new Hand(new Card[]{cards[0], cards[1]});
        hands[0] = visible_hand;
        hands[1] = new Hand(new Card[]{cards[0], cards[1], cards[1], cards[2], cards[4]});
        hands[2] = new Hand(new Card[]{cards[0], cards[1], cards[1], cards[2], cards[4], cards[5]});
        hands[3] = hand;
    }
    void updateHand()
    {
        visible_hand = hands[turn-1];
    }
    void checkTurn(int playerAmount)
    {
        if (turn == playerAmount) { turn = 1;  outcome = Actions.NONE; }
    }
    public Actions action()
    {
        return outcome;
    }
    public Actions getOutcome()
    {
        return outcome;
    }

    void endOfTurn() {
        turn++;
        if(go == 1)
        {
            dealt = false;
        }
        raised = false;
        table = 0;
        if(outcome != Actions.FOLDS && outcome != Actions.DEALS) { outcome = Actions.NONE; }
    }
}
