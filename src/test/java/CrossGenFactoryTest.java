import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CrossGenFactoryTest {


    static Stream<Arguments> wordsAndHints() {
        return Stream.of(
                arguments("Bicycles", "Two-Wheelers"),
                arguments("Clothes", "What you wear"),
                arguments("Trains", "Locomotives"),
                arguments("Thinking", "What you do in class."),
                arguments("Flying", "Don't try this at home.")
                );
    }

    @ParameterizedTest
    @MethodSource("wordsAndHints")
    void generate(String theWord, String wordHint) {
    }

    @Test
    void generateManyPuzzles() {
    }

    @Test
    void placeWordsRepeatedly() {
    }

    @Test
    void placeWord() {
    }

    @Test
    void canPlace() {
    }

    @Test
    void cropGrid() {
    }

    @Test
    void generateScore() {
    }
}