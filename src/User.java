import javax.swing.*;

public class User extends Player{
    private final Object lock = new Object();
    private boolean inputReceived = false;
    public User(int kolejka, String name) {
        super(kolejka, name);
        active = true;
    }
    public void waitForInput() throws InterruptedException {
        synchronized (lock) {
            while (!inputReceived)
            {
                lock.wait();
            }
            inputReceived = false;
        }
    }

    public void inputDone()
    {
        synchronized (lock)
        {
            inputReceived = true;
            lock.notify();
        }
    }

    public void buttons(JButton[] actionButtons, int max, boolean check_possible, boolean call)
    {
        boolean other = false;
        if(go == 1 && !dealt) { actionButtons[0].setEnabled(true); other = true; }

        if (go == 2 && turn == 1 && !dealt) {
            actionButtons[2].setEnabled(true);
            other = true;
        }
        if (go == 3 && turn == 1 && !dealt) {
            actionButtons[1].setEnabled(true);
            other = true;
        }
        if (max < table)
        { actionButtons[6].setEnabled(true); }

        if(folded && go != 1)
        {
            actionButtons[3].setEnabled(true);
            other = true;
        }
        if (!other) {
            actionButtons[3].setEnabled(true);
            if(!folded)
            {
                if(!raised) { actionButtons[5].setEnabled(true); }
                if(check_possible)
                {
                    actionButtons[6].setEnabled(true);
                }
                else if(call) { actionButtons[4].setEnabled(true); }
        } }
    }
}
