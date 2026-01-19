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

  // ═══════════════════════════════════════════════════════════════════════════
  // Three HashSets per Row/Column/Box - O(1) time (fixed 9x9), O(1) space
  // ═══════════════════════════════════════════════════════════════════════════
  //
  // BOX KEY FORMULA EXPLAINED:
  // For cell (i,j), box index = (i/3)*3 + (j/3)
  // Example: (4,7) → (4/3)*3 + (7/3) = 1*3 + 2 = 5 (middle-right box)
  //
  // Box layout:
  // 0 | 1 | 2
  // 3 | 4 | 5
  // 6 | 7 | 8
  public boolean isValidSudoku(char[][] board) {
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
        if (!Character.isDigit(board[i][j])) {
          continue;
        }

        // ─────────────────────────────────────────────────────────────────────
        // BOX INDEX: (i/3)*3 + (j/3) maps 9x9 grid to 9 boxes (0-8)
        // ─────────────────────────────────────────────────────────────────────
        int boxKey = (i / 3) * 3 + (j / 3);

        // ─────────────────────────────────────────────────────────────────────
        // TRICK: Set.add() returns FALSE if element already exists
        // ─────────────────────────────────────────────────────────────────────
        boolean isRowPresent = row.get(i).add(board[i][j]);
        boolean isColumnPresent = column.get(j).add(board[i][j]);
        boolean isBoxPresent = box.get(boxKey).add(board[i][j]);

        if (!isRowPresent || !isColumnPresent || !isBoxPresent) {
          return false;
        }
      }
    }

    return true;
  }
}
