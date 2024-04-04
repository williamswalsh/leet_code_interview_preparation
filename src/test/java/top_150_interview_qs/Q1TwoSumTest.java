package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * <pre>
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Constraints:
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 * - Can't sort numbers as need to identify element by its original index.??
 * </pre>
 */
public class Q1TwoSumTest {

    /**
     * <pre>
     * This is my brute force implementation.
     *
     * Leetcode:
     * - 44ms        - beats 40.38% of users with Java
     * - 44.37MB     - beats 94.98% of users with Java
     * </pre>
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * <pre>
     * This is my hashmap implementation.
     * Runtime Complexity - O(n):
     * - only iterate loop once looking for the inverse(complement) value.
     * - inverse value = target - value >> =9-2=7
     *
     * Leetcode:
     * - 3ms         - beats 68.04% of users with Java
     * - 44.90MB     - beats 42.09% of users with Java
     * </pre>
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    @Test
    void eg1Test() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};
        int[] actual = twoSum(nums, target);
        assertArrayEquals(expected, actual);
    }


    @Test
    void eg2Test() {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] expected = {1, 2};
        int[] actual = twoSum(nums, target);
        assertArrayEquals(expected, actual);
    }

    @Test
    void eg3Test() {
        int[] nums = {3, 3};
        int target = 6;
        int[] expected = {0, 1};
        int[] actual = twoSum(nums, target);
        assertArrayEquals(expected, actual);
    }
}
