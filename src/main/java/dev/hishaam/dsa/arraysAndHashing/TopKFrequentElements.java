package dev.hishaam.dsa.arraysAndHashing;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Problem: Top K Frequent Elements (LeetCode #347)
 *
 * <p>Description: Given an integer array nums and an integer k, return the k most frequent
 * elements. The answer can be returned in any order.
 *
 * <p>Example: Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2]
 *
 * <p>Approach: HashMap + Sorting by Frequency 1. Build frequency map 2. Sort entries by frequency
 * in descending order 3. Take first k elements
 *
 * <p>Alternative: Bucket Sort for O(n) time - create buckets where index = frequency
 *
 * <p>Time Complexity: O(n log n) due to sorting Space Complexity: O(n) for frequency map
 */
public class TopKFrequentElements {
  public int[] topKFrequent(int[] nums, int k) {
    // Step 1: Build frequency map
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums) {
      freq.put(num, freq.getOrDefault(num, 0) + 1);
    }
    // Step 2: Sort by frequency (descending) using Stream API
    // KEY: Use LinkedHashMap to preserve insertion order after sorting
    LinkedHashMap<Integer, Integer> freqSorted =
        freq.entrySet().stream()
            // IMPORTANT: reverseOrder() for descending - highest frequency first
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey, // Key mapper
                    Map.Entry::getValue, // Value mapper
                    (a, b) -> a, // Merge function (not used, no duplicates)
                    LinkedHashMap::new)); // TRICK: LinkedHashMap maintains insertion order!

    // Step 3: Extract first k keys from the sorted map
    int counter = 0;
    int[] ans = new int[k];
    for (Map.Entry<Integer, Integer> entry : freqSorted.entrySet()) {
      if (counter < k) {
        // NOTE: We want the KEY (the number), not the VALUE (frequency)
        ans[counter] = entry.getKey();
        counter++;
      }
    }
    return ans;
  }
}
