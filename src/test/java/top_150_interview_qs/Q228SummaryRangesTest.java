package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * <pre>
 * You are given a sorted unique integer array nums.
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges,
 * and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 * "a->b" if a != b
 * "a" if a == b
 *
 * Constraints:
 * - 0 <= nums.length <= 20
 * - -231 <= nums[i] <= 231 - 1 - Can use a short instead of an int
 * - All the values of nums are unique.
 * - nums is sorted in ascending order.
 * </pre>
 */
public class Q228SummaryRangesTest {


    /**
     * <pre>
     * This is my first implementation.
     * I am using 3 index variables.
     *
     * Leetcode:
     * - 5ms       - beats 78.93% of users with Java
     * - 41.60MB   - beats 59.85% of users with Java
     * </pre>
     */
    public List<String> summaryRangesThreeVars(int[] nums) {
//    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();

        if (nums.length == 1) {
            summary.add("" + nums[0]);
            return summary;
        }

        int start = 0;
        int end = 0;
        int newEnd = 0;

        while (newEnd < nums.length) {
            newEnd = end + 1;

            if (newEnd < nums.length && nums[end] == nums[newEnd] - 1) {
                end = newEnd;
            } else {
                if (start != end) {
                    summary.add(nums[start] + "->" + nums[end]);
                    start = newEnd;
                    end = newEnd;
                } else {
                    summary.add("" + nums[start]);
                    start = newEnd;
                    end = newEnd;
                }
            }
        }
        return summary;
    }

    /**
     * <pre>
     * This is my second implementation.
     * I am using less variables for the indexes 2 instead of three.
     * Not using the newEnd index variable.
     *
     * Leetcode:
     * - 5ms       - beats 78.93% of users with Java
     * - 41.49MB   - beats 73.69% of users with Java
     * </pre>
     */
//    public List<String> summaryRangesLessVars(int[] nums) {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();

        if (nums.length == 1) {
            summary.add("" + nums[0]);
            return summary;
        }

        int start = 0;
        int end = 0;

        while (end < nums.length) {
            if (end + 1 < nums.length && nums[end] == nums[end+1] - 1) {
                end += 1;
            } else {
                if (start != end) {
                    summary.add(nums[start] + "->" + nums[end]);
                } else {
                    summary.add("" + nums[start]);
                }
                start = end + 1;
                end = end + 1;
            }
        }
        return summary;
    }

    @Test
    void eg1Test() {
        int[] nums = {0, 1, 2, 4, 5, 7};
        List<String> expected = Arrays.asList("0->2", "4->5", "7");
        List<String> actual = summaryRanges(nums);
        assertEquals(expected, actual);
    }

    @Test
    void eg2Test() {
        int[] nums = {0, 2, 3, 4, 6, 8, 9};
        List<String> expected = Arrays.asList("0", "2->4", "6", "8->9");
        List<String> actual = summaryRanges(nums);
        assertEquals(expected, actual);
    }

    @Test
    void emptyArrayTest() {
        int[] nums = {};
        List<String> expected = new ArrayList<>();
        List<String> actual = summaryRanges(nums);
        assertEquals(expected, actual);
    }

    @Test
    void singleElementArrayTest() {
        int[] nums = {5};
        List<String> expected = List.of("5");
        List<String> actual = summaryRanges(nums);
        assertEquals(expected, actual);
    }
}
