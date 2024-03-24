package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Q121BuyingSellingStockTest {
//    Question:
//    You are given an array prices where prices[i] is the price of a given stock on the ith day.
//    You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
//    Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
//    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

    private static final String priceAbsoluteFilePathStr = "/Users/legoman/code/leetcode/interview_prep/src/test/resources/prices.txt";
    private static final Path priceFilePath = Paths.get(priceAbsoluteFilePathStr);

    public int maxProfitWithDaysRecorded(int[] prices) {
        int[] best = new int[3];
        int diff;
        for (int i = 0; i < prices.length-1; i++) {
            for (int j = i+1; j < prices.length; j++) {
                System.out.println("i: " + i + ",j: " + j);

                if(prices[i] < prices[j]) {
                    diff = prices[j] - prices[i];
                    System.out.println("best[0]: " + best[0]);
                    if(best[0] < diff) {
                        best[0] = diff;
                        best[1] = i;
                        best[2] = j;
                    }
                }
            }
        }
        return best[0];
    }

    // This is very readable:
    public int maxProfitWithDiffVariable(int[] prices) {
        int bestDiff = 0;
        int diff;
        for (int i = 0; i < prices.length-1; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if(prices[i] < prices[j]) {
                    diff = prices[j] - prices[i];
                    if(bestDiff < diff) {
                        bestDiff = diff;
                    }
                }
            }
        }
        return bestDiff;
    }

    public int maxProfitWithIfStatementsMerged(int[] prices) {
        int bestDiff = 0;
        for (int i = 0; i < prices.length-1; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if(prices[i] < prices[j] && bestDiff < (prices[j] - prices[i])) {
                        bestDiff = prices[j] - prices[i];
                    }
                }
            }
        return bestDiff;
    }

    public int maxProfitIfConditionSimplified(int[] prices) {
        int bestDiff = 0;
        for (int i = 0; i < prices.length-1; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if((prices[j] - prices[i]) > bestDiff) {
                    bestDiff = prices[j] - prices[i];
                }
            }
        }
        return bestDiff;
    }

//        7,6,4,3,1
//       i^
//         j^
//        7<6 x, 7<4 x, 7<3 x, 7<1 x >> increment i
//        6<4 x, 6<3 x, 6<1 x        >> increment i
//        4<3 x, 4<1 x               >> increment i
//        3<1 x                      >> increment i >> end condition

//        7,1,5,3,6,4
//       i^
//         j^
//      7<1 x, 7<5 x, 7<3 x, 7<6 x, 7<4 x >> i++
//      1<5 y??

    @Test
    void maxProfitsExampleOneTest() {
        int[] prices = {7,1,5,3,6,4};
        int expected = 5;

        int actual = maxProfitIfConditionSimplified(prices);
        assertEquals(expected, actual);
    }

    @Test
    void maxProfitsExampleTwoTest() {
        int[] prices = {7,6,4,3,1};
        int expected = 0;

        int actual = maxProfitIfConditionSimplified(prices);
        assertEquals(expected, actual);
    }

    @Test
    void maxProfitsExampleThreeTest() throws IOException {
        int[] fetchedPrices = readPriceFileBetter();
        int expected = 999;

        int actual = maxProfitIfConditionSimplified(fetchedPrices);
        System.out.println("Actual: " + actual);
        assertEquals(expected, actual);
    }

    public int[] readPriceFile() throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(priceAbsoluteFilePathStr));
        String[] priceStrs = lines.get(0).split(",");
        int[] prices = new int[priceStrs.length];

        for (int i=0; i<priceStrs.length; i++) {
            prices[i] = Integer.parseInt(priceStrs[i]);
        }
        return prices;
    }

    public int[] readPriceFileBetter() throws IOException {
        try(Stream<String> priceStream = Files.lines(priceFilePath)) {
            return priceStream.mapToInt(Integer::parseInt).toArray();
        }
    }
}
