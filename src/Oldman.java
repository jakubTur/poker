public class Oldman extends AI
{
    public Oldman(int kolejka, String name) {
        super(kolejka, name);
    }

    @Override
    public Actions action() {
        if(money<=0) { outcome = Actions.FOLDS; }
        int[] ranks = visible_hand.getRanks();
        Suits[] suits = visible_hand.getSuits();
        Handstate handstate = new Handstate(ranks, suits);
        if (go == 1 && !dealt) { outcome = Actions.DEALS; dealt = true; }
        else if (!folded)
        {
            if (turn == 4) {
                if (money > 0) {
                    if ((handstate.poker_temp == 4 || handstate.hand == 10) && !raised) {
                        outcome = Actions.RAISES;
                        raised = true;
                    } else {
                        outcome = Actions.CALLS;
                    }
                }
            }
            if (turn == 3) {
                if (money > 0) {
                    if (handstate.poker_temp >= 4 && !raised) {
                        outcome = Actions.RAISES;
                        raised = true;
                    } else {
                        outcome = Actions.CALLS;
                    }
                }
            }
            if (turn == 2) {
                if (money > 0) {
                    if ((handstate.poker_temp >= 3 || handstate.straight_temp >= 3 || handstate.hand == 7) && !raised) {
                        outcome = Actions.RAISES;
                        raised = true;
                    } else {
                        if (handstate.poker_temp >= 1 || handstate.straight_temp >= 2) {
                            outcome = Actions.CALLS;
                        } else {
                            outcome = Actions.FOLDS;
                        }
                    }
                }
            }
            if (turn == 1) {
                turnOne(handstate);
            }
        } else { outcome = Actions.FOLDS; }
        if(outcome == null) { outcome = Actions.CALLS; }
        return outcome;
    }
}
