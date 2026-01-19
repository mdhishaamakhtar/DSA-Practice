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

  public String encode(List<String> strs) {
    StringBuilder ans = new StringBuilder();
    for (String str : strs) {
      // TRICK: Use length prefix + separator so we know exactly where string ends
      // Format: "5#Hello" means read next 5 chars after '#'
      char separator = '#';
      ans.append(str.length()).append(separator).append(str);
    }
    return ans.toString();
  }

  public List<String> decode(String str) {
    List<String> ans = new ArrayList<>();
    int i = 0;
    while (i < str.length()) {
      // IMPORTANT: Build the length by reading all consecutive digits
      StringBuilder length = new StringBuilder();
      // KEY: Start with "0" to handle edge case of empty string (length = 0)
      length.append("0");
      int j = i;
      // REMEMBER: Length can be multiple digits (e.g., "100#" for a 100-char string)
      while (j < str.length() && Character.isDigit(str.charAt(j))) {
        length.append(str.charAt(j));
        j++;
      }
      int wordLength = Integer.parseInt(length.toString());
      // KEY: j is now at '#', so actual string starts at j + 1
      int k = j + 1;
      String word = str.substring(k, k + wordLength);
      ans.add(word);
      // IMPORTANT: Move i to start of next encoded segment
      i = k + wordLength;
    }
    return ans;
  }
}
