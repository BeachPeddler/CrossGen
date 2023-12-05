import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class PuzzleWordTest {
    //public static void main(String[] args) {

    @ParameterizedTest
    @MethodSource("wordData")
    void testPuzzleWord(String firstWord, String firstHint, int score, String secondWord, String secondHint) {

        // Create a PuzzleWord object
        PuzzleWord puzzleWord = new PuzzleWord(firstWord, firstHint);

        // Display the first series of words,hints, and initial score of correct words
        assertEquals(firstWord, puzzleWord.getWordString());
        assertEquals(firstHint, puzzleWord.getWordHint());
        assertEquals(0, puzzleWord.getWordScore());

        // Set a new score
        puzzleWord.setWordScore(score);

        // Display the updated score
        assertEquals(score, puzzleWord.getWordScore());

        // Change the word and hint to the second one
        puzzleWord.setWordString(secondWord);
        puzzleWord.setWordHint(secondHint);

        // Display the second series of words and hints
        assertEquals(secondWord, puzzleWord.getWordString());
        assertEquals(secondHint, puzzleWord.getWordHint());

        // Convert the word to an array and verify it
        char[] wordArray = puzzleWord.toArray();
        assertArrayEquals(secondWord.toCharArray(), wordArray);
    }

    private static Collection<Object[]> wordData() {
        return Arrays.asList(new Object[][] {

                //Format: {"firstWord", "firstHint", Possible Score, "secondWord", "secondHint"}
                {"Christmas", "Santa Claus", 25, "Easter", "Bunny"},
                {"Apple", "Red Crunchy Fruit", 50, "Kiwi", "A Flightless Bird"},
                {"Harley Davidson", "American Classic Motorcycle", 75, "Ducati", "Luxury Italian Motorcycles"}
        });
    }
}
