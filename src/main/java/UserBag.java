import java.util.LinkedList;



public class userBag {
    public static void main(String[] args) {
        // Create a LinkedList array with some sample strings
        LinkedList<String> wordList = new LinkedList<>();
        wordList.add("apple");
        wordList.add("banana");
        wordList.add("cherry");

        // Count the frequency of each letter in the LinkedList array
        countLetterFrequency(wordList);
        wordScore(wordList);

        // Calculate the number of objects in the LinkedList array
        int wordCount = calculateWordCount(wordList);
        System.out.println("Number of objects in the LinkedList array: " + wordCount);

    }

    // Method to count the frequency of each letter in the LinkedList array
    public static void countLetterFrequency(LinkedList<String> wordList) {
        int[] letterFrequency = new int[26]; // Assuming only lowercase letters

        for (String word : wordList) {
            word = word.toLowerCase(); // Convert the string to lowercase for case-insensitive counting
            for (char i : word.toCharArray()) {
                if (i >= 'a' && i <= 'z') {
                    letterFrequency[i - 'a']++;
                }
            }
        }

        // Display the letter frequencies
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            System.out.println(letter + ": " + letterFrequency[i]);
        }
    }

    // Method to calculate the number of objects in the LinkedList array
    public static int calculateWordCount(LinkedList<String> wordList) {
        return wordList.size();
    }

    public static void wordScore(LinkedList<String> wordList) {
        //SOMETHING HERE THAT PRINTS OUT THE SCORE OF EACH WORD

    }
}

