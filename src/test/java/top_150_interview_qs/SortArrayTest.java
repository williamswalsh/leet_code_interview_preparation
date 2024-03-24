package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortArrayTest {

    private int[] sort(int[] a) {
        int maxInd;
        boolean newMaxDetected = false;

        // loop to control end pointer
        for (int j = a.length-1; j >= 0; j--) {
            // set max as current end element
            maxInd = j;
            for (int i = 0; i < j; i++) {
                // find max
                if (a[i] > a[maxInd]) {
                    maxInd = i;
                    newMaxDetected = true;
                }
            }
            if (newMaxDetected) {
                swap(a, j, maxInd);
            }
        }
        return a;
    }

    private int[] swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        return arr;
    }

    @Test
    void sortAlreadySortedArrayTest() {
        int[] a = {1, 2, 3};
        int[] expected = {1, 2, 3};
        int[] actual = sort(a);

        assertArrayEquals(expected, actual);
    }

    @Test
    void sortEvenNumberArrayTest() {
        int[] a = {1, 4, 2, 3};
        int[] expected = {1, 2, 3, 4};
        int[] actual = sort(a);

        assertArrayEquals(expected, actual);
    }


    @Test
    void sortLargestNumberAtEndTest() {
        int[] a = {2, 1, 3};
        int[] expected = {1, 2, 3};
        int[] actual = sort(a);

        assertArrayEquals(expected, actual);
    }

    @Test
    void sortOddNumberArrayTest() {
        int[] a = {1, 4, 3};
        int[] expected = {1, 3, 4};
        int[] actual = sort(a);

        assertArrayEquals(expected, actual);
    }


    @Test
    void sortOddNumberArray2MovesRequiredTest() {
        int[] a = {2, 4, 1};
        int[] expected = {1, 2, 4};
        int[] actual = sort(a);

        assertArrayEquals(expected, actual);
    }


    @Test
    void sortArrayTest() {
        int[] a = {4, 99, 2, 5};
        int[] expected = {2, 4, 5, 99};
        int[] actual = sort(a);

        assertArrayEquals(expected, actual);
    }

    @Test
    void sortArrayWithZeroTest() {
        int[] a = {1, 2, 3, 0};
        int[] expected = {0, 1, 2, 3};
        int[] actual = sort(a);

        assertArrayEquals(expected, actual);
    }

    @Test
    void sortArrayWithNegativeNumberTest() {
        int[] a = {1, 2, 3, -1};
        int[] expected = {-1, 1, 2, 3};
        int[] actual = sort(a);

        assertArrayEquals(expected, actual);
    }
}
