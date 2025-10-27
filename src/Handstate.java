//https://pl.wikipedia.org/wiki/Texas_Hold%E2%80%99em
public class Handstate //used to calculate the value of the hand
{
    int value;
    int hand;//1-10
    final Suits[] sub_suit = { Suits.DIAMONDS, Suits.SPADES, Suits.CLUBS, Suits.HEARTS };
    int highest;
    int poker_temp;
    int straight_temp;
    public Handstate (int[] values, Suits[] suits)
    {
        int l = values.length;
        poker_temp = 0;
        straight_temp = 0;
        for(int i = 0; i < l - 1; i++)
        {
            for (int j = 0; j < l - i - 1; j++)
            {
                if (values[j] > values[j + 1])
                {
                    int m = values[j];
                    Suits n = suits[j];
                    values[j] = values[j + 1];
                    suits[j] = suits[j + 1];
                    values[j + 1] = m;
                    suits[j + 1] = n;
                }
            }
        }
        int pair = 0;
        value = values[l - 1];
        highest = values[l - 2];
        hand = 1;///1
        boolean three = false;
        for(int i = 0; i <= l - 2; i++)
        {
            if(values[i] == values[i + 1])
            {
                for(int h = 0; h <= i - 1; h++)
                { if(values[h] != values[i]) { highest = values[h]; } }
                if (i == l - 2 || values[i + 1] != values[i + 2])
                {
                    pair++;
                    if (hand < 3 && pair < 3) { hand = 2; } ///2+3
                    value = values[i];
                }
                else
                {
                    hand = 4; ///4
                    three = true;

                    if(i != l - 3 && values[i + 2] == values[i + 3])
                    {
                        hand = 8;///8

                    }
                }
                if(pair == 2 && hand < 3) { hand = 3; value = values[i]; }
            }
            if (three && pair > 1 && hand < 7)
            {
                hand = 7;///7
            }
        }
        int colour = 0;
        if(hand < 6)//6
        {
            for(int i = 0; i <= 3; i++)
            {
                for (int a = 0; a <= l - 1; a++)
                {
                    if(suits[a].equals(sub_suit[i])) { colour++; }
                    if(colour >= 5)
                    {
                        hand = 6; i = 15; value = values[a]; a = l + 1; highest = value;
                    }

                }
                colour = 0;
            }
        }
        int poker = 0;
        int strit = 0;
        for(int i = 0; i <= l - 2; i++)
        {
            if(values[i] + 1 == values[i + 1])
            {
                if(suits[i].equals(suits[i + 1]))
                {
                    poker++;
                    poker_temp = poker;
                }
                else { poker = 0; }
                strit++;
                straight_temp = strit;
            }
            else { strit = 0;  }
        }
        if(poker >= 4) ///9+10
        {
            hand = 9;
            if(values[l - 1] == 14 || values[l - 2] == 14 || values[l - 3] == 14 || values[l - 4] == 14)
            {
                hand = 10;
            }
        }
        if(strit >= 4 && hand < 5) { hand = 5; }///5
        }
    }

