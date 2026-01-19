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
  public int maxArea(int[] height) {
    // KEY: Two pointers starting at maximum width
    int i = 0, j = height.length - 1;
    int ans = 0;
    while (i < j) {
      // KEY: Container height is limited by the shorter line
      int containerHeight = Math.min(height[i], height[j]);
      int area = (j - i) * containerHeight;
      ans = Math.max(ans, area);
      // KEY: Move the pointer at the shorter line inward.
      // The shorter line limits the height, so keeping it while
      // reducing width can only decrease area. Moving the taller
      // one would guarantee a smaller or equal area.
      if (height[i] > height[j]) {
        j--;
      } else {
        i++;
      }
    }
    return ans;
  }
}
