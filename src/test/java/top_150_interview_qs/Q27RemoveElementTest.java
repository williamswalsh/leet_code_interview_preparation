package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Q27RemoveElementTest {

    public int removeElementNew(int[] nums, int val) {
        int i = 0;
        int index = nums.length;
        int tmp;

        while(i<index){
            if (nums[i] == val) {
                tmp = nums[index-1];
                nums[index-1] = nums[i];
                nums[i] = tmp;
                index--;
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return index;
    }

//    public int removeElement(int[] nums, int val) {
//        int index = 0;
//
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != val) {
//                nums[index] = nums[i];
//                index++;
//            }
//        }
//        return index;
//    }

    public int removeElementWithShift(int[] nums, int val) {
        int valCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == val) {
                nums[i] = -1;
                valCount++;

                for (int j = i; j < nums.length-1; j++) {
                    if(nums[j+1] == val) {
                        nums[j] = -1;
                    } else {
                        nums[j] = nums[j+1];
                    }
                }
            }
        }

        return nums.length - valCount;
    }

    public int removeElementSecondImplementation(int[] nums, int val) {
        int valCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == val) {
                nums[i] = -1;
                valCount++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == -1) {
                if (j == nums.length-1) { // Last index - no i+1 index
                    nums[j] = -1;
                    continue;
                }
                int k = j;
                while(k < nums.length-1) {
                    nums[k] = nums[k+1];
                    k++;
                }
            }
        }
        return nums.length - valCount;
    }

    @Test
    void shiftLeftTest() {
        int[] a = {3,4,4,3};
        int index = 0;
        int[] expected = {4, 4, 3, 3};
        int[] actual = shiftLeft(a, index);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shiftLeftLastElementTest() {
        int[] a = {3,4,4,3};
        int index = 3;
        int[] expected = {3, 4, 4, 3};
        int[] actual = shiftLeft(a, index);
        assertArrayEquals(expected, actual);
    }

    private int[] shiftLeft(int[] a, int i) {
        for (int j = i; j < a.length-1; j++) {
            a[j] = a[j+1];
        }
        return a;
    }

    @Test
    void removeElementSimpleArrayTest() {
        int[] nums = { 0, 1, 2};
        int val = 1;
        int expected = 2;
        int actual = removeElementNew(nums, val);

        assertEquals(expected, actual);
    }

    @Test
    void removeElementMultipleTargetHitsTest() {
        int[] nums = { 3, 4, 4, 3};
        int val = 3;
        int expected = 2;
        int actual = removeElementNew(nums, val);

        assertEquals(expected, actual);
    }
    @Test
    void removeElementWithShiftMultipleTargetHitsTest() {
        int[] nums = { 3, 2, 2, 3};
        int val = 3;
        int expected = 2;
        int actual = removeElementNew(nums, val);

        assertEquals(expected, actual);
    }

    @Test
    void removeElementSecondTest() {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int expected = 5;
        int actual = removeElementNew(nums, val);

        assertEquals(expected, actual);
    }
}
