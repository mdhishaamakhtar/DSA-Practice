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
  public int[] productExceptSelf(int[] nums) {
    int[] ans = new int[nums.length];

    // PASS 1: Left to Right - build PREFIX products
    // ans[i] = product of all elements BEFORE index i
    int prod = 1;
    // KEY: ans[0] = 1 because there are no elements before index 0
    ans[0] = prod;
    for (int i = 1; i < nums.length; i++) {
      // REMEMBER: Multiply by nums[i-1], not nums[i] (we want product BEFORE current)
      prod = prod * nums[i - 1];
      ans[i] = prod;
    }

    // PASS 2: Right to Left - multiply by SUFFIX products
    // Now ans[i] will have prefix * suffix = product of all except self
    prod = 1;
    // KEY: Start from second-to-last element (last element already has correct
    // prefix)
    for (int i = nums.length - 2; i >= 0; i--) {
      // REMEMBER: Multiply by nums[i+1], not nums[i] (we want product AFTER current)
      prod = prod * nums[i + 1];
      // TRICK: Multiply existing prefix product by new suffix product
      ans[i] = ans[i] * prod;
    }

    return ans;
  }
}
