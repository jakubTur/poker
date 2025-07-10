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
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;

//images of cards are in the zip, any filepaths in this file should be changed accordingly to their placement on the PC
public class Main {



    public static void main(String[] args) {
        {
            Player p1 = new User(1);
            Player p2 = new Kid(2);
            Player p3 = new Pro(3);
            Player p4 = new Oldman(4);
            Logic logic = new Logic(p1,p2,p3,p4);
            Interface main = new Interface();
    }
    } }