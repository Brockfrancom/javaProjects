/**
 * Brock Francom
 * A02052161
 * CS-2420
 * Vicki Allen
 * 3/25/2019
 *
 * Programming Exercise 5 - AutoComplete
 *
 * Term object to store word and frequency.
 */

public class Term implements Comparable<Term> {

    public long freq;
    public String word;

    // Constructor
    public Term(String word, long freq){
        this.word = word;
        this.freq = freq;
    }

    public String toString(){
        return "Wt: " + freq + "\t " + word;
    }

    public int compareTo(Term t2){
        if (this.freq==t2.freq) return 0;
        else if (this.freq < t2.freq) return -1;
        return 1;
     }
}

