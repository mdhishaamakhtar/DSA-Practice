package dev.hishaam.dsa.slidingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Contains Duplicate II (LeetCode #219)
 *
 * <p>Description: Given an integer array nums and an integer k, return true if there are two
 * distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * <p>Example: Input: nums = [1,2,3,1], k = 3 Output: true (indices 0 and 3 have same value, and
 * |0-3| = 3 <= k)
 *
 * <p>Input: nums = [1,0,1,1], k = 1 Output: true (indices 2 and 3 have same value, and |2-3| = 1 <=
 * k)
 *
 * <p>═══════════════════════════════════════════════════════════════════════════
 *
 * <p>Approach: Sliding Window with HashSet (SIMILAR TO LONGEST SUBSTRING WITHOUT REPEATING
 * CHARACTERS)
 *
 * <p>═══════════════════════════════════════════════════════════════════════════
 *
 * <p>This problem is conceptually similar to "Longest Substring Without Repeating Characters" where
 * we maintain a window and shrink from the left when a constraint is violated:
 *
 * <p>- In Longest Substring: Shrink when duplicate CHARACTER is found (to remove the previous
 * occurrence) - In Contains Duplicate II: Shrink when window SIZE exceeds k (to maintain the
 * distance constraint abs(i - j) <= k)
 *
 * <p>KEY INSIGHT: We maintain a sliding window of size at most k. If any duplicate is found within
 * this window, we immediately know the distance constraint is satisfied (since window size <= k
 * means indices are at most k apart).
 *
 * <p>─────────────────────────────────────────────────────────────────────────
 *
 * <p>DRY RUN: nums = [1, 2, 3, 1], k = 3
 *
 * <p>─────────────────────────────────────────────────────────────────────────
 *
 * <p>R=0: window={}, nums[R]=1 not in window → add 1 → window={1}, size=1<=3 ✓ L=0, R=0
 *
 * <p>R=1: window={1}, nums[R]=2 not in window → add 2 → window={1,2}, size=2<=3 ✓ L=0, R=1
 *
 * <p>R=2: window={1,2}, nums[R]=3 not in window → add 3 → window={1,2,3}, size=3<=3 ✓ L=0, R=2
 *
 * <p>R=3: window={1,2,3}, nums[R]=1 IS IN WINDOW → FOUND DUPLICATE! Distance = |3 - 0| = 3 <= k ✓ →
 * return true
 *
 * <p>Time Complexity: O(n) - each element is added and removed from HashSet at most once (amortized
 * O(1) per operation)
 *
 * <p>Space Complexity: O(min(n, k)) - window HashSet stores at most k+1 elements
 */
public class ContainsDuplicateII {

  public boolean containsNearbyDuplicate(int[] nums, int k) {
    boolean contains = false;
    int L = 0;
    // KEY: HashSet for O(1) duplicate detection within current window
    Set<Integer> window = new HashSet<>();
    for (int R = 0; R < nums.length; R++) {
      // TRICK: If duplicate found in window, distance is guaranteed <= k
      // (because window size is maintained <= k)
      if (window.contains(nums[R])) {
        return true;
      }
      window.add(nums[R]);
      // ─────────────────────────────────────────────────────────────────────
      // SHRINK-FROM-LEFT PATTERN (similar to Longest Substring Without Repeating)
      // ─────────────────────────────────────────────────────────────────────
      // IMPORTANT: When window size exceeds k, shrink from left to maintain
      // the constraint. R - L >= k means we have k+1 elements, so remove leftmost.
      // This ensures any duplicate found is within k distance.
      if (R - L >= k) {
        window.remove(nums[L]);
        L++;
      }
    }
    return contains;
  }
}
