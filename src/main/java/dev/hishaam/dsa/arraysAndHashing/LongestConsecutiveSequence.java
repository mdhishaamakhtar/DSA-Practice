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

  // ═══════════════════════════════════════════════════════════════════════════
  // HashSet with Sequence Start Detection - O(n) time, O(n) space
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // EXAMPLE WALKTHROUGH with [100, 4, 200, 1, 3, 2]:
  // Set = {100, 4, 200, 1, 3, 2}
  // num=100: 99 not in set → START! Count: 100 (len=1)
  // num=4: 3 IS in set → SKIP (not a start)
  // num=200: 199 not in set → START! Count: 200 (len=1)
  // num=1: 0 not in set → START! Count: 1,2,3,4 (len=4) ✓
  // num=3: 2 IS in set → SKIP
  // num=2: 1 IS in set → SKIP
  // Final: 4
  public int longestConsecutive(int[] nums) {
    int ans = 0;
    Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    for (Integer num : numsSet) {
      // ─────────────────────────────────────────────────────────────────────
      // SEQUENCE START CHECK: Only count from numbers with NO predecessor
      // WHY? Avoids recounting - sequences starting mid-way are handled by their
      // start
      // ─────────────────────────────────────────────────────────────────────
      if (!numsSet.contains(num - 1)) {
        int localConsecutive = 1;
        int counter = 1;
        // Count forward from this start point
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
