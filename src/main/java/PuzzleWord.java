import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class PuzzleWord {
    private String newWord;
    private String wordHint;

    public int wordScore = 0;


    public PuzzleWord(String word, String hint) {
        newWord = word;
        wordHint = hint;
    }

    public void setWordScore(int score) {
        wordScore = score;
    }

}
