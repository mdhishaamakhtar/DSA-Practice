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

  // ═══════════════════════════════════════════════════════════════════════════
  // Two Pointers with Running Max - O(n) time, O(1) space
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // KEY INSIGHT: Water at position i = min(maxLeft, maxRight) - height[i]
  // We process the SHORTER side because it's the limiting factor.
  //
  // EXAMPLE WALKTHROUGH with [0,1,0,2,1,0,1,3,2,1,2,1]:
  // Pre-process: L skips 0,1 → L=3 (height=2), R stays 11 (height=1)
  // leftMax=2, rightMax=1
  // Since height[R]=1 < height[L]=2, process right side:
  // R=10: height=2 > rightMax=1, update rightMax=2
  // R=9: height=1 < rightMax=2, water += 2-1=1
  // R=8: height=2 >= rightMax=2, update rightMax=2
  // ... continues until L meets R
  // Final: 6 units of water
  public int trap(int[] height) {
    int length = height.length;
    int ans = 0;
    int L = 0, R = length - 1;

    // ─────────────────────────────────────────────────────────────────────
    // PRE-PROCESSING: Skip ascending edges where water cannot be trapped
    // ─────────────────────────────────────────────────────────────────────
    // Left side: skip zeros and rising edges [0,1,2,3,...] → no container
    while (L < length - 1 && ((height[L] == 0) || (height[L] < height[L + 1]))) {
      L++;
    }
    // Right side: mirror logic [...,1,2,3] → no container
    while (R > 1 && ((height[R] == 0) || (height[R] < height[R - 1]))) {
      R--;
    }

    int leftMax = height[L], rightMax = height[R];

    // ─────────────────────────────────────────────────────────────────────
    // MAIN LOOP: Process the shorter side (limiting factor)
    // ─────────────────────────────────────────────────────────────────────
    while (L < R) {
      if (height[L] < height[R]) {
        // Left is shorter → leftMax is the bottleneck
        // PROCESS-AFTER-MOVE: Move first, then calculate
        L++;
        if (height[L] < leftMax) {
          ans = ans + (leftMax - height[L]);
        } else {
          leftMax = height[L];
        }
      } else {
        // Right is shorter → rightMax is the bottleneck
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
