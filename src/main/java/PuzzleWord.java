import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class PuzzleWord {
    private String wordString;
    private String wordHint;
    public int wordScore = 0;


    public PuzzleWord(String word, String hint) {
        this.wordString = word;
        this.wordHint = hint;
    }

    public void setWordScore(int score) {
        this.wordScore = score;
    }
    public int getWordScore() {
        return this.wordScore;
    }

    public String getWordString() {
        return this.wordString;
    }
    public void setWordString(String word) {
        this.wordString = word;
    }

    public String getWordHint() {
        return this.wordHint;
    }
    public void setWordHint(String hint) {
        this.wordHint = hint;
    }

    public char[] toArray() {
        char[] wordArray = new char[wordString.length()];
        for (int i = 0; i<wordString.length(); i++) {
            wordArray[i] = wordString.charAt(i);
        }
        return wordArray;
    }
}
