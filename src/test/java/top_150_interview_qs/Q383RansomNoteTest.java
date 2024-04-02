package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Given two strings ransomNote and magazine,
 * - return true if ransomNote can be constructed by using the letters from magazine
 * - false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Constraints:
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 * </pre>
 */
public class Q383RansomNoteTest {

    /**
     * <pre>
     * This is my fastest implementation.
     * The idea for using an integer array for alphabetCount is from a solution
     * on Leetcode.
     *
     * Leetcode:
     * - 1ms        - beats 99.20% of users with Java
     * - 44.62MB    - beats 66.79% of users with Java
     * </pre>
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] alphabetCount = new int[26];

        for (char c : magazine.toCharArray()) {
            alphabetCount[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--alphabetCount[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * <pre>
     * This is my 4th fastest implementation.
     * Leetcode:
     * - 18ms       - beats 15.24% of users with Java
     * - 44.85MB    - beats 53.63%
     * </pre>
     */
    public boolean canConstructSlowest(String ransomNote, String magazine) {
        Map<Character, Integer> charMap = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) + 1);
            } else {
                charMap.put(c, 1);
            }
        }

        for (char c : ransomNote.toCharArray()) {
            if (charMap.containsKey(c) && charMap.get(c) > 0) {
                charMap.put(c, charMap.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * This is my 3rd fastest implementation.
     * Leetcode:
     * - 15ms       - beats 26.28% of users with Java
     * - 46.06MB    - beats 46.06%
     * </pre>
     */
    public boolean canConstructWithGetOrDefault(String ransomNote, String magazine) {
        Map<Character, Integer> charMap = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            if (charMap.containsKey(c) && charMap.get(c) > 0) {
                charMap.put(c, charMap.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * This is my 2nd fastest implementation.
     * Leetcode:
     * - 14ms       - beats 32.06% of users with Java
     * - 45.27MB    - beats 17.21%
     * </pre>
     */
    public boolean canConstructWithJava8MapMethods(String ransomNote, String magazine) {
        Map<Character, Integer> charMap = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            charMap.computeIfPresent(c, (k, v) -> v + 1);
            charMap.putIfAbsent(c, 1);
        }

        for (char c : ransomNote.toCharArray()) {
            if (charMap.containsKey(c) && charMap.get(c) > 0) {
                charMap.put(c, charMap.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void canConstructEg1() {
        String ransomNote = "a";
        String magazine = "b";
        assertFalse(canConstruct(ransomNote, magazine));
    }

    @Test
    public void canConstructEg2() {
        String ransomNote = "aa";
        String magazine = "ab";
        assertFalse(canConstruct(ransomNote, magazine));
    }

    @Test
    public void canConstructEg3() {
        String ransomNote = "aa";
        String magazine = "aab";
        assertTrue(canConstruct(ransomNote, magazine));
    }
}
