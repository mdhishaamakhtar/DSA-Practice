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

  // ═══════════════════════════════════════════════════════════════════════════
  // HashMap Frequency Count - O(n) time, O(n) space
  // ═══════════════════════════════════════════════════════════════════════════
  // NOTE: A more optimal approach would use HashSet with add() returning false
  // for duplicates, but this shows the frequency counting pattern.
  public boolean containsDuplicate(int[] nums) {
    // ─────────────────────────────────────────────────────────────────────
    // PASS 1: Build frequency map
    // ─────────────────────────────────────────────────────────────────────
    Map<Integer, Integer> frequency = new HashMap<>();
    for (int num : nums) {
      Integer currentFrequency = frequency.getOrDefault(num, 0) + 1;
      frequency.put(num, currentFrequency);
    }

    // ─────────────────────────────────────────────────────────────────────
    // PASS 2: Check for any frequency > 1
    // ─────────────────────────────────────────────────────────────────────
    for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
      if (entry.getValue() > 1) {
        return true;
      }
    }
    return false;
  }
}
