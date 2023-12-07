import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class PuzzleWord {
    private String wordString;
    private String wordHint;
    private int wordScore = 0;
    private boolean downDirection; //Whether the word goes down (rather than across) in puzzle.
    private int yPosition;
    private int xPosition;
    private int wordNumber; //1-Across, 5-Down, etc.

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
        return wordString.toCharArray();
    }

    public PuzzleWord clone() {
        PuzzleWord clonedWord = new PuzzleWord(this.getWordString(), this.getWordHint());
        clonedWord.setWordScore(this.wordScore);
        clonedWord.downDirection = this.downDirection;
        clonedWord.setPosition(this.xPosition, this.yPosition);
        clonedWord.wordNumber = this.wordNumber;
        return clonedWord;
    }

    public void setDirection(boolean goesDown) {
        this.downDirection = goesDown;
    }

    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }
    public boolean goesDown() {
        return downDirection;
    }
}

