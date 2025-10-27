abstract public class AI extends Player
{
    public AI(int go, String name)
    {
        super(go, name);
    }
    final void turnOne(Handstate handstate)
    {
        if (money > 0)
        {
            if ((handstate.getHand() > 1 || handstate.getValue() >= 10) && !raised)
            {
                outcome = Actions.RAISES;
                raised = true;
            }
            else
            {
                outcome = Actions.CALLS;
            }
            if (go == 2 && !dealt) {
                outcome = Actions.SMALL_BLINDS;
                dealt = true;
            }
            if (go == 3 && !dealt) {
                outcome = Actions.BIG_BLINDS;
                dealt = true;
            }
        }
    }
}
