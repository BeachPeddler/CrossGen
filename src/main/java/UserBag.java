import java.util.Arrays;

public class UserBag implements BagInterface<PuzzleWord> {
    public static int bagSize = 0;// Array size
    private int[] letterFrequency = new int[26];//Alphabet size
    public static PuzzleWord[] wordBag;


    public UserBag(int bagCapacity){// Initialize UserBag
       wordBag = new PuzzleWord[bagCapacity];
    }


//Start here

    @Override
    public boolean add(PuzzleWord newWord){
        boolean addSuccess = false;
        for (int i = 0; i < bagSize; i++) {//iterate through array
            if (wordBag[i] == null) {//if null
                wordBag[i] = newWord;//add new word
                addSuccess = true;
                return addSuccess;
            } //end if
        } //end for
        return addSuccess;
    } //end add

    //return current size

    @Override
    public int getCurrentSize() {
        int currentSize= 0;

        for(int i=0; i<bagSize; i++){
            if(wordBag[i] !=null){
                currentSize++;
            }//end if
        }//end for
        return currentSize;
    }//end getCurrentSize


    //remove
    @Override
    public boolean remove(PuzzleWord anEntry) {//Remove word
        boolean removed = false; // Flag to track if the word was removed
        int shiftCount = 0; // Counter for shifting elements

        for (int i = 0; i < bagSize; i++) {
            if (wordBag[i] != null && wordBag[i].equals(anEntry)) {
                removed = true; // Word found and removed
                shiftCount++;
            } //end if
            else if (shiftCount > 0) {
                wordBag[i - shiftCount] = wordBag[i];
            }//end else if
        }//end for

        if (removed) {
            for (int i = bagSize - shiftCount; i < bagSize; i++) {
                wordBag[i] = null; // Set the last elements to null
            }//end for
            bagSize -= shiftCount; // Update the size
        }//end if

        return removed;

    }//end Remove


    //clear
    @Override
    public void clear() {
        for (int i = 0; i < bagSize; i++) {
            wordBag[i] = null;
        } //end for
    } //end clear

    //isEmpty
    @Override
    public boolean isEmpty(){
        return getCurrentSize()==0;//Returns True if getCurrentSize=0

    }// end isEmpty


    @Override
    public PuzzleWord remove() {
        if(this.isEmpty()){// check if bag is empty
            return null;
        }//end if
        else {
            int i=0;
            PuzzleWord result = wordBag[i];
            while(result ==null){
                i++;
                result = wordBag[i];
            }//end while
            this.remove(result);//call the remove method to remove result
            return result;//return the removed element
        }//end else
    }// end remove


    @Override
    public int getFrequencyOf(PuzzleWord anEntry) {
        int freq= 0;// inialize freq to 0
        for(int i=0; i< bagSize; i++){
            if(wordBag.equals(anEntry)){
                freq++;
            }// end if
        }//end for
        return freq;
    }// end getFrequencyOF


    @Override
    public boolean contains(PuzzleWord anEntry) {
        for(int i=0; i<bagSize; i++){
            if(wordBag[i] !=null && wordBag[i].equals(anEntry)){// Null check prevents NullPointerException
                return true;
            }// end if
        }// end for
        return false;
    }// end contains

    @Override
    public PuzzleWord[] toArray() {
        PuzzleWord[] resultArray= new PuzzleWord[bagSize];
        for (int i=0; i<bagSize; i++){
            resultArray[i]= wordBag[i];
        }// end for
        return resultArray;
    }// end toArray


    @Override
    public String getWord(PuzzleWord anEntry){

        return anEntry.getWordString();
    }


    @Override
    public int[] countLetterFrequencies() {

        for (int i = 0; i < bagSize; i++) {
            PuzzleWord puzzleWord = wordBag[i];// iterate through each element
            if (puzzleWord !=null) { //check if puzzleword is not null
                String word= puzzleWord.getWordString();// get word from puzzleword
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


    @Override
    public void printWordScores() {
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

                System.out.println("Word: " + word + ", Score: " + score);
            }//end if
        }//end for
    }//end print word score
}
