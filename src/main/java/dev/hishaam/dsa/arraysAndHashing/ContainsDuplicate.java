package dev.hishaam.dsa.arraysAndHashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Contains Duplicate (LeetCode #217)
 *
 * <p>Description: Given an integer array nums, return true if any value appears at least twice in
 * the array, and return false if every element is distinct.
 *
 * <p>Example: Input: nums = [1,2,3,1] Output: true
 *
 * <p>Approach: HashMap Frequency Count Use a HashMap to count the frequency of each element. If any
 * element appears more than once, return true.
 *
 * <p>Time Complexity: O(n) - single pass to build frequency map, single pass to check Space
 * Complexity: O(n) - HashMap stores up to n unique elements
 *
 * <p>NOTE: A more optimal approach would use a HashSet and check contains() + add() in a single
 * pass, returning immediately upon finding a duplicate.
 */
public class ContainsDuplicate {
  public boolean containsDuplicate(int[] nums) {
    // KEY: Use HashMap to track how many times each number appears
    Map<Integer, Integer> frequency = new HashMap<>();
    for (int num : nums) {
      // REMEMBER: getOrDefault avoids null checks - returns 0 if key doesn't exist
      Integer currentFrequency = frequency.getOrDefault(num, 0) + 1;
      frequency.put(num, currentFrequency);
    }
    for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
      // KEY: Duplicate means frequency > 1, not >= 1
      if (entry.getValue() > 1) {
        return true;
      }
    }
    return false;
  }
}
