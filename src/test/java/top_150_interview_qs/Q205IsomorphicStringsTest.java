package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 * Constraints:
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 * </pre>
 */
public class Q205IsomorphicStringsTest {
    /**
     * <pre>
     * This is my 2nd fastest implementation.
     * Leetcode:
     * - 11ms       - beats 58.83% of users with Java
     * - 41.79MB    - beats 98.49% of users with Java
     * </pre>
     */
    public boolean isIsomorphicFirst(String s, String t) {
        Map<Character, Character> charMap = new HashMap<>();
        char currentValue;
        char cS;
        char cT;
        for (int i = 0; i < s.length(); i++) {
            cS = s.charAt(i);
            cT = t.charAt(i);

            if (charMap.containsKey(cS)) {
                currentValue = charMap.get(cS);
                if (currentValue != cT) {
                    return false;
                }
            } else if (charMap.containsValue(cT)) {
                return false;
            } else {
                charMap.put(cS, cT);
            }
        }
        return true;
    }

    /**
     * <pre>
     * This is my fastest implementation.
     * Using 2 Character Arrays instead of a HashMap.
     * Leetcode:
     * - 5ms        - beats 97.18% of users with Java
     * - 42.42MB    - beats 37.68% of users with Java
     * </pre>
     */
    public boolean isIsomorphic(String s, String t) {
        // 256 -> Number of ASCII characters
        char[] sToT = new char[256];
        char[] tToS = new char[256];
        char sChar;
        char tChar;

        for (int i = 0; i < s.length(); i++) {
            sChar = s.charAt(i);
            tChar = t.charAt(i);

            // If char relation doesn't exist in either direction >> add it
            if (sToT[sChar] == 0 && tToS[tChar] == 0) {
                sToT[sChar] = tChar;
                tToS[tChar] = sChar;
            // otherwise if the value of t from s->t doesn't equal to the char in tString >> return false
            } else if (sToT[sChar] != tChar ) {
                return false;
            }
        }
        return true;
    }

    @Test
    void isIsomorphicEg1Test() {
        String s = "egg", t = "add";
        assertTrue(isIsomorphic(s, t));
    }

    @Test
    void isIsomorphicEg2Test() {
        String s = "foo", t = "bar";
        assertFalse(isIsomorphic(s, t));
    }

    @Test
    void isIsomorphicEg3Test() {
        String s = "paper", t = "title";
        assertTrue(isIsomorphic(s, t));
    }

    @Test
    void isIsomorphicEg4Test() {
        String s = "badc", t = "baba";
        assertFalse(isIsomorphic(s, t));
    }
}
