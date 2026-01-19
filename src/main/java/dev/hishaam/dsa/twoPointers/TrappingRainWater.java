package dev.hishaam.dsa.twoPointers;

/**
 * Problem: Trapping Rain Water (LeetCode #42)
 *
 * <p>Description: Given n non-negative integers representing an elevation map where each bar has
 * width 1, compute how much water can be trapped after raining.
 *
 * <p>Example: Input: height = [0,1,0,2,1,0,1,3,2,1,2,1] Output: 6 Explanation: The elevation map
 * can trap 6 units of rain water.
 *
 * <p>Approach: Two Pointers with Running Max
 *
 * <p>KEY INSIGHT: Water trapped at position i = min(maxLeft, maxRight) - height[i] - We need to
 * know the tallest bar to the LEFT and RIGHT of each position - Water level at any point is limited
 * by the SHORTER of the two boundaries
 *
 * <p>WHY TWO POINTERS WORKS: - We don't need to know BOTH maxLeft and maxRight for every position
 * upfront - We only need to know which side is the "limiting factor" (the shorter boundary) - If
 * height[L] < height[R], then even if there's a taller bar beyond R, the water at L is still
 * bounded by leftMax (since leftMax <= height[L] < height[R]) - This means we can safely process
 * the left side without knowing the true rightMax
 *
 * <p>PROCESS-AFTER-MOVE PATTERN: - We move the pointer FIRST, then check for water - This ensures
 * boundary bars (the max heights) are never counted as holding water - Water is only calculated for
 * positions BETWEEN the current boundaries
 *
 * <p>Time Complexity: O(n) - single pass through the array Space Complexity: O(1) - only using
 * constant extra space
 */
public class TrappingRainWater {
  public int trap(int[] height) {
    int length = height.length;
    int ans = 0;
    int L = 0, R = length - 1;

    // OPTIMIZATION: Skip ascending edges on the left where water cannot be trapped
    // Water needs a "container" - ascending edges on the boundary can't hold water
    // Example: [0,1,2,3,...] - no water can be trapped on these rising edges
    // Stop when we find a local peak (height[L] >= height[L+1] and height[L] > 0)
    while (L < length - 1 && ((height[L] == 0) || (height[L] < height[L + 1]))) {
      L++;
    }

    // OPTIMIZATION: Skip ascending edges on the right (same logic, mirrored)
    // Example: [...,1,2,3] - no water can be trapped on these rising edges from
    // right
    while (R > 1 && ((height[R] == 0) || (height[R] < height[R - 1]))) {
      R--;
    }

    // Initialize max heights with the starting positions after optimization
    // These represent the tallest bars seen so far from each direction
    int leftMax = height[L], rightMax = height[R];

    while (L < R) {
      // KEY DECISION: Process the side with the SHORTER current height
      // WHY? The shorter side is the "bottleneck" for water capacity
      if (height[L] < height[R]) {
        // IMPORTANT: Move pointer FIRST, then calculate water
        // This ensures we don't count the boundary bar itself as holding water
        L++;

        // TRICK: Water calculation - only add water if current bar is below the max
        if (height[L] < leftMax) {
          // Water trapped = leftMax - current height
          // We use leftMax (not min of both) because we KNOW height[L] < height[R]
          // guarantees leftMax is the limiting factor here
          ans = ans + (leftMax - height[L]);
        } else {
          // Current bar is taller - update the max (this bar becomes new boundary)
          leftMax = height[L];
        }
      } else {
        // Mirror logic for the right side when right is the limiting factor
        R--;

        if (height[R] < rightMax) {
          ans = ans + (rightMax - height[R]);
        } else {
          rightMax = height[R];
        }
      }
    }
    return ans;
  }
}
