
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

class Olympic {
    private int NO;
    private String country;
    private double[] scores;
    private int rank;

    public Olympic(String country) {
        this.NO = 12; // Assume 12 judges
        this.country = country;
        this.scores = new double[NO];
        this.rank = getRank();
    }

    public Olympic(Olympic oly) {
        this.NO = oly.NO;
        this.country = oly.getName();
        this.scores = oly.getScoresArray();
        this.rank = oly.getRank();
    }

    public void processScores() {
        for (int i = 0; i < 12; i++) {
            scores[i] = Math.random() * 100;
        }
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return country;
    }

    public double[] getScoresArray() {
        return scores;
    }

    public double totalScores() {
        double total = 0;
        for (double score : scores) {
            total += score;
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("%s ==> Rank: %d",getName(),getRank());
    }
}

class OlympicFrame extends JFrame {
    private final String[] countryArray = {"USA", "Spain", "China", "Japan", "Italy", "Germany", "France", "Brazil", "Netherlands", "Poland", "Russia", "Ukraine"};
    private ArrayList<Olympic> alist;
    private JButton[] jbArray;

    public OlympicFrame () {
        super("Olympic 2023");
        alist = new ArrayList<Olympic>();
        jbArray = new JButton[12];
        JPanel panel = new JPanel(new GridLayout(4, 3));

        constructAList();

        for (int i = 0; i < 12; i++) {
            JButton button = new JButton();
            String name = alist.get(i).getName();
            int rank = alist.get(i).getRank();

            button.setToolTipText(alist.get(i).getName());
            button.setText(name);

            jbArray[i] = button;

            String imageFile = "" + (i + 1) + ".jpg";
            Icon ic = new ImageIcon(imageFile);
            jbArray[i].setIcon(ic);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setForeground(Color.blue);
                    button.setText( name + "==> Rank: " + rank );
                    getFinalRanking();
                }
            });
            panel.add(button);
        }
        add(panel);
    }
    private int getRank(double[] scoreArray, double d){
        int rank = 0;
        for (int i = 0;i < scoreArray.length;i++){
            if (d == scoreArray[i]){
                rank = i +1;
                alist.get(i).setRank(rank);
                break;
            }
        }
        return rank;
    }
    private void constructAList() {
        for (String countryName : countryArray) {
            Olympic olympic = new Olympic(countryName);
            olympic.processScores();
            alist.add(olympic);
        }
    }

    private void getFinalRanking() {
        StringBuilder rankingText = new StringBuilder("FINAL RANKING\n\n");
        Collections.sort(alist, (r1, r2) -> Double.compare(r2.totalScores(), r1.totalScores()));

        for (int i = 0; i < 12; i++) {
            Olympic oly = alist.get(i);
            int rank = i + 1;
            oly.setRank(rank);
            String format = String.format("Rank %2d: %2s (%.2f)%n", rank, oly.getName(), oly.totalScores());
            rankingText.append(format);
        }
        Icon ic = new ImageIcon("trophy.png");
        JOptionPane.showMessageDialog(this, rankingText.toString(), "Miss World 2020", JOptionPane.PLAIN_MESSAGE, ic);
    }
    private String getCountry(ArrayList<Olympic> alist, int n) {
        if (n >= 1 && n <= alist.size()) {
            Olympic olympic = alist.get(n - 1);
            return olympic.getName();
        } else {
            return "Invalid rank";
        }
    }
    private double getScore(ArrayList<Olympic>alist, String name){
        double total = 0;
        for(int i = 0; i < alist.size();i++){
            if(alist.get(i).getName().equals(countryArray[i])){
                alist.get(i).processScores();
                total = alist.get(i).totalScores();
            }
        }
        return total;
    }
}
public class Lab_4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OlympicFrame frame = new OlympicFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);
});
}
}


 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

class Olympic {
    private int NO;
    private String country;
    private double[] scores;
    private int rank;

    public Olympic(String country) {
        this.NO = 12; // Assume 12 judges
        this.country = country;
        this.scores = new double[NO];
        this.rank = getRank();
    }

    public Olympic(Olympic oly) {
        this.NO = oly.NO;
        this.country = oly.getName();
        this.scores = oly.getScoresArray();
        this.rank = oly.getRank();
    }

    public void processScores() {
        for (int i = 0; i < 12; i++) {
            scores[i] = Math.random() * 100;
        }
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return country;
    }

    public double[] getScoresArray() {
        return scores;
    }

    public double totalScores() {
        double total = 0;
        for (double score : scores) {
            total += score;
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("%s ==> Rank: %d",getName(),getRank());
    }
}

class OlympicFrame extends JFrame {
    private final String[] countryArray = {"USA", "Spain", "China", "Japan", "Italy", "Germany", "France", "Brazil", "Netherlands", "Poland", "Russia", "Ukraine"};
    private ArrayList<Olympic> alist;
    private JButton[] jbArray;

    public OlympicFrame () {
        super("Olympic 2023");
        alist = new ArrayList<Olympic>();
        jbArray = new JButton[12];
        JPanel panel = new JPanel(new GridLayout(4, 3));

        constructAList();

        for (int i = 0; i < 12; i++) {
            JButton button = new JButton();
            String name = alist.get(i).getName();
            int rank = alist.get(i).getRank();

            button.setToolTipText(alist.get(i).getName());
            button.setText(name);

            jbArray[i] = button;

            String imageFile = "" + (i + 1) + ".jpg";
            Icon ic = new ImageIcon(imageFile);
            jbArray[i].setIcon(ic);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setForeground(Color.blue);
                    button.setText( name + "==> Rank: " + rank );
                    getFinalRanking();
                }
            });
            panel.add(button);
        }
        add(panel);
    }
    private int getRank(double[] scoreArray, double d){
        int rank = 0;
        for (int i = 0;i < scoreArray.length;i++){
            if (d == scoreArray[i]){
                rank = i +1;
                alist.get(i).setRank(rank);
                break;
            }
        }
        return rank;
    }
    private void constructAList() {
        for (String countryName : countryArray) {
            Olympic olympic = new Olympic(countryName);
            olympic.processScores();
            alist.add(olympic);
        }
    }

    private void getFinalRanking() {
        StringBuilder rankingText = new StringBuilder("FINAL RANKING\n\n");
        Collections.sort(alist, (r1, r2) -> Double.compare(r2.totalScores(), r1.totalScores()));

        for (int i = 0; i < 12; i++) {
            Olympic oly = alist.get(i);
            int rank = i + 1;
            oly.setRank(rank);
            String format = String.format("Rank %2d: %2s (%.2f)%n", rank, oly.getName(), oly.totalScores());
            rankingText.append(format);
        }
        Icon ic = new ImageIcon("trophy.png");
        JOptionPane.showMessageDialog(this, rankingText.toString(), "Miss World 2020", JOptionPane.PLAIN_MESSAGE, ic);
    }
    private String getCountry(ArrayList<Olympic> alist, int n) {
        if (n >= 1 && n <= alist.size()) {
            Olympic olympic = alist.get(n - 1);
            return olympic.getName();
        } else {
            return "Invalid rank";
        }
    }
    private double getScore(ArrayList<Olympic>alist, String name){
        double total = 0;
        for(int i = 0; i < alist.size();i++){
            if(alist.get(i).getName().equals(countryArray[i])){
                alist.get(i).processScores();
                total = alist.get(i).totalScores();
            }
        }
        return total;
    }
}
public class Lab_4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OlympicFrame frame = new OlympicFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}