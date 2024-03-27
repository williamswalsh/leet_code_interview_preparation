package atlassian_interview_q;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubPartTest {

    private boolean isWordInNote(char[] word, char[] note) {
        boolean[] isUsed = new boolean[note.length];

        for (char c : word) {
            int index = new String(note).indexOf(c);

            if (index != -1 && !isUsed[index]) {
                isUsed[index] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    void isWordInNotePositiveTest() {
        char[] word = {'c', 'a', 't'};
        char[] note1 = {'c', 't', 'a', 'y'};

        boolean present = isWordInNote(word, note1);
        assertTrue(present);
    }

    @Test
    void lettersNotTogetherTest() {
        char[] word = "cat".toCharArray();
        char[] note2 = "bcanihjsrrrferet".toCharArray();

        boolean present = isWordInNote(word, note2);
        assertTrue(present);
    }
}
