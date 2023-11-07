import java.util.ArrayList;
import java.util.List;
public class WordsList {
    private List<String> words;

    public WordsList() {
        words = new ArrayList<>();
    }

    public void addWord(String word) {
        words.add(word);
    }

    public List<String> getWords() {
        return words;
    }

}