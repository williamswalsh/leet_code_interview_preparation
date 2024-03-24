package top_150_interview_qs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Q121BuyingSellingStockTest {
    int[] prices = {983,341,957,541,470,660,118,742,334,822,165,145,730,656,567,25,684,113,351,295,468,918,587,4,399,220,11,222,777,127,135,688,267,570,342,748,382,428,340,35,896,846,376,655,147,891,198,420,729,685,989,543,285,822,254,878,380,758,490,73,870,328,234,489,990,387,688,12,795,746,275,371,321,298,186,925,845,816,775,647,379,15,602,756,619,256,106,312,965,661,973,147,437,796,56,955,846,245,502,889,557,281,936,812,880,880,834,186,303,96,706};


//    You are given an array prices where prices[i] is the price of a given stock on the ith day.
//    You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
//    Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
//    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
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
    public int maxProfitWithDeltaVariable(int[] prices) {
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

    public int maxProfitIfStatementsMerged(int[] prices) {
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

//    @Test
//    void readPriceFileTest() throws IOException {
//        readPriceFile();
//    }

    public int[] readPriceFile() throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("/Users/legoman/code/leetcode/interview_prep/src/test/resources/prices.txt"));
        String[] priceStrs = lines.get(0).split(",");
        int[] prices = new int[priceStrs.length];

        for (int i=0; i<priceStrs.length; i++) {
            prices[i] = Integer.parseInt(priceStrs[i]);
        }
        return prices;
    }

    public int[] readPriceFileBetter() throws IOException {
        return Files.lines(
                Paths.get("/Users/legoman/code/leetcode/interview_prep/src/test/resources/prices.txt"))
                .mapToInt(Integer::parseInt).toArray();
    }
}
