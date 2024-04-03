package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 * </pre>
 */
public class Q242AnagramFinderTest {

    /**
     * <pre>
     * This is my 2nd fastest implementation.
     * Leetcode:
     * - 3ms        - beats 89.50% of users with Java
     * - 43.03MB    - beats 74.74% of users with Java
     * </pre>
     */
    public boolean isAnagram(String s, String t) {
        int[] charCount = new int[26];

        if (s.length() != t.length()) {
            return false;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        int i = 0;
        while (i < sChars.length) {

// NB: Changing from -97 to -'a' increases the runtime performance by 1 ms?
            charCount[sChars[i] - 'a']++;
            charCount[tChars[i] - 'a']--;
            i++;
        }

        for (int val : charCount) {
            if (val != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * This is my fastest implementation.
     * Leetcode:
     * - 4ms        - beats 75.21% of users with Java
     * - 43.98MB    - beats 60.77% of users with Java
     * </pre>
     */
    public boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    @Test
    void eg1Test() {
        String s = "anagram";
        String t = "nagaram";

        assertTrue(isAnagram(s, t));
    }

    @Test
    void eg2Test() {
        String s = "rat";
        String t = "car";

        assertFalse(isAnagram(s, t));
    }
}
