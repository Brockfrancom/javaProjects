/**
 * Brock Francom
 * A02052161
 * CS-2410
 * Vicki Allan
 * 3/5/2019
 *
 * Program 4 Hashing
 *
 * Program to calculate the score for a word game.
 */

import java.util.HashMap;
import java.util.Map;

public class Game {
    private String name;
    private DoubleHashingHashTable<WordInfo> H;

    public Game() {
        H = new DoubleHashingHashTable<WordInfo>();
    }

    // This searches the tree for the word, if it is not present it computes the score and
    // adds it; if it is present, it computes the score and increases the times it has been seen.
    public int computeScore(WordInfo wi) {
        int score = 0;
        WordInfo wordInfo = H.find(wi); // is word in table?
        if (wordInfo != null) { // word is in table
            score = (letterValue(wi.word)) * (lengthValue(wi.word))*bonus(wordInfo.count);
            wordInfo.count += 1;
        }
        else { // word not in table
            score = (letterValue(wi.word)) * (lengthValue(wi.word))*bonus(wi.count);
            wi.count += 1;
            H.insert(wi);
        }
        return score;
    }

    // This reads in a file line by line, and computes the score for each word.
    // Also prints out all of the data and scores.
    public void playGame(String filename) {
        name = filename.replace(".txt", "");
        int score = 0;

        // Read in file
        try  {
            java.util.Scanner input = new java.util.Scanner(new java.io.File(filename));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                WordInfo word = new WordInfo(line, 0);
                score += computeScore(word); // compute score and add to score so far.
            }
        }
        catch (Exception e){
            System.out.println("File not found.");
            System.exit(0);
        }

        // Print out stats
        System.out.println();
        System.out.println("Game: " + name);
        System.out.println("Score: " + score);
        System.out.println("Finds: " + H.finds);
        System.out.println("Probes: " + H.probes);
        System.out.println("Number of items: " + H.size());
        System.out.println("Length of table: " + H.capacity());
    }

    public String toString() {
        int LIMIT = 20;
        return name+ "\n"+ H.toString(LIMIT);
    }

    public static void main( String [ ] args ) {
        try {
            Game g0 = new Game(  );
            g0.playGame("game0.txt" );
            System.out.println(g0);
            Game g1 = new Game(  );
            g1.playGame("game1.txt" );
            System.out.println(g1);
            Game g2 = new Game(  );
            g2.playGame("game2.txt" );
            System.out.println(g2);
            Game g3 = new Game(  );
            g3.playGame("game3.txt" );
            System.out.println(g3);
            Game g4 = new Game(  );
            g4.playGame("game4.txt" );
            System.out.println(g4);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Computes the letter value of the word and returns it.
    public int letterValue(String word) {
        int score = 0;

        // dictionary of letter values
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        dictionary.put("a", 1);
        dictionary.put("b",3);
        dictionary.put("c",3);
        dictionary.put("d",2);
        dictionary.put("e",1);
        dictionary.put("f",4);
        dictionary.put("g",2);
        dictionary.put("h",4);
        dictionary.put("i",1);
        dictionary.put("j",8);
        dictionary.put("k",5);
        dictionary.put("l",1);
        dictionary.put("m",3);
        dictionary.put("n",1);
        dictionary.put("o",1);
        dictionary.put("p",3);
        dictionary.put("q",10);
        dictionary.put("r",1);
        dictionary.put("s",1);
        dictionary.put("t",1);
        dictionary.put("u",1);
        dictionary.put("v",4);
        dictionary.put("w",4);
        dictionary.put("x",8);
        dictionary.put("y",4);
        dictionary.put("z",10);

        char[] CharsArray = new char[word.length()];
        word.getChars(0, word.length(), CharsArray, 0);

        for(char letter:CharsArray) {
            score += dictionary.get(Character.toString(letter)); // score for each letter
        }
        return score;
    }

    // Computes the length value, and returns it.
    public int lengthValue(String word) {
        if (word.length() <= 2){
            return 0;
        }
        if (word.length() == 3 ) {
            return 1;
        }
        if (word.length() == 4 ) {
            return 2;
        }
        if (word.length() == 5 ) {
            return 3;
        }
        if (word.length() == 6 ) {
            return 4;
        }
        if (word.length() == 7 ) {
            return 5;
        }
        else {
            return 6;
        }
    }

    //Computes the bonus, the bonus passed in is the word info number of times seen.
    public int bonus(int bonus) {
        if (bonus == 0) {
            return 5;
        }
        if (bonus <= 5) {
            return 4;
        }
        if (bonus <= 10) {
            return 3;
        }
        if (bonus <= 15) {
            return 2;
        }
        else {
            return 1;
        }
    }
}
