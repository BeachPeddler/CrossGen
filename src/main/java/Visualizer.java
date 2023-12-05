import javax.swing.*;
import java.awt.*;

public class Visualizer
{

    public static void createAndShowGUI(char[][] crossword)
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().setLayout(new BorderLayout());

        JPanel container = new JPanel(new FlowLayout());
        final CrosswordPanel panel = new CrosswordPanel();
        container.add(panel);
        f.getContentPane().add(container, BorderLayout.CENTER);
        panel.setCrossword(crossword);

        f.setSize(800, 800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}


