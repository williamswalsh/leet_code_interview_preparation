package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Q1MergeArraysTest {

    /**
     * Start with 3 pointers.
     * i - End index of numbers in nums1
     * j - End index of nums2
     * k - Actual end index of nums1
     * Think about nums2 as being the input array for the code.
     * Once the nums2 index j is invalid (<0) while(j>=0).
     * If the nums1 index value is greater - insert that value into nums1 end index
     * else f the nums2 index value is greater - insert that value into nums1 end index
     * The iteration is controlled by decrementing the indexes, no inner loop.
     *
     * Example 1:
     * <p>
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     * Example 2:
     * <p>
     * Input: nums1 = [1], m = 1, nums2 = [], n = 0
     * Output: [1]
     * Explanation: The arrays we are merging are [1] and [].
     * The result of the merge is [1].
     * Example 3:
     * <p>
     * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
     * Output: [1]
     * Explanation: The arrays we are merging are [] and [1].
     * The result of the merge is [1].
     * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
     */
//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//
//        if (n == 0 && m == 0) {
//            nums1 = new int[0];
//            return;
//        }
//        if (n == 0) {
//            // Do nothing - in this case nums1 is correct
//            return;
//        }
//        if (m == 0) {
//            nums1[0] = nums2[0];
//            return;
//        }
//
//        // Merging the arrays
//        mergeArrays(a, b);
//
//// Input: nums1 = [1,2,3,0,0,0]
////                 ^
//// nums2 = [2,5,6],
////          ^
//// n = 3
//// * Output: [1,2,2,3,5,6]
//// * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
//// * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
//        int tmp;
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (nums1[i] > nums2[j]) {
//                    tmp = nums1[i];
//                    nums1[i] = nums2[j];
////                    tmp;
//                }
//            }
//        }
//    }

    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = n + m - 1;

        while(j >=0) {
            if(i>=0 && nums1[i]>nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        return nums1;
    }

    @Test
    public void mergeArraysTest() {
        int[] a = {1,2,3,0,0,0};
        int[] b = {2,5,6};
        int[] expected = {1,2,2,3,5,6};
        int[] actual = merge(a, 3, b, 3);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void mergeArraysUnequalLengthTest() {
        int[] a = {1,2,3,4,0,0,0};
        int[] b = {2,5,6};
        int[] expected = {1,2,2,3,4,5,6};
        int[] actual = merge(a, 4, b, 3);

        assertArrayEquals(expected, actual);
    }

    private int[] shiftRightFromStart(int[] a, int start) {
        // shift all elements to the right of start by 1 position
//        [1,2,3,0,0,0] start=0
//         ^
//        [0,1,2,3,3,0]
        for (int i = a.length - 1; i >= start; i--) {
            if (i == 0) {
                a[i] = 0;
            } else {
                a[i] = a[i - 1];
            }
        }
        return a;
    }

    @Test
    public void shiftFromIndexTwoTest() {
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] expected = {1, 2, 3, 3, 0, 0};
        int[] actual = shiftRightFromStart(a, 2);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shiftFromIndexZeroTest() {
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] expected = {0, 1, 2, 3, 0, 0};
        int[] actual = shiftRightFromStart(a, 0);
        assertArrayEquals(expected, actual);
    }
}
