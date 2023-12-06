import java.util.Arrays;

public class UserBag implements BagInterface<PuzzleWord> {
    public static int bagSize = 0;// Array size
    public final int[] letterFrequency = new int[26];//Alphabet size
    public static PuzzleWord[] wordBag;


    public UserBag(int bagCapacity){// Base Constructor
        wordBag = new PuzzleWord[bagCapacity];
    }
    //updated the add method, i was getting errors while testing and had to change it

    //Adds PuzzleWord object to bag
    @Override
    public boolean add(PuzzleWord newWord) {
        if (bagSize < wordBag.length) {// checks if there is space in 'wordBag'
            for (int i = 0; i < wordBag.length; i++) {
                if (wordBag[i] == null) {
                    wordBag[i] = newWord; // add new word
                    bagSize++;// increment bagSize
                    return true;
                }
            }
        }
        return false;// if there is no space in the bag
    }

    //Returns # of PuzzleWords in bag.
    @Override
    public int getCurrentSize() {
        return bagSize;
    }//end getCurrentSize


    //Removes a specific PuzzleWord object from bag
    @Override
    public boolean remove(PuzzleWord anEntry) {
        for (int i = 0; i < wordBag.length; i++) {
            if (wordBag[i] == anEntry) {
                wordBag[i] = null;
                bagSize--;
                return true; // Word found and removed
            }
        } //end for
        return false;
    }//end remove


    //Sets all array elements of bag to null
    @Override
    public void clear() {
        //end for
        Arrays.fill(wordBag, null);
        bagSize = 0;
    } //end clear

    //isEmpty
    @Override
    public boolean isEmpty(){
        return (bagSize == 0);//Returns True if getCurrentSize=0
    }// end isEmpty

    @Override
    public PuzzleWord remove() {
        PuzzleWord removedWord;
        for (int i = 0; i < wordBag.length; i++) {
            if (wordBag[i] != null) {
                removedWord = wordBag[i];
                wordBag[i] = null;
                bagSize--;
                return removedWord;
            }
        }
        return null;
    }// end remove

    public int getFrequencyOf(PuzzleWord anEntry) {
        int freq= 0;// initialize freq to 0
        for(int i=0; i < bagSize; i++){
            if(wordBag[i] == anEntry){
                freq++;
            }// end if
        }//end for
        return freq;
    }// end getFrequencyOf


    @Override
    public boolean contains(PuzzleWord anEntry) {
        for(int i=0; i<bagSize; i++){
            if((wordBag[i] == anEntry)){// Null check needed for NullPointerException?
                return true;
            }// end if
        }// end for
        return false;
    }// end contains

    //Creates new array sized to only fit current content of bag. Ranks contents of array and sorts.
    @Override
    public PuzzleWord[] toArray() {
        PuzzleWord[] resultArray= new PuzzleWord[bagSize];
        int arrayIndex = 0;
        this.shiftContents();
        this.orderContents();
        for (int i = 0; i < wordBag.length; i++) {
            if (wordBag[i] != null) {
                resultArray[arrayIndex] = wordBag[i];
                arrayIndex++;
            }
        }
        return resultArray;
    }// end toArray

    public String[] toWordArray() {
        String[] resultArray= new String[bagSize];
        int arrayIndex = 0;
        for (int i = 0; i < wordBag.length; i++) {
            if (wordBag[i] != null) {
                resultArray[arrayIndex] = wordBag[i].getWordString();
                arrayIndex++;
            }
        }
        return resultArray;
    }// end toArray

    //Shifts all objects in wordBag so that all entries are towards front of array.
    public void shiftContents() {
        for (int i = 0; i < (wordBag.length - 1); i++) {
            if (wordBag[i] == null) {
                for (int j = (i+1); j < wordBag.length; j++) {
                    if (wordBag[j] != null) {
                        wordBag[i] = wordBag[j];
                        wordBag[j] = null;
                    }
                }
            }
        }
    }

    //Orders bag from lowest score to highest score.
    public void orderContents() {
        this.countLetterFrequencies();
        this.calculateWordScores();
        PuzzleWord temp;
        for (int i = 0; i < (wordBag.length - 1); i++) {
            for (int j = (i+1); j < wordBag.length; j++) {
                if (wordBag[i].getWordScore() > wordBag[j].getWordScore()) {
                    temp = wordBag[i];
                    wordBag[i] = wordBag[j];
                    wordBag[j] = temp;
                }
            }
        }
    }
    public void countLetterFrequencies() {

        for (int i = 0; i < wordBag.length; i++) {
            PuzzleWord puzzleWord = wordBag[i];// iterate through each element
            if (puzzleWord !=null) { //check if PuzzleWord is not null
                String word= puzzleWord.getWordString();// get word from PuzzleWord
                for (int j = 0; j < word.length(); j++) {//iterate through the word
                    char c = Character.toLowerCase(word.charAt(j));
                    if (c >= 'a' && c <= 'z') {//checks char
                        letterFrequency[c - 'a']++; //update frequency counter
                    }//end if
                }// end for
            }//end if
        }//end for
    }// end countLetterFrequency


    public void calculateWordScores() {
        for (int i = 0; i < wordBag.length; i++) {
            PuzzleWord puzzleWord = wordBag[i];// iterate through each element
            if (puzzleWord != null) {//null check
                String word = puzzleWord.getWordString();//get word from puzzle word
                int score = 0;// initialize score at 0

                for (int j = 0; j < word.length(); j++) {//iterate through word
                    char c = Character.toLowerCase(word.charAt(j));
                    if (c >= 'a' && c <= 'z') {//check char
                        score += letterFrequency[c - 'a'];// update score from letter frequency array
                    }//end if
                }//end for
                puzzleWord.setWordScore(score);
            }//end if
        }//end for
    }//end print word score
}