public class Card
{
 private final Suits suit;
 private final int rank;
    public Card (Suits suit, int rank)
    {
        this.rank = rank;
        this.suit = suit;
    }
    public Suits getSuit()
    {
        return suit;
    }

    public int getRank()
    {
        return rank;
    }

}