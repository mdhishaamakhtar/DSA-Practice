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

  // ═══════════════════════════════════════════════════════════════════════════
  // APPROACH 1: Running Minimum (DP-like) - O(n)
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // KEY INSIGHT: For any sell day j, the optimal buy day is the minimum price
  // from day 0 to j-1. Track this running minimum as we iterate.
  //
  // EXAMPLE WALKTHROUGH with [7,1,5,3,6,4]:
  // j=1: price=1, profit=1-7=-6→0, minCost=min(7,1)=1
  // j=2: price=5, profit=5-1=4, minCost=1
  // j=3: price=3, profit=3-1=2, maxProfit stays 4, minCost=1
  // j=4: price=6, profit=6-1=5, maxProfit=5, minCost=1
  // j=5: price=4, profit=4-1=3, maxProfit stays 5
  // Final: 5
  public int maxProfitDP(int[] prices) {
    int minCost = prices[0];
    int maxProfit = 0;

    // NOTE: Start from index 1 since we need at least one day before to buy
    for (int j = 1; j < prices.length; j++) {
      // ─────────────────────────────────────────────────────────────────────
      // CALCULATE PHASE: What if we sell today?
      // ─────────────────────────────────────────────────────────────────────
      maxProfit = Math.max(maxProfit, prices[j] - minCost);

      // ─────────────────────────────────────────────────────────────────────
      // UPDATE PHASE: Update minCost for future sell days
      // ORDER MATTERS: Calculate profit BEFORE updating (can't buy & sell same day)
      // ─────────────────────────────────────────────────────────────────────
      minCost = Math.min(minCost, prices[j]);
    }
    return maxProfit;
  }

  // ═══════════════════════════════════════════════════════════════════════════
  // APPROACH 2: Sliding Window / Two Pointers - O(n)
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // L = buy day, R = sell day candidate
  //
  // EXAMPLE WALKTHROUGH with [7,1,5,3,6,4]:
  // R=1: prices[1]=1 < prices[0]=7, L jumps to 1
  // R=2: prices[2]=5 > prices[1]=1, profit=4
  // R=3: prices[3]=3 > prices[1]=1, profit=2, maxProfit=4
  // R=4: prices[4]=6 > prices[1]=1, profit=5, maxProfit=5
  // R=5: prices[5]=4 > prices[1]=1, profit=3, maxProfit=5
  // Final: 5
  public int maxProfitSlidingWindow(int[] prices) {
    int L = 0, R = 1, maxProfit = 0;

    while (R < prices.length) {
      if (prices[L] < prices[R]) {
        // Valid transaction: buy low, sell high
        maxProfit = Math.max(maxProfit, (prices[R] - prices[L]));
      } else {
        // ─────────────────────────────────────────────────────────────────────
        // JUMP: Found a lower buy price at R, so move L to R
        // WHY? Any future sell day would be better paired with R than L
        // ─────────────────────────────────────────────────────────────────────
        L = R;
      }
      R++;
    }
    return maxProfit;
  }
}
