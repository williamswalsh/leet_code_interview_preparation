package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 * Bijective:
 * ==========
 * - Surjective (Onto)       - each y in co-domain maps to an x in the domain.
 * - Injective  (one-to-one) - only one x in domain maps to y in co-domain.
 *
 * Constraints:
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 *
 * s contains only lowercase English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 * </pre>
 */
public class Q290WordPatternTest {

    // c -> word
    // a -> dog
    // b -> cat
    // b -> cat
    // a -> dog
    //        must manage char offset of - 97
    // pattern string is a pattern of the s string
    // similar pattern to isomorphic string question
    // but instead of char to char its char to string
    public boolean wordPattern(String pattern, String s) {
        char[] cToStr = new char[26];
        Map<String, Integer> strToC = new HashMap<>();
        char patternChar;
        int charValue;
        String[] words = s.split(" ");

        if (words.length != pattern.length()) {
            return false;
        }

        int i = 0;
        while (i < pattern.length()) {
            patternChar = pattern.charAt(i);
            charValue = patternChar - 97;

            if (cToStr[charValue] == 0 && strToC.get(words[i]) == null) {
                cToStr[charValue] = patternChar;
                strToC.put(words[i], charValue);
            } else if (!Objects.equals(strToC.get(words[i]), charValue)) {
                return false;
            }
            i++;
        }
        return true;
    }

    @Test
    void eg1Test() {
        String pattern = "abba";
        String s = "dog cat cat dog";
        assertTrue(wordPattern(pattern, s));
    }

    @Test
    void eg2Test() {
        String pattern = "abba";
        String s = "dog cat cat fish";
        assertFalse(wordPattern(pattern, s));
    }

    @Test
    void eg3Test() {
        String pattern = "aaaa";
        String s = "dog cat cat dog";
        assertFalse(wordPattern(pattern, s));
    }

    @Test
    void eg4Test() {
        String pattern = "abba";
        String s = "dog dog dog dog";
        assertFalse(wordPattern(pattern, s));
    }

    @Test
    void eg5Test() {
        String pattern = "aaa";
        String s = "aa aa aa aa";
        assertFalse(wordPattern(pattern, s));
    }
}
