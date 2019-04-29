package com.ruppyrup.herokusudoku.controller;


import com.ruppyrup.herokusudoku.model.SudokuService;
import com.ruppyrup.herokusudoku.util.AttributeNames;
import com.ruppyrup.herokusudoku.util.GameMappings;
import com.ruppyrup.herokusudoku.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
public class GameController {
    // == fields ==

    private final SudokuService sudokuService;

    private int[] displaySudoku =  {0, 0, 0, 0, 0, 4, 0, 0, 0,
            7, 0, 0, 0, 0, 3, 0, 0, 6,
            9, 0, 2, 0, 1, 0, 8, 0, 0,
            3, 0, 0, 0, 0, 0, 0, 1, 0,
            0, 0, 5, 0, 0, 0, 2, 0, 0,
            0, 9, 0, 0, 0, 7, 5, 0, 0,
            0, 5, 0, 8, 0, 0, 0, 0, 0,
            4, 0, 0, 0, 0, 0, 0, 0, 5,
            0, 1, 0, 2, 0, 0, 0, 9, 3};
    // == constructor ==

    @Autowired
    public GameController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    // == request methods ==

    @GetMapping(GameMappings.RESTART)
    public String restart() {
        log.warn("Restart Called");
        displaySudoku = new int[AttributeNames.NONET_SIZE * AttributeNames.NONET_SIZE];
        return GameMappings.REDIRECT_SUDOKU;
    }

    @GetMapping(GameMappings.SUDOKU)
    public String sudoku(Model model) {
        log.warn("Sudoku Started");

        model.addAttribute("RR", "Rupert Was Here");
        model.addAttribute("cellValues",displaySudoku);
        return ViewNames.SUDOKU;
    }

    @PostMapping(GameMappings.SUDOKU)
    public String processCels(@RequestParam("cell") int[] cells) {
        for (int i : cells) {
            log.warn("cell = {}", i);
        }
        displaySudoku = sudokuService.processSudoku(cells);

        // send back to play
        return GameMappings.REDIRECT_SUDOKU;
    }


}
