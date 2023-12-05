import javax.swing.*;
import java.awt.*;

public class CrosswordPanel extends JPanel {

    public void setCrossword(char[][] array) {
        removeAll();
        int w = array.length;
        int h = array[0].length;
        setLayout(new GridLayout(w, h));
        JTextField[][] textFields = new JTextField[w][h];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                char c = array[x][y];
                if (c != 0) {
                    textFields[x][y] = new JTextField(String.valueOf(c));
                    textFields[x][y].setFont(textFields[x][y].getFont().deriveFont(20.0f));
                    add(textFields[x][y]);
                } else {
                    add(new JLabel());
                }
            }
        }
        getParent().validate();
        repaint();
    }
}
