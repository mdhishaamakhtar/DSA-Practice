package dev.hishaam.dsa.arraysAndHashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem: Group Anagrams (LeetCode #49)
 *
 * <p>Description: Given an array of strings, group the anagrams together. An anagram is a word
 * formed by rearranging the letters of another word.
 *
 * <p>Example: Input: ["eat","tea","tan","ate","nat","bat"] Output:
 * [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * <p>Approach: Character Frequency Map as Key All anagrams have the same character frequency
 * distribution. Use the frequency map itself as a key to group anagrams together.
 *
 * <p>Alternative: Sort each string and use sorted string as key (simpler but O(k log k) per string)
 *
 * <p>Time Complexity: O(n * k) where n = number of strings, k = max string length Space Complexity:
 * O(n * k) for storing all strings in groups
 */
class GroupAnagrams {

  // ═══════════════════════════════════════════════════════════════════════════
  // Character Frequency Map as Key - O(n * k) time, O(n * k) space
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // EXAMPLE: ["eat","tea","tan","ate","nat","bat"]
  // "eat" → {e:1,a:1,t:1} → bucket1
  // "tea" → {t:1,e:1,a:1} → same bucket1 (maps are equal!)
  // "tan" → {t:1,a:1,n:1} → bucket2
  // Result: [[eat,tea,ate], [tan,nat], [bat]]
  public List<List<String>> groupAnagrams(String[] strs) {
    // ─────────────────────────────────────────────────────────────────────
    // TRICK: Use Map<Character,Integer> as key! Java uses .equals() for comparison
    // ─────────────────────────────────────────────────────────────────────
    Map<Map<Character, Integer>, List<String>> bigMap = new HashMap<>();

    for (String str : strs) {
      // Build frequency map for current string
      Map<Character, Integer> freqMap = new HashMap<>();
      for (int j = 0; j < str.length(); j++) {
        Character currentLetter = str.charAt(j);
        Integer currentFrequency = freqMap.getOrDefault(currentLetter, 0);
        freqMap.put(currentLetter, currentFrequency + 1);
      }
      // Add to bucket (create new bucket if needed)
      List<String> currentBucket = bigMap.getOrDefault(freqMap, new ArrayList<>());
      currentBucket.add(str);
      bigMap.put(freqMap, currentBucket);
    }

    // Convert map values to list of lists
    List<List<String>> ans = new ArrayList<>();
    for (Map.Entry<Map<Character, Integer>, List<String>> entry : bigMap.entrySet()) {
      ans.add(entry.getValue());
    }
    return ans;
  }
}
