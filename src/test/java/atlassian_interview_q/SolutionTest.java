package atlassian_interview_q;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/*
You are running a classroom and suspect that some of your students are passing around the answer to a multiple-choice question disguised as a random note.
Your task is to write a function that, given a list of words and a note, finds and returns the word in the list that is scrambled inside the note, if any exists.
If none exist, it returns the result "-" as a string.
There will be at most one matching word.
The letters don't need to be in order or next to each other. The letters cannot be reused.

Example:
words = ["baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"]
note1 = "ctay"
find(words, note1) => "cat"   (the letters do not have to be in order)

Complexity analysis variables:

W = number of words in `words`
S = maximal length of each word or of the note
*/


public class SolutionTest {
    private final String[] words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};

    private char[] find(String[] words, char[] note) {
        for (String word : words) {
            if (isWordInNote(word.toCharArray(), note)) {
                return word.toCharArray();
            }
        }
        return new char[]{'-'};
    }

    private boolean isWordInNote(char[] word, char[] note) {
        char[] localNote = Arrays.copyOf(note, note.length);

        for (char c : word) {
            int index = new String(localNote).indexOf(c);

            if (index != -1) {
                localNote[index] = '_';
                System.out.println(Arrays.toString(localNote));
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    void lettersNotInOrderTest() {
        String note1 = "ctay";
        char[] expected = "cat".toCharArray();
        char[] actual = find(words, note1.toCharArray());

        assertArrayEquals(expected, actual);
    }

    @Test
    void lettersNotTogetherTest() {
        char[] note2 = "bcanihjsrrrferet".toCharArray();
        char[] expected = "cat".toCharArray();
        char[] actual = find(words, note2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void lettersCannotBeReusedTest() {
        char[] note3 = "tbaykkjlga".toCharArray();
        char[] expected = {'-'};
        char[] actual = find(words, note3);
        assertArrayEquals(expected, actual);
    }

    @Test
    void note4Test() {
        char[] note4 = "bbbblkkjbaby".toCharArray();
        char[] expected = "baby".toCharArray();
        char[] actual = find(words, note4);
        assertArrayEquals(expected, actual);
    }

    @Test
    void note5Test() {
        char[] note5 = "dad".toCharArray();
        char[] expected = "-".toCharArray();
        char[] actual = find(words, note5);
        assertArrayEquals(expected, actual);
    }

    @Test
    void note6Test() {
        char[] note6 = "breadmaking".toCharArray();
        char[] expected = "bird".toCharArray();
        char[] actual = find(words, note6);
        assertArrayEquals(expected, actual);
    }

    @Test
    void note7Test() {
        char[] note7 = "dadaa".toCharArray();
        char[] expected = "dada".toCharArray();
        char[] actual = find(words, note7);
        assertArrayEquals(expected, actual);
    }
}

