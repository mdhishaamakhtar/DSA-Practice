package dev.hishaam.dsa.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: 3Sum (Leetcode #15)
 *
 * <p>Description: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, j != k, and nums[i] + nums[j] + nums[k] == 0. The solution set must not
 * contain duplicate triplets.
 *
 * <p>Example: Input: nums = [-1, 0, 1, 2, -1, -4] Output: [[-1, -1, 2], [-1, 0, 1]]
 *
 * <p>Approach: Sorting + Two Pointers 1. Sort the array to enable two-pointer technique and easy
 * duplicate skipping 2. Fix one element (k), then use two pointers to find pairs that sum to
 * -nums[k] 3. Skip duplicates at all three positions to avoid duplicate triplets
 *
 * <p>Time Complexity: O(n²) - O(n log n) for sort + O(n²) for nested loops (two pointers inside
 * outer loop) Space Complexity: O(1) - excluding the output array, only constant extra space used
 */
public class ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    // IMPORTANT: Sorting enables two-pointer approach and makes duplicate detection
    // trivial
    Arrays.sort(nums);
    for (int k = 0; k < nums.length - 2; k++) {

      // KEY: Skip duplicate values for k to avoid duplicate triplets in result
      if (k > 0 && nums[k] == nums[k - 1]) {
        continue;
      }

      // Two pointers: i starts just after k, j starts at end
      int i = k + 1, j = nums.length - 1;
      while (i < j) {
        int currentSum = nums[i] + nums[j] + nums[k];
        if (currentSum == 0) {
          List<Integer> triplet = new ArrayList<>(List.of(nums[i], nums[j], nums[k]));
          ans.add(triplet);
          i++;
          j--;
          // IMPORTANT: Skip duplicates for i and j after finding a valid triplet
          while (i < j && nums[i] == nums[i - 1]) {
            i++;
          }
          while (i < j && nums[j] == nums[j + 1]) {
            j--;
          }
        } else if (currentSum > 0) {
          // TRICK: Sum too large, decrease it by moving right pointer left (smaller
          // value)
          j--;
        } else {
          // TRICK: Sum too small, increase it by moving left pointer right (larger value)
          i++;
        }
      }
    }
    return ans;
  }
}
