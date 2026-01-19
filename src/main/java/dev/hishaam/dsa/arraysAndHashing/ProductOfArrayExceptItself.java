package dev.hishaam.dsa.arraysAndHashing;

/**
 * Problem: Product of Array Except Self (LeetCode #238)
 *
 * <p>Description: Given an integer array nums, return an array answer such that answer[i] is equal
 * to the product of all elements except nums[i]. Must solve WITHOUT using division and in O(n)
 * time.
 *
 * <p>Example: Input: [1,2,3,4] Output: [24,12,8,6]
 *
 * <p>Approach: Prefix and Suffix Products (Two-Pass) For each position i, the answer is: (product
 * of all elements before i) * (product of all elements after i) Pass 1 (left to right): Calculate
 * prefix products Pass 2 (right to left): Multiply by suffix products
 *
 * <p>Time Complexity: O(n) - two linear passes Space Complexity: O(1) extra - output array doesn't
 * count as extra space
 */
class ProductOfArrayExceptItself {

  // ═══════════════════════════════════════════════════════════════════════════
  // Prefix & Suffix Products (Two-Pass) - O(n) time, O(1) extra space
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // EXAMPLE WALKTHROUGH with [1,2,3,4]:
  // Pass 1 (Prefix): ans = [1, 1, 2, 6] (product of all BEFORE each index)
  // Pass 2 (Suffix):
  // i=2: prod=4, ans[2]=2*4=8
  // i=1: prod=4*3=12, ans[1]=1*12=12
  // i=0: prod=12*2=24, ans[0]=1*24=24
  // Final: [24, 12, 8, 6]
  public int[] productExceptSelf(int[] nums) {
    int[] ans = new int[nums.length];

    // ─────────────────────────────────────────────────────────────────────
    // PASS 1: Left to Right - build PREFIX products
    // ans[i] = product of all elements BEFORE index i
    // ─────────────────────────────────────────────────────────────────────
    int prod = 1;
    ans[0] = prod; // No elements before index 0
    for (int i = 1; i < nums.length; i++) {
      prod = prod * nums[i - 1]; // Multiply by element BEFORE current
      ans[i] = prod;
    }

    // ─────────────────────────────────────────────────────────────────────
    // PASS 2: Right to Left - multiply by SUFFIX products
    // Now ans[i] = prefix * suffix = product of all except self
    // ─────────────────────────────────────────────────────────────────────
    prod = 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      prod = prod * nums[i + 1]; // Multiply by element AFTER current
      ans[i] = ans[i] * prod; // Combine prefix with suffix
    }

    return ans;
  }
}
