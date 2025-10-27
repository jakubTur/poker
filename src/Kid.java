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
        if (go == 1 && !dealt) { outcome = Actions.DEALS; dealt = true; }
        else if (!folded)
        {
            if (turn == 4)
            {
                if (money > 0)
                {
                    if ((handstate.hand == 7 || handstate.hand == 8) && !raised)
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
                    if (((handstate.hand == 4) || handstate.hand == 7 || handstate.hand == 8)&&!raised)
                    {
                        outcome = Actions.RAISES;raised = true;
                    }
                    else
                    {
                            if (handstate.hand == 2 || handstate.hand == 3||handstate.hand == 4 || handstate.hand == 7 || handstate.hand == 8) {
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
                    if (((handstate.hand > 2 && handstate.hand < 5) || handstate.hand == 7 || handstate.hand == 8)&&!raised)
                    {
                        outcome = Actions.RAISES; raised = true;
                    }
                    else
                    {
                        if ((handstate.hand >= 2 && handstate.hand <= 5) || handstate.hand == 7 || handstate.hand == 8 || (handstate.hand == 1 && handstate.value >= 10))
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
