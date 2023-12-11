public class PuzzleWord {
    private String wordString;
    private String wordHint;
    private int wordScore = 0;
    private boolean downDirection; //Whether the word goes down (rather than across) in puzzle.
    private int yPosition;
    private int xPosition;
    public int wordNumber; //1-Across, 5-Down, etc.

    public PuzzleWord(String word, String hint) {
        this.wordString = word;
        this.wordHint = hint;
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
        clonedWord.downDirection = this.downDirection;
        clonedWord.setPosition(this.xPosition, this.yPosition);
        clonedWord.wordNumber = this.wordNumber;
        return clonedWord;
    }

    public void setDirection(boolean goesDown) {
        this.downDirection = goesDown;
    }
    public boolean goesDown() {
        return downDirection;
    }

    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    public int getY() {
        return yPosition;
    }
    public int getX() {
        return xPosition;
    }

    public void setWordNumber(int num) {
        this.wordNumber = num;
    }

    public String getWordNumber()  {
        return String.valueOf(wordNumber);
    }


    public String getDirection() {
        if (this.goesDown()) {
            return "Down";
        }
        return "Across";
    }
}

