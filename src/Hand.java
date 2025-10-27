public class Hand {
    private final Card[] cards;
    private final Suits[] suits;
    private final int[] ranks;
    public Hand(Card[] cards)
    {
        this.cards = cards;
        suits = new Suits[cards.length];
        ranks = new int[cards.length];
        for(int i = 0; i<cards.length; i++)
        {
            suits[i] = cards[i].getSuit();
            ranks[i] = cards[i].getRank();
        }
    }
    public Suits[] getSuits()
    {
        return suits;
    }
    public int[] getRanks()
    {
        return ranks;
    }
    public Card[] getCards()
    {
        return cards;
    }
}
