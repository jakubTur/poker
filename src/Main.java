import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//images of cards are in the zip, any filepaths in this file should be changed accordingly to their placement on the PC
public class Main extends JFrame{
    int pot = 0;
    player player1 = new player(1, "user");
    player player2 = new player(2, "kid");
    player player3 = new player(3, "pro");
    player player4 = new player(4, "oldman");
    JLabel action1 = new JLabel("this label will");
    JLabel action2 = new JLabel("register other");
    JLabel action3 = new JLabel("players' moves");
    JLabel tablepro = new JLabel("Table: "+player3.table);
    JLabel tablekid = new JLabel("Table: "+player2.table);
    JLabel tableuser = new JLabel("Table: "+player1.table);
    JLabel tableoldman = new JLabel("Table: "+player4.table);
    JLabel moneyepro = new JLabel("Money: "+player3.money);
    JLabel moneyykid = new JLabel("Money: "+player2.money);
    JLabel moneyuser = new JLabel("Money: "+ player1.money);
    JLabel moneyoldman = new JLabel("Money: "+ player4.money);
    JLabel potson = new JLabel("Pot: "+pot);
    JPanel gracza;

    public Main()
    {
        setTitle("Poker");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton play = new JButton("Play");
        play.setPreferredSize(new Dimension(600, 500));

        JButton load = new JButton("Load a save");
        setLayout(new FlowLayout());
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(play);
                remove(load);
                initializeUI();
                revalidate();
                repaint();
                game();
            }
        });
        load.setPreferredSize(new Dimension(600, 500));
        play.setFont(new FontUIResource(null, Font.BOLD, 60));
        load.setFont(new FontUIResource(null, Font.BOLD, 60));
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("C:/Users/leesz/IdeaProjects/poker/save.txt");
                Scanner reader = null;
                try {
                    reader = new Scanner(file);
                    player1.money = Integer.valueOf(reader.nextLine());
                    player2.money = Integer.valueOf(reader.nextLine());
                    player3.money = Integer.valueOf(reader.nextLine());
                    player4.money = Integer.valueOf(reader.nextLine());

                } catch (FileNotFoundException ex) {
                    JOptionPane.showInputDialog("file not found, starting a new game instead");
                }
                remove(play);
                remove(load);
                initializeUI();
                revalidate();
                game();
            }
        });
        add(play);
        add(load);
    }
    JPanel common = placeholder(5);
    JPanel pro = new JPanel();
    JPanel user = new JPanel();
    JPanel kid = new JPanel();
    JPanel oldman = new JPanel();
    JButton[] actionButtons;
    JPanel commonPanel = new JPanel();
    JPanel placeholderyuser = placeholder(2);
    JPanel placeholderykid = placeholder(2);
    JPanel placeholderypro = placeholder(2);
    JPanel placeholderyoldman = placeholder(2);
    JPanel cardbackkid;
    JPanel cardbackpro;
    JPanel cardbackoldman;
    void save() throws IOException {
        File save = new File("C:/Users/leesz/IdeaProjects/poker/save.txt");
        FileWriter saver;
        try {
            saver = new FileWriter(save);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        saver.write(player1.money+"\n"+player2.money+"\n"+player3.money+"\n"+player4.money);
        try {
            saver.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private void initializeUI() {

        action1.setFont(new Font(null, Font.BOLD, 60));
        action2.setFont(new Font(null, Font.BOLD, 60));
        action3.setFont(new Font(null, Font.BOLD, 60));

        common.add(potson);

        pro.add(moneyepro);
        pro.add(tablepro);
        pro.add(placeholderypro);

        user.add(moneyuser);
        user.add(tableuser);
        user.add(placeholderyuser);

        kid.add(moneyykid);
        kid.add(tablekid);
        kid.add(placeholderykid);

        oldman.add(moneyoldman);
        oldman.add(tableoldman);
        oldman.add(placeholderyoldman);
        actionButtons = createActionButtonArray();

        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (JButton button : actionButtons) {
            buttonPanel.add(button);
            button.setEnabled(false);
        }
        setLayout(new BorderLayout());
        add(pro, BorderLayout.NORTH);
        add(kid, BorderLayout.WEST);
        add(oldman, BorderLayout.EAST);
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.add(user, BorderLayout.CENTER);
        userPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(userPanel, BorderLayout.SOUTH);
        commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.Y_AXIS));
        commonPanel.add(action1);
        commonPanel.add(action2);
        commonPanel.add(action3);
        commonPanel.add(common);
        add(commonPanel, BorderLayout.CENTER);
    }
    private JPanel placeholder(int numPlaceholders) {
        JPanel panel;
        panel = new JPanel(new GridLayout(1, numPlaceholders));
        ImageIcon blank = new ImageIcon("C:/Users/leesz/OneDrive/Dokumenty/karty/blank.png");
        for (int i = 0; i < numPlaceholders; i++) {
            panel.add(new JLabel(blank));
        }
        return panel;
    }

    private JButton[] createActionButtonArray() {
        String[] buttonLabels = {"Deal","Big Blind", "Small Blind", "Fold", "Call", "Raise / Bet", "Check"};
        JButton[] buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    {
                        if (finalI == 0) {
                            player1.outcome = "deals";
                            player1.dealt = true;
                        }
                        if (finalI == 1) {
                            player1.outcome = "big blinds";
                            player1.dealt = true;
                        }
                        if (finalI == 2) {
                            player1.outcome = "small blinds";
                            player1.dealt = true;
                        }
                        if (finalI == 3) {
                            player1.outcome = "folds";
                            player1.folded = true;
                        }
                        if (finalI == 4) {
                            player1.outcome = "calls";
                        }
                        if (finalI == 5) {

                            player1.outcome = "raises";
                            player1.raised = true;
                        }
                        if(finalI == 6) { player1.outcome = "checks"; }
                        player1.kolej=player1.kolej+4;
                        whatsup(player1);
                        moneyuser.setText("Money: " + player1.money);
                        tableuser.setText("Table: " + player1.table);
                        player1.moved = true;
                        player4.moved = false;
                        disableall();
                    }
                }
            });
        }
        return buttons;
    }
    boolean call()
    {
        boolean call = false;
        if(player1.table==0) {
            if("folds".equals(player4.outcome))
            {
                if("folds".equals(player3.outcome))
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
        if("folds".equals(player4.outcome))
        {
            if("folds".equals(player3.outcome))
            {
                if(player1.table==player2.table) { check = true; }
            }
            else { if(player3.table==player1.table) { check = true; } }
        }
        else { if(player1.table==player4.table) { check = true; } } }
        return check;
    }
    void buttons()
    {
        boolean other = false;
        player1.moved = false;
        if(player1.kolej==1 && !player1.dealt) { actionButtons[0].setEnabled(true); other = true; }

        if (player1.kolej == 2 && player1.tura == 1) {
                actionButtons[2].setEnabled(true);
                other = true;
            }
            if (player1.kolej == 3 && player1.tura == 1) {
                actionButtons[1].setEnabled(true);
                other = true;
            }
        if (player1.kolej != 1&&(player1.table == player4.table && player1.table != 0&& !player4.outcome.equals("folds")))
        { actionButtons[6].setEnabled(true); }

        if(player1.folded&&player1.kolej!=1)
        {
            actionButtons[3].setEnabled(true);
            other = true;
        }

            if (!other) {

                actionButtons[3].setEnabled(true);
                if(!player1.folded) {
                if(!player1.raised) { actionButtons[5].setEnabled(true); }
                if(((player1.table==player2.table)&&(player1.table==player3.table)&&(player1.table==player4.table)&&player1.table!=0))
                {
                    actionButtons[6].setEnabled(true);
                }
                else { if(check()) { actionButtons[6].setEnabled(true); } if(call()) { actionButtons[4].setEnabled(true); }} }


            } }
    void disableall()
    {
        for (JButton button : actionButtons) {
            button.setEnabled(false);
        }
    }
    int[] numery;
    String[] kolory;
    void game() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws IOException {
                while(player1.money>=0) {
                    carddraw allcards = new carddraw();
                    numery = allcards.allcards;
                    kolory = allcards.kolorallcards;
                    player1.outcome=null;
                    player2.outcome=null;
                    player3.outcome=null;
                    player4.outcome=null;
                    player1.tura=1;
                    player2.tura=1;
                    player3.tura=1;
                    player4.tura=1;
                    player1.kolej=1;
                    player2.kolej=2;
                    player3.kolej=3;
                    player4.kolej=4;
                    player1.folded=false;
                    player2.folded=false;
                    player3.folded=false;
                    player4.folded=false;
                    moneyuser.setText("Money: " + player1.money);
                    tableuser.setText("Table: " + player1.table);
                    moneyykid.setText("Money: " + player2.money);
                    tablekid.setText("Table: " + player2.table);
                    moneyepro.setText("Money: " + player3.money);
                    tablepro.setText("Table: " + player3.table);
                    moneyoldman.setText("Money: " + player4.money);
                    tableoldman.setText("Table: " + player4.table);
                    potson.setText("Pot: " + pot);
                    for (int j = 1; j <= 4; j++) {
                        do {
                            if ((!player1.moved && player4.moved ) || (!player1.moved && player1.kolej == 1)) {
                                buttons();
                                if ("checks".equals(player1.outcome)) {
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
                                if ((!player2.moved && player1.moved) || (!player2.moved && player2.kolej == 1)) {
                                    player2.action(allcards.handkid, allcards.kolorkid);
                                    if(player1.folded&&(player2.outcome.equals("calls")||player2.outcome.equals("small blinds"))&&!player2.raised)
                                    {
                                        player2.outcome = "raises";
                                        player2.raised = true;
                                    }
                                    whatsup(player2);
                                    SwingUtilities.invokeLater(() -> {
                                        action3.setText(player2.player + " " + player2.outcome);
                                        moneyykid.setText("Money: " + player2.money);
                                        tablekid.setText("Table: " + player2.table);
                                        if ("checks".equals(player2.outcome)) {
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
                                if ((!player3.moved && player2.moved) || (!player3.moved && player3.kolej == 1)) {
                                    player3.action(allcards.handpro, allcards.kolorpro);
                                    if(player1.folded&&(player3.outcome.equals("calls")||player2.outcome.equals("big blinds"))&&!player3.raised&&!player2.raised)
                                    {
                                        player2.outcome = "raises";
                                        player3.raised = true;
                                    }
                                    whatsup(player3);
                                    SwingUtilities.invokeLater(() -> {
                                        action2.setText(player3.player + " " + player3.outcome);
                                        moneyepro.setText("Money: " + player3.money);
                                        tablepro.setText("Table: " + player3.table);
                                        if ("checks".equals(player3.outcome)) {
                                            action1.setText("");
                                        }
                                    });
                                    player3.moved = true;
                                    player2.moved = false;
                                }
                                if (breakcheck(player3.outcome, player3.table)) {
                                    break;
                                }
                                if ((!player4.moved && player3.moved) || (!player4.moved && player4.kolej == 1)) {
                                    player4.action(allcards.handoldman, allcards.koloroldman);
                                    whatsup(player4);
                                    SwingUtilities.invokeLater(() -> {
                                        action1.setText(player4.player + " " + player4.outcome);
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
                        potson.setText("Pot: " + pot);
                        koniectury();
                        if (j != 4 && !threefold()) {
                            String message = ("End of turn");
                            JFrame framepopup = new JFrame();
                            JOptionPane.showMessageDialog(framepopup, message);
                        }
                    }
                    handstate p1 = new handstate(allcards.handuser, allcards.koloruser);
                    handstate p2 = new handstate(allcards.handkid, allcards.kolorkid);
                    handstate p3 = new handstate(allcards.handpro, allcards.kolorpro);
                    handstate p4 = new handstate(allcards.handoldman, allcards.koloroldman);
                    int[] winki = {p1.wskaznik, p2.wskaznik, p3.wskaznik, p4.wskaznik};
                    int[] remisy = {p1.wartosc, p2.wartosc, p3.wartosc, p4.wartosc };
                    int[] remisy2 = {p1.wartosc, p2.wartosc, p3.wartosc, p4.wartosc };
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
                            if (winki[g] == p1.wskaznik && !("folds".equals(player1.outcome))) {
                                player1.money = player1.money + pot;
                                winner = player1.player;
                            } else if (winki[g] == p2.wskaznik && !("folds".equals(player2.outcome))) {
                                player2.money = player2.money + pot;
                                winner = player2.player;
                            } else if (winki[g] == p3.wskaznik && !("folds".equals(player3.outcome))) {
                                player3.money = player3.money + pot;
                                winner = player3.player;
                            } else if (winki[g] == p4.wskaznik && !("folds".equals(player4.outcome))) {
                                player4.money = player4.money + pot;
                                winner = player4.player;
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
                                    if(p==0&&!("folds".equals(player1.outcome))) { player1.money = player1.money + pot;
                                        winner = player1.player; }
                                    if(p==1&&!("folds".equals(player2.outcome))) { player2.money = player2.money + pot;
                                        winner = player2.player; }
                                    if(p==2&&!("folds".equals(player3.outcome))) { player3.money = player3.money + pot;
                                        winner = player3.player; }
                                    if(p==3&&!("folds".equals(player4.outcome))) { player4.money = player4.money + pot;
                                        winner = player4.player; }
                                }
                            }
                        }
                    }
                    System.out.print("lol");
                    pot = 0;
                    potson.setText("Pot: " + pot);
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

                return null;
            }
            void boardwipe()
            {
                SwingUtilities.invokeLater(() -> {
                    commonPanel.remove(common);
                common = placeholder(5);
                commonPanel.add(common);
                commonPanel.add(potson);
                kid.remove(cardbackkid);
                kid.add(placeholderykid);
                pro.remove(cardbackpro);
                pro.add(placeholderypro);
                oldman.remove(cardbackoldman);
                oldman.add(placeholderyoldman);
                user.remove(gracza);
                user.add(placeholderyuser);
                user.repaint();
                user.revalidate();
                kid.repaint();
                kid.revalidate();
                pro.repaint();
                pro.revalidate();
                oldman.repaint();
                oldman.revalidate(); });

            }
            private void showcards() {
                kid.remove(cardbackkid);
                pro.remove(cardbackpro);
                oldman.remove(cardbackoldman);

                cardbackkid = new JPanel();
                cardbackpro = new JPanel();
                cardbackoldman = new JPanel();

                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 1; j++) {
                        String imagePath;
                        try {
                            if (i == 0) {
                                imagePath = "C:/Users/leesz/OneDrive/Dokumenty/karty/" + player2.showwartosci[j] + player2.showkolory[j] + ".png";
                                BufferedImage image = ImageIO.read(new File(imagePath));
                                cardbackkid.add(new JLabel(new ImageIcon(image)));
                            } else if (i == 1) {
                                imagePath = "C:/Users/leesz/OneDrive/Dokumenty/karty/" + player3.showwartosci[j] + player3.showkolory[j] + ".png";
                                BufferedImage image = ImageIO.read(new File(imagePath));
                                cardbackpro.add(new JLabel(new ImageIcon(image)));
                            } else if (i == 2) {
                                imagePath = "C:/Users/leesz/OneDrive/Dokumenty/karty/" + player4.showwartosci[j] + player4.showkolory[j] + ".png";
                                BufferedImage image = ImageIO.read(new File(imagePath));
                                cardbackoldman.add(new JLabel(new ImageIcon(image)));
                            }
                        } catch (IOException e) {

                        }
                    }
                }
                SwingUtilities.invokeLater(() -> {

                    kid.add(cardbackkid);
                    kid.revalidate();
                    kid.repaint();

                    pro.add(cardbackpro);
                    pro.revalidate();
                    pro.repaint();

                    oldman.add(cardbackoldman);
                    oldman.revalidate();
                    oldman.repaint();
                });
            }
        };
        worker.execute();
    }
    boolean threefold()
    {
        boolean check = false;
        int foldcounter = 0;
        String[] outcomes = {player1.outcome, player2.outcome, player3.outcome, player4.outcome};
        for(int i = 0;i<=3;i++)
        {
            if("folds".equals(outcomes[i])) { foldcounter++; }
        }
        if(foldcounter>=3) { check = true; }
        return check;
    }
    boolean breakcheck(String outcome, int kasa)
    {
        boolean check = false;
        String[] outcomes = {player1.outcome, player2.outcome, player3.outcome, player4.outcome};
        int[] money = {player1.table, player2.table, player3.table, player4.table};
        if(kasa!=0&&outcome.equals("checks")) {
        for(int i = 0;i<=3;i++)
        {
            if (!outcomes[i].equals("folds"))
            {
                if(money[i]==kasa) {
                    check = true;
                }
                else{ check = false; break;}
            }
        } }
        return check;
    }
    void koniectury()
    {
        player1.moved = false;
        player2.moved = false;
        player3.moved = false;
        player4.moved = false;
        player1.koniectury();
        player2.koniectury();
        player3.koniectury();
        player4.koniectury();
        tableoldman.setText("Table: " + player4.table);
        tablepro.setText("Table: " + player3.table);
        tablekid.setText("Table: " + player2.table);
        tableuser.setText("Table: " + player1.table);
        moneyoldman.setText("Money: " + player4.money);
        moneyykid.setText("Money: " + player2.money);
        moneyepro.setText("Money: " + player3.money);
    }
    void whatsup(player gracz)
    {
        int[] stawki = {player1.table, player2.table, player3.table, player4.table};
        int max = stawki[0];
        for (int i = 1; i < stawki.length; i++)
            if (stawki[i] > max)
            { max = stawki[i]; }
        int bezsens = max-gracz.table;
        if(gracz.outcome.equals("calls"))
        {
            if(bezsens==0) { gracz.outcome="checks"; }
            gracz.table = gracz.table+bezsens;
            gracz.money = gracz.money-bezsens;
        }
        if(gracz.outcome.equals("folds")) { gracz.folded=true; }
        if(gracz.outcome.equals("raises"))
        {
            gracz.table = gracz.table + 100 + bezsens;
            gracz.money = gracz.money-100-bezsens;
        }
        if(gracz.outcome.equals("small blinds")) { gracz.money = gracz.money-100; gracz.table = gracz.table + 100; }
        if(gracz.outcome.equals("big blinds")) { gracz.money = gracz.money-200; gracz.table = gracz.table + 200; }
        if(gracz.outcome.equals("deals"))
        {
            String number;
            JPanel panel5 = new JPanel(new GridLayout(1, 5));
            JPanel panel2 = new JPanel(new GridLayout(1, 2));
            for (int i = 0; i <= 6; i++) {
                number = Integer.toString((numery[i]));
                String imagePath = "C:/Users/leesz/OneDrive/Dokumenty/karty/" + number + kolory[i] + ".png";
                try {
                    BufferedImage image = ImageIO.read(new File(imagePath));

                    if (gracz.tura == 1 && (i == 5 || i == 6)) {
                        panel2.add(new JLabel(new ImageIcon(image)));
                    }
                    if (gracz.tura == 2 && i <= 2) {
                        panel5.add(new JLabel(new ImageIcon(image)));
                    }
                    if (gracz.tura == 3 && i <= 3) {
                        panel5.add(new JLabel(new ImageIcon(image)));
                    }
                    if (gracz.tura == 4 && i <= 4) {
                        panel5.add(new JLabel(new ImageIcon(image)));
                    }
                } catch (IOException e) {
                    System.out.println("error: picture was not found");
                }
            }
            if(gracz.tura==1)
            {
                cardbackkid = cardbacks();
                cardbackpro = cardbacks();
                cardbackoldman = cardbacks();

                gracza=panel2;
                user.remove(placeholderyuser);
                user.add(gracza);

                kid.remove(placeholderykid);
                pro.remove(placeholderypro);
                oldman.remove(placeholderyoldman);

                kid.add(cardbackkid);
                pro.add(cardbackpro);
                oldman.add(cardbackoldman);
            }
            if (gracz.tura == 2) {
                panel5.add(placeholder(2));
            }
            if (gracz.tura == 3) {
                panel5.add(placeholder(1));
            }
            if(gracz.tura!=1)
            { commonPanel.remove(common);
            common=panel5;
            commonPanel.add(common);
            commonPanel.add(potson);
            commonPanel.revalidate();
            commonPanel.repaint();
            }
        }
    }
    private JPanel cardbacks() {
        JPanel cardbacks = new JPanel();
        BufferedImage back;
        try {
            back = ImageIO.read(new File("C:/Users/leesz/OneDrive/Dokumenty/karty/cardback.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cardbacks.add(new JLabel(new ImageIcon(back)));
        cardbacks.add(new JLabel(new ImageIcon(back)));
        return cardbacks;
    }
    public static void main(String[] args) {
        {
            SwingUtilities.invokeLater(() -> {
                new Main().setVisible(true);
            });
    }
    } }