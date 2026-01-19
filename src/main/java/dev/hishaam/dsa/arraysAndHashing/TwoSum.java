package dev.hishaam.dsa.arraysAndHashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Two Sum (LeetCode #1)
 *
 * <p>Description: Given an array of integers nums and an integer target, return the indices of the
 * two numbers such that they add up to target. Each input has exactly one solution, and you may not
 * use the same element twice.
 *
 * <p>Example: Input: nums = [2, 7, 11, 15], target = 9 Output: [0, 1] (because nums[0] + nums[1] =
 * 2 + 7 = 9)
 *
 * <p>Approach: HashMap for O(1) Complement Lookup Store each number's index in a HashMap, then for
 * each number check if its complement (target - num) exists in the map. This avoids O(n²) brute
 * force.
 *
 * <p>Time Complexity: O(n) - Two passes through the array Space Complexity: O(n) - HashMap stores
 * up to n elements
 */
public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    // KEY: Map stores value -> index for O(1) complement lookup
    Map<Integer, Integer> indexMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      indexMap.put(nums[i], i);
    }
    int[] ans = new int[2];
    for (int i = 0; i < nums.length; i++) {
      // IMPORTANT: Check complement exists AND ensure it's not the same element (i !=
      // index)
      if (indexMap.containsKey(target - nums[i]) && i != indexMap.get(target - nums[i])) {
        ans[0] = i;
        ans[1] = indexMap.get(target - nums[i]);
        break;
      }
    }
    return ans;
  }
}
