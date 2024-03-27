package top_150_interview_qs;

import lombok.extern.java.Log;

@Log
public class Q125ValidPalindromeTest {
    /**
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
     * Given a string s, return true if it is a palindrome, or false otherwise.
     */

    public boolean isPalindrome(String s) {
        // converting all uppercase letters into lowercase letters
        // remove non-alphanumeric characters
        return false;
    }

//    @Test
//    void isPalindromeTrueWhitespaceStringTest() {
//        String candidate =  " ";
//        boolean expected = true;
//
//        boolean actual = isPalindrome(candidate);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void isPalindromeTrueTest() {
//        String candidate = "racecar";
//        boolean expected = true;
//
//        boolean actual = isPalindrome(candidate);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void isPalindromeWithOtherCharactersTest() {
//        // "amanaplanacanalpanama" is a palindrome.
//        String candidate = "A man, a plan, a canal: Panama";
//        boolean expected = true;
//
//        boolean actual = isPalindrome(candidate);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void isPalindromeFalseTest() {
//        String candidate = "notAPalindrome";
//        boolean expected = false;
//
//        boolean actual = isPalindrome(candidate);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void isPalindromeFalseWithSpacesTest() {
//        String candidate = "race a car";
//        boolean expected = false;
//
//        boolean actual = isPalindrome(candidate);
//        assertEquals(expected, actual);
//    }
//
//
//    @Test
//    public void testHarness() {
//        long startTime, endTime, duration, avg;
//        long noOfRuns = 100000;
//
//        startTime = System.nanoTime();
//
//        for (int i = 0; i < noOfRuns; i++) {
//
//        }
//        endTime = System.nanoTime();
//        duration = (endTime - startTime);
//        System.out.println("startTime: " + startTime);
//        System.out.println("endTime: "+ endTime);
//        System.out.println("duration: "+ duration);
//        System.out.println("noOfRuns: "+ noOfRuns);
//        avg = duration/noOfRuns;
//        System.out.println("Average test duration across " + noOfRuns + " iterations: " + avg + " nanoseconds");
//    }
}
