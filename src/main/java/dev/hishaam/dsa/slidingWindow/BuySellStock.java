package dev.hishaam.dsa.slidingWindow;

/**
 * Problem: Best Time to Buy and Sell Stock (LeetCode #121)
 *
 * <p>Description: Given an array where prices[i] is the price of a stock on day i, find the maximum
 * profit from buying on one day and selling on a later day. Return 0 if no profit possible.
 *
 * <p>Example: Input: prices = [7,1,5,3,6,4] Output: 5 (Buy on day 2 at price 1, sell on day 5 at
 * price 6)
 *
 * <p>Constraint: Must buy BEFORE selling (can't sell on same day or earlier)
 *
 * <p>Two approaches provided below:
 *
 * <p>APPROACH 1 - Running Minimum (DP-like): Track the minimum price seen so far. For each day, the
 * best possible profit is current price minus that minimum. This works because you're always
 * considering the optimal buy point for any potential sell day.
 *
 * <p>APPROACH 2 - Sliding Window: L = buy day, R = sell day. Expand R to explore sell options. When
 * prices[R] < prices[L], we've found a better buy opportunity, so jump L to R.
 *
 * <p>Time Complexity: O(n) - single pass for both approaches Space Complexity: O(1) - constant
 * extra space
 */
public class BuySellStock {

  // APPROACH 1: Running Minimum (feels like intuition, but it's actually DP!)
  // KEY INSIGHT: For any sell day j, the optimal buy day is simply the minimum
  // price from day 0 to j-1
  public int maxProfitDP(int[] prices) {
    // Track the minimum price seen so far - this is our optimal buy point
    int minCost = prices[0];
    int maxProfit = 0;

    // NOTE: Start from index 1 since we need at least one day before to buy
    for (int j = 1; j < prices.length; j++) {
      // TRICK: Calculate profit if we sell today using the best buy price so far
      // This implicitly considers ALL valid (buy, sell) pairs ending at j
      maxProfit = Math.max(maxProfit, prices[j] - minCost);

      // Update minimum for future sell days
      // ORDER MATTERS: Calculate profit BEFORE updating minCost
      // (can't buy and sell on same day)
      minCost = Math.min(minCost, prices[j]);
    }
    return maxProfit;
  }

  // APPROACH 2: Sliding Window / Two Pointers
  // L = buy day, R = sell day candidate
  public int maxProfitSlidingWindow(int[] prices) {
    int L = 0, R = 1, maxProfit = 0;

    while (R < prices.length) {
      if (prices[L] < prices[R]) {
        // Valid transaction: buy low, sell high
        maxProfit = Math.max(maxProfit, (prices[R] - prices[L]));
      } else {
        // KEY: Found a lower buy price at R, so move L to R
        // WHY? Any future sell day would be better paired with R than L
        // since prices[R] <= prices[L]
        L = R;
      }
      // Always expand the sell window
      R++;
    }
    return maxProfit;
  }
}
