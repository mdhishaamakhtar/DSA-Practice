package dev.hishaam.dsa.slidingWindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Longest Repeating Character Replacement (LeetCode 424)
 *
 * <p>Description: Given a string s and an integer k, you can choose any character of the string and
 * change it to any other uppercase English character. You can perform this operation at most k
 * times. Return the length of the longest substring containing the same letter you can get after
 * performing the above operations.
 *
 * <p>Example: Input: s = "AABABBA", k = 1 Output: 4 Explanation: Replace the one 'A' in the middle
 * with 'B' and form "AABBBBA". The substring "BBBB" has the longest length 4.
 *
 * <p>Approach: Sliding Window We maintain a window [L, R] and track the frequency of each
 * character. The number of replacements needed in the current window is (WindowSize -
 * MaxFrequency). If replacements > k, we must shrink the window from the left.
 *
 * <p>Time Complexity: O(N * 26) = O(N), where N is string length and 26 is character set size.
 * Space Complexity: O(26) = O(1) for the frequency map.
 */
public class LongestRepeatingCharacterReplacement {

  /**
   * <b>EXAMPLE WALKTHROUGH</b> with {@code s="AABABBA", k=1}: <br>
   * <br>
   * <b>1. Expansion Phase (R=0 to R=3)</b><br>
   * {@code R=0 (A)}: map={A:1}, max=1, win=1. Replacements(0) &le; k(1). OK. {@code ans=1}<br>
   * {@code R=1 (A)}: map={A:2}, max=2, win=2. Replacements(0) &le; k(1). OK. {@code ans=2}<br>
   * {@code R=2 (B)}: map={A:2, B:1}, max=2, win=3. Replacements(1) &le; k(1). OK. {@code ans=3}
   * <br>
   * {@code R=3 (A)}: map={A:3, B:1}, max=3, win=4. Replacements(1) &le; k(1). OK. {@code ans=4}
   * <br>
   * <br>
   * <b>2. Shrink Phase (R=4)</b><br>
   * {@code R=4 (B)}: map={A:3, B:2}, max=3, win=5. Replacements(2) &gt; k(1). <b>INVALID</b>.<br>
   * <i>Metric:</i> {@code windowSize - maxFrequency = 5 - 3 = 2 > 1}<br>
   * <br>
   * &rarr; <b>SHRINK LOOP:</b><br>
   * &nbsp;&nbsp; Remove {@code s[L=0] = 'A'}: map={A:2, B:2}, max=2, win=4. Replace(2) &gt; k.
   * <b>STILL INVALID</b>.<br>
   * &nbsp;&nbsp; Remove {@code s[L=1] = 'A'}: map={A:1, B:2}, max=2, win=3. Replace(1) &le; k.
   * <b>OK</b>.<br>
   * &nbsp;&nbsp; New {@code L=2}. <br>
   * <br>
   * <b>3. Expansion Again</b><br>
   * {@code R=5 (B)}: map={A:1, B:3}, max=3, win=4. Replacements(1) &le; k. OK. {@code ans=4} <br>
   * <br>
   * <b>4. Second Shrink (R=6)</b><br>
   * {@code R=6 (A)}: map={A:2, B:3}, max=3, win=5. Replacements(2) &gt; k. <b>INVALID</b>.<br>
   * <br>
   * &rarr; <b>SHRINK LOOP:</b><br>
   * &nbsp;&nbsp; Remove {@code s[L=2] = 'B'}: map={A:2, B:2}, max=2, win=4. Replace(2) &gt; k.
   * <b>INVALID</b>.<br>
   * &nbsp;&nbsp; Remove {@code s[L=3] = 'A'}: map={A:1, B:2}, max=2, win=3. Replace(1) &le; k.
   * <b>OK</b>.<br>
   * &nbsp;&nbsp; New {@code L=4}. <br>
   * <br>
   * <b>Final Answer:</b> 4 (Longest valid window found was length 4)
   */
  public int characterReplacement(String s, int k) {
    // ═══════════════════════════════════════════════════════════════════════════
    // SLIDING WINDOW WITH FREQUENCY MAP
    // ═══════════════════════════════════════════════════════════════════════════

    // TRICK: windowSize - maxFrequency = characters that MUST be replaced
    Map<Character, Integer> frequency = new HashMap<>();
    char[] c = s.toCharArray();
    int L = 0;
    int ans = 0;

    for (int R = 0; R < c.length; R++) {
      // PHASE 1: Expand window and update frequency
      frequency.put(c[R], frequency.getOrDefault(c[R], 0) + 1);

      // KEY: Collect highest frequency in current window to determine validity
      int maxValue = Collections.max(frequency.values());
      int windowSize = R - L + 1;

      // PHASE 2: Check window validity
      if (windowSize - maxValue <= k) {
        // Window is valid (enough replacements allowed)
        ans = Math.max(ans, windowSize);
      } else {
        // ─────────────────────────────────────────────────────────────────────────
        // PHASE 3: Shrink window until it becomes valid again
        // ─────────────────────────────────────────────────────────────────────────
        while (windowSize - maxValue > k) {
          frequency.put(c[L], frequency.getOrDefault(c[L], 0) - 1);
          L++;
          windowSize = R - L + 1;
          // IMPORTANT: Recalculate maxValue after removing an element from the left
          maxValue = Collections.max(frequency.values());
        }
      }
    }
    return ans;
  }
}
