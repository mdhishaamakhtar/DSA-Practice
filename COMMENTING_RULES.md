# Commenting Rules for LeetCode Java Classes

This document defines the standard for adding comments to all LeetCode problem solutions in this repository.

---

## 1. Class-Level Comments (Mandatory)

Every class must have a **Javadoc block** at the top (above the class declaration) that includes:

### 1.1 Problem Description

- **Problem Name**: The LeetCode problem title
- **Problem Statement**: A concise explanation of what the problem asks
- **Input/Output**: Describe the inputs and expected outputs
- **Constraints**: Any important constraints mentioned in the problem

### 1.2 Solution Approach

- **Algorithm**: The name of the algorithm/technique used (e.g., Two Pointers, HashMap, Sliding Window)
- **Intuition**: Why this approach works for the problem
- **Time Complexity**: Big-O time complexity with brief explanation
- **Space Complexity**: Big-O space complexity with brief explanation

### Example Format

```java
/**
 * Problem: [Problem Name]
 * 
 * Description:
 * [Concise problem statement]
 * 
 * Example:
 * Input: [sample input]
 * Output: [expected output]
 * 
 * Approach: [Algorithm Name]
 * [Brief explanation of the solution strategy]
 * 
 * Time Complexity: O(...)
 * Space Complexity: O(...)
 */
```

---

## 2. Inline Comments for "Easy-to-Forget" Parts (Critical)

Add comments to parts of the code that are **commonly forgotten or tricky**. These include:

### 2.1 Edge Cases

- Early returns for boundary conditions
- Length/null checks
- Special handling for empty inputs

### 2.2 Key Algorithm Steps

- The "trick" that makes the solution work
- Non-obvious initialization values (e.g., why start at 1 instead of 0)
- Loop boundary conditions that are easy to get wrong

### 2.3 Data Structure Choices

- Why a specific data structure is used (HashMap vs HashSet, ArrayList vs Array)
- The purpose of composite keys or complex map structures

### 2.4 Mathematical Formulas

- Index calculations (especially for 2D arrays, modular arithmetic)
- Off-by-one situations
- Prefix/suffix product accumulations

### 2.5 Two-Pointer / Sliding Window Mechanics

- Pointer movement conditions
- When to increment/decrement which pointer

### 2.6 Sequence Detection Logic

- Start of sequence detection (no predecessor check)
- Why we only count from sequence starts

---

## 3. Comment Style Guidelines

### 3.1 Use Single-Line Comments for Inline Notes

```java
// IMPORTANT: [explanation]
```

### 3.2 Keep Comments Concise

- One line, max two lines for inline comments
- Focus on the "why" not the "what"

### 3.3 Use Action Words

- "IMPORTANT:", "KEY:", "TRICK:", "NOTE:", "REMEMBER:"
- These highlight the critical nature of the comment

### 3.4 Don't Over-Comment

- Only comment non-obvious parts
- Skip obvious statements like `// increment i`

### 3.5 Visual Separators for Complex Algorithms

For problems with multiple approaches or distinct phases, use visual separators:

```java
// ═══════════════════════════════════════════════════════════════════════════
// APPROACH 1: [Name] (OPTIMAL - O(n))
// ═══════════════════════════════════════════════════════════════════════════

// ─────────────────────────────────────────────────────────────────────────
// PHASE NAME: Brief explanation of what this section does
// ─────────────────────────────────────────────────────────────────────────
```

- Use `═══` for major section headers (approaches, algorithms)
- Use `───` for subsections within an approach (phases, key decision points)

### 3.6 Dry Run Examples for Tricky Logic

For non-obvious algorithms, include a brief step-by-step walkthrough:

```java
// EXAMPLE WALKTHROUGH with [input]:
// Step 1: [state after step 1]
// Step 2: [state after step 2]
// ...
// Final answer: [result]
```

- Keep dry runs to 4-6 key steps max
- Show the critical state changes (variable values, data structure contents)
- Pick an input that demonstrates the tricky case

---

## 4. Things People Commonly Forget (Focus Areas)

Based on common mistakes, always comment these patterns:

| Pattern | What to Comment |
| --------- | --------------------: |
| **HashMap.getOrDefault** | Why default value is chosen |
| **Sorting with custom comparator** | The sorting order and why |
| **Index calculations in 2D** | The formula breakdown (e.g., `(i/3)*3 + (j/3)`) |
| **Prefix/Suffix arrays** | Direction of traversal and accumulation |
| **Sequence start detection** | The "no predecessor" check |
| **Encoding/Decoding formats** | The format structure (e.g., `length#string`) |
| **Two-pointer convergence** | Why pointers move the way they do |
| **Greedy elimination in two-pointers** | Why one option is provably suboptimal (e.g., moving shorter line because it's the limiting factor) |
| **Using Map as a Key** | Why Map equality works for grouping |
| **Stream operations** | What each chain operation does |
| **Set.add() return value** | Using boolean return for duplicate detection |
| **1-indexed output requirements** | When problem expects 1-indexed but we use 0-indexed internally |
| **Duplicate skipping in k-sum problems** | Why we skip duplicates at each position (outer loop AND after finding match) |
| **Process-after-move pattern** | Why pointer moves BEFORE calculation (prevents counting boundaries as holding values) |
| **Limiting factor reasoning** | Why processing one side is safe without knowing the other side's true max (proof that current side is the bottleneck) |
| **Boundary pre-processing** | Why we skip ascending/descending edges (optimization: no water/value can be captured there) |
| **Water/container problems** | The core formula: `min(maxLeft, maxRight) - height[i]` and why min is used (water overflows at shorter boundary) |
| **Running min/max as implicit DP** | When tracking min/max so far replaces needing prefix arrays (optimal substructure in disguise) |
| **Order of operations in update loops** | When calculate-before-update vs update-before-calculate matters (e.g., can't buy and sell same day) |
| **Sliding window jump to better opportunity** | Why L jumps to R when a strictly better option is found (all future pairings prefer new position) |
| **Sliding window shrink vs reset** | Why shrink-from-left is O(n) but full reset is O(n²) - each element enters/exits at most once |
| **Amortized O(n) in sliding window** | Why "while loop inside for loop" is still O(n) - each element added/removed at most once total |
| **Final boundary check after loop** | When loop may exit with valid window state that needs one more max/min comparison |

---

## 5. What NOT to Comment

- Obvious variable declarations
- Standard for-loop boilerplate
- Return statements (unless tricky)
- Import statements

---

## 6. Example Application

**Before:**

```java
int boxKey = (i / 3) * 3 + (j / 3);
```

**After:**

```java
// KEY: Maps 9x9 grid to 9 boxes (0-8). Integer division groups rows/cols into 3s
int boxKey = (i / 3) * 3 + (j / 3);
```

---

## Summary Checklist

- [ ] Class-level Javadoc with problem description and approach
- [ ] Inline comments on edge case checks
- [ ] Inline comments on key algorithm "tricks"
- [ ] Inline comments on complex index calculations
- [ ] Inline comments on data structure choice rationale
- [ ] Comments are concise and use action words
