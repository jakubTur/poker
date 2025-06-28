
import java.util.Random;

public class carddraw {

    int[] allcards = new int[13];
    int[] common = new int[3];
    int[] turn = new int[4];
    int[] river = new int[5];
    String[] kolorallcards = new String[13];
    String[] kolorcommon = new String[3];
    String[] kolorturn = new String[4];
    String[] kolorriver = new String[5];

    int[] handuser = new int[7];
    int[] handpro = new int[7];
    int[] handkid = new int[7];
    int[] handoldman = new int[7];
     String[] koloruser = new String[7];
    String[] kolorpro = new String[7];
    String[] kolorkid = new String[7];
    String[] koloroldman = new String[7];


public carddraw()
{
    for(int i=1;i<=13;i++)
    {
        Random losowa=new Random();
        int numerkoloru=losowa.nextInt(4);
        int numerkarty=losowa.nextInt(13);
        kolor karta=new kolor(numerkoloru,numerkarty);
        allcards[i-1]=karta.karta;
        kolorallcards[i-1]=karta.kolor;
        if(i<=3)
        {
            common[i-1]=karta.karta;
            kolorcommon[i-1]=karta.kolor;
        }
        if(i<=4)
        {
            turn[i-1]=karta.karta;
            kolorturn[i-1]=karta.kolor;
        }
        if(i<=5)
        {
            river[i-1]=handuser[i+1]=handkid[i+1]=handpro[i+1]=handoldman[i+1]=karta.karta;
            kolorriver[i-1]=koloruser[i+1]=kolorkid[i+1]=koloroldman[i+1]=kolorpro[i+1]=karta.kolor;
        }
        if(i==6||i==7)
        {
            handuser[i-6]=karta.karta;
            koloruser[i-6]=karta.kolor;
        }
        if(i==8||i==9)
        {
            handkid[i-8]=karta.karta;
            kolorkid[i-8]=karta.kolor;
        }
        if(i==10||i==11)
        {
            handpro[i-10]=karta.karta;
            kolorpro[i-10]=karta.kolor;
        }
        if(i==12||i==13)
        {
            handoldman[i-12]=karta.karta;
            koloroldman[i-12]=karta.kolor;
        }
        for(int j=i-2;j>=0;j--)
        {
            if(karta.karta==allcards[j]&&karta.kolor.equals(kolorallcards[j])){
            i--;
        }
        }
    }
}
}