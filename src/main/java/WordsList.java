import java.util.List;

public class WordsList implements ListInterface<PuzzleWord> {

    private PuzzleWord[] list;   // Array of list entries; ignore list[0]
    private int numberOfEntries;
    //private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 5;
    private static final int MAX_CAPACITY = 10;
    private List<PuzzleWord> wordObjects; //

    public WordsList()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor
    public WordsList(int defaultCapacity) {
        // Is initialCapacity too small?
        if (defaultCapacity < 1)
            defaultCapacity = DEFAULT_CAPACITY;
        else if (defaultCapacity > MAX_CAPACITY) {
            defaultCapacity = MAX_CAPACITY;
        } else // Is initialCapacity too big?
            //checkCapacity(defaultCapacity);
            defaultCapacity = defaultCapacity;

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        PuzzleWord[] tempList = (PuzzleWord[]) new Object[defaultCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        //integrityOK = true;
    }

    /**
     * Adds a new entry to the end of this list.
     * Entries currently in the list are unaffected.
     * The list's size is increased by 1.
     *
     * @param newEntry The object to be added as a new entry.
     */
    @Override
    public void add(PuzzleWord newEntry) {
        add(numberOfEntries + 1, newEntry);
    }

    /**
     * Adds a new entry at a specified position within this list.
     * Entries originally at and above the specified position
     * are at the next higher position within the list.
     * The list's size is increased by 1.
     *
     * @param givenPosition An integer that specifies the desired
     *                    position of the new entry.
     * @param newEntry    The object to be added as a new entry.
     * @throws IndexOutOfBoundsException if either
     *                                   newPosition < 1 or newPosition > getLength() + 1.
     */
    @Override
    public void add(int givenPosition, PuzzleWord newEntry) {
        //checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1))
        {
            if (givenPosition <= numberOfEntries)
               // makeRoom(givenPosition);
            list[givenPosition] = newEntry;
            numberOfEntries++;
            //ensureCapacity(); // Ensure enough room for next add
        }
        else
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");

    }

    /**
     * Removes the entry at a given position from this list.
     * Entries originally at positions higher than the given
     * position are at the next lower position within the list,
     * and the list's size is decreased by 1.
     *
     * @param givenPosition An integer that indicates the position of
     *                      the entry to be removed.
     * @return A reference to the removed entry.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition < 1 or givenPosition > getLength().
     */
    @Override
    public PuzzleWord remove(int givenPosition) {
        //checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            // Assertion: The list is not empty
            PuzzleWord result = list[givenPosition]; // Get entry to be removed


            // Move subsequent entries towards entry to be removed,
            // unless it is last in list
            if (givenPosition < numberOfEntries)
                removeGap(givenPosition);
            list[numberOfEntries] = null;
            numberOfEntries--;
            return result;                  // Return reference to removed entry
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }

    /**
     * Removes all entries from this list.
     */
    @Override
    public void clear() {
       // checkIntegrity();

        // Clear entries but retain array; no need to create a new array
        for (int index = 1; index <= numberOfEntries; index++) // Loop is part of Q4
            list[index] = null;

        numberOfEntries = 0;
    }

    /**
     * Replaces the entry at a given position in this list.
     *
     * @param givenPosition An integer that indicates the position of
     *                      the entry to be replaced.
     * @param newEntry      The object that will replace the entry at the
     *                      position givenPosition.
     * @return The original entry that was replaced.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition < 1 or givenPosition > getLength().
     */
    @Override
    public PuzzleWord replace(int givenPosition, PuzzleWord newEntry) {
        // checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            // Assertion: The list is not empty
            PuzzleWord originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }

    /**
     * Retrieves the entry at a given position in this list.
     *
     * @param givenPosition An integer that indicates the position of
     *                      the desired entry.
     * @return A reference to the indicated entry.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition < 1 or givenPosition > getLength().
     */
    @Override
    public PuzzleWord getEntry(int givenPosition) {
        // checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            // Assertion: The list is not empty
            return list[givenPosition];
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    }

    /**
     * Retrieves all entries that are in this list in the order in which
     * they occur in the list.
     *
     * @return A newly allocated array of all the entries in the list.
     * If the list is empty, the returned array is empty.
     */
    @Override
    public PuzzleWord[] toArray() {
        //checkIntegrity();

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        PuzzleWord[] result = (PuzzleWord[]) new Object[numberOfEntries]; // Unchecked cast
        for (int index = 0; index < numberOfEntries; index++)
        {
            result[index] = list[index + 1];
        } // end for

        return result;
    }

    /**
     * Sees whether this list contains a given entry.
     *
     * @param anEntry The object that is the desired entry.
     * @return True if the list contains anEntry, or false if not.
     */
    @Override
    public boolean contains(PuzzleWord anEntry) {
        // checkIntegrity();
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries))
        {
            if (anEntry.equals(list[index]))
                found = true;
            index++;
        } // end while
        return found;
    }

    /**
     * Gets the length of this list.
     *
     * @return The integer number of entries currently in the list.
     */
    @Override
    public int getLength() {
        return numberOfEntries;
    }

    /**
     * Sees whether this list is empty.
     *
     * @return True if the list is empty, or false if not.
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    private void removeGap(int givenPosition)
    {
        int removedIndex = givenPosition;
        for (int index = removedIndex; index < numberOfEntries; index++)
            list[index] = list[index + 1];
    } // end removeGap

}

// List: pull words from userBag from highest to lowest
// list in the name for
// + add, everything from list interface
//
//
