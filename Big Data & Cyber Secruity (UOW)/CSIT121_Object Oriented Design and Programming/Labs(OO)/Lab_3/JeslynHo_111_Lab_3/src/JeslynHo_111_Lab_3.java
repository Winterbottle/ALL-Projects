
//Name: Jeslyn Ho Ka Yan
//Tutorial group:121T05
//JDK version: 11.016
//Declaration: This is my work and I have not copied anyone's work, I am willing to accept whatever penalty given to me.


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout; //for arranging components
import java.awt.Toolkit; // for the image
import java.awt.Image; // for image manipulation
import javax.swing.ImageIcon; // Import ImageIcon
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;


public class JeslynHo_111_Lab_3 extends JFrame {
    private final JTextField nameField;
    private final JTextField dobField;
    private final JTextField emailField;
    private final JTextField[] comments; // An array to store comments

    public static void main(String[] args) {
        JeslynHo_111_Lab_3 frame = new JeslynHo_111_Lab_3();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(280, 450);
        frame.setVisible(true);
    }

    public JeslynHo_111_Lab_3() {
        super("Introduction to myself");
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField("Jeslyn Ho Ka Yan", 15);
        nameField.setEditable(false);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobField = new JTextField("30 December 2004", 15);
        dobField.setEditable(false);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField("kyjh095@uowmail.edu.au", 15);
        emailField.setEditable(false);

        add(nameLabel);
        add(nameField);// add nameField to JFrame

        add(dobLabel);
        add(dobField);// add dobField to JFrame

        add(emailLabel);
        add(emailField);// add emailField to JFrame

        try {
            // Load the image using Toolkit
            Image pfp = Toolkit.getDefaultToolkit().getImage(JeslynHo_111_Lab_3.class.getResource("smile.jpg"));
            pfp = pfp.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

            JLabel picLabel = new JLabel(new ImageIcon(pfp));
            picLabel.setToolTipText("I am a monkey"); //when the mouse hover over the pic, this will display

            add(picLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        comments = new JTextField[2]; // Create an array of JTextField for comments
        JLabel commentsLabel = new JLabel("My comments to the subject, will update further");
        comments[0] = new JTextField("121 is hard", 15);
        comments[1] = new JTextField("More examples if possible", 15);

        add(commentsLabel);
        for (JTextField comment : comments) {
            add(comment);
        }

        InfoFieldHandler handler = new InfoFieldHandler(); // register event handlers
        nameField.addActionListener(handler);
        dobField.addActionListener(handler);
        emailField.addActionListener(handler);
        for (JTextField comment : comments) {
            comment.addActionListener(handler);
        }
    }

    private class InfoFieldHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String string = "";

            if (event.getSource() == comments[0] || event.getSource() == comments[1])
                string = String.format("Summary of your changes:%n1. %s%n2. %s", comments[0].getText(), comments[1].getText());

            JOptionPane.showMessageDialog(null, string,"My suggesttion to the course", JOptionPane.WARNING_MESSAGE);
        }
    }

}











