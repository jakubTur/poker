public class Pro extends Player {
    public Pro(int kolejka) {
        super(kolejka);
        name="pro";
    }
    @Override
    public Actions action() {
        checkTurn();
        updateHand();
        if(money<=0) { outcome = Actions.FOLDS; }
        if (go == 1) { outcome = Actions.DEALS; dealt = true; }
        int[] ranks = visible_hand.getRanks();
        Suits[] suits = visible_hand.getSuits();
        Handstate handstate = new Handstate(ranks, suits);
        if (go != 1&&!folded) {
            if (turn == 4) {
                if (money > 0) {
                    if (handstate.hand >= 7 && !raised) {
                        outcome = Actions.RAISES;
                        raised = true;
                    } else {
                        outcome = Actions.CALLS;
                    }
                }
            }
            if (turn == 3) {
                if (money > 0) {
                    if (handstate.hand >= 4 && !raised) {
                        outcome = Actions.RAISES;
                        raised = true;
                    } else {
                        if (handstate.hand == 2 || handstate.hand == 3) {
                            outcome = Actions.CALLS;
                        } else {
                            outcome = Actions.FOLDS;
                        }
                    }
                }
            }
            if (turn == 2) {
                if (money > 0) {
                    if (handstate.hand >= 4 && !raised) {
                        outcome = Actions.RAISES;
                        raised = true;
                    } else {
                        if (handstate.hand > 2) {
                            outcome = Actions.CALLS;
                        } else {
                            outcome = Actions.FOLDS;

                        }
                    }
                }
            }
            if (turn == 1) {
                if (money > 0) {
                    if ((handstate.hand > 1 || handstate.value >= 10) && !raised) {
                        outcome = Actions.RAISES;
                        raised = true;
                    } else {
                        outcome = Actions.CALLS;
                    }
                    if (go == 2 && !dealt && turn == 1) {
                        outcome = Actions.SMALL_BLINDS;
                        dealt = true;
                    }
                    if (go == 3 && !dealt && turn == 1) {
                        outcome = Actions.BIG_BLINDS;
                        dealt = true;
                    }
                }
            }
        }
        else if (folded) { outcome = Actions.FOLDS; }
        if(outcome == null) { outcome = Actions.CALLS; }
        go = go +4;
        return outcome;
    }
}
