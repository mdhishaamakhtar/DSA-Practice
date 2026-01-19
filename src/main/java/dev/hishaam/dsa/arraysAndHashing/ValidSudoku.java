package dev.hishaam.dsa.arraysAndHashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem: Valid Sudoku (LeetCode #36)
 *
 * <p>Description: Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be
 * validated according to these rules: 1. Each row must contain digits 1-9 without repetition 2.
 * Each column must contain digits 1-9 without repetition 3. Each of the nine 3x3 sub-boxes must
 * contain digits 1-9 without repetition
 *
 * <p>Note: The board doesn't need to be solvable, just currently valid.
 *
 * <p>Example: A partially filled board with no duplicates in any row, column, or box -> true
 *
 * <p>Approach: Three HashSets per Row/Column/Box Use 9 sets each for rows, columns, and 3x3 boxes.
 * For each cell, check if the digit already exists in its row, column, or box.
 *
 * <p>Time Complexity: O(81) = O(1) - fixed 9x9 board size Space Complexity: O(81) = O(1) - fixed
 * number of sets and elements
 */
class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    // Create 9 sets each for rows, columns, and 3x3 boxes
    List<Set<Character>> row = new ArrayList<>();
    List<Set<Character>> column = new ArrayList<>();
    List<Set<Character>> box = new ArrayList<>();

    for (int i = 0; i < 9; i++) {
      row.add(new HashSet<>());
      column.add(new HashSet<>());
      box.add(new HashSet<>());
    }

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {

        // Skip empty cells (represented as '.')
        if (!Character.isDigit(board[i][j])) {
          continue;
        }
        // KEY FORMULA: Convert 2D position to box index (0-8)
        // (i/3) gives box row (0,1,2), (j/3) gives box col (0,1,2)
        // (i/3)*3 + (j/3) maps to: 0,1,2 | 3,4,5 | 6,7,8
        int boxKey = (i / 3) * 3 + (j / 3);

        // TRICK: Set.add() returns FALSE if element already exists!
        // This is a one-liner duplicate check - add and verify in one operation
        boolean isRowPresent = row.get(i).add(board[i][j]);
        boolean isColumnPresent = column.get(j).add(board[i][j]);
        boolean isBoxPresent = box.get(boxKey).add(board[i][j]);

        // If any add() returned false, we found a duplicate
        if (!isRowPresent || !isColumnPresent || !isBoxPresent) {
          return false;
        }
      }
    }

    return true;
  }
}
