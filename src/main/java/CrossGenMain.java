import java.util.Scanner;

//Receives input from users. Follows Factory Pattern method.
public class CrossGenMain {
    static int wordCount = 0;
    private static final int MAX_WORD_COUNT = 10;
    private static final int MAX_LETTER_COUNT = 12;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String userInput;
        String newWord;
        String wordHint;

        System.out.println("Welcome to our Crossword Generator!");
        System.out.println("Please set the word count for your crossword. (Maximum is 10)");

        while (wordCount == 0)
        {
            userInput = input.nextLine();
            while (!validateCount(userInput)) {
                System.out.println("Enter a number 1-10.");
                userInput = input.nextLine();
            }
            if (wordCount <= MAX_WORD_COUNT && wordCount > 0)
            {
                System.out.println("Your word count is " + wordCount + ".");
            }
            else
            {
                System.out.println("Enter a number 1-10.");
            }
        }

        UserBag wordBag = new UserBag(wordCount);


        //Receiving User Inputs
        for(int i = 0; i < wordCount; i++)
        {
            String xth = getString(i);
            System.out.println("Enter your " + xth + " word.");
            newWord = input.nextLine();
            while (!validateWord(newWord)){
                System.out.println("Enter a valid word. Only " + MAX_LETTER_COUNT + " letters are allowed.");
                newWord = input.nextLine();
                validateWord(newWord);
            }
            System.out.println("Enter the hint for your " + xth  +  " word.");
            wordHint = input.nextLine();

            PuzzleWord addWord = new PuzzleWord(newWord.toUpperCase(), wordHint);
            wordBag.add(addWord);
        }
        System.out.println("Generating puzzle.");

        //Constructs PuzzleWord() based on inputs
        //Constructs WordList()
        //Constructs a new CrossGenFactory
    }

    private static String getString(int iterator) {
        return switch (iterator) {
            case 1 -> "second";
            case 2 -> "third";
            case 3 -> "fourth";
            case 4 -> "fifth";
            case 5 -> "sixth";
            case 6 -> "seventh";
            case 7 -> "eighth";
            case 8 -> "ninth";
            case 9 -> "tenth";
            default -> "first";
        };
    }

    public static boolean validateCount(String newNum) {
        String clean = newNum.replaceAll("\\D+","");
        return clean.equals(newNum);
    }


    public static boolean validateWord(String newWord) {
        char[] chars = newWord.toCharArray();

        if ((newWord.length() > MAX_LETTER_COUNT) || (newWord.isEmpty()))
        {
            return false;
        }
        for (char c : chars)
        {
            if (!Character.isLetter(c))
            {
                return false;
            }
        }
        return true;
    }
}