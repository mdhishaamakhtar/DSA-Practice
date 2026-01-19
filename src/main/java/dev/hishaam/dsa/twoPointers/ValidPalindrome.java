package dev.hishaam.dsa.twoPointers;

/**
 * Problem: Valid Palindrome (LeetCode #125)
 *
 * <p>Description: Given a string s, return true if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 *
 * <p>Example: Input: s = "A man, a plan, a canal: Panama" Output: true (after filtering:
 * "amanaplanacanalpanama" is a palindrome)
 *
 * <p>Approach: Preprocessing + Two Pointers First, filter the string to keep only alphanumeric
 * characters (lowercase). Then use two pointers from both ends to verify palindrome property.
 *
 * <p>Time Complexity: O(n) - One pass to preprocess, one pass to compare Space Complexity: O(n) -
 * Storing the processed string
 */
public class ValidPalindrome {

  // ═══════════════════════════════════════════════════════════════════════════
  // Preprocessing + Two Pointers - O(n) time, O(n) space
  // ═══════════════════════════════════════════════════════════════════════════

  // ─────────────────────────────────────────────────────────────────────────
  // STEP 1: Filter to alphanumeric only, convert to lowercase
  // ─────────────────────────────────────────────────────────────────────────
  public String processString(String input) {
    StringBuilder processed = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      if (Character.isLetterOrDigit(input.charAt(i))) {
        processed.append(Character.toLowerCase(input.charAt(i)));
      }
    }
    return processed.toString();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // STEP 2: Two pointers from opposite ends, converge toward middle
  // ─────────────────────────────────────────────────────────────────────────
  public boolean isPalindrome(String s) {
    String processed = processString(s);
    int i = 0, j = processed.length() - 1;
    while (i <= j) {
      if (processed.charAt(i) != processed.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }
}
