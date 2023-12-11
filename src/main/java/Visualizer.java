import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualizer
{

    public static void runPopUp()
    {
        JFrame popUpWindow = new JFrame();
        popUpWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        popUpWindow.getContentPane().setLayout(new BorderLayout());

        JPanel centerContainer = new JPanel(new GridLayout(1,2));
        final Panel crosswordPanel = new Panel();
        final Panel tablePanel = new Panel();
        centerContainer.add(crosswordPanel);
        centerContainer.add(tablePanel);
        popUpWindow.getContentPane().add(centerContainer, BorderLayout.CENTER);

        JPanel buttonContainer = new JPanel(new GridLayout(1, 2));
        JButton populateFields = new JButton("Populate");
        populateFields.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                generateCrosswordPanel(crosswordPanel);
                generateTablePanel(tablePanel);
            }
        });
        buttonContainer.add(populateFields);

        JButton clearPuzzle = new JButton("Clear");
        clearPuzzle.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clearPuzzlePanel(crosswordPanel);
            }
        });
        buttonContainer.add(clearPuzzle);

        popUpWindow.getContentPane().add(buttonContainer, BorderLayout.SOUTH);

        popUpWindow.setSize(800, 800);
        popUpWindow.setLocationRelativeTo(null);
        popUpWindow.setVisible(true);
    }

    private static void generateCrosswordPanel(Panel crosswordPanel) {
        CrosswordPuzzle crossword = CrossGenFactory.crosswordBest;
        crosswordPanel.setCrossword(crossword);
    }
    private static void generateTablePanel(Panel tablePanel) {
        CrosswordPuzzle crossword = CrossGenFactory.crosswordBest;
        tablePanel.setTable(crossword);
    }
    private static void clearPuzzlePanel(Panel crosswordPanel) {
        CrossGenFactory.crosswordBest.setWordOrder();
        CrosswordPuzzle crossword = CrossGenFactory.crosswordBest;
        crosswordPanel.clearPuzzle(crossword);
    }

}


