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

  // ═══════════════════════════════════════════════════════════════════════════
  // Sorting + Two Pointers - O(n²) time, O(1) space (excluding output)
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // EXAMPLE WALKTHROUGH with [-1,0,1,2,-1,-4]:
  // After sort: [-4,-1,-1,0,1,2]
  // k=0 (val=-4): i=1,j=5, need sum=4, max possible=-1+2=1 < 4, no triplet
  // k=1 (val=-1): i=2,j=5, need sum=1
  // sum=-1+2=1 ✓ → [-1,-1,2], skip duplicates
  // i=3,j=4: sum=0+1=1 ✓ → [-1,0,1]
  // k=2 (val=-1): SKIP (duplicate of k=1)
  // Final: [[-1,-1,2], [-1,0,1]]
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    // ─────────────────────────────────────────────────────────────────────
    // SORT: Enables two-pointer and makes duplicate detection trivial
    // ─────────────────────────────────────────────────────────────────────
    Arrays.sort(nums);

    for (int k = 0; k < nums.length - 2; k++) {
      // ─────────────────────────────────────────────────────────────────────
      // SKIP DUPLICATES (k level): Avoid duplicate triplets with same first element
      // ─────────────────────────────────────────────────────────────────────
      if (k > 0 && nums[k] == nums[k - 1]) {
        continue;
      }

      int i = k + 1, j = nums.length - 1;
      while (i < j) {
        int currentSum = nums[i] + nums[j] + nums[k];
        if (currentSum == 0) {
          ans.add(new ArrayList<>(List.of(nums[i], nums[j], nums[k])));
          i++;
          j--;
          // ─────────────────────────────────────────────────────────────────────
          // SKIP DUPLICATES (i,j level): After finding match, skip same values
          // ─────────────────────────────────────────────────────────────────────
          while (i < j && nums[i] == nums[i - 1]) {
            i++;
          }
          while (i < j && nums[j] == nums[j + 1]) {
            j--;
          }
        } else if (currentSum > 0) {
          j--; // Sum too large, need smaller
        } else {
          i++; // Sum too small, need larger
        }
      }
    }
    return ans;
  }
}
