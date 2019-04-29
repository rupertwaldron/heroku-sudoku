package com.ruppyrup.herokusudoku.model;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class Sudoku {
    private final int NONET_SIZE = 9;
    private final int MIN = 1;
    private int[][] sudoku;
    private int sudokuSize;

    public Sudoku() {
    }

    protected void solve(int[][] sudoku) {
        this.sudoku = sudoku;
        this.sudokuSize = sudoku.length;
        if (solveSudoku(0, 0)) {
            showResult();
        } else {
            System.out.println("No solution...");
        }
    }

    private boolean solveSudoku(int rowIndex, int colIndex) {
        //showResult();
        // if we have come to last cell on the board
        //System.out.println("x = " + rowIndex + " y = " + colIndex);
        if (rowIndex == sudokuSize && colIndex + 1 == sudokuSize) {
            return true;
        }

        if (rowIndex == sudokuSize) {
            //move to next column
            //reset rows to zero
            //System.out.println("Hop to next Column");
            rowIndex = 0;
            ++colIndex;
        }

        // if value is already in the matrix
        if (sudoku[rowIndex][colIndex] != 0) {
            return solveSudoku(rowIndex + 1, colIndex);
        }

        for (int value = MIN; value <= NONET_SIZE; value++) {
            if (isValid(rowIndex, colIndex, value)) {

                sudoku[rowIndex][colIndex] = value;

                if ( solveSudoku(rowIndex + 1, colIndex)) {
                    return true;
                }
            }
        }
        // Back Tracking
        sudoku[rowIndex][colIndex] = 0;

        return false;
    }

    private boolean isValid(int row, int col, int value) {

        //check rows and columns for the same value
        for (int i = 0; i < sudokuSize; i++) {
            if (value == sudoku[row][i]) {
                return false;
            }
            if (value == sudoku[i][col]) {
                return false;
            }
        }

        //check nonet values

        int positionX = (row / 3) * 3;
        int positionY = (col / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (value == sudoku[positionX + i][positionY + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showResult() {
        for (int i = 0; i < sudokuSize; ++i) {

            if(i % 3 == 0) System.out.println(" ");

            for (int j = 0; j < sudokuSize; ++j) {

                if (j % 3 == 0) System.out.print(" ");
                System.out.print(sudoku[i][j]+" ");
            }
            System.out.println(" ");
        }
    }

}
