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
    private final Player user;
    private JPanel p2Cardbacks;
    private JPanel p3Cardbacks;
    private JPanel p4Cardbacks;
    private JPanel commonCards = placeholdersPanel(5);
    private final JPanel userPanel = new JPanel();
    private final JPanel p2Panel = new JPanel();
    private final JPanel p3Panel = new JPanel();
    private final JPanel p4Panel = new JPanel();
    private final JButton[] actionButtons;
    private final JPanel commonPanel = new JPanel();
    private final JPanel p1Placeholders = placeholdersPanel(2);
    private final JPanel p2Placeholders = placeholdersPanel(2);
    private final JPanel p3Placeholders = placeholdersPanel(2);
    private final JPanel p4Placeholders = placeholdersPanel(2);
    private final JLabel actionLabel = new JLabel("<html>This label will<br/>register actions</html>");
    private final JLabel tablePro;
    private final JLabel tableKid;
    private final JLabel tableUser;
    private final JLabel tableOldman;
    private final JLabel moneyPro;
    private final JLabel moneyKid;
    private final JLabel moneyUser;
    private final JLabel moneyOldman;
    private final JLabel potLabel;
    private JPanel playerPanel;
    public Interface(Player p1) {
        setTitle("Poker");
        setSize(1280, 720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        user = p1;
        actionLabel.setFont(new Font(null, Font.BOLD, 45));
        actionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        tablePro = new JLabel("Table: 0");
        tableKid = new JLabel("Table: 0");
        tableUser = new JLabel("Table: 0");
        tableOldman = new JLabel("Table: 0");
        moneyPro = new JLabel("Money: 2000");
        moneyKid = new JLabel("Money: 2000");
        moneyUser = new JLabel("Money: 2000");
        moneyOldman = new JLabel("Money: 2000");

        int fontSize = 32;
        String fontName = null;

        tablePro.setFont(new Font(fontName, Font.BOLD, fontSize));
        tableKid.setFont(new Font(fontName, Font.BOLD, fontSize));
        tableUser.setFont(new Font(fontName, Font.BOLD, fontSize));
        tableOldman.setFont(new Font(fontName, Font.BOLD, fontSize));
        moneyPro.setFont(new Font(fontName, Font.BOLD, fontSize));
        moneyKid.setFont(new Font(fontName, Font.BOLD, fontSize));
        moneyUser.setFont(new Font(fontName, Font.BOLD, fontSize));
        moneyOldman.setFont(new Font(fontName, Font.BOLD, fontSize));
        potLabel = new JLabel("Pot: 0");
        potLabel.setFont(new Font(null, Font.BOLD, 45));
        potLabel.setHorizontalTextPosition(SwingConstants.CENTER);


        p3Panel.add(moneyPro);
        p3Panel.add(tablePro);
        p3Panel.add(p3Placeholders);

        userPanel.add(moneyUser);
        userPanel.add(tableUser);
        userPanel.add(p1Placeholders);

        p2Panel.add(moneyKid);
        p2Panel.add(tableKid);
        p2Panel.add(p2Placeholders);
        p2Panel.setLayout(new BoxLayout(p2Panel, BoxLayout.Y_AXIS));

        p4Panel.add(moneyOldman);
        p4Panel.add(tableOldman);
        p4Panel.add(p4Placeholders);
        p4Panel.setLayout(new BoxLayout(p4Panel, BoxLayout.Y_AXIS));

        actionButtons = createActionButtonArray();

        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (JButton button : actionButtons) {
            buttonPanel.add(button);
            button.setEnabled(false);
        }
        buttonPanel.add(potLabel);
        setLayout(new BorderLayout());
        add(p3Panel, BorderLayout.NORTH);
        add(p2Panel, BorderLayout.WEST);
        add(p4Panel, BorderLayout.EAST);
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.add(this.userPanel, BorderLayout.CENTER);
        userPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(userPanel, BorderLayout.SOUTH);
        commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.Y_AXIS));
        commonPanel.add(actionLabel);
        commonPanel.add(commonCards);
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
    JButton[] getButtons()
    {
        return actionButtons;
    }
    void updateLabel(String text)
    {
        SwingUtilities.invokeLater(() -> {
        actionLabel.setText(text);
        commonPanel.revalidate();});
    }
    void disableButtons()
    {
        for(JButton button : actionButtons)
        {
            button.setEnabled(false);
        }
    }
    private JPanel placeholdersPanel(int numPlaceholders) {
        JPanel panel;
        panel = new JPanel(new GridLayout(1, numPlaceholders));
        ImageIcon blank = new ImageIcon(Path.of("").toAbsolutePath() + "/karty/blank.png");
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
                        moneyUser.setText("Money: " + player1.money);
                        tableUser.setText("Table: " + player1.table);
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
            commonPanel.remove(commonCards);
            commonCards = placeholdersPanel(5);
            commonPanel.add(commonCards);
            p2Panel.remove(p2Cardbacks);
            p2Panel.add(p2Placeholders);
            p3Panel.remove(p3Cardbacks);
            p3Panel.add(p3Placeholders);
            p4Panel.remove(p4Cardbacks);
            p4Panel.add(p4Placeholders);
            userPanel.remove(playerPanel);
            userPanel.add(p1Placeholders);
            userPanel.repaint();
            userPanel.revalidate();
            p2Panel.repaint();
            p2Panel.revalidate();
            p3Panel.repaint();
            p3Panel.revalidate();
            p4Panel.repaint();
            p4Panel.revalidate(); });
    }
    void updateMoney(Player player1, Player player2, Player player3, Player player4, int pot) {
        SwingUtilities.invokeLater(() -> {
            moneyUser.setText("Your" + " money: " + player1.money);
            tableUser.setText("Your" + " table: " + player1.table);
            moneyKid.setText(player2.name + "'s money: " + player2.money);
            tableKid.setText(player2.name + "'s table: " + player2.table);
            moneyPro.setText(player3.name + "'s money: " + player3.money);
            tablePro.setText(player3.name + "'s table: " + player3.table);
            moneyOldman.setText(player4.name + "'s money: " + player4.money);
            tableOldman.setText(player4.name + "'s table: " + player4.table);
            potLabel.setText("Pot: " + pot);
            repaint();
            revalidate();
        });
    }
    void commonRepaint()
    {
        commonPanel.repaint();
        commonPanel.revalidate();
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
            p2Cardbacks = cardbacks();
            p3Cardbacks = cardbacks();
            p4Cardbacks = cardbacks();

            this.playerPanel = playerPanel;
            userPanel.remove(p1Placeholders);
            userPanel.add(this.playerPanel);

            p2Panel.remove(p2Placeholders);
            p3Panel.remove(p3Placeholders);
            p4Panel.remove(p4Placeholders);

            p2Panel.add(p2Cardbacks);
            p3Panel.add(p3Cardbacks);
            p4Panel.add(p4Cardbacks);
        }
        else
        {
            switch (player.turn) {
                case 2:
                    cardPanel.add(placeholdersPanel(2));
                    break;
                case 3:
                    cardPanel.add(placeholdersPanel(1));
                    break;
            }
            commonPanel.remove(commonCards);
            commonCards = cardPanel;
            commonPanel.add(commonCards);
            commonPanel.revalidate();
            commonPanel.repaint();
        }
    }

void showCards(Player player2, Player player3, Player player4) {
    p2Panel.remove(p2Cardbacks);
    p3Panel.remove(p3Cardbacks);
    p4Panel.remove(p4Cardbacks);

    p2Cardbacks = new JPanel();
    p3Cardbacks = new JPanel();
    p4Cardbacks = new JPanel();

    for (int i = 0; i <= 2; i++) {
        for (int j = 0; j <= 1; j++) {
            String imagePath=Path.of("").toAbsolutePath() + "/karty/";
            try {
                Card card;
                if (i == 0) {
                    card = player2.hands[0].getCards()[j];
                    BufferedImage image = ImageIO.read(new File(imagePath + card.getRank() + card.getSuit() + ".png"));
                    p2Cardbacks.add(new JLabel(new ImageIcon(image)));
                } else if (i == 1) {
                    card = player3.hands[0].getCards()[j];
                    BufferedImage image = ImageIO.read(new File(imagePath + card.getRank() + card.getSuit() + ".png"));
                    p3Cardbacks.add(new JLabel(new ImageIcon(image)));
                } else {
                    card = player4.hands[0].getCards()[j];
                    BufferedImage image = ImageIO.read(new File(imagePath + card.getRank() + card.getSuit() + ".png"));
                    p4Cardbacks.add(new JLabel(new ImageIcon(image)));
                }
            } catch (IOException e) {
                System.out.println("can't load from path " + imagePath);
            }
        }
    }
    SwingUtilities.invokeLater(() -> {

        p2Panel.add(p2Cardbacks);
        p2Panel.revalidate();
        p2Panel.repaint();

        p3Panel.add(p3Cardbacks);
        p3Panel.revalidate();
        p3Panel.repaint();

        p4Panel.add(p4Cardbacks);
        p4Panel.revalidate();
        p4Panel.repaint();
    });
}
}
