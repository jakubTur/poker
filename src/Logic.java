import javax.swing.*;
import java.util.*;
import java.lang.Thread;
import java.util.List;

public class Logic {
    Interface ui;
    int pot;
    Card[] deck;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    public Logic(Player p1, Player p2, Player p3, Player p4, Interface main)
    {
        player1 = p1;
        player2 = p2;
        player3 = p3;
        player4 = p4;
        pot = 0;
        ui = main;
        Card[] deck = new Card[52]; //deck creation
        Suits[] suits = { Suits.DIAMONDS, Suits.SPADES, Suits.CLUBS, Suits.HEARTS };
        int counter=0;
        for(int i = 0; i < 4; i++)
        {
            for(int j = 2; j < 15; j++)
            {
                deck[counter] = new Card(suits[i], j);
                counter++;
            }
        }
        this.deck = deck; //
    }
    boolean notReady(ArrayList<Player> players) //just for the gameplay while loop
    {
        for(Player player : players)
        {
            if(player.outcome!=Actions.FOLDS && player.outcome!=Actions.CHECKS && player.outcome!=Actions.CALLS)
            {
                return true;
            }
        }
        return false;
    }
    boolean canCall(ArrayList<Player> players, Player user)
    {
        for(Player player : players)
        {
            if(player.table > user.table)
            {
                return true;
            }
        }
        return false;
    }
    boolean canCheck(ArrayList<Player> players, Player user)
    {
        for(Player player : players)
        {
            if(player.outcome != Actions.FOLDS && player.table > user.table)
            {
                return false;
            }
        }
        return true;
    }

    Player getWinner()
    {
        Handstate p1 = new Handstate(player1.hands[3].getRanks(), player1.hands[3].getSuits());
        Handstate p2 = new Handstate(player2.hands[3].getRanks(), player2.hands[3].getSuits());
        Handstate p3 = new Handstate(player3.hands[3].getRanks(), player3.hands[3].getSuits());
        Handstate p4 = new Handstate(player4.hands[3].getRanks(), player4.hands[3].getSuits());
        int[] winki = { p1.hand, p2.hand, p3.hand, p4.hand };
        int[] remisy = { p1.value, p2.value, p3.value, p4.value };
        int[] remisy2 = { p1.value, p2.value, p3.value, p4.value };
        for (int i = 0; i < winki.length - 1; i++)
        {
            for (int j = 0; j < winki.length - 1 - i; j++)
            {
                if (winki[j] < winki[j + 1])
                {
                    int temp3 = winki[j];
                    int temp2 = remisy[j];
                    winki[j] = winki[j + 1];
                    winki[j + 1] = temp3;
                    remisy[j] = remisy[j+1];
                    remisy[j+1] = temp2;
                }
            }
        }
        Player winner = null;
        for(int g = 0; g <= 3; g++)
        {
            if (winki[g] == p1.hand && (player1.outcome != Actions.FOLDS))
            {
                player1.money = player1.money + pot;
                winner = player1;
            } else if (winki[g] == p2.hand && (player2.outcome != Actions.FOLDS))
            {
                player2.money = player2.money + pot;
                winner = player2;
            } else if (winki[g] == p3.hand && (player3.outcome != Actions.FOLDS))
            {
                player3.money = player3.money + pot;
                winner = player3;
            } else if (winki[g] == p4.hand && (player4.outcome != Actions.FOLDS))
            {
                player4.money = player4.money + pot;
                winner = player4;
            }
            if(winner != null) { break; }
        }
        if(winki[1] == winki[0])
        {
            int lol = 1;
            if(winki[1] == winki[2]) { lol++; if(winki[2] == winki[3]) { lol++; } }
            for(int p = 0; p <= lol; p++)
            {
                if(remisy[0] == remisy2[p])
                {
                    if(remisy2[p] > remisy[1])
                    {
                        if(p == 0 && !(player1.outcome==Actions.FOLDS)) { player1.money = player1.money + pot;
                            winner = player1; }
                        if(p==1&&!(player2.outcome==Actions.FOLDS)) { player2.money = player2.money + pot;
                            winner = player2; }
                        if(p==2&&!(player3.outcome==Actions.FOLDS)) { player3.money = player3.money + pot;
                            winner = player3; }
                        if(p==3&&!(player4.outcome==Actions.FOLDS)) { player4.money = player4.money + pot;
                            winner = player4; }
                    }
                }
            }
        }
        return winner;
    }
    void play(Interface ui) throws InterruptedException
    {
        ArrayList<Player> active_players = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));
        List<Card> deck_list = Arrays.asList(deck);
        Card[] hand1 = new Card[7];
        Card[] hand2 = new Card[7];
        Card[] hand3 = new Card[7];
        Card[] hand4 = new Card[7];
        Card[][] allhands = { hand1, hand2, hand3, hand4 };
        while(player1.money>=0)
        {
            Collections.shuffle(deck_list);
            deck = deck_list.toArray(new Card[0]);
            for(Card[] cards : allhands)
            {
                System.arraycopy(deck, 2, cards, 2, 5);
            }
            for(int i=0;i<2;i++)
            {
                hand1[i] = deck[i];
                hand2[i] = deck[i+7];
                hand3[i] = deck[i+9];
                hand4[i] = deck[i+11]; //maybe add functions to corresponding players?
            }
            int temp=0;
            for (Player player : active_players)
            {
                player.setHands(new Hand(allhands[temp]));
                temp++;
            }
            ui.updateMoney(player1, player2, player3, player4, pot);
            for (int j = 1; j <= 4; j++)
            {
                while (notReady(active_players))
                {
                    for(Player player : active_players)
                    {
                        if(player.active)
                        {
                            if (player instanceof User)
                            {
                                SwingUtilities.invokeLater(() -> {
                                    player.buttons(ui.action_buttons, largestTable(active_players), canCheck(active_players, player1), canCall(active_players, player1));
                                });
                                ((User) player).waitForInput(); // Wait for user input before continuing
                            }
                            else
                            {
                                player.checkTurn(active_players.size());
                                player.updateHand();
                                player.action();
                                Thread.sleep(2000); //ai "thinks" + time for the user to read the action
                            }
                            inGameOutcome(active_players, player);
                            int index = active_players.indexOf(player) + 1;
                            if(index >= active_players.size())
                            {
                                active_players.getFirst().active = true;
                            }
                            else
                            {
                                active_players.get(index).active = true;
                            }
                        }
                        player.active = false;
                        ui.update_label(player.name + " " + player.outcome);
                        ui.updateMoney(player1, player2, player3, player4, pot);
                        if(!notReady(active_players)) { break; }
                    }
                }
                pot = pot + player1.table + player2.table + player3.table + player4.table;
                ui.pot_label.setText("Pot: " + pot);
                for(Player player : active_players)
                {
                    player.endOfTurn();
                }
                Player active = active_players.getFirst();
                active.active = true;
                if (j != 4)
                {
                    String message = ("End of turn");
                    JFrame framepopup = new JFrame();
                    JOptionPane.showMessageDialog(framepopup, message);
                }
            }
            Player winner = getWinner();

            Iterator<Player> iterator = active_players.iterator();
            while (iterator.hasNext())
            {
                Player player = iterator.next();
                if (player.money <= 0) {
                    player.outcome = Actions.OUT;
                    iterator.remove();
                }
                else
                {
                    player.outcome = Actions.NONE;
                    player.turn = 1;
                    player.folded = false;
                    player.dealt = false;
                    player.raised = false;
                    player.active = false;
                }
            }
            Collections.rotate(active_players, 1);
            active_players.getFirst().active = true;
            int counter = 1;
            for(Player player : active_players)
            {
                player.go = counter;
                counter++;
            }
            pot = 0;
            ui.pot_label.setText("Pot: " + pot);
            ui.showcards(player2, player3, player4);
            ui.repaint();
            ui.revalidate();
            String message;
            if(winner instanceof User)
            {
                message = "You win!";
            }
            else
            {
                message = winner.name + " wins";
            }
            JFrame framepopup = new JFrame();
            JOptionPane.showMessageDialog(framepopup, message);
            SwingUtilities.invokeLater(() ->
            {
                ui.commonPanel.revalidate();
                ui.commonPanel.repaint();
                ui.boardwipe();
            });
            ui.action_label.setText("");
        }
    }
    int largestTable(ArrayList<Player> players)
    {
        int max = 0;
        for (Player p : players)
        {
            if(p.outcome != Actions.FOLDS)
            {
                if(p.table > max)
                {
                    max = p.table;
                }
            }
        }
        return max;
    }
    void inGameOutcome(ArrayList<Player> players, Player player)
    {
        int max = largestTable(players);
        int offset_money = max - player.table;
        if(player.outcome == Actions.CALLS)
        {
            if(max == player.table) { player.outcome = Actions.CHECKS; }
            else
            {
                player.table = player.table + offset_money;
                player.money = player.money - offset_money;
            }
        }
        else if(player.outcome == Actions.FOLDS) { player.folded = true; }
        else if(player.outcome == Actions.RAISES)
        {
            player.table = player.table + 100 + offset_money;
            player.money = player.money - 100 - offset_money;
        }
        else if(player.outcome == Actions.SMALL_BLINDS) { player.money = player.money - 100; player.table = player.table + 100; }
        else if(player.outcome == Actions.BIG_BLINDS) { player.money = player.money - 200; player.table = player.table + 200; }
        else if(player.outcome == Actions.DEALS)
        {
            SwingUtilities.invokeLater(() -> {ui.deal(player1);});
        }
    }
    }
