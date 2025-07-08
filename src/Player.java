public class Player {
    String name;
    int money = 2000;
    int table = 0;
    Hand[] hands;
    Hand visible_hand;
    int turn=1;
    int go;
    int go_over;
    Actions outcome;

    Boolean moved = false;
    Boolean dealt = false;
    Boolean raised = false;
    Boolean folded = false;
    public Player(int kolejka) {

        go = kolejka;
        go_over = kolejka;
        outcome=Actions.NONE;
    }
    public void setHands(Hand hand)
    {
        hands = new Hand[4];
        Card[] cards=hand.getCards();
        visible_hand= new Hand(new Card[]{cards[0], cards[1]});
        hands[0]=visible_hand;
        hands[1]=new Hand(new Card[]{cards[0], cards[1], cards[1], cards[2], cards[4]});
        hands[2]=new Hand(new Card[]{cards[0], cards[1], cards[1], cards[2], cards[4], cards[5]});
        hands[3]=hand;
    }
    void updateHand()
    {
        int turn=this.turn;
        switch (turn)
        {
            case 1:
                visible_hand=hands[0];
                break;
            case 2:
                visible_hand=hands[1];
                break;
            case 3:
                visible_hand=hands[2];
                break;
            case 4:
                visible_hand=hands[3];
                break;
        }
    }
    void checkTurn()
    {
        if (turn == 5) { turn = 1;  outcome  = null; go = go_over;}
    }
    public Actions action()
    {
        return outcome;
    }

    void koniectury() {
        turn++; dealt = false; raised = false; table = 0; go = go_over;}
    }
