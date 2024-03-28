package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Constraints:
 * 0 <= s.length <= 100
 * 0 <= t.length <= 104
 * s and t consist only of lowercase English letters.
 *
 * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 * </pre>
 */
public class Q392IsSubsequenceTest {

    /**
     * <pre>
     * This is my 2nd fastest implementation.
     * Leetcode:
     * - 1ms        - beats 90.79% of users with Java
     * - 41.14MB    - beats 81.66%
     * </pre>
     */
    private boolean isSubsequence2(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }

        int i = 0;
        int j = 0;
        while (j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                if (i == s.length()) {
                    return true;
                }
            }
            j++;
        }
        return false;
    }

    /**
     * <pre>
     * This is my fastest implementation.
     * Leetcode:
     * - 0ms        - beats 100.00% of users with Java
     * - 41.22MB    - beats 68.67%
     * </pre>
     */
    private boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }

        int i = 0;
        int j = 0;
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();

        while (j < t1.length) {
            if (s1[i] == t1[j]) {
                i++;
                if (i == s1.length) {
                    return true;
                }
            }
            j++;
        }
        return false;
    }

    @Test
    public void isSubsequenceEg1Test() {
        String s = "axc";
        String t = "ahbgdc";
        assertFalse(isSubsequence(s, t));
    }

    @Test
    public void isSubsequenceEg2Test() {
        String s = "abc";
        String t = "ahbgdc";
        assertTrue(isSubsequence(s, t));
    }

    @Test
    public void isSubsequenceEg3Test() {
        String s = "";
        String t = "ahbgdc";
        assertTrue(isSubsequence(s, t));
    }
}
