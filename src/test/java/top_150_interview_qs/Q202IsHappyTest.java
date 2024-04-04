package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>
 * Write an algorithm to determine if a number n is happy.
 * A happy number is a number defined by the following process:
 * - Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * - Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * - Those numbers for which this process ends in 1 are happy.
 * - Return true if n is a happy number, and false if not.
 *
 * Constraints:
 * 1 <= n <= 231 - 1
 * </pre>
 */
public class Q202IsHappyTest {

//    public boolean isHappyFirst(int n) {
//        char[] digits = String.valueOf(n).toCharArray();
//        int sum = 0;
//        for (char digit: digits) {
//            sum += digit^2;
//        }
//        if (sum == 1) {
//            return true;
//        } else if(alreadySeen) {
//            return isHappy(sum);
//        } else {
//            return false;
//        }
//    }


    /**
     * <pre>
     * This is my first implementation.
     *
     * Leetcode:
     * - 8ms         - beats  5.58% of users with Java
     * - 42.54MB     - beats  5.19% of users with Java
     * </pre>
     */
    public boolean isHappy(int n) {
        Set<Integer> integerSet = new HashSet<>();

        int sum = 0;

        while (true) {
            sum = 0;

            if (!integerSet.add(n)) {
                return false;
            }

            for (Integer digit: String.valueOf(n).chars().map(Character::getNumericValue).boxed().toList()) {
                sum += (digit * digit);
            }

            if (sum == 1) {
                return true;
            }
            n = sum;
        }
    }

    /**
     *  Example 1:
     *  * Input: n = 19
     *  * Output: true
     *  * Explanation:
     *  * 12 + 92 = 82
     *  * 82 + 22 = 68
     *  * 62 + 82 = 100
     *  * 12 + 02 + 02 = 1]
     */
    @Test
    void isHappyEg1Test() {
        int n = 19;
        assertTrue(isHappy(n));
    }

    @Test
    void isHappyEg2Test() {
        int n = 2;
        assertFalse(isHappy(n));
    }
}
