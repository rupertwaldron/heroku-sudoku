package com.ruppyrup.herokusudoku.model;

public class Main {

    public static void main(String[] args) {

        int[] testArray =  {0, 0, 0, 0, 0, 4, 0, 0, 0,
        7, 0, 0, 0, 0, 3, 0, 0, 6,
        9, 0, 2, 0, 1, 0, 8, 0, 0,
        3, 0, 0, 0, 0, 0, 0, 1, 0,
        0, 0, 5, 0, 0, 0, 2, 0, 0,
        0, 9, 0, 0, 0, 7, 5, 0, 0,
        0, 5, 0, 8, 0, 0, 0, 0, 0,
        4, 0, 0, 0, 0, 0, 0, 0, 5,
        0, 1, 0, 2, 0, 0, 0, 9, 3};


        Sudoku sudoku = new Sudoku();
        SudokuService sudokuService = new SudokuService(sudoku);
        sudokuService.processSudoku(testArray);
    }
}
