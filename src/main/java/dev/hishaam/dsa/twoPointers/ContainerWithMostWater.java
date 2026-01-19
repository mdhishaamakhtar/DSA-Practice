package dev.hishaam.dsa.twoPointers;

/**
 * Problem: Container With Most Water (Leetcode #11)
 *
 * <p>Description: Given an array of heights where each element represents a vertical line, find two
 * lines that together with the x-axis form a container that holds the most water. Return the
 * maximum amount of water the container can store.
 *
 * <p>Example: Input: height = [1,8,6,2,5,4,8,3,7] Output: 49 (between index 1 and 8, min height =
 * 7, width = 7)
 *
 * <p>Approach: Two Pointers (Greedy) Start with the widest container (pointers at both ends).
 * Always move the pointer pointing to the shorter line inward, because the shorter line is the
 * limiting factor for height - keeping it can never produce a larger area as width decreases.
 *
 * <p>Time Complexity: O(n) - single pass through the array Space Complexity: O(1) - only using
 * constant extra space
 */
public class ContainerWithMostWater {

  // ═══════════════════════════════════════════════════════════════════════════
  // Two Pointers (Greedy) - O(n) time, O(1) space
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // KEY INSIGHT: Move the pointer at the shorter line. The shorter line limits
  // the height, keeping it while reducing width can only decrease area.
  //
  // EXAMPLE WALKTHROUGH with [1,8,6,2,5,4,8,3,7]:
  // i=0,j=8: h=min(1,7)=1, area=8*1=8, move i (shorter)
  // i=1,j=8: h=min(8,7)=7, area=7*7=49, move j (shorter)
  // i=1,j=7: h=min(8,3)=3, area=6*3=18, move j
  // ... continues, but 49 remains max
  // Final: 49
  public int maxArea(int[] height) {
    int i = 0, j = height.length - 1;
    int ans = 0;

    while (i < j) {
      // ─────────────────────────────────────────────────────────────────────
      // CALCULATE: Container height limited by shorter line
      // ─────────────────────────────────────────────────────────────────────
      int containerHeight = Math.min(height[i], height[j]);
      int area = (j - i) * containerHeight;
      ans = Math.max(ans, area);

      // ─────────────────────────────────────────────────────────────────────
      // GREEDY MOVE: Move the shorter side inward
      // WHY? Shorter line is the bottleneck. Moving taller one guarantees
      // smaller or equal area (width decreases, height can't increase).
      // ─────────────────────────────────────────────────────────────────────
      if (height[i] > height[j]) {
        j--;
      } else {
        i++;
      }
    }
    return ans;
  }
}
