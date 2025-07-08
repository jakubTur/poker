//https://pl.wikipedia.org/wiki/Texas_Hold%E2%80%99em
public class Handstate
{
    int value;
    int hand;//1-10
    Suits[] sub_suit = {Suits.DIAMONDS, Suits.SPADES, Suits.CLUBS, Suits.HEARTS};
    int highest=0;
    int poker_temp=0;
    int straight_temp=0;
    public Handstate()
    {
        value=0;
        hand=0;
    }
    public Handstate (int[] values, Suits[] suits)
    {
        int l = values.length;
        for (int j = 0; j < l; j++)
        {
            if (values[j] > values[j + 1]) {
                int m = values[j];
                Suits n = suits[j];
                values[j] = values[j + 1];
                suits[j] = suits[j+1];
                values[j + 1] = m;
                suits[j+1] = n;
            }
        }
        int para = 0;
        value = values[l-1];
        highest = values[l-2];
        hand = 1;///1
        boolean trojka = false;
        for(int p = 0;p<=l-2;p++)
        {
            if(values[p]==values[p+1])
            {
                for(int h = 0; h<=p-1;h++)
                { if(values[h]!=values[p]) { highest = values[h]; } }
                if (p==l-2||values[p+1]!=values[p+2])
                {
                    para++;
                    if (hand <3 && para <3) { hand = 2; } ///2+3
                    value = values[p];
                }
                else
                {
                    hand = 4; ///4
                    trojka = true;

                    if(p!=l-3 && values[p+2]==values[p+3])
                    {
                        hand = 8;///8

                    }
                }
                if(para==2&& hand <3) { hand = 3; value = values[p]; }
            }
            if (trojka && para>1 && hand <7)
            {
                hand = 7;///7
            }
        }
        int kolor = 0;
        if(hand <6)//6
        {
            for(int b =0;b<=3;b++)
            {
                for (int a = 0;a<=l-1;a++)
                {
                    if(suits[a].equals(sub_suit[b])) { kolor++; }
                    if(kolor>=5) { hand = 6; b=15; value = values[a];a=l+1; highest = value;
                    }

                }
                kolor=0;
            }
        }
        int poker = 0;
        int strit = 0;
        for(int jajo=0;jajo<=l-2;jajo++)
        {
            if(values[jajo]+1==values[jajo+1])
            {
                if(suits[jajo].equals(suits[jajo+1]))
                {
                    poker++;
                    poker_temp = poker;
                }
                else { poker = 0; }
                strit++;
                straight_temp =strit;
            }
            else { strit = 0;  }
        }
        if(poker>=4) ///9+10
        {
            hand = 9;
            if(values[l-1]==14 || values[l-2]==14 || values[l-3]==14 || values[l-4]==14)
            {
                hand = 10;
            }
        }
        if(strit>=4 && hand <5) { hand = 5; }///5
        }
    }

