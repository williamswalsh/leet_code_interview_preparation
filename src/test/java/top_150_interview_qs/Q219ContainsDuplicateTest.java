package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * <pre>
 * Given an integer array nums and an integer k, return true if there are two
 * distinct indices i and j in the array
 * such that:
 * 1) nums[i] == nums[j]
 * AND
 * 2) abs(i - j) <= k.
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 * </pre>
 */
public class Q219ContainsDuplicateTest {

    /**
     * Brute force approach.
     * Leetcode Time limit exceeded.
     */
    public boolean containsNearbyDuplicateBruteForce(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] == nums[j] && abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    // could control ptr locations by using the range amount
    // only comparing ints whose indexes meet the criteria of being "Nearby"
    // the nearby criteria is the upper limit for the inner loop j.
    // Calculate indexes that meet the criteria first abs(i - j) <= k
    // Only iterate through those indexes
    /**
     * <pre>
     * This is my second implementation.
     *
     * Leetcode:
     * - 1395ms      - beats  5.07% of users with Java
     * - 54.74MB     - beats 95.46% of users with Java
     * </pre>
     */
    public boolean containsNearbyDuplicateStillBruteForce(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; abs(i - j) <= k && j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <pre>
     * This is my third implementation. Just as slow.
     *
     * Leetcode:
     * - 1365ms      - beats  5.07% of users with Java
     * - 55.35MB     - beats 77.65% of users with Java
     * </pre>
     */
    public boolean containsNearbyDuplicateCaseWhereKIsTooBig(int[] nums, int k) {
        if(k>=nums.length) {
            for (int i = 0; i < nums.length-1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if(nums[i] == nums[j]) {
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < nums.length -1; i++) {
                for (int j = i + 1; abs(i - j) <= k && j < nums.length; j++) {
                    if(nums[i] == nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * <pre>
     * This is my fourth implementation.
     * This uses a hashset to record the multiple unique elements.
     * It removes the starting element at index i from the set, once the window size is exceeded.
     *
     * Leetcode:
     * - 17ms      - beats 88.29% of users with Java
     * - 57.15MB   - beats 49.89% of users with Java
     * </pre>
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
//    public boolean containsNearbyDuplicateWithHash(int[] nums, int k) {

        if(nums == null || nums.length < 2 || k == 0) {
            return false;
        }

        HashSet<Integer> intSet = new HashSet<>();
        int j = 0;
        for (int num : nums) {
            if (!intSet.add(num)) {
                return true;
            }

            if (intSet.size() >= k + 1) {
                intSet.remove(nums[j++]);
            }
        }
        return false;
    }

    /**
     * <pre>
     * This is my fifth implementation.
     * Enhanced for loop, speeds up execution.
     *
     * Leetcode:
     * - 16ms      - beats 92.12% of users with Java
     * - 56.21MB   - beats 63.74% of users with Java
     * </pre>
     */
    public boolean containsNearbyDuplicateEnhancedFor(int[] nums, int k) {
        HashSet<Integer> intSet = new HashSet<>();
        int j = 0;
        for (int num : nums) {
            if (!intSet.add(num)) {
                return true;
            }

            if (intSet.size() >= k + 1) {
                intSet.remove(nums[j++]);
            }
        }
        return false;
    }

    @Test
    void testEg1() {
        int[] nums = {1,2,3,1};
        int k = 3;
        assertTrue(containsNearbyDuplicate(nums, k));
    }

    @Test
    void testEg2() {
        int[] nums = {1,0,1,1};
        int k = 1;
        assertTrue(containsNearbyDuplicate(nums, k));

    }

    @Test
    void testEg3() {
        int[] nums = {1,2,3,1,2,3};
        int k = 2;
        assertFalse(containsNearbyDuplicate(nums, k));
    }
}
