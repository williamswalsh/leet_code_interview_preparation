package top_150_interview_qs;

import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
public class Q169MajorityElementTest {

    public int majorityElement(int[] nums) {
        int n = nums.length;
        int majCount = n/2;

        Map<Integer, Integer> map = new HashMap<>();
        int i=0;
        int count;

        while(i<n) {
            if (map.containsKey(nums[i])){
                count = map.get(nums[i]) + 1;
                if (count > majCount) {
                    return nums[i];
                }
                map.put(nums[i], count);
            } else {
                map.put(nums[i], 1);
            }
            i++;
        }
        return nums[0];
    }

// Map getOrDefault method
// Must iterate array then iterate hashmap.
//    2N time complexity
    public int majorityElementGetOrDefault(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        n = n / 2;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n) {
                return entry.getKey();
            }
        }
        return 0;
    }

// Sorting an array takes - O(n log n) time
    public int majorityElementBySorting(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n/2];
    }


//    Moore Voting Algorithm
    public int majorityElementMoore(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int i = 0; i < nums.length; i++) {
            if(count==0) {
                candidate = nums[i];
                count++;
            }

            if(candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }


    private static Stream<Arguments> inputsAndResults() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 3}, 3),
                Arguments.of(new int[]{2,2,1,1,1,2,2}, 2),
                Arguments.of(new int[]{1}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsAndResults")
    void majorityElemFirstCaseTest(int[] nums, int expectedMajorityElem) {

        int actual = majorityElement(nums);
        assertEquals(expectedMajorityElem, actual);
    }

    @ParameterizedTest
    @MethodSource("inputsAndResults")
    void majorityElemBySortingTest(int[] nums, int expectedMajorityElem) {

        int actual = majorityElementMoore(nums);
        assertEquals(expectedMajorityElem, actual);
    }


    @Test
    public void testHarness() {
        long startTime, endTime, duration, avg;
        long noOfRuns = 1000000;

        startTime = System.nanoTime();

        int[] nums = {2,2,1,1,1,2,2};

        for (int i = 0; i < noOfRuns; i++) {
//            majorityElementMoore(nums);
            majorityElement(nums);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        log.info("startTime: " + startTime);
        log.info("endTime: "+ endTime);
        log.info("duration: "+ duration);
        log.info("noOfRuns: "+ noOfRuns);
        avg = duration/noOfRuns;
        log.info("Average test duration across " + noOfRuns + " iterations: " + avg + " nanoseconds");
    }
}
