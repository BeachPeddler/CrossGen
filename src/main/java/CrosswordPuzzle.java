public class CrosswordPuzzle {
    public static char empty = '\u0000';
    public UserBag wordsAdded;
    public char[][] crossword;
    public int height;
    public int width;

    //Default constructor, only utilized for CrossGenFactory.crosswordBest
    public CrosswordPuzzle() {
        this.crossword = new char[1][1];
        height = 1;
        width = 1;
    }

    //Creates 2D array of gridHeight(y) height and gridWidth(x) length
    //Clones wordsBag to new UserBag of new PuzzleWords for this specific puzzle.
    public CrosswordPuzzle(UserBag wordsBag, int gridHeight, int gridWidth) {
        this.crossword = new char[gridHeight][gridWidth];
        this.wordsAdded = new UserBag(wordsBag.getCurrentSize());
        height = gridHeight;
        width = gridWidth;
    }
    //Returns a score based on width:height ratio, # of squares which are filled, and # of squares left blank in the puzzle.
    public double generateScore() {
        double score = 0;
        double lengthRatio;
        int squaresFilled = 0;
        int squaresEmpty = 0;
        double fillRatio;

        lengthRatio = ((double) this.crossword.length /this.crossword[0].length);
        if (this.crossword.length > this.crossword[0].length) { //Ratio should be <1.
            lengthRatio = ((double) this.crossword[0].length / this.crossword.length);
        }
        for (int y = 0; y < (this.crossword.length); y++) {
            for (int x = 0; x < this.crossword[0].length; x++) {
                if (this.crossword[y][x] == '\u0000') { //should be default char in array
                    squaresEmpty++;
                }
                else {
                    squaresFilled++;
                }
            }
        }
        fillRatio = (double) (squaresFilled /squaresEmpty);
        score = (lengthRatio * 5) + (fillRatio * 10); //Gives higher weight to density over volume.
        if(!wordsAdded.isFull()) {
            score = 0;
        }
        return score;
    }


    //Method places a single word onto existing 2D crossword array
    //addedWord references object passed as parameter.
    public void placeWord(PuzzleWord addedWord, int x, int y, int junctionIndex) {
        char[] wordArray = addedWord.getWordString().toCharArray();
        if (addedWord.goesDown()) {
            for (int i = 0; i < addedWord.getWordString().length(); i++) {
                crossword[y+i-junctionIndex][x] = wordArray[i];
            }
            addedWord.setPosition(x, y-junctionIndex);
        }
        else {
            for (int i = 0; i < addedWord.getWordString().length(); i++) {
                crossword[y][x+i-junctionIndex] = wordArray[i];
            }
            addedWord.setPosition(x-junctionIndex, y);
        }
        wordsAdded.add(addedWord.clone());
    }

    public boolean canPlace(PuzzleWord checkWord, int x, int y, int junctionIndex) {
        setWordDirection(checkWord, x, y);
        if (checkWord.goesDown()) {
            if (((y-junctionIndex) < 0) || (y+checkWord.getWordString().length()-junctionIndex > (this.crossword.length - 1))) {
                return false;
            }
            for (int i = 0; i < checkWord.getWordString().length(); i++) {
                if (x+1 >= this.crossword[0].length) {
                    return (crossword[y+i-junctionIndex][x-1]) != empty;
                }
                else if ((x-1) < 0) {
                    return (crossword[y+i-junctionIndex][x+1]) != empty;
                }
                else if (((y+i)-junctionIndex) == y) {
                    continue;
                }
                else if (((crossword[y+i-junctionIndex][x]) != empty) && (crossword[y+i-junctionIndex][x] != checkWord.getWordString().charAt(i))){
                    return false;
                }
                else if ((crossword[y+i-junctionIndex][x+1] != empty) || ((crossword[y+i-junctionIndex][x-1]) != empty) || ((crossword[y+i-junctionIndex][x]) != empty)){
                    return false;
                }
                if (crossword[y-junctionIndex-1][x] != empty) {
                    return false;
                }
                if (crossword[y-junctionIndex+checkWord.getWordString().length()][x] != empty) {
                    return false;
                }
            }
        }
        else {
            if ((x-junctionIndex < 0) || (x+checkWord.getWordString().length()-junctionIndex) >= crossword[0].length) {
                return false;
            }
            for (int i = 0; i < checkWord.getWordString().length(); i++) {
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
                if (crossword[y][x - junctionIndex + checkWord.getWordString().length()] != empty) {
                    return false;
                }
            }
        }
        return true;
    }

    public void cropGrid(){
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
        this.crossword = croppedPuzzle;
        this.height = crossword.length;
        this.width = crossword[0].length;
    }

    public void setWordDirection(PuzzleWord puzzleWord, int x, int y) {
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
        puzzleWord.setDirection(downDirection);
    }

    public char getValueOf(int x, int y) {
        return crossword[y][x];
    }

    public void setWordOrder() {
        PuzzleWord[] orderedArray = this.wordsAdded.toArray();


        //Assign Values for String output, increasing top to bottom, then left to right.
        PuzzleWord temp;
        PuzzleWord nextHighestWord;
        for (int i = 0; i < wordsAdded.bagSize; i++) {
            nextHighestWord = orderedArray[i];
            for (int j = i+1; j < wordsAdded.bagSize; j++) {
                if (orderedArray[j].getY() < nextHighestWord.getY()) {
                    temp = nextHighestWord;
                    nextHighestWord = orderedArray[j];
                    orderedArray[j] = temp;
                }
                else if (orderedArray[j].getY() == nextHighestWord.getY()) {
                   if (orderedArray[j].getX() < nextHighestWord.getX()) {
                       temp = nextHighestWord;
                       nextHighestWord = orderedArray[j];
                       orderedArray[j] = temp;
                    }
                }
                orderedArray[i] = nextHighestWord;
            }
        }
        for (int i = 0; i < orderedArray.length; i++) {
            System.out.println(orderedArray[i].getWordString() + ": " + orderedArray[i].getWordNumber() + "  X:" + orderedArray[i].getX() + " Y:" + orderedArray[i].getY());
        }
        //Update all x/y coordinates of PuzzleWords in crossword.
        int shiftUp;
        int shiftLeft;
        int indexOfX = 0;
        for (int i = 0; i< this.crossword[0].length; i++) {
            if (this.crossword[0][i] != empty) {
                indexOfX = i;
                break;
            }
        }
        shiftLeft = (orderedArray[0].getX() - indexOfX);
        shiftUp = orderedArray[0].getY();
        for (int i = 0; i < wordsAdded.bagSize; i++) {
            orderedArray[i].setPosition((orderedArray[i].getX()-shiftLeft),(orderedArray[i].getY() - shiftUp));
        }

        //Adds indices to each PuzzleWord, accounting for those which share a start point.
        int wordIndex = 1;
        for (int i = 0; i < wordsAdded.bagSize; i++) {
            orderedArray[i].setWordNumber(wordIndex);
            if (i < wordsAdded.bagSize - 1) {
                if ((orderedArray[i + 1].getY() == orderedArray[i].getY()) && (orderedArray[i + 1].getX() == orderedArray[i].getX())) {
                    orderedArray[i+1].setWordNumber(wordIndex);
                    i++;
                }
            }
            System.out.println(orderedArray[i].getWordString() + ": " + orderedArray[i].getWordNumber());
            wordIndex++;
        }
    }
}
