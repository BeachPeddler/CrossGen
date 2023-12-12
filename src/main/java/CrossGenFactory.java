import java.util.Arrays;

public class CrossGenFactory {
    final static int GRID_DEFAULT = 100; //Sets dimensions of default grid as a square
    static char empty = '\u0000';
    static CrosswordPuzzle crosswordBest = new CrosswordPuzzle();

    //Generates a crossword puzzle through iterative placement.
    //Mostly done, will require testing.

    public CrosswordPuzzle generate(PuzzleWord[] thisArray, UserBag wordBag) {
        boolean exitLoop = false;
        int loopCount = 0;
        PuzzleWord[] tempWordArray = new PuzzleWord[thisArray.length];
        for (int i = 0; i < thisArray.length; i++) {
            tempWordArray[i] = thisArray[i].clone();
        }//Creates a temporary array containing clones of each PuzzleWord.
        CrosswordPuzzle crossword = new CrosswordPuzzle(wordBag, GRID_DEFAULT, GRID_DEFAULT);  //This begins the crossword puzzle with a word in the middle.
        crossword.placeWord(tempWordArray[0], (GRID_DEFAULT/2), (GRID_DEFAULT/2), 0);
        tempWordArray[0] = null;
        while (!exitLoop) {
            for (int i = 1; i < tempWordArray.length; i++) {
                wordLoop:
                if (tempWordArray[i] != null) {
                    String word = tempWordArray[i].getWordString();
                    char[] charArray = word.toCharArray();
                    for (int j = 0; j < word.length(); j++) {
                        char currChar = charArray[j];
                        for (int y = 0; y < crossword.height; y++) {
                            for (int x = 0; x < crossword.width; x++) {
                                if (currChar == crossword.getValueOf(x, y)) {
                                    if (crossword.canPlace(tempWordArray[i], x, y, j)) {
                                        crossword.placeWord(tempWordArray[i], x, y, j);
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
            exitLoop = (crossword.wordsAdded.isFull() || (loopCount > 300));
        }
        return crossword;
    }


    //Creates number of crossword puzzles equal to maximum word count and returns one with the highest score.
    //Finished, needs testing
    public void generateManyPuzzles(UserBag wordsBag) { //input: words, maxWords
        wordsBag.populateRecursive(wordsBag.bagSize, wordsBag.toArray());
        double maxScore = 0;
        CrosswordPuzzle bestPuzzle = null;
        CrosswordPuzzle crossword;
            for (int i = 0; i < wordsBag.allPermutations.length; i++) {
                PuzzleWord[] passedArray = new PuzzleWord[wordsBag.bagSize];
                for (int j = 0; j < wordsBag.bagSize; j++ ) {
                    passedArray[j] = wordsBag.allPermutations[i][j].clone();
                }
                crossword = generate(passedArray, wordsBag);
                System.out.println("Generated " + i);
                crossword.cropGrid();
                double score = crossword.generateScore();
                if (score > maxScore) {
                    maxScore = score;
                    bestPuzzle = crossword;
                }
            }
            crosswordBest = bestPuzzle;
    }
}
