import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class WordsList {

    private List<PuzzleWord> wordObjects; //

    public WordsList(List<PuzzleWord> wordObjects) {

        this.wordObjects = wordObjects; // this wordObjects is going to be the same as the wordObjects up there
    }

    public void createPriorityList() {
        Collections.sort(wordObjects, (a, b) -> Integer.compare(b.wordString(), a.wordHint()));
    } // compares the word string
    public PuzzleWord getHighestPriority() {
        if (wordObjects.isEmpty()) {
            return null;
        }
        return wordObjects.get(0);
    }

    public PuzzleWord removeHighestPriority() {
        if (wordObjects.isEmpty()) {
            return null;
        }
        return wordObjects.remove(0);
    }

    public boolean isEmpty() {
        return wordObjects.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (PuzzleWord word : wordObjects) {
            result.append(word.toString()).append("\n");
        }
        return result.toString();
    }
}

}