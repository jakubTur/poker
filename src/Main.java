public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        {
            Player p1 = new User(1, "user");
            Player p2 = new Kid(2, "Jack");
            Player p3 = new Pro(3, "Jim");
            Player p4 = new Oldman(4, "John");
            Interface main = new Interface(p1);
            Logic logic = new Logic(p1, p2, p3, p4, main);
            logic.play(main);
        }
    }
}