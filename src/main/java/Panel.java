import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

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

    public void clearPuzzle(CrosswordPuzzle crossword) {
        removeAll();
        setLayout(new GridLayout(crossword.height, crossword.width));
        JTextField[][] textFields = new JTextField[crossword.height][crossword.width];
        PuzzleWord[] wordArray = crossword.wordsAdded.toArray();


        for (int y = 0; y < crossword.height; y++) {
            for (int x = 0; x < crossword.width; x++) {
                char c = crossword.getValueOf(x, y);
                if (c != '\u0000') {
                    textFields[y][x] = new JTextField(" ");
                    textFields[y][x].setFont(textFields[y][x].getFont().deriveFont(20.0f));
                    add(textFields[y][x]);
                }
                else {
                    add(new JLabel());
                }
            }
        }
        for (int i = 0; i < wordArray.length; i++) {
            textFields[wordArray[i].getY()][wordArray[i].getX()].setText(wordArray[i].getWordNumber());
        }
      getParent().validate();
      repaint();
    }

    public void setTable(CrosswordPuzzle crossword) {
        removeAll();
        setLayout(new BorderLayout());
        PuzzleWord[] wordArray = crossword.wordsAdded.toArray();
        PuzzleWord[] cloneArray = wordArray.clone();
        int minValue = 0;
        String[] columnNames = {"#", "Direction", "Clue"};
        String[][] data = new String[cloneArray.length][3];
        for (int i = 0; i < cloneArray.length; i++) {
            for (int j = 0; j < cloneArray.length; j++) {
                if (cloneArray[j] != null) {
                    if (cloneArray[minValue] == null) {
                        minValue = j;
                    }
                    if (cloneArray[j].wordNumber < cloneArray[minValue].wordNumber) {
                        minValue = j;
                    }
                }
            }
            data[i] = new String[]{String.valueOf(cloneArray[minValue].getWordNumber()), cloneArray[minValue].getDirection(), cloneArray[minValue].getWordHint()};
            cloneArray[minValue] = null;
        }
        JTable wordTable = new JTable(data, columnNames);
        this.add(wordTable);

        getParent().validate();
        repaint();
    }
}
