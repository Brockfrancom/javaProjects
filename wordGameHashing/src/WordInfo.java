/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Vicki Allan
 * 3/5/2019
 *
 * Program 4 Hashing
 *
 * Word info class, keeps track of the number of times a word is seen in a game.
 */

public class WordInfo {

    public String word;
    public int count; // Number of times seen

    // Constructor
    public WordInfo(String line, int count) {
        word = line.toLowerCase();
        this.count = count;
    }

    // Hash function, based on the word
    @Override
    public int hashCode() {
        var hash = 0;
        char[] CharsArray = new char[word.length()];
        word.getChars(0, word.length(), CharsArray, 0);

        for(char letter:CharsArray) {
            hash += Character.hashCode(letter);
        }
        return hash;
    }

    // Overridden equals, checks the word
    @Override
    public boolean equals(Object w2) {
        if (w2 == this) {
            return true;
        }
        if (!(w2 instanceof WordInfo)) {
            return false;
        }
        WordInfo w = (WordInfo)w2;
        return (this.word.compareTo(w.word) == 0);
    }

    //overridden toString method.
    @Override
    public String toString() {
        return word + ", " + count;
    }

}
