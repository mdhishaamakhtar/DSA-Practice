package dev.hishaam.dsa.arraysAndHashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Valid Anagram (LeetCode #242)
 *
 * <p>Description: Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise. An anagram uses all the original letters exactly once, just rearranged.
 *
 * <p>Example: Input: s = "anagram", t = "nagaram" Output: true
 *
 * <p>Approach: Character Frequency Map Comparison Build frequency maps for both strings and compare
 * them. If the maps are equal, the strings are anagrams.
 *
 * <p>Alternative: Sort both strings and compare (O(n log n)) Alternative: Use int[26] array for
 * lowercase letters only (more efficient)
 *
 * <p>Time Complexity: O(n) where n is string length Space Complexity: O(k) where k is the character
 * set size (at most 26 for lowercase)
 */
class ValidAnagram {
  public boolean isAnagram(String s, String t) {
    // KEY: Early exit - anagrams must have same length
    if (s.length() != t.length()) {
      return false;
    }
    // Build frequency map for string s
    Map<Character, Integer> smap = new HashMap<>();
    Map<Character, Integer> tmap = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      Character currentLetter = s.charAt(i);
      Integer currentFrequency = smap.getOrDefault(currentLetter, 0);
      smap.put(currentLetter, currentFrequency + 1);
    }
    // Build frequency map for string t
    for (int i = 0; i < t.length(); i++) {
      Character currentLetter = t.charAt(i);
      Integer currentFrequency = tmap.getOrDefault(currentLetter, 0);
      tmap.put(currentLetter, currentFrequency + 1);
    }
    // TRICK: Map.equals() compares both keys and values - perfect for anagram check
    return smap.equals(tmap);
  }
}
