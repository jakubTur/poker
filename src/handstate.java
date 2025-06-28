//https://pl.wikipedia.org/wiki/Texas_Hold%E2%80%99em
public class handstate
{
    int wartosc;
    int wskaznik;//1-10
    String[] podkolor = {"d", "s", "c", "h"};
    int highest;
    int pokertemp=0;
    int strittemp=0;
    public handstate (int[] wartosci, String[] kolory)
    {
        int l = wartosci.length;
        for (int i = 0; i < l - 1; i++)
            for (int j = 0; j < l - i - 1; j++)
                if (wartosci[j] > wartosci[j + 1]) {

                    int m = wartosci[j];
                    String n = kolory[j];
                    wartosci[j] = wartosci[j + 1];
                    kolory[j] = kolory[j+1];
                    wartosci[j + 1] = m;
                    kolory[j+1] = n;
                }
        int para = 0;

        wartosc = wartosci[l-1];
        highest = wartosci[l-2];
        wskaznik = 1;///1
        boolean trojka = false;
        for(int p = 0;p<=l-2;p++)
        {
            if(wartosci[p]==wartosci[p+1])
            {
                for(int h = 0; h<=p-1;h++)
                { if(wartosci[h]!=wartosci[p]) { highest = wartosci[h]; } }
                if (p==l-2||wartosci[p+1]!=wartosci[p+2])
                {
                    para++;
                    if (wskaznik<3 && para <3) { wskaznik = 2; } ///2+3
                    wartosc = wartosci[p];
                }
                else
                {
                    wskaznik = 4; ///4
                    trojka = true;

                    if(p!=l-3 && wartosci[p+2]==wartosci[p+3])
                    {
                        wskaznik = 8;///8

                    }
                }
                if(para==2&&wskaznik<3) { wskaznik = 3; wartosc = wartosci[p]; }
            }
            if (trojka && para>1 && wskaznik<7)
            {
                wskaznik = 7;///7
            }
        }
        int kolor = 0;
        if(wskaznik<6)//6
        {
            for(int b =0;b<=3;b++)
            {
                for (int a = 0;a<=l-1;a++)
                {
                    if(kolory[a].equals(podkolor[b])) { kolor++; }
                    if(kolor>=5) { wskaznik = 6; b=15; wartosc = wartosci[a];a=l+1; highest = wartosc;
                    }

                }
                kolor=0;
            }
        }
        int poker = 0;
        int strit = 0;
        for(int jajo=0;jajo<=l-2;jajo++)
        {
            if(wartosci[jajo]+1==wartosci[jajo+1])
            {
                if(kolory[jajo].equals(kolory[jajo+1]))
                {
                    poker++;
                    pokertemp = poker;
                }
                else { poker = 0; }
                strit++;
                strittemp=strit;
            }
            else { strit = 0;  }
        }
        if(poker>=4) ///9+10
        {
            wskaznik = 9;
            if(wartosci[l-1]==14 || wartosci[l-2]==14 || wartosci[l-3]==14 || wartosci[l-4]==14)
            {
                wskaznik = 10;
            }
        }
        if(strit>=4 && wskaznik<5) { wskaznik = 5; }///5
        }
    }

