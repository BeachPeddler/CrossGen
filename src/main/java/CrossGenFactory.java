public class CrossGenFactory {
    final static int GRID_DEFAULT = 20; //Sets dimensions of default grid as a square
    static char empty = '\u0000';
    public char[][] generate(UserBag wordsBag) {
        PuzzleWord[] wordArray = wordsBag.toArray();
        boolean placementOK;
        char[][] crossword = placeWord(wordArray[0].getWordString(), new char[GRID_DEFAULT][GRID_DEFAULT], 0, 0, 0);
        while (!wordsBag.isEmpty()) {
            for (int i = 1; i < wordArray.length; i++) {
                String word = wordArray[i].getWordString();
                char[] charArray = word.toCharArray();
                for (int j = 0; j < word.length(); j++) {
                    char currChar = charArray[j];
                    for (int y = 0; y < crossword.length; y++) {
                        for (int x = 0; x < crossword[0].length; x++) {
                            if (crossword[y][x] == currChar) {
                                placementOK = canPlace(word, crossword, x, y);
                                if (placementOK) {
                                    crossword = placeWord(word, crossword, x, y, j);
                                    wordsBag.remove(wordArray[i]);
                                }
                            }
                        }
                    }
                }
            }
        }
                return crossword;
    }

    //Creates number of crossword puzzles equal to maximum word count and returns one with highest score.
    public char[][] generateManyPuzzles(UserBag wordsBag) { //input: words, maxWords
        double maxScore = 0;
        char[][] bestPuzzle = null;
        for (int i = 0; i < CrossGenMain.MAX_WORD_COUNT; i++) {
           char[][] crossword = generate(wordsBag);
            double score = generateScore(crossword);
            if (score > maxScore) {
                maxScore = score;
                bestPuzzle = crossword;
            }
        }
        return bestPuzzle;
    }

    public char[][] placeWordsRepeatedly(UserBag wordsBag) {
        return char[][];
    }

    public char[][] placeWord(String word, char[][] crossword, int x, int y, int junctionIndex) {
        char junction = crossword[y][x];
        char[] wordArray = word.toCharArray();
        boolean downDirection = (crossword[y][x - 1] != empty) && (crossword[y][x + 1] != empty);
        if (downDirection) {
            for (int i = 1; i < word.length(); i++) {
                crossword[y+i-junctionIndex][x] = wordArray[i];
            }
        }
        else {
            for (int i = 1; i < word.length(); i++) {
                crossword[y][x+i-junctionIndex] = wordArray[i];
            }
        }
        return crossword;
    }

    public boolean canPlace(String word, char[][] crossword, int x, int y) {
        return false;
    }

    //Gives crossword a score based on width:height ratio, # of squares which are filled, and # of squares left blank.
    public static double generateScore(char[][] crossword) {
        double score = 0;
        double lengthRatio;
        int squaresFilled = 0;
        int squaresEmpty = 0;
        double fillRatio;

        lengthRatio = ((double) crossword.length /crossword[0].length);
        if (crossword.length > crossword[0].length) { //Ratio should be <1.
            lengthRatio = ((double) crossword[0].length / crossword.length);
        }
        for (int i = 0; i < (crossword.length); i++) {
            for (int j = 0; j < crossword[0].length; j++) {
                if (crossword[i][j] == '\u0000') { //should be default char in array
                    squaresEmpty++;
                }
                else {
                    squaresFilled++;
                }
            }
        }
        fillRatio = (double) squaresFilled /squaresEmpty;
        score = (lengthRatio * 10) + (fillRatio + 20);
        return score;
    }
}
