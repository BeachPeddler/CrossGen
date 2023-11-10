import java.util.Scanner;

//Receives input from users. Follows Factory Pattern method.
public class CrossGenMain {
    static int wordCount = 0;
    private static final int MAX_WORD_COUNT = 10;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String userInput = null;
        String newWord;
        String wordHint;

        System.out.println("Welcome to our Crossword Generator!");
        System.out.println("Please set the word count for your crossword. (Maximum is 10)");

        while (wordCount == 0)
        {
            userInput = input.nextLine();
            int numWords = Integer.parseInt(userInput);
            if (numWords <= MAX_WORD_COUNT)
            {
                System.out.println("You got it, your word count is now " + numWords + ".");
                wordCount = numWords;
            }
            else
            {
                System.out.println("Hey, put in a number that matches this time.");
            }
        }

        UserBag wordBag = new UserBag(wordCount);


        //Receiving User Inputs
        System.out.println("Fantastic. Now we can make your words and hints!");
        for(int i = 0; i < wordCount;i++)
        {
            System.out.println("Please enter a word.");
            newWord = input.nextLine();

            System.out.println("What is that word's hint?");
            wordHint = input.nextLine();

            PuzzleWord addWord = new PuzzleWord(newWord, wordHint);
            UserBag.add(addWord);
        }
        System.out.println("Please wait while we make your new crossword puzzle.");

        WordsList wordList = new WordsList<>();
        WordBag.sendToList(wordList);

        //Constructs PuzzleWord() based on inputs
        //Constructs WordList()
        //Constructs a new CrossGenFactory
        CrossGenFactory newCross = new CrossGenFactory<>();
        newCross.generateCrossword(wordList);
    }
}