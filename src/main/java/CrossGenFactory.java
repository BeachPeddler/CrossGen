public class CrossGenFactory {

    public char[][] placeIteratively(UserBag wordsBag) {
        PuzzleWord[] wordArray = wordsBag.toArray();
        String word = wordArray[0].getWordString();
        int[][] crossword = new int[CrossGenMain.MAX_WORD_COUNT][CrossGenMain.MAX_WORD_COUNT];
                return null;
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
                if (crossword[i][j] == ' ') {
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
