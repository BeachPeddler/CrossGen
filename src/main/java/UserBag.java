import java.util.Arrays;

public class UserBag implements BagInterface<PuzzleWord> {
    public static int bagSize = 0;// Array size
    private int[] letterFrequency = new int[26];//Alphabet size
    public static PuzzleWord[] wordBag;


    public UserBag(int bagCapacity){// Initialize UserBag
       wordBag = new PuzzleWord[bagCapacity];
    }


//Start here


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
    public int getCurrentSize() {
        int currentSize= 0;

        for(int i=0; i<size; i++){
            if(wordList[i] !=null){
                currentSize++;
            }//end if
        }//end for
        return currentSize;
    }//end getCurrentSize

    public boolean remove(T wordToRemove) {//Remove word
        boolean removed = false; // Flag to track if the word was removed
        int shiftCount = 0; // Counter for shifting elements

        for (int i = 0; i < size; i++) {
            if (wordList[i] != null && wordList[i].equals(wordToRemove)) {
                removed = true; // Word found and removed
                shiftCount++;
            } else if (shiftCount > 0) {
                wordList[i - shiftCount] = wordList[i];
            }
        }

        if (removed) {
            for (int i = size - shiftCount; i < size; i++) {
                wordList[i] = null; // Set the last elements to null
            }
            size -= shiftCount; // Update the size
        }

        return removed;

    }
    //clear
    public void clear(UserBag<String> wordBag) {
        for (int i = 0; i < size; i++) {
            wordList[i] = null;
        } //end for
        size=initialCapacity;

    } //end clear
    public boolean isEmpty(){
        return size==0;

    }



    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal.
     * was successful, or null.
     */
    @Override
    public PuzzleWord remove() {
        return null;
    }

    /**
     * Removes one occurrence of a given entry from this bag, if possible.
     *
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    @Override
    public boolean remove(PuzzleWord anEntry) {
        return false;
    }

    /**
     * Removes all entries from this bag.
     */
    @Override
    public void clear() {

    }

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the bag.
     */
    @Override
    public int getFrequencyOf(PuzzleWord anEntry) {
        return 0;
    }

    /**
     * Tests whether this bag contains a given entry.
     *
     * @param anEntry The entry to find.
     * @return True if the bag contains anEntry, or false if not.
     */
    @Override
    public boolean contains(PuzzleWord anEntry) {
        return false;
    }

    /**
     * Retrieves all entries that are in this bag.
     *
     * @return A newly allocated array of all the entries in the bag.
     * Note: If the bag is empty, the returned array is empty.
     */
    @Override
    public PuzzleWord[] toArray() {
        return new PuzzleWord[0];
    }

    public T get(int index){
        if (index < 0 || index >=size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }// end if
        return wordList[index];
    }
    @SuppressWarnings("unchecked")
    public T[] getAllEntries() {
        T[] entries = Arrays.copyOf(wordList, size);
        return entries;
    }

    public int[] countLetterFrequencies() {

        for (int i = 0; i < size; i++) {
            Object word = wordList[i];
            if (word instanceof String) {
                String str = (String) word;
                for (int j = 0; j < str.length(); j++) {
                    char c = str.charAt(j);
                    if (c >= 'a' && c <= 'z') {
                        letterFrequency[c - 'a']++;
                    }
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            int frequency = letterFrequency[i];
            System.out.println(letter + ": " + frequency);
        }

        return letterFrequency;
    }

    public void printWordScores() {
        for (int i = 0; i < size; i++) {
            T word = wordList[i];
            if (word instanceof String) {
                String str = (String) word;
                int score = 0;

                for (int j = 0; j < str.length(); j++) {
                    char c = str.charAt(j);
                    if (c >= 'a' && c <= 'z') {
                        score += letterFrequency[c - 'a'];
                    }
                }

                System.out.println("Word: " + str + ", Score: " + score);
            }
        }
    }
}
