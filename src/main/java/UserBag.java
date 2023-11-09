package org.example;
import java.util.Arrays;

public class UserBag<T> {
    private T[] wordList;// Wordlist Array
    public static final int initialCapacity=15;// Array size
    private int size;
    private int[] letterFrequency = new int[26];//Alphabet size

    @SuppressWarnings("unchecked")
//Delete me, just for ref
    //for ref


    public UserBag(){// Initialize UserBag
        wordList = (T[]) new Object[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            wordList[i] = null;} //each element initialized to null
        size=initialCapacity;//size is equal to capacity
    }

    public boolean add(T newWord){
        boolean addSuccess = false;
        for (int i = 0; i < size; i++) {//iterate through array
            if (wordList[i] == null) {//if null
                wordList[i] = newWord;//add new word
                addSuccess = true;
                break;
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
