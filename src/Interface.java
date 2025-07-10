import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Interface extends JFrame {
    JPanel cardbackkid;
    JPanel cardbackpro;
    JPanel cardbackoldman;
    JPanel common = placeholder(5);
    JPanel pro_panel = new JPanel();
    JPanel user_panel = new JPanel();
    JPanel kid_panel = new JPanel();
    JPanel oldman_panel = new JPanel();
    JButton[] actionButtons;
    JPanel commonPanel = new JPanel();
    JPanel placeholderyuser = placeholder(2);
    JPanel placeholderykid = placeholder(2);
    JPanel placeholderypro = placeholder(2);
    JPanel placeholderyoldman = placeholder(2);
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
    JLabel pot_label = new JLabel("Pot: "+pot);
    JPanel player_panel;
    public Interface() {
        setTitle("Poker");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        action1.setFont(new Font(null, Font.BOLD, 60));
        action2.setFont(new Font(null, Font.BOLD, 60));
        action3.setFont(new Font(null, Font.BOLD, 60));

        tablepro = new JLabel("Table: "+player3.table);
        tablekid = new JLabel("Table: "+player2.table);
        tableuser = new JLabel("Table: "+player1.table);
        tableoldman = new JLabel("Table: "+player4.table);
        moneyepro = new JLabel("Money: "+player3.money);
        moneyykid = new JLabel("Money: "+player2.money);
        moneyuser = new JLabel("Money: "+ player1.money);
        moneyoldman = new JLabel("Money: "+ player4.money);
        pot_label = new JLabel("Pot: "+pot);

        common.add(pot_label);

        pro_panel.add(moneyepro);
        pro_panel.add(tablepro);
        pro_panel.add(placeholderypro);

        user_panel.add(moneyuser);
        user_panel.add(tableuser);
        user_panel.add(placeholderyuser);

        kid_panel.add(moneyykid);
        kid_panel.add(tablekid);
        kid_panel.add(placeholderykid);

        oldman_panel.add(moneyoldman);
        oldman_panel.add(tableoldman);
        oldman_panel.add(placeholderyoldman);
        actionButtons = createActionButtonArray();

        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (JButton button : actionButtons) {
            buttonPanel.add(button);
            button.setEnabled(false);
        }
        setLayout(new BorderLayout());
        add(pro_panel, BorderLayout.NORTH);
        add(kid_panel, BorderLayout.WEST);
        add(oldman_panel, BorderLayout.EAST);
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.add(user_panel, BorderLayout.CENTER);
        userPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(userPanel, BorderLayout.SOUTH);
        commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.Y_AXIS));
        commonPanel.add(action1);
        commonPanel.add(action2);
        commonPanel.add(action3);
        commonPanel.add(common);
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
    void disableButtons()
    {
        for(JButton button : actionButtons)
        {
            button.setEnabled(false);
        }
    }
    private JPanel placeholder(int numPlaceholders) {
        JPanel panel;
        panel = new JPanel(new GridLayout(1, numPlaceholders));
        ImageIcon blank = new ImageIcon("C:/Users/leesz/IdeaProjects/poker/karty/blank.png");
        for (int i = 0; i < numPlaceholders; i++) {
            panel.add(new JLabel(blank));
        }
        return panel;
    }
    JButton[] createActionButtonArray(Player player1) {
        String[] buttonLabels = {"Deal","Big Blind", "Small Blind", "Fold", "Call", "Raise / Bet", "Check"};
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
                        player1.go = player1.go+4;
                        whatsup(player1);
                        moneyuser.setText("Money: " + player1.money);
                        tableuser.setText("Table: " + player1.table);
                        player1.moved = true;
                        player4.moved = false;
                        disableButtons();
                    }
                }
            });
        }
        return buttons;
    }

    void boardwipe()
    {
        SwingUtilities.invokeLater(() -> {
            commonPanel.remove(common);
            common = placeholder(5);
            commonPanel.add(common);
            commonPanel.add(pot_label);
            kid_panel.remove(cardbackkid);
            kid_panel.add(placeholderykid);
            pro_panel.remove(cardbackpro);
            pro_panel.add(placeholderypro);
            oldman_panel.remove(cardbackoldman);
            oldman_panel.add(placeholderyoldman);
            user_panel.remove(player_panel);
            user_panel.add(placeholderyuser);
            user_panel.repaint();
            user_panel.revalidate();
            kid_panel.repaint();
            kid_panel.revalidate();
            pro_panel.repaint();
            pro_panel.revalidate();
            oldman_panel.repaint();
            oldman_panel.revalidate(); });

    }
    void buttons()
    {
        boolean other = false;
        player1.moved = false;
        if(player1.go ==1 && !player1.dealt) { actionButtons[0].setEnabled(true); other = true; }

        if (player1.go == 2 && player1.turn == 1) {
            actionButtons[2].setEnabled(true);
            other = true;
        }
        if (player1.go == 3 && player1.turn == 1) {
            actionButtons[1].setEnabled(true);
            other = true;
        }
        if (player1.go != 1&&(player1.table == player4.table && player1.table != 0&& Actions.FOLDS!=player4.outcome))
        { actionButtons[6].setEnabled(true); }

        if(player1.folded&&player1.go !=1)
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
        }
    }
    private void showcards() {
        kid_panel.remove(cardbackkid);
        pro_panel.remove(cardbackpro);
        oldman_panel.remove(cardbackoldman);

        cardbackkid = new JPanel();
        cardbackpro = new JPanel();
        cardbackoldman = new JPanel();

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 1; j++) {
                String imagePath;
                try {
                    Card card;
                    if (i == 0) {
                        card = player2.hands[0].getCards()[j];
                        imagePath = "C:/Users/leesz/IdeaProjects/poker" + card.rank + card.suit + ".png";
                        BufferedImage image = ImageIO.read(new File(imagePath));
                        cardbackkid.add(new JLabel(new ImageIcon(image)));
                    } else if (i == 1) {
                        card = player3.hands[0].getCards()[j];
                        imagePath = "C:/Users/leesz/IdeaProjects/poker" + card.rank + card.suit + ".png";
                        BufferedImage image = ImageIO.read(new File(imagePath));
                        cardbackpro.add(new JLabel(new ImageIcon(image)));
                    } else if (i == 2) {
                        card = player4.hands[0].getCards()[j];
                        imagePath = "C:/Users/leesz/IdeaProjects/poker" + card.rank + card.suit + ".png";
                        BufferedImage image = ImageIO.read(new File(imagePath));
                        cardbackoldman.add(new JLabel(new ImageIcon(image)));
                    }
                } catch (IOException e) {
                    e.printStackTrace(); // Optional: show file loading error
                }
            }
        }

        SwingUtilities.invokeLater(() -> {

            kid_panel.add(cardbackkid);
            kid_panel.revalidate();
            kid_panel.repaint();

            pro_panel.add(cardbackpro);
            pro_panel.revalidate();
            pro_panel.repaint();

            oldman_panel.add(cardbackoldman);
            oldman_panel.revalidate();
            oldman_panel.repaint();
        });
    }
}
