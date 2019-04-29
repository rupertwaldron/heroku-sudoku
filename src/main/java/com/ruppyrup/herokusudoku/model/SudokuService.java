package com.ruppyrup.herokusudoku.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruppyrup.herokusudoku.util.AttributeNames;

@Slf4j
@Service
public class SudokuService {

    private int[] unformatedTable;
    private int[] resultTable = new int[AttributeNames.NONET_SIZE * AttributeNames.NONET_SIZE];
    private Sudoku sudoku;

    private int[][] sudokuTable = new int[AttributeNames.NONET_SIZE][AttributeNames.NONET_SIZE];
//                { {0, 0, 0, 0, 0, 4, 0, 0, 0},
//        {7, 0, 0, 0, 0, 3, 0, 0, 6},
//        {9, 0, 2, 0, 1, 0, 8, 0, 0},
//        {3, 0, 0, 0, 0, 0, 0, 1, 0},
//        {0, 0, 5, 0, 0, 0, 2, 0, 0},
//        {0, 9, 0, 0, 0, 7, 5, 0, 0},
//        {0, 5, 0, 8, 0, 0, 0, 0, 0},
//        {4, 0, 0, 0, 0, 0, 0, 0, 5},
//        {0, 1, 0, 2, 0, 0, 0, 9, 3}};

    @Autowired
    public SudokuService(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public int[] processSudoku(int[] unformatedTable) {
        this.unformatedTable = unformatedTable;
        setSudokuTable();
        sudoku.solve(sudokuTable);
        setResultTable();
        return resultTable;
    }

    private void setSudokuTable() {
        for (int i = 0; i < unformatedTable.length; i++) {
            sudokuTable[i / AttributeNames.NONET_SIZE][i % AttributeNames.NONET_SIZE] = unformatedTable[i];
        }
    }

    private void setResultTable() {
        for (int i = 0; i < AttributeNames.NONET_SIZE; i++) {
            for (int j = 0; j < AttributeNames.NONET_SIZE; j++) {
                System.out.println("index = " + (i * AttributeNames.NONET_SIZE + j));
                resultTable[i * AttributeNames.NONET_SIZE + j] = sudokuTable[i][j];
            }
        }
    }


}