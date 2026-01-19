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
  public int[] twoSum(int[] numbers, int target) {
    // KEY: Start pointers at opposite ends to leverage sorted property
    int i = 0, j = numbers.length - 1;
    int[] ans = new int[2];
    while (i < j) {
      if (numbers[i] + numbers[j] == target) {
        // IMPORTANT: Problem requires 1-indexed output, so add 1 to both indices
        ans[0] = i + 1;
        ans[1] = j + 1;
        break;
      } else if (numbers[i] + numbers[j] < target) {
        // TRICK: Sum too small -> move left pointer right to increase sum
        i++;
      } else {
        // TRICK: Sum too large -> move right pointer left to decrease sum
        j--;
      }
    }
    return ans;
  }
}
