public class Kid extends AI
{
    public Kid(int kolejka, String name) {
        super(kolejka, name);
    }

    @Override
    public Actions action() {
        if(money <= 0) { outcome = Actions.FOLDS; }
        int[] ranks = visible_hand.getRanks();
        Suits[] suits = visible_hand.getSuits();
        Handstate handstate = new Handstate(ranks, suits);
        int value = handstate.getValue();
        int hand = handstate.getHand();
        if (go == 1 && !dealt) { outcome = Actions.DEALS; dealt = true; }
        else if (!folded)
        {
            if (turn == 4)
            {
                if (money > 0)
                {
                    if ((hand == 7 || hand == 8) && !raised)
                    {
                        outcome = Actions.RAISES;
                        raised = true;
                    }
                    else
                    {
                        outcome = Actions.CALLS;
                    }
                }
            }
            if (turn == 3)
            {
                if (money > 0)
                {
                    if ((hand == 4 || hand == 7 || hand == 8) && !raised)
                    {
                        outcome = Actions.RAISES;raised = true;
                    }
                    else
                    {
                            if (hand == 2 || hand == 3 || hand == 4 || hand == 7 || hand == 8) {
                                outcome = Actions.CALLS;
                            }
                            else
                            {
                                outcome = Actions.FOLDS;
                            }
                        }
                    }

                }
            if (turn == 2)
            {
                if (money > 0)
                {
                    if (((hand > 2 && hand < 5) || hand == 7 || hand == 8) && !raised)
                    {
                        outcome = Actions.RAISES; raised = true;
                    }
                    else
                    {
                        if ((hand >= 2 && hand <= 5) || hand == 7 || hand == 8 || (hand == 1 && value >= 10))
                        {
                            outcome = Actions.CALLS;
                        }
                        else
                        {
                            outcome = Actions.FOLDS;
                        }
                    }
                }
            }
            if (turn == 1)
            {
                turnOne(handstate);
            }
        } else { outcome = Actions.FOLDS; }
        if(outcome == null) { outcome = Actions.CALLS; }
        return outcome;
    }
}
