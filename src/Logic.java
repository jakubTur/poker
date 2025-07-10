import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Logic {
    int pot;
    Card[] deck;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    public Logic(Player p1, Player p2, Player p3, Player p4)
    {
        player1=p1;
        player2=p2;
        player3=p3;
        player4=p4;
        pot=0;

        Card[] deck = new Card[52]; //deck creation
        Suits[] suits = {Suits.DIAMONDS, Suits.SPADES, Suits.CLUBS, Suits.HEARTS};
        int counter=0;
        for(int i=0;i<4;i++)
        {
            for(int j=2;j<15;j++)
            {
                deck[counter]=new Card(suits[i],j);
                counter++;
            }
        }
        this.deck=deck; //
    }
    boolean call()
    {
        boolean call = false;
        if(player1.table==0) {
            if(Actions.FOLDS==player4.outcome)
            {
                if(Actions.FOLDS==player3.outcome)
                {
                    if(player1.table!=player2.table) { call = true; }
                }
                else { if(player3.table!=player1.table) { call = true; } }
            }
            else { if(player1.table!=player4.table) { call = true; } } }
        return call;
    }
    boolean check()
    {
        boolean check = false;
        if(player1.table!=0) {
            if(Actions.FOLDS==player4.outcome)
            {
                if(Actions.FOLDS==player3.outcome)
                {
                    if(player1.table==player2.table) { check = true; }
                }
                else { if(player3.table==player1.table) { check = true; } }
            }
            else { if(player1.table==player4.table) { check = true; } } }
        return check;
    }
    boolean breakcheck(Actions outcome, int kasa)
    {
        boolean check = false;
        Actions[] outcomes = {player1.outcome, player2.outcome, player3.outcome, player4.outcome};
        int[] money = {player1.table, player2.table, player3.table, player4.table};
        if(kasa!=0&&outcome==Actions.CHECKS) {
            for(int i = 0;i<=3;i++)
            {
                if (outcomes[i]!=Actions.FOLDS)
                {
                    if(money[i]==kasa) {
                        check = true;
                    }
                    else{ check = false; break;}
                }
            } }
        return check;
    }
    void whatsup(Player player)
    {
        int[] tables = {player1.table, player2.table, player3.table, player4.table};
        int max = tables[0];
        for (int i = 1; i < tables.length; i++)
            if (tables[i] > max)
            { max = tables[i]; }
        int bezsens = max-player.table;
        if(player.outcome==Actions.CALLS)
        {
            if(bezsens==0) { player.outcome=Actions.CHECKS; }
            player.table = player.table+bezsens;
            player.money = player.money-bezsens;
        }
        if(player.outcome==Actions.FOLDS) { player.folded=true; }
        if(player.outcome==Actions.RAISES)
        {
            player.table = player.table + 100 + bezsens;
            player.money = player.money-100-bezsens;
        }
        if(player.outcome==Actions.SMALL_BLINDS) { player.money = player.money-100; player.table = player.table + 100; }
        if(player.outcome==Actions.BIG_BLINDS) { player.money = player.money-200; player.table = player.table + 200; }
        if(player.outcome==Actions.DEALS)
        {
            int[] values=player.hands[3].getRanks();
            Suits[] suits=player.hands[3].getSuits();
            JPanel panel5 = new JPanel(new GridLayout(1, 5));
            JPanel panel2 = new JPanel(new GridLayout(1, 2));
            for (int i = 0; i <= 6; i++) {
                String imagePath = "C:/Users/leesz/IdeaProjects/poker/karty/" + values[i] + suits[i] + ".png";
                try {
                    BufferedImage image = ImageIO.read(new File(imagePath));

                    if (player.turn == 1 && (i == 0 || i == 1)) {
                        panel2.add(new JLabel(new ImageIcon(image)));
                    }
                    else if (player.turn == 2 && i <= 4) {
                        panel5.add(new JLabel(new ImageIcon(image)));
                    }
                    else if (player.turn == 3 && i <= 5) {
                        panel5.add(new JLabel(new ImageIcon(image)));
                    }
                    else if (player.turn == 4 && i <= 6) {
                        panel5.add(new JLabel(new ImageIcon(image)));
                    }
                } catch (IOException e) {
                    System.out.println("error: picture was not found");
                }
            }
            if(player.turn==1)
            {
                cardbackkid = cardbacks();
                cardbackpro = cardbacks();
                cardbackoldman = cardbacks();

                player_panel=panel2;
                user_panel.remove(placeholderyuser);
                user_panel.add(player_panel);

                kid_panel.remove(placeholderykid);
                pro_panel.remove(placeholderypro);
                oldman_panel.remove(placeholderyoldman);

                kid_panel.add(cardbackkid);
                pro_panel.add(cardbackpro);
                oldman_panel.add(cardbackoldman);
            }
            if (player.turn == 2) {
                panel5.add(placeholder(2));
            }
            if (player.turn == 3) {
                panel5.add(placeholder(1));
            }
            if(player.turn !=1)
            { commonPanel.remove(common);
                common=panel5;
                commonPanel.add(common);
                commonPanel.add(pot_label);
                commonPanel.revalidate();
                commonPanel.repaint();
            }
        }
    }
    void play()
    {
        deck=createDeck();
        Player[] all_players={player1,player2,player3, player4};
        while(player1.money>=0) {
            List<Card> deckList = Arrays.asList(deck);
            Collections.shuffle(deckList);
            deck = deckList.toArray(new Card[0]);
            Card[] hand1=new Card[7];
            Card[] hand2=new Card[7];
            Card[] hand3=new Card[7];
            Card[] hand4=new Card[7];
            Card[][] allhands={hand1,hand2,hand3,hand4};
            for(int i=0;i<2;i++)
            {
                hand1[i]=deck[i];
                hand2[i]=deck[i+7];
                hand3[i]=deck[i+9];
                hand4[i]=deck[i+11];
            }
            for(Card[] cards : allhands)
            {
                System.arraycopy(deck, 2, cards, 2, 5);
            }
            for(Player player : all_players)
            {
                player.outcome=Actions.NONE;
                player.turn=1;
                player.folded=false;
            }
            player1.setHands(new Hand(allhands[0]));
            player2.setHands(new Hand(allhands[1]));
            player3.setHands(new Hand(allhands[2]));
            player4.setHands(new Hand(allhands[3]));
            player1.go=1;
            player2.go=2;
            player3.go=3;
            player4.go=4;
            moneyuser.setText("Money: " + player1.money);
            tableuser.setText("Table: " + player1.table);
            moneyykid.setText("Money: " + player2.money);
            tablekid.setText("Table: " + player2.table);
            moneyepro.setText("Money: " + player3.money);
            tablepro.setText("Table: " + player3.table);
            moneyoldman.setText("Money: " + player4.money);
            tableoldman.setText("Table: " + player4.table);
            pot_label.setText("Pot: " + pot);
            repaint();
            revalidate();
            for (int j = 1; j <= 4; j++) {
                do {
                    if ((!player1.moved && player4.moved ) || (!player1.moved && player1.go == 1)) {
                        buttons();
                        if (Actions.CHECKS==player1.outcome) {
                            SwingUtilities.invokeLater(() -> {
                                action1.setText("");
                                action2.setText("");
                                action3.setText("");
                            });
                        }
                    }
                    if (breakcheck(player1.outcome, player1.table)) {
                        break;
                    }
                    if(!threefold()) {
                        if ((!player2.moved && player1.moved) || (!player2.moved && player2.go == 1)) {
                            player2.action();
                            if(player1.folded&&(player2.outcome==Actions.CALLS||player2.outcome==Actions.SMALL_BLINDS)&&!player2.raised)
                            {
                                player2.outcome = Actions.RAISES;
                                player2.raised = true;
                            }
                            whatsup(player2);
                            SwingUtilities.invokeLater(() -> {
                                action3.setText(player2.name + " " + player2.outcome);
                                moneyykid.setText("Money: " + player2.money);
                                tablekid.setText("Table: " + player2.table);
                                if (player2.outcome==Actions.CHECKS) {
                                    action1.setText("");
                                    action2.setText("");
                                }
                            });
                            player2.moved = true;
                            player1.moved = false;
                        }
                        if (breakcheck(player2.outcome, player2.table)) {
                            break;
                        }
                        if ((!player3.moved && player2.moved) || (!player3.moved && player3.go == 1)) {
                            player3.action();
                            if(player1.folded&&(player3.outcome==Actions.CALLS||player2.outcome==Actions.BIG_BLINDS)&&!player3.raised&&!player2.raised)
                            {
                                player2.outcome =Actions.RAISES;
                                player3.raised = true;
                            }
                            whatsup(player3);
                            SwingUtilities.invokeLater(() -> {
                                action2.setText(player3.name + " " + player3.outcome);
                                moneyepro.setText("Money: " + player3.money);
                                tablepro.setText("Table: " + player3.table);
                                if (player3.outcome==Actions.CHECKS) {
                                    action1.setText("");
                                }
                            });
                            player3.moved = true;
                            player2.moved = false;
                        }
                        if (breakcheck(player3.outcome, player3.table)) {
                            break;
                        }
                        if ((!player4.moved && player3.moved) || (!player4.moved && player4.go == 1)) {
                            player4.action();
                            whatsup(player4);
                            SwingUtilities.invokeLater(() -> {
                                action1.setText(player4.name + " " + player4.outcome);
                                moneyoldman.setText("Money: " + player4.money);
                                tableoldman.setText("Table: " + player4.table);
                            });
                            player4.moved = true;
                            player3.moved = false;
                        }
                        if (breakcheck(player4.outcome, player4.table)) {
                            break;
                        }
                    }
                    else { break; }
                } while (player1.table >= 0);
                pot = pot + player1.table + player2.table + player3.table + player4.table;
                pot_label.setText("Pot: " + pot);
                koniectury();
                if (j != 4 && !threefold()) {
                    String message = ("End of turn");
                    JFrame framepopup = new JFrame();
                    JOptionPane.showMessageDialog(framepopup, message);
                }
            }
            Handstate p1 = new Handstate(player1.hands[3].getRanks(), player1.hands[3].getSuits());
            Handstate p2 = new Handstate(player2.hands[3].getRanks(), player2.hands[3].getSuits());
            Handstate p3 = new Handstate(player3.hands[3].getRanks(), player3.hands[3].getSuits());
            Handstate p4 = new Handstate(player4.hands[3].getRanks(), player4.hands[3].getSuits());
            int[] winki = {p1.hand, p2.hand, p3.hand, p4.hand};
            int[] remisy = {p1.value, p2.value, p3.value, p4.value};
            int[] remisy2 = {p1.value, p2.value, p3.value, p4.value};
            for (int i = 0; i < winki.length - 1; i++) {
                for (int j = 0; j < winki.length - 1 - i; j++) {
                    if (winki[j] < winki[j + 1]) {
                        int temp = winki[j];
                        int temp2 = remisy[j];
                        winki[j] = winki[j + 1];
                        winki[j + 1] = temp;
                        remisy[j] = remisy[j+1];
                        remisy[j+1] = temp2;
                    }
                }
            }
            String winner = null;
            for(int g = 0;g<=3;g++)
            {
                if (winki[g] == p1.hand && (player1.outcome!=Actions.FOLDS)) {
                    player1.money = player1.money + pot;
                    winner = player1.name;
                } else if (winki[g] == p2.hand && (player2.outcome!=Actions.FOLDS)) {
                    player2.money = player2.money + pot;
                    winner = player2.name;
                } else if (winki[g] == p3.hand && (player3.outcome!=Actions.FOLDS)) {
                    player3.money = player3.money + pot;
                    winner = player3.name;
                } else if (winki[g] == p4.hand && (player4.outcome!=Actions.FOLDS)) {
                    player4.money = player4.money + pot;
                    winner = player4.name;
                }
                if(winner!=null) { break; }
            }
            if(winki[1]==winki[0]) {
                int lol = 1;
                if(winki[1]==winki[2]) { lol++; if(winki[2]==winki[3]) { lol++; }}
                for(int p = 0; p<=lol;p++)
                {
                    if(remisy[0]==remisy2[p])
                    {
                        if(remisy2[p]>remisy[1])
                        {
                            if(p==0&&!(player1.outcome==Actions.FOLDS)) { player1.money = player1.money + pot;
                                winner = player1.name; }
                            if(p==1&&!(player2.outcome==Actions.FOLDS)) { player2.money = player2.money + pot;
                                winner = player2.name; }
                            if(p==2&&!(player3.outcome==Actions.FOLDS)) { player3.money = player3.money + pot;
                                winner = player3.name; }
                            if(p==3&&!(player4.outcome==Actions.FOLDS)) { player4.money = player4.money + pot;
                                winner = player4.name; }
                        }
                    }
                }
            }
            System.out.print("lol");
            pot = 0;
            pot_label.setText("Pot: " + pot);
            save();
            showcards();
            repaint();
            revalidate();
            String message = (winner+" wins");
            JFrame framepopup = new JFrame();
            JOptionPane.showMessageDialog(framepopup, message);
            SwingUtilities.invokeLater(() ->
            {
                commonPanel.revalidate();
                commonPanel.repaint();
                boardwipe();});
            action1.setText("");
            action2.setText("");
            action3.setText("");
            player1.folded=false;
        }
    }
    }

}
