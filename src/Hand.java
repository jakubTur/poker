public class Hand {
    Card[] cards;
    Suits[] suits;
    int[] ranks;
    public Hand(Card[] cards)
    {
        this.cards=cards;
        suits=new Suits[cards.length];
        ranks=new int[cards.length];
        for(int i=0;i<cards.length;i++)
        {
            suits[i]=cards[i].suit;
            ranks[i]=cards[i].rank;
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
