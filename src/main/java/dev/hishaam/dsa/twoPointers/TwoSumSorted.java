package dev.hishaam.dsa.twoPointers;

/**
 * Problem: Two Sum II - Input Array Is Sorted (LeetCode #167)
 *
 * <p>Description: Given a 1-indexed sorted array of integers, find two numbers that add up to the
 * target. Return their indices (1-indexed). The solution must use constant extra space.
 *
 * <p>Example: Input: numbers = [2, 7, 11, 15], target = 9 Output: [1, 2] (1-indexed: numbers[1] +
 * numbers[2] = 2 + 7 = 9)
 *
 * <p>Approach: Two Pointers Since the array is sorted, use two pointers at both ends. If sum is too
 * small, move left pointer right (increase sum). If sum is too large, move right pointer left
 * (decrease sum).
 *
 * <p>Time Complexity: O(n) - Each pointer moves at most n times Space Complexity: O(1) - Only using
 * two pointers
 */
public class TwoSumSorted {

  // ═══════════════════════════════════════════════════════════════════════════
  // Two Pointers on Sorted Array - O(n) time, O(1) space
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // EXAMPLE WALKTHROUGH with [2,7,11,15], target=9:
  // i=0,j=3: 2+15=17 > 9 → j--
  // i=0,j=2: 2+11=13 > 9 → j--
  // i=0,j=1: 2+7=9 ✓ → return [1,2] (1-indexed)
  public int[] twoSum(int[] numbers, int target) {
    int i = 0, j = numbers.length - 1;
    int[] ans = new int[2];

    while (i < j) {
      if (numbers[i] + numbers[j] == target) {
        // IMPORTANT: Problem requires 1-indexed output
        ans[0] = i + 1;
        ans[1] = j + 1;
        break;
      } else if (numbers[i] + numbers[j] < target) {
        i++; // Sum too small → need larger value
      } else {
        j--; // Sum too large → need smaller value
      }
    }
    return ans;
  }
}
