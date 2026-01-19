package dev.hishaam.dsa.arraysAndHashing;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Problem: Longest Consecutive Sequence (LeetCode #128)
 *
 * <p>Description: Given an unsorted array of integers, find the length of the longest consecutive
 * elements sequence. Must run in O(n) time.
 *
 * <p>Example: Input: [100, 4, 200, 1, 3, 2] Output: 4 (The sequence is [1, 2, 3, 4])
 *
 * <p>Approach: HashSet with Smart Sequence Start Detection
 *
 * <ol>
 *   <li>Put all numbers in a HashSet for O(1) lookup
 *   <li>Only start counting from "sequence beginnings" (numbers with no predecessor)
 *   <li>This ensures each number is visited at most twice: once in loop, once in while
 * </ol>
 *
 * <p>Time Complexity: O(n) - each number visited at most twice Space Complexity: O(n) - HashSet
 * storage
 *
 * <p>NOTE: Sorting would give O(n log n), this HashSet approach achieves true O(n)
 */
class LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    int ans = 0;
    // KEY: Convert to Set for O(1) lookup and automatic duplicate removal
    Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
    for (Integer num : numsSet) {
      // TRICK: Only count sequences starting from their FIRST element
      // If (num - 1) exists, this number is part of a sequence we'll count elsewhere
      if (!numsSet.contains(num - 1)) {
        // This is a sequence START - no predecessor exists
        int localConsecutive = 1;
        int counter = 1;
        // Count consecutive numbers forward from this start point
        while (numsSet.contains(num + counter)) {
          localConsecutive++;
          counter++;
        }
        ans = Math.max(ans, localConsecutive);
      }
    }
    return ans;
  }
}
