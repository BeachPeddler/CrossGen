import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualizer
{

    public static void createAndShowGUI()
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().setLayout(new BorderLayout());

        JPanel container = new JPanel(new FlowLayout());
        final CrosswordPanel panel = new CrosswordPanel();
        container.add(panel);
        f.getContentPane().add(container, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                generate(panel);
            }
        });
        f.getContentPane().add(generateButton, BorderLayout.SOUTH);

        f.setSize(800, 800);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static void generate(CrosswordPanel panel) {
        CrosswordPuzzle crossword = CrossGenFactory.crosswordBest;
        panel.setCrossword(crossword);
    }

}


