package dev.hishaam.dsa.arraysAndHashing;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Encode and Decode Strings (LeetCode #271)
 *
 * <p>Description: Design an algorithm to encode a list of strings to a single string. The encoded
 * string is then decoded back to the original list of strings.
 *
 * <p>Example: Input: ["Hello","World"] Encoded: "5#Hello5#World" Decoded: ["Hello","World"]
 *
 * <p>Approach: Length-Prefixed Encoding Format each string as: [length]#[string] This handles ALL
 * edge cases including strings containing '#' or digits, because we know exactly how many
 * characters to read after the separator.
 *
 * <p>Time Complexity: O(n) where n is total characters across all strings Space Complexity: O(n)
 * for the encoded/decoded result
 */
class EncodeDecodeString {

  // ═══════════════════════════════════════════════════════════════════════════
  // Length-Prefixed Encoding - O(n) time, O(n) space
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // FORMAT: [length]#[string] for each string
  // EXAMPLE: ["Hello","World"] → "5#Hello5#World"
  // This handles ALL edge cases including strings with '#' or digits!
  public String encode(List<String> strs) {
    StringBuilder ans = new StringBuilder();
    for (String str : strs) {
      char separator = '#';
      ans.append(str.length()).append(separator).append(str);
    }
    return ans.toString();
  }

  // ═══════════════════════════════════════════════════════════════════════════
  // Decode by parsing length prefix
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // EXAMPLE WALKTHROUGH with "5#Hello5#World":
  // i=0: read "5", j=1, skip '#', read chars 2-6 = "Hello", i=7
  // i=7: read "5", j=8, skip '#', read chars 9-13 = "World", i=14
  // Final: ["Hello", "World"]
  public List<String> decode(String str) {
    List<String> ans = new ArrayList<>();
    int i = 0;

    while (i < str.length()) {
      // ─────────────────────────────────────────────────────────────────────
      // PARSE LENGTH: Read all consecutive digits
      // ─────────────────────────────────────────────────────────────────────
      StringBuilder length = new StringBuilder();
      length.append("0"); // Handle edge case of empty string (length = 0)
      int j = i;
      while (j < str.length() && Character.isDigit(str.charAt(j))) {
        length.append(str.charAt(j));
        j++;
      }
      int wordLength = Integer.parseInt(length.toString());

      // ─────────────────────────────────────────────────────────────────────
      // EXTRACT STRING: j is at '#', read next wordLength chars
      // ─────────────────────────────────────────────────────────────────────
      int k = j + 1;
      String word = str.substring(k, k + wordLength);
      ans.add(word);
      i = k + wordLength; // Move to next encoded segment
    }
    return ans;
  }
}
