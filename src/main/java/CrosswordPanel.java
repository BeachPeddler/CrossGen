import javax.swing.*;
import java.awt.*;

public class CrosswordPanel extends JPanel {

    public void setCrossword(char[][] array) {
        removeAll();
        int h = array.length;
        int w = array[0].length;
        setLayout(new GridLayout(h, w));
        JTextField[][] textFields = new JTextField[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                char c = array[y][x];
                if (c != '\u0000') {
                    textFields[y][x] = new JTextField(String.valueOf(c));
                    textFields[y][x].setFont(textFields[y][x].getFont().deriveFont(20.0f));
                    add(textFields[y][x]);
                } else {
                    add(new JLabel());
                }
            }
        }
        getParent().validate();
        repaint();
    }
}
