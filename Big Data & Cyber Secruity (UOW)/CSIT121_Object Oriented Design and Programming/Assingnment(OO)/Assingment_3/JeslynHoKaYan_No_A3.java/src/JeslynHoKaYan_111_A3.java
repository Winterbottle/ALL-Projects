
 //Name: Jeslyn Ho Ka Yan
 //Tutorial group:121T05
//JDK version: 11.016
//Declaration:
//This program is created by me and have not passed to any friends.
//If this program is found to be copied from friend or friend copy from me, I am willing to accept whatever penalty given to me.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JeslynHoKaYan_111_A3 {

    public static void main(String[] args) {
        OlympicFrame olympicFrame = new OlympicFrame();
        olympicFrame.setTitle("Olympic 2023");
        olympicFrame.setSize(900, 700);//Width, Height of the JFrame
        olympicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        olympicFrame.setLocationRelativeTo(null);//Make the JFrame appear at the center of the screen
        olympicFrame.setVisible(true);// Make the JFrame visible
    }
}


class Olympic {
    private int NO = 10;
    private String country;
    private double[] score = new double[NO];
    private int rank;

    public Olympic(String country) { // Constructor
        processScores();// Generate random scores for this country
        this.country = country;
    }

    public Olympic(Olympic oly) {    // Copy constructor
        this(oly.country);
    }

    public void processScores() {
        for (int i = 0; i < getScoreArray().length; i++)
            score[i] = Math.random() * 100;// Math.random() return double type grater than or equal 0.0 and less than 1.0
    }

    public double totalScores() { // Calculate the total scores for the country
        return Arrays.stream(score).sum();
    }

    public void set(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return country;
    }

    private double[] getScoreArray() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("Rank %d: %s (%.2f)", getRank(), getName(), totalScores());
    }
}

class OlympicFrame extends JFrame {

    private static final long serialVersionUID = -28758456412791483L;
    private JButton[] jbArray = new JButton[12];
    final String[] countryArray = {	"USA", 		"SPAIN", 	"CHINA",
            "JAPAN", 	"ITALY", 	"GERMANY",
            "FRANCE", 	"BRAZIL", 	"NETHERLAND",
            "POLAND", 	"RUSSIA", 	"UKRAINE"};
    ArrayList<Olympic> alist = new ArrayList<>();

    public OlympicFrame() {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(4, 3, 1, 1));// rows, columns, horizontal and vertical gaps

        constructAList();
        String finalRanking = getFinalRanking();
        for (int i = 0; i < jbArray.length; i++) {
            String country = countryArray[i];
            JButton jB = new JButton(country);
            jB.setIcon(new ImageIcon("src//"+ (i+1) + ".jpg"));
            jB.setHorizontalTextPosition(SwingConstants.CENTER);
            jB.setVerticalTextPosition(SwingConstants.BOTTOM);

            jB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    jB.setText(country + " ==> Rank: "  + getRank(country));
                    jB.setForeground(Color.BLUE);
                    JOptionPane.showMessageDialog(null, finalRanking, "Miss World 2020", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src//trophy.jpg"));
                }
            });

            jbArray[i] = jB;
            jpanel.add(jbArray[i]);// Add the button to the panel
        }

        add(jpanel);// Add the panel to the frame
    }

    private void constructAList() {
        for (int i = 0; i < countryArray.length; i++) {
            alist.add(new Olympic(countryArray[i]));// Create Olympic objects and add them to the list
        }
    }

    private int getRank(String country) {
        for (Olympic item : alist) {
            if(country != null && country.equals(item.getName()))
                return  item.getRank();// Get the rank of the specified country
        }

        return 0;
    }


    private int getRank(double[] scoreArray, double d) {
        for (int i = 0; i < scoreArray.length; i++) {
            if(scoreArray[i] == d)
                return  alist.get(i).getRank();// Get the rank of the country with the specified score
        }

        for (Olympic olympic : alist) {
            if(olympic.totalScores() == d)
                return olympic.getRank();// Get the rank of the country with the specified total scores
        }
        return 0;
    }

    private String getFinalRanking() {
        Collections.sort(alist, (o1, o2) -> o1.totalScores() < o2.totalScores() ? 1 : o1.totalScores() == o2.totalScores() ? 0 : -1);

        int rank=1;
        String finalRanking = "FINAL RANKING\n\n";
        for (Olympic olympic : alist) {
            olympic.set(rank++);
            finalRanking += olympic.toString() + "\n";
        }

        return finalRanking;
    }

    private String getCountry(ArrayList<Olympic> alist, int n) {
        for (Olympic item : alist) {
            if (item.getRank() == n)
                return item.getName();
        }
        return null;
    }

    private double getScores(ArrayList<Olympic> alist, String name) {
        for (Olympic item : alist) {
            if (name != null && name.equals(item.getName()))
                return item.totalScores();
        }
        return 0;
    }
}


