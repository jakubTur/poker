public class player {
    int money = 2000;
    int table = 0;
    public int[] wartoscigracza;
    public String[] kolorygracza;
    public int tura=1;
    public int kolej;
    public int kolejover;
    String outcome;
    String player;
    int[] wartosci;
    String[]kolory;
    int[] showwartosci;
    String[]showkolory;
    public Boolean moved = false;
    public  Boolean dealt = false;
    public  Boolean raised = false;
    Boolean folded = false;
    public player(int kolejka, String gracz) {

        kolej = kolejka;
        kolejover = kolejka;
        player = gracz;
    }
public String action(int[] macierz, String[] macierz2)
{
    if (tura == 5) { tura = 1;  outcome  = null; kolej = kolejover;}

    wartoscigracza = macierz;
    kolorygracza = macierz2;

    wartosci = new int[0];
    if (tura == 1) { wartosci = new int[]{wartoscigracza[0], wartoscigracza[1]}; showwartosci = new int[]{wartoscigracza[0], wartoscigracza[1]};}
    if (tura == 2) { wartosci = new int[]{wartoscigracza[0], wartoscigracza[1], wartoscigracza[2], wartoscigracza[3], wartoscigracza[4]}; }
    if (tura == 3) { wartosci = new int[]{wartoscigracza[0], wartoscigracza[1], wartoscigracza[2], wartoscigracza[3], wartoscigracza[4], wartoscigracza[5]}; }
    if (tura == 4) { wartosci = new int[]{wartoscigracza[0], wartoscigracza[1], wartoscigracza[2], wartoscigracza[3], wartoscigracza[4], wartoscigracza[5], wartoscigracza[6]}; }

    kolory = new String[0];
    if (tura == 1) { kolory = new String[]{kolorygracza[0], kolorygracza[1]}; showkolory = new String[]{kolorygracza[0], kolorygracza[1]};}
    if (tura == 2) { kolory = new String[]{kolorygracza[0], kolorygracza[1], kolorygracza[2], kolorygracza[3], kolorygracza[4]}; }
    if (tura == 3) { kolory = new String[]{kolorygracza[0], kolorygracza[1], kolorygracza[2], kolorygracza[3], kolorygracza[4], kolorygracza[5]}; }
    if (tura == 4) {kolory = new String[]{kolorygracza[0], kolorygracza[1], kolorygracza[2], kolorygracza[3], kolorygracza[4], kolorygracza[5], kolorygracza[6]};}

    handstate reka = new handstate(wartosci, kolory);

    if(money<=0) { outcome = "folds"; }
    if (kolej == 1) { outcome = "deals"; dealt = true; }
    if (kolej != 1&&!folded)
    {
        if(player.equals("kid"))
        {
        if (tura == 4) {
            if (money > 0) {
                if ((reka.wskaznik == 7 || reka.wskaznik == 8) && !raised) {
                    outcome = "raises";raised = true;

                } else {
                        outcome = "calls";
                }
            }
        }
        if (tura == 3) {
            if (money > 0) {
                if (((reka.wskaznik == 4) || reka.wskaznik == 7 || reka.wskaznik == 8)&&!raised) {
                    outcome = "raises";raised = true;

                } else {
                    if (reka.wskaznik == 2 || reka.wskaznik == 3||reka.wskaznik == 4 || reka.wskaznik == 7 || reka.wskaznik == 8) {
                        outcome = "calls";

                    } else {
                        outcome = "folds";

                    }
                }
            }

        }
        if (tura == 2) {
            if (money > 0) {
                if (((reka.wskaznik > 2 && reka.wskaznik < 5) || reka.wskaznik == 7 || reka.wskaznik == 8)&&!raised) {
                    outcome = "raises";raised = true;

                } else {
                    if ((reka.wskaznik >= 2 && reka.wskaznik <= 5) || reka.wskaznik == 7 || reka.wskaznik == 8 || (reka.wskaznik == 1 && reka.wartosc >= 10)) {
                        outcome = "calls";
                    } else {
                        outcome = "folds";
                    }
                }
            }
        }
        if (tura == 1)
        {
            if (money > 0)
            {
                if ((reka.wskaznik > 1 || reka.wartosc >= 10)&&!raised)
                {
                    outcome = "raises";raised = true;

                } else
                {
                    outcome = "calls";

                }
                if(kolej==2 && !dealt && tura==1) { outcome = "small blinds"; dealt = true; }
                if(kolej==3 && !dealt && tura==1) { outcome = "big blinds";  dealt = true; }

            }
        }
        }
        if(player.equals("pro"))
        {
            if (tura == 4) {
                if (money > 0) {
                    if (reka.wskaznik >=7&& !raised) {
                        outcome = "raises";raised = true;

                    } else {
                            outcome = "calls";
                    }
                }
            }
            if (tura == 3) {
                if (money > 0) {
                    if (reka.wskaznik >=4&& !raised) {
                        outcome = "raises";raised = true;

                    } else {
                        if (reka.wskaznik == 2 || reka.wskaznik == 3) {
                            outcome = "calls";

                        } else {
                            outcome = "folds";
                        }
                    }
                }

            }
            if (tura == 2) {
                if (money > 0) {
                    if (reka.wskaznik >=4&& !raised) {
                        outcome = "raises";raised = true;

                    } else {
                        if (reka.wskaznik >2) {
                            outcome = "calls";

                        } else {
                            outcome = "folds";

                        }
                    }
                }
            }
            if (tura == 1)
            {
                if (money > 0)
                {
                    if ((reka.wskaznik > 1 || reka.wartosc >= 10) &&!raised)
                    {
                        outcome = "raises";raised = true;

                    } else
                    {
                        outcome = "calls";

                    }
                    if(kolej==2 && !dealt && tura==1) { outcome = "small blinds";  dealt=true;}
                    if(kolej==3 && !dealt && tura==1) { outcome = "big blinds"; dealt=true;}

                }
            }
        }
        if(player.equals("oldman"))
        {
            if (tura == 4)
            {
                if (money > 0)
                {
                    if ((reka.pokertemp == 4||reka.wskaznik==10)&&!raised)
                    {
                        outcome = "raises";raised = true;
                    }
                    else
                    {
                            outcome = "calls";
                    }
                }

            }
            if (tura == 3)
            {
                if (money > 0)
                {
                    if (reka.pokertemp>=4&&!raised)
                    {
                        outcome = "raises";raised = true;
                    }
                    else
                    {
                            outcome = "calls";
                    }
                }
            }
            if (tura == 2)
            {
                if (money > 0)
                {
                    if ((reka.pokertemp>=3  || reka.strittemp>=3 || reka.wskaznik == 7)&&!raised)
                    {
                        outcome = "raises";raised = true;
                    }
                    else
                    {
                        if (reka.pokertemp>=1 || reka.strittemp>=2)
                        {
                            outcome = "calls";
                        }
                        else
                        {
                            outcome = "folds";
                        }
                    }
                }
            }
            if (tura == 1)
            {
                if (money > 0)
                {
                    if ((reka.strittemp==1 || reka.pokertemp == 1)&&!raised)
                    {
                        outcome = "raises"; raised = true;
                    } else
                    {
                        outcome = "calls";
                    }
                    if(kolej==2 && !dealt && tura==1) { outcome = "small blinds"; dealt=true;}
                    if(kolej==3 && !dealt && tura==1) { outcome = "big blinds"; dealt=true;}
                }
            }
        }
    } else if (folded) { outcome = "folds";

    }
    if(outcome == null) { outcome = "calls"; }
    kolej = kolej+4;
    return outcome;
}

void koniectury() {tura++; dealt = false; raised = false; table = 0; kolej=kolejover;}
}
