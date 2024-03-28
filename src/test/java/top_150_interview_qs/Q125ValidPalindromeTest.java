package top_150_interview_qs;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Log
public class Q125ValidPalindromeTest {
    /**
     * A phrase is a palindrome if
     * - after converting all uppercase letters into lowercase letters
     * - removing all non-alphanumeric characters ???? !(A-z || 0-9)
     * it reads the same forward and backward.
     * <p>
     * Alphanumeric characters include letters and numbers.
     * Given a string s, return true if it is a palindrome, or false otherwise.
     */

    private static final Pattern validCharsPattern = Pattern.compile("[A-Za-z0-9]");

    private boolean isInvalid(char c) {
        return !validCharsPattern.matcher(String.valueOf(c)).find();
    }

    @Test
    void isInvalidTest() {
        assertFalse(isInvalid('n'));
    }

/**
 * Leetcode: 22ms, 45.40MB
 */
    public boolean isPalindromeSlow(String s) {
        char[] target = s.toLowerCase().toCharArray();
        int i = 0;
        int j = target.length - 1;

        while (i < j) {
            if (isInvalid(target[i]) && isInvalid(target[j])) {
                i++;
                j--;
                continue;
            }
            if (isInvalid(target[i])) {
                i++;
                continue;
            }
            if (isInvalid(target[j])) {
                j--;
                continue;
            }
            if (target[i] != target[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

/**
 * Leetcode: 4ms, 44.57MB
 * Faster than slow version.
 * Less memory usage than slow version.
*/
    public boolean isPalindrome(String s) {
        char[] c = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase().toCharArray();

        if (c.length <= 1) {
            return true;
        }
        for (int i = 0, j = c.length - 1; i <= j; i++, j--) {
            if (c[i] != c[j]) {
                return false;
            }
        }
        return true;
    }

    @Test
    void isPalindromeTrueWhitespaceStringTest() {
        String candidate = " ";
        boolean expected = true;

        boolean actual = isPalindrome(candidate);
        assertEquals(expected, actual);
    }

    @Test
    void isPalindromeTrueTest() {
        String candidate = "racecar";
        boolean expected = true;

        boolean actual = isPalindrome(candidate);
        assertEquals(expected, actual);
    }

    @Test
    void isPalindromeWithCapitalTrueTest() {
        String candidate = "raceCar";
        boolean expected = true;

        boolean actual = isPalindrome(candidate);
        assertEquals(expected, actual);
    }

    @Test
    void isPalindromeWithOtherCharactersTest() {
        // "amanaplanacanalpanama" is a palindrome.
        String candidate = "A man, a plan, a canal: Panama";
        boolean expected = true;

        boolean actual = isPalindrome(candidate);
        assertEquals(expected, actual);
    }

    @Test
    void isPalindromeFalseTest() {
        String candidate = "notAPalindrome";
        boolean expected = false;

        boolean actual = isPalindrome(candidate);
        assertEquals(expected, actual);
    }

    @Test
    void isPalindromeFalseWithSpacesTest() {
        String candidate = "race a car";
        boolean expected = false;

        boolean actual = isPalindrome(candidate);
        assertEquals(expected, actual);
    }
}
