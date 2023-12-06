import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBagTest {

    @Test
    void add() {
    }

    @Test
    void getCurrentSize() {
    }

    @Test
    void remove() {
    }

    @Test
    void clear() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void getFrequencyOf() {
    }

    @Test
    void contains() {
    }

    @Test
    void toArray() {
    }

    @Test
    void shiftContents() {
        UserBag testBag = new UserBag(4);
        PuzzleWord addWord = new PuzzleWord("Grandma".toUpperCase(), "Cookies");
        testBag.add(addWord);
        addWord = new PuzzleWord("Chives".toUpperCase(), "Cookies");
        testBag.add(addWord);
        addWord = new PuzzleWord("Refrain".toUpperCase(), "Cookies");
        testBag.add(addWord);
        addWord = new PuzzleWord("Trains".toUpperCase(), "Cookies");
        testBag.add(addWord);

        System.out.println(testBag.remove().getWordString());
        System.out.println(testBag.remove().getWordString());
        System.out.println(testBag.remove().getWordString());
        System.out.println(testBag.remove().getWordString());
    }

    @Test
    void orderContents() {
    }

    @Test
    void countLetterFrequencies() {
    }

    @Test
    void calculateWordScores() {
    }
}