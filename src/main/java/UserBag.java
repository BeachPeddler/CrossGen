

public class UserBag implements BagInterface<PuzzleWord> {
    public static int bagSize = 0;// Array size
    private final int[] letterFrequency = new int[26];//Alphabet size
    public static PuzzleWord[] wordBag;


    public UserBag(int bagCapacity){// Base Constructor
       wordBag = new PuzzleWord[bagCapacity];
    }

    //Adds PuzzleWord object to bag
    @Override
    public boolean add(PuzzleWord newWord){
        for (int i = 0; i < bagSize; i++) {//iterate through array
            if (wordBag[i] == null) {//if null
                wordBag[i] = newWord;//add new word
                bagSize++;
                return true;
            } //end if
        } //end for
        return false;
    } //end add

    //Returns # of PuzzleWords in bag.
    @Override
    public int getCurrentSize() {
        return bagSize;
    }//end getCurrentSize


    //Removes a specific PuzzleWord object from bag
    @Override
    public boolean remove(PuzzleWord anEntry) {
        for (int i = 0; i < bagSize; i++) {
            if (wordBag[i].equals(anEntry)) {
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
        for (int i = 0; i < bagSize; i++) {
            wordBag[i] = null;
        } //end for
        bagSize = 0;
    } //end clear

    //isEmpty
    @Override
    public boolean isEmpty(){
        return bagSize==0;//Returns True if getCurrentSize=0
    }// end isEmpty

    @Override
    public PuzzleWord remove() {
        PuzzleWord removedWord;
        for (int i = 0; i < bagSize; i++) {
            removedWord = wordBag[i];
            if (removedWord != null) {
                bagSize--;
                wordBag[i] = null;
                return removedWord;
            }
        }
        return null;
    }// end remove

    public int getFrequencyOf(PuzzleWord anEntry) {
        int freq= 0;// initialize freq to 0
        for(int i=0; i < bagSize; i++){
            if(wordBag[i].equals(anEntry)){
                freq++;
            }// end if
        }//end for
        return freq;
    }// end getFrequencyOf


    @Override
    public boolean contains(PuzzleWord anEntry) {
        for(int i=0; i<bagSize; i++){
            if(wordBag[i].equals(anEntry)){// Null check needed for NullPointerException?
                return true;
            }// end if
        }// end for
        return false;
    }// end contains

    //Creates new array sized to only fit current content of bag.
    @Override
    public PuzzleWord[] toArray() {
        PuzzleWord[] resultArray= new PuzzleWord[bagSize];
        this.shiftContents();
        System.arraycopy(wordBag, 0, resultArray, 0, bagSize);
        return resultArray;
    }// end toArray

    //Shifts all objects in wordBag so that all entries are towards front of array.
    public void shiftContents() {
        for (int i = 0; i < CrossGenMain.wordCount; i++) {
            if (wordBag[i] == null) {
                for (int j = 1; j < CrossGenMain.wordCount; j++) {
                    if (wordBag[j] != null) {
                        wordBag[i] = wordBag[j];
                        wordBag[j] = null;
                    }
                }
            }
        }
    }


    public int[] countLetterFrequencies() {

        for (int i = 0; i < bagSize; i++) {
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
        for (int i = 0; i < 26; i++) {//iterates through letter
            char letter = (char) ('a' + i);// calculate corresponding letter a=0 b=1 ...
            int frequency = letterFrequency[i];//get frequency for corresponding letter
            System.out.println(letter + ": " + frequency);
        }//end for

        return letterFrequency;
    }// end countLetterFrequency


    public void calculateWordScores() {
        for (int i = 0; i < bagSize; i++) {
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
