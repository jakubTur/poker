import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Interface extends JFrame {
    Player user;
    JPanel carbacks_p2;
    JPanel cardbacks_p3;
    JPanel cardbacks_p4;
    JPanel common_cards = placeholders_panel(5);
    JPanel user_panel = new JPanel();
    JPanel p2_panel = new JPanel();
    JPanel p3_panel = new JPanel();
    JPanel p4_panel = new JPanel();
    JButton[] action_buttons;
    JPanel commonPanel = new JPanel();
    JPanel p1_placeholders = placeholders_panel(2);
    JPanel p2_placeholders = placeholders_panel(2);
    JPanel p3_placeholders = placeholders_panel(2);
    JPanel p4_placeholders = placeholders_panel(2);
    JLabel action_label = new JLabel("<html>This label will<br/>register actions</html>");
    JLabel tablepro;
    JLabel tablekid;
    JLabel tableuser;
    JLabel tableoldman;
    JLabel moneyepro;
    JLabel moneyykid;
    JLabel moneyuser;
    JLabel moneyoldman;
    JLabel pot_label;
    JPanel player_panel;
    public Interface(Player p1) {
        setTitle("Poker");
        setSize(1280, 720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        user = p1;
        action_label.setFont(new Font(null, Font.BOLD, 45));
        action_label.setHorizontalAlignment(SwingConstants.CENTER);

        tablepro = new JLabel("Table: 0");
        tablekid = new JLabel("Table: 0");
        tableuser = new JLabel("Table: 0");
        tableoldman = new JLabel("Table: 0");
        moneyepro = new JLabel("Money: 2000");
        moneyykid = new JLabel("Money: 2000");
        moneyuser = new JLabel("Money: 2000");
        moneyoldman = new JLabel("Money: 2000");

        int fontSize = 32;
        String fontName = null;

        tablepro.setFont(new Font(fontName, Font.BOLD, fontSize));
        tablekid.setFont(new Font(fontName, Font.BOLD, fontSize));
        tableuser.setFont(new Font(fontName, Font.BOLD, fontSize));
        tableoldman.setFont(new Font(fontName, Font.BOLD, fontSize));
        moneyepro.setFont(new Font(fontName, Font.BOLD, fontSize));
        moneyykid.setFont(new Font(fontName, Font.BOLD, fontSize));
        moneyuser.setFont(new Font(fontName, Font.BOLD, fontSize));
        moneyoldman.setFont(new Font(fontName, Font.BOLD, fontSize));
        pot_label = new JLabel("Pot: 0");
        pot_label.setFont(new Font(null, Font.BOLD, 45));
        pot_label.setHorizontalTextPosition(SwingConstants.CENTER);


        p3_panel.add(moneyepro);
        p3_panel.add(tablepro);
        p3_panel.add(p3_placeholders);

        user_panel.add(moneyuser);
        user_panel.add(tableuser);
        user_panel.add(p1_placeholders);

        p2_panel.add(moneyykid);
        p2_panel.add(tablekid);
        p2_panel.add(p2_placeholders);
        p2_panel.setLayout(new BoxLayout(p2_panel, BoxLayout.Y_AXIS));

        p4_panel.add(moneyoldman);
        p4_panel.add(tableoldman);
        p4_panel.add(p4_placeholders);
        p4_panel.setLayout(new BoxLayout(p4_panel, BoxLayout.Y_AXIS));

        action_buttons = createActionButtonArray();

        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (JButton button : action_buttons) {
            buttonPanel.add(button);
            button.setEnabled(false);
        }
        buttonPanel.add(pot_label);
        setLayout(new BorderLayout());
        add(p3_panel, BorderLayout.NORTH);
        add(p2_panel, BorderLayout.WEST);
        add(p4_panel, BorderLayout.EAST);
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.add(user_panel, BorderLayout.CENTER);
        userPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(userPanel, BorderLayout.SOUTH);
        commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.Y_AXIS));
        commonPanel.add(action_label);
        commonPanel.add(common_cards);
        add(commonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    private JPanel cardbacks() {
        JPanel cardbacks = new JPanel();
        BufferedImage back;
        try {
            back = ImageIO.read(new File("C:/Users/leesz/IdeaProjects/poker/karty/cardback.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cardbacks.add(new JLabel(new ImageIcon(back)));
        cardbacks.add(new JLabel(new ImageIcon(back)));
        return cardbacks;
    }
    void update_label(String text)
    {
        SwingUtilities.invokeLater(() -> {
        action_label.setText(text);
        commonPanel.revalidate();});
    }
    void disableButtons()
    {
        for(JButton button : action_buttons)
        {
            button.setEnabled(false);
        }
    }
    private JPanel placeholders_panel(int numPlaceholders) {
        JPanel panel;
        panel = new JPanel(new GridLayout(1, numPlaceholders));
        ImageIcon blank = new ImageIcon("C:/Users/leesz/IdeaProjects/poker/karty/blank.png");
        for (int i = 0; i < numPlaceholders; i++) {
            panel.add(new JLabel(blank));
        }
        return panel;
    }
    JButton[] createActionButtonArray() {
        Player player1 = user;
        String[] buttonLabels = {"Deal","Big Blind", "Small Blind", "Fold", "Call", "Raise", "Check"};
        JButton[] buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    {
                        if (finalI == 0) {
                            player1.outcome = Actions.DEALS;
                            player1.dealt = true;
                        }
                        if (finalI == 1) {
                            player1.outcome = Actions.BIG_BLINDS;
                            player1.dealt = true;
                        }
                        if (finalI == 2) {
                            player1.outcome = Actions.SMALL_BLINDS;
                            player1.dealt = true;
                        }
                        if (finalI == 3) {
                            player1.outcome = Actions.FOLDS;
                            player1.folded = true;
                        }
                        if (finalI == 4) {
                            player1.outcome = Actions.CALLS;
                        }
                        if (finalI == 5) {

                            player1.outcome = Actions.RAISES;
                            player1.raised = true;
                        }
                        if(finalI == 6) { player1.outcome = Actions.CHECKS; }
                        moneyuser.setText("Money: " + player1.money);
                        tableuser.setText("Table: " + player1.table);
                        disableButtons();
                        ((User) player1).inputDone();
                    }
                }
            });
        }
        return buttons;
    }
    void boardwipe()
    {
        SwingUtilities.invokeLater(() -> {
            commonPanel.remove(common_cards);
            common_cards = placeholders_panel(5);
            commonPanel.add(common_cards);
            p2_panel.remove(carbacks_p2);
            p2_panel.add(p2_placeholders);
            p3_panel.remove(cardbacks_p3);
            p3_panel.add(p3_placeholders);
            p4_panel.remove(cardbacks_p4);
            p4_panel.add(p4_placeholders);
            user_panel.remove(player_panel);
            user_panel.add(p1_placeholders);
            user_panel.repaint();
            user_panel.revalidate();
            p2_panel.repaint();
            p2_panel.revalidate();
            p3_panel.repaint();
            p3_panel.revalidate();
            p4_panel.repaint();
            p4_panel.revalidate(); });
    }
    void updateMoney(Player player1, Player player2, Player player3, Player player4, int pot) {
        SwingUtilities.invokeLater(() -> {
            moneyuser.setText("Your" + "'s money: " + player1.money);
            tableuser.setText("Your" + "'s table: " + player1.table);
            moneyykid.setText(player2.name + "'s money: " + player2.money);
            tablekid.setText(player2.name + "'s table: " + player2.table);
            moneyepro.setText(player3.name + "'s money: " + player3.money);
            tablepro.setText(player3.name + "'s table: " + player3.table);
            moneyoldman.setText(player4.name + "'s money: " + player4.money);
            tableoldman.setText(player4.name + "'s table: " + player4.table);
            pot_label.setText("Pot: " + pot);
            repaint();
            revalidate();
        });
    }
    void deal(Player player) {
        int[] ranks = player.hands[3].getRanks();
        Suits[] suits = player.hands[3].getSuits();

        JPanel cardPanel = new JPanel(new GridLayout(1, 5));
        JPanel playerPanel = new JPanel(new GridLayout(1, 2));

        for (int i = 0; i <= 6; i++) {
            String imagePath = Path.of("").toAbsolutePath() + "/karty/" + ranks[i] + suits[i] + ".png";
            try {
                BufferedImage image = ImageIO.read(new File(imagePath));
                JLabel cardLabel = new JLabel(new ImageIcon(image));

                switch (player.turn) {
                    case 1:
                        if (i == 0 || i == 1) playerPanel.add(cardLabel);
                        break;
                    case 2:
                        if (i <= 4 && i >= 2) cardPanel.add(cardLabel);
                        break;
                    case 3:
                        if (i <= 5 && i >= 2) cardPanel.add(cardLabel);
                        break;
                    case 4:
                        if (i >= 2) cardPanel.add(cardLabel);
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error: Picture not found at " + imagePath);
            }
        }

        if (player.turn == 1) {
            carbacks_p2 = cardbacks();
            cardbacks_p3 = cardbacks();
            cardbacks_p4 = cardbacks();

            this.player_panel = playerPanel;
            user_panel.remove(p1_placeholders);
            user_panel.add(player_panel);

            p2_panel.remove(p2_placeholders);
            p3_panel.remove(p3_placeholders);
            p4_panel.remove(p4_placeholders);

            p2_panel.add(carbacks_p2);
            p3_panel.add(cardbacks_p3);
            p4_panel.add(cardbacks_p4);
        } else {
            switch (player.turn) {
                case 2:
                    cardPanel.add(placeholders_panel(2));
                    break;
                case 3:
                    cardPanel.add(placeholders_panel(1));
                    break;
            }
            commonPanel.remove(common_cards);
            common_cards = cardPanel;
            commonPanel.add(common_cards);
            commonPanel.revalidate();
            commonPanel.repaint();
        }
    }

void showcards(Player player2, Player player3, Player player4) {
    p2_panel.remove(carbacks_p2);
    p3_panel.remove(cardbacks_p3);
    p4_panel.remove(cardbacks_p4);

    carbacks_p2 = new JPanel();
    cardbacks_p3 = new JPanel();
    cardbacks_p4 = new JPanel();

    for (int i = 0; i <= 2; i++) { //zmien to na switche
        for (int j = 0; j <= 1; j++) {
            String imagePath=Path.of("").toAbsolutePath() + "/karty/";
            try {
                Card card;
                if (i == 0) {
                    card = player2.hands[0].getCards()[j];
                    BufferedImage image = ImageIO.read(new File(imagePath + card.rank + card.suit + ".png"));
                    carbacks_p2.add(new JLabel(new ImageIcon(image)));
                } else if (i == 1) {
                    card = player3.hands[0].getCards()[j];
                    BufferedImage image = ImageIO.read(new File(imagePath + card.rank + card.suit + ".png"));
                    cardbacks_p3.add(new JLabel(new ImageIcon(image)));
                } else {
                    card = player4.hands[0].getCards()[j];
                    BufferedImage image = ImageIO.read(new File(imagePath + card.rank + card.suit + ".png"));
                    cardbacks_p4.add(new JLabel(new ImageIcon(image)));
                }
            } catch (IOException e) {
                System.out.println("can't load from path " + imagePath);
            }
        }
    }
    SwingUtilities.invokeLater(() -> {

        p2_panel.add(carbacks_p2);
        p2_panel.revalidate();
        p2_panel.repaint();

        p3_panel.add(cardbacks_p3);
        p3_panel.revalidate();
        p3_panel.repaint();

        p4_panel.add(cardbacks_p4);
        p4_panel.revalidate();
        p4_panel.repaint();
    });
}
}
