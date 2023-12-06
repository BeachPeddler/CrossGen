import java.util.Arrays;

public class CrossGenFactory {
    final static int GRID_DEFAULT = 40; //Sets dimensions of default grid as a square
    static char empty = '\u0000';
    static char[][] crosswordBest = {{1,1},{2,2}};

    //Generates a crossword puzzle through iterative placement.
    //Mostly done, will require testing.

    public static char[][] generate(PuzzleWord[] wordArray, int firstWord) {
        PuzzleWord[] tempWordArray = wordArray;
        boolean placementOK;
        boolean emptyArray = false;
        boolean exitLoop = false;
        int loopCount = 0;
        //This begins the crossword puzzle with a word in the upper-left-hand
        char[][] crossword = placeWord(tempWordArray[firstWord].getWordString(), new char[GRID_DEFAULT][GRID_DEFAULT], (GRID_DEFAULT/2), (GRID_DEFAULT/2), 0);
        tempWordArray[firstWord] = null;
        while (!exitLoop) {
            for (int i = 0; i < tempWordArray.length; i++) {
                wordLoop:
                if (tempWordArray[i] != null) {
                    String word = tempWordArray[i].getWordString();
                    char[] charArray = word.toCharArray();
                    for (int j = 0; j < word.length(); j++) {
                        char currChar = charArray[j];
                        for (int y = 0; y < crossword.length; y++) {
                            for (int x = 0; x < crossword[0].length; x++) {
                                if (crossword[y][x] == currChar) {
                                    placementOK = canPlace(word, crossword, x, y, j);
                                    if (placementOK) {
                                        crossword = placeWord(word, crossword, x, y, j);
                                        tempWordArray[i] = null;
                                        break wordLoop;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            loopCount++;
            checkArray:
            for (int i = 0; i < tempWordArray.length; i++) {
                if (tempWordArray[i] != null) {
                    emptyArray = false;
                    break checkArray;
                }
                else {
                    emptyArray = true;
                }
            }

            exitLoop = (emptyArray || (loopCount > 300));
        }
        return crossword;
    }


    //Creates number of crossword puzzles equal to maximum word count and returns one with the highest score.
    //Finished, needs testing
    public static void generateManyPuzzles(UserBag wordsBag) { //input: words, maxWords
        double maxScore = 0;
        PuzzleWord[] wordsArray = wordsBag.toArray();
        char[][] bestPuzzle = null;
        char[][] crossword = {};
            for (int i = 0; i < wordsBag.getCurrentSize(); i++) {
                wordsArray = wordsBag.toArray();
                crossword = generate(wordsArray, i);
                crossword = cropGrid(crossword);
                double score = generateScore(crossword);
                if (score > maxScore) {
                    maxScore = score;
                    bestPuzzle = crossword;
                }
            }
            crosswordBest = bestPuzzle;
    }


    //Method places a single word onto existing 2D crossword array
    //Finished, needs testing
    public static char[][] placeWord(String word, char[][] crossword, int x, int y, int junctionIndex) {
        char[] wordArray = word.toCharArray();
        boolean downDirection = false;
        if (x == 0) {
            if ((crossword[y][1] != empty)) {
                downDirection = true;
            }
        }
        else if ((x+1) >= crossword[0].length) {
            if ((crossword[y][x - 1] != empty)) {
                downDirection = true;
            }
        }
        else {
            if (y == 0) {
                downDirection = (crossword[0][x - 1] != empty) || (crossword[0][x + 1] != empty);
            }
            else {
                downDirection = ((crossword[y][x - 1] != empty) && (crossword[y - 1][x] == empty) || (crossword[y - 1][x] == empty) && (crossword[y][x + 1] != empty));
            }
        }
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

    public static boolean canPlace(String word, char[][] crossword, int x, int y, int junctionIndex) {
        boolean downDirection = false;
        if (x == 0) {
            if ((crossword[y][1] != empty)) {
                downDirection = true;
            }
        }
        else if ((x+1) >= crossword[0].length) {
            if ((crossword[y][x - 1] != empty)) {
                downDirection = true;
            }
        }
        else {
            if (y == 0) {
                downDirection = (crossword[0][x - 1] != empty) || (crossword[0][x + 1] != empty);
            }
            else {
                downDirection = ((crossword[y][x - 1] != empty) && (crossword[y - 1][x] == empty) || (crossword[y - 1][x] == empty) && (crossword[y][x + 1] != empty));
            }
        }
        if (downDirection) {
            if (((y-junctionIndex) < 0) || (y+word.length()-junctionIndex > (crossword.length - 1))) {
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if (x+1 >= crossword[0].length) {
                    return (crossword[y+i-junctionIndex][x-1]) != empty;
                }
                else if ((x-1) < 0) {
                    return (crossword[y+i-junctionIndex][x+1]) != empty;
                }
                else if (((y+i)-junctionIndex) == y) {
                    continue;
                }
                else if ((crossword[y+i-junctionIndex][x+1] != empty) || ((crossword[y+i-junctionIndex][x-1]) != empty) || ((crossword[y+i-junctionIndex][x]) != empty)){
                    return false;
                }
                if (crossword[y-junctionIndex-1][x] != empty) {
                    return false;
                }
                if (crossword[y-junctionIndex+word.length()][x] != empty) {
                    return false;
                }
            }
        }
        if (!downDirection) {
            if ((x-junctionIndex < 0) || (x+word.length()-junctionIndex) >= crossword[0].length) {
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if (y-1 < 0) {
                    return crossword[y+1][x+i-junctionIndex] != empty;
                }
                else if (y+1 >= crossword.length) {
                    return crossword[y-1][x+i-junctionIndex] != empty;
                }
                else if (((x+i)-junctionIndex) == x) {
                    continue;
                }
                if ((crossword[y+1][x+i-junctionIndex] != empty) || ((crossword[y-1][x+i-junctionIndex]) != empty) || ((crossword[y][x+i-junctionIndex]) != empty)){
                    return false;
                }
                if (crossword[y][x-junctionIndex-1] != empty) {
                    return false;
                }
                if (crossword[y][x - junctionIndex + word.length()] != empty) {
                    return false;
                }
            }
        }
        return true;
    }

    //This takes a 2D char array and crops it down by removing rows/columns which contain no characters.
    public static char[][] cropGrid(char[][] crossword){
        boolean emptyRow = true;
        boolean emptyColumn = true;
        int rowsEmpty = 0;
        int columnsEmpty = 0;
        int lastRow = 0;
        int lastColumn = 0;


        for (int y = 0; y < crossword.length; y++){
            for (int x = 0; x < crossword[0].length; x++) {
                if (crossword[y][x] != empty) {
                    lastRow = y;
                    emptyRow = false;
                    break;
                }
            }
            if (emptyRow) {
                rowsEmpty++;
            }
            emptyRow = true;
        }

        for (int x = 0; x < crossword[0].length; x++){
            for (int y = 0; y < crossword.length; y++) {
                if (crossword[y][x] != empty) {
                    lastColumn = x;
                    emptyColumn = false;
                    break;
                }
            }
            if (emptyColumn) {
                columnsEmpty++;
            }
            emptyColumn = true;
        }

        //New array formed with
        char[][] croppedPuzzle = new char[(crossword.length)-rowsEmpty][crossword[0].length - columnsEmpty];
        for (int y = (croppedPuzzle.length - 1); y >= 0; y--){
            for (int x = (croppedPuzzle[0].length - 1); x >= 0; x--){
                int oldX = lastColumn + (x - (croppedPuzzle[0].length - 1));
                int oldY = lastRow + (y - (croppedPuzzle.length - 1));
                croppedPuzzle[y][x] = crossword[oldY][oldX];
            }
        }
        return croppedPuzzle;
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
        for (int y = 0; y < (crossword.length); y++) {
            for (int x = 0; x < crossword[0].length; x++) {
                if (crossword[y][x] == '\u0000') { //should be default char in array
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
