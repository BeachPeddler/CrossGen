import javax.swing.*;
import java.awt.*;

public class CrosswordPanel extends JPanel {

    public void setCrossword(CrosswordPuzzle crossword) {
        removeAll();
        setLayout(new GridLayout(crossword.height, crossword.width));
        JTextField[][] textFields = new JTextField[crossword.height][crossword.width];

        for (int y = 0; y < crossword.height; y++) {
            for (int x = 0; x < crossword.width; x++) {
                char c = crossword.getValueOf(x, y);
                if (c != '\u0000') {
                    textFields[y][x] = new JTextField(String.valueOf(c));
                    textFields[y][x].setFont(textFields[y][x].getFont().deriveFont(20.0f));
                    add(textFields[y][x]);
                }
                else {
                    add(new JLabel());
                }
            }
        }
        getParent().validate();
        repaint();
    }
}
