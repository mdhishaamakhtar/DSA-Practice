package dev.hishaam.dsa.slidingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem: Longest Substring Without Repeating Characters (LeetCode #3)
 *
 * <p>Description: Given a string s, find the length of the longest substring without repeating
 * characters.
 *
 * <p>Example: Input: s = "abcabcbb" Output: 3 (The answer is "abc")
 *
 * <p>Two approaches provided below:
 *
 * <p>APPROACH 1 - Shrink Window for Incoming (Optimal): When a duplicate is found at R, shrink from
 * L until the duplicate is removed. Each character is added/removed at most once, so O(2n) = O(n).
 *
 * <p>APPROACH 2 - Reset Set on Conflict (Suboptimal): When a duplicate is found, reset the entire
 * window starting from L+1. Worst case O(n²) for strings like "abcabc".
 *
 * <p>Time Complexity: O(n) for Approach 1, O(n²) worst case for Approach 2 Space Complexity:
 * O(min(n, alphabet_size)) for the HashSet
 */
public class LongestSubstringWithoutRepeatingCharacters {

  // ═══════════════════════════════════════════════════════════════════════════
  // APPROACH 1: Shrink Window Until Duplicate Removed (OPTIMAL - O(n))
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // CORE IDEA: Maintain a sliding window [L, R] that ALWAYS contains unique
  // chars.
  // When we want to add c[R] but it's already in the window, shrink from L until
  // it's removed.
  //
  // WHY O(n) AND NOT O(n²)?
  // - The inner while loop looks scary (loop inside loop), but think about it:
  // - Each character can be ADDED to the set at most once (when R reaches it)
  // - Each character can be REMOVED from the set at most once (when L passes it)
  // - Total operations: at most n adds + n removes = O(2n) = O(n)
  //
  // EXAMPLE WALKTHROUGH with "abcabc":
  // R=0: window={a}, L=0, maxLen=1
  // R=1: window={a,b}, L=0, maxLen=2
  // R=2: window={a,b,c}, L=0, maxLen=3
  // R=3: c[R]='a' is in set! Shrink: remove 'a', L=1. Now window={b,c}. Add 'a'.
  // maxLen=3
  // R=4: c[R]='b' is in set! Shrink: remove 'b', L=2. Now window={c,a}. Add 'b'.
  // maxLen=3
  // R=5: c[R]='c' is in set! Shrink: remove 'c', L=3. Now window={a,b}. Add 'c'.
  // maxLen=3
  // Final answer: 3
  public int lengthOfLongestSubstringShrinkSetForIncoming(String s) {
    char[] c = s.toCharArray();
    if (c.length == 0) {
      return 0;
    }
    int L = 0, maxLength = 0;

    // Set tracks ALL characters currently in our window [L, R-1]
    // (R will be added at the end of each iteration)
    Set<Character> windowChars = new HashSet<>();

    for (int R = 0; R < c.length; R++) {
      // ─────────────────────────────────────────────────────────────────────
      // SHRINK PHASE: If c[R] is already in window, we CANNOT add it.
      // We must shrink from the LEFT until c[R] is no longer in the set.
      //
      // IMPORTANT: We don't know WHERE the duplicate is located!
      // It could be at L, or somewhere in the middle of the window.
      // So we keep removing from L until c[R] is gone.
      //
      // Example: window = {a,b,c,d} and c[R] = 'b'
      // - Remove 'a' (L=0), set = {b,c,d}, still contains 'b'
      // - Remove 'b' (L=1), set = {c,d}, now 'b' is gone! Stop.
      // - L is now at index 2
      // ─────────────────────────────────────────────────────────────────────
      while (windowChars.contains(c[R])) {
        windowChars.remove(c[L]);
        L++;
      }

      // ─────────────────────────────────────────────────────────────────────
      // RECORD PHASE: Window [L, R] is now VALID (no duplicates).
      // Calculate length BEFORE adding c[R] to include it in the count.
      // Formula: R - L + 1 (inclusive range)
      // ─────────────────────────────────────────────────────────────────────
      maxLength = Math.max(maxLength, (R - L + 1));

      // EXPAND PHASE: Add c[R] to the window for the next iteration
      windowChars.add(c[R]);
    }
    return maxLength;
  }

  // ═══════════════════════════════════════════════════════════════════════════
  // APPROACH 2: Reset Entire Window on Conflict (SUBOPTIMAL - O(n²) worst case)
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // CORE IDEA: When we find a duplicate, abandon the current window entirely.
  // Start fresh from L+1.
  //
  // WHY IS THIS O(n²)?
  // - Consider "abcdefga" - when we hit the second 'a', we reset to 'b'
  // - Then we have to re-scan "bcdefga" which we already processed!
  // - Worst case "abcabc": we process the same characters multiple times
  //
  // WHY INCLUDE THIS? It's a common first attempt. Understanding why it's
  // suboptimal helps appreciate the shrink approach.
  public int lengthOfLongestSubstringResetSet(String s) {
    char[] c = s.toCharArray();
    if (c.length == 0) {
      return 0;
    }
    int L = 0, R = 1, maxLength = 0;
    Set<Character> localLookup = new HashSet<>();
    localLookup.add(c[L]);

    while (L < c.length && R < c.length) {
      // NOTE: Using (R - L) not (R - L + 1) because c[R] hasn't been validated yet
      // Current valid window is [L, R-1]
      maxLength = Math.max(maxLength, (R - L));

      if (localLookup.contains(c[R])) {
        // ─────────────────────────────────────────────────────────────────────
        // SUBOPTIMAL RESET: Instead of smartly shrinking to remove just the
        // duplicate, we throw away everything and start from L+1.
        //
        // This re-processes characters we already scanned!
        // Example: "abcabc" at R=3 (second 'a'):
        // - We had window {a,b,c}, now we reset to just {b}
        // - R resets to 2, we re-scan 'b','c','a','b','c' again
        // ─────────────────────────────────────────────────────────────────────
        L++;
        R = L + 1;
        localLookup.clear();
        localLookup.add(c[L]);

      } else {
        // No conflict - expand window
        localLookup.add(c[R]);
        R++;
      }
    }

    // ─────────────────────────────────────────────────────────────────────
    // IMPORTANT: Final boundary check!
    // The loop exits when R >= c.length, but the window [L, R-1] might still
    // be valid and could be the longest. We need one more max check.
    // Example: "abc" -> loop exits with L=0, R=3, window "abc" is valid
    // ─────────────────────────────────────────────────────────────────────
    maxLength = Math.max(maxLength, (R - L));
    return maxLength;
  }
}
