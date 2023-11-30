public class CrossGenFactory {
    final static int GRID_DEFAULT = 20; //Sets dimensions of default grid as a square
    static char empty = '\u0000';

    //Generates a crossword puzzle through iterative placement.
    //Mostly done, will require testing.
    public char[][] generate(UserBag wordsBag) {
        PuzzleWord[] wordArray = wordsBag.toArray();
        boolean placementOK;
        int loopCount = 0;
        //This begins the crossword puzzle with a word in the upper-left-hand
        char[][] crossword = placeWord(wordArray[0].getWordString(), new char[GRID_DEFAULT][GRID_DEFAULT], 0, 0, 0);
        while (!wordsBag.isEmpty() || (loopCount < 10) ) {
            for (int i = 1; i < wordArray.length; i++) {
                if (wordArray[i] != null) {
                    String word = wordArray[i].getWordString();
                    char[] charArray = word.toCharArray();
                    for (int j = 0; j < word.length(); j++) {
                        char currChar = charArray[j];
                        for (int y = 0; y < crossword.length; y++) {
                            for (int x = 0; x < crossword[0].length; x++) {
                                if (crossword[y][x] == currChar) {
                                    placementOK = canPlace(word, crossword, x, y, j);
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
            loopCount++;
        }
                return crossword;
    }

    //Creates number of crossword puzzles equal to maximum word count and returns one with the highest score.
    //Finished, needs testing
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

    //This would try multiple attempts at word placement for best score when creating crossword.
    public char[][] placeWordsRepeatedly(UserBag wordsBag) {
        return new char[0][0];
    }

    //Method places a single word onto existing 2D crossword array
    //Finished, needs testing
    public char[][] placeWord(String word, char[][] crossword, int x, int y, int junctionIndex) {
        char[] wordArray = word.toCharArray();
        boolean downDirection = (crossword[y][x - 1] != empty) && (crossword[y][x + 1] != empty);
        if (downDirection) {
            for (int i = 0; i < word.length(); i++) {
                crossword[y+i-junctionIndex][x] = wordArray[i];
            }
        }
        else {
            for (int i = 0; i < word.length(); i++) {
                crossword[y][x+i-junctionIndex] = wordArray[i];
            }
        }
        return crossword;
    }

    public boolean canPlace(String word, char[][] crossword, int x, int y, int junctionIndex) {
        boolean downDirection = (crossword[y][x - 1] != empty) && (crossword[y][x + 1] != empty);
        if (downDirection) {
            if ((y-junctionIndex < 0) || (y+word.length()) > crossword[0].length) {
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if ((crossword[y+i-junctionIndex][x+1] != empty) || ((crossword[y+i-junctionIndex][x-1]) != empty)){
                    return false;
                }
            }
        }
        else {
            if ((x-junctionIndex < 0) || (x+word.length()) > crossword.length) {
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if ((crossword[y+1][x+i-junctionIndex] != empty) || ((crossword[y-1][x+i-junctionIndex]) != empty)){
                    return false;
                }
            }
        }
        return true;
    }

    //This takes a 2D char array and crops it down by removing rows/columns which contain no characters.
    public char[][] cropGrid (char[][] crossword){
        boolean emptyRow = true;
        boolean emptyColumn = true;
        int xMax = crossword.length;
        int xMin = 0;
        int xBarrier = 0;
        int yMax = crossword[0].length;
        int yMin = 0;
        int yBarrier = 0;

        for (int i = 0; i < crossword[0].length; i++){
            for (int j = 0; j < crossword.length; j++){
                if (crossword[i][j] != empty){
                    emptyRow = false;
                }
            }
            if (emptyRow){
                yMin++;
            }
            emptyRow = true;
        }

        for (int i = 0; i < crossword[0].length; i++){
            for (int j = 0; j < crossword.length; j++){
                if (crossword[i][j] != empty){
                    yBarrier = i;
                    break;
                }
            }
            if (yBarrier != 0){
                break;
            }
        }

        for (int i = 0; i < crossword.length; i++){
            for (int j = 0; j < crossword[0].length; j++){
                if (crossword[i][j] != empty){
                    emptyColumn = false;
                }
            }
            if (emptyColumn){
                xMin++;
            }
            emptyColumn = true;
        }

        for (int i = 0; i < crossword.length; i++){
            for (int j = 0; j < crossword[0].length; j++){
                if (crossword[i][j] != empty){
                    xBarrier = i;
                    break;
                }
            }
            if (xBarrier != 0){
                break;
            }
        }

        char[][] clipCross = new char[yMax - yMin][xMax - xMin];

        for (int k = 0; k < clipCross.length; k++){
            for (int l = 0; l < clipCross[0].length; l++){
                int x = l + xBarrier;
                int y = k + yBarrier;
                clipCross[l][k] = crossword[x][y];
            }
        }

        return clipCross;
    }

    //Gives crossword a score based on width:height ratio, # of squares which are filled, and # of squares left blank.
    //Finished, needs testing.
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
        score = (lengthRatio * 10) + (fillRatio * 20);
        return score;
    }
}
