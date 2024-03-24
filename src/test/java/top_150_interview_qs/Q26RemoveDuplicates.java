package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Q26RemoveDuplicates {

    public int removeDuplicatesCopied(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public int removeDuplicatesThirdImpl(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i-1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

//    [1,1,2]
//      i^
//      j^
//    if i != j
//       j++
//
//

    public int removeDuplicatesFirstImpl(int[] nums) {

//        Init set
        Set<Integer> unique = new LinkedHashSet<>();

//        Add values to set
        for (int num : nums) {
            unique.add(num);
        }

//        Iterate set adding values to nums array
        int index = 0;
        for (Integer integer : unique) {
            nums[index] = integer;
            index++;
        }
//        Return number of items in unique set
        return index;
    }


    @Test
    void removeDuplicatesTest() {
        int[] nums = {1,1,2};
        int[] expectedNums = {1,2};
        int k = removeDuplicatesThirdImpl(nums);

        assertEquals(k, expectedNums.length);
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

    @Test
    void removeDuplicatesSecondTest() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int[] expectedNums = {0,1,2,3,4}; //,_,_,_,_,_};
        int k = removeDuplicatesThirdImpl(nums);
        // Output - k: 5 - actual k = 7 ?????

        assertEquals(k, expectedNums.length);
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }
}
