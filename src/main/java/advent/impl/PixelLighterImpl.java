package advent.impl;

import advent.PixelLighter;
import advent.PuzzleInput;

import java.util.List;

public class PixelLighterImpl implements PixelLighter {
    private PuzzleInput puzzleInput;

    PixelLighterImpl(PuzzleInput puzzleInput) {
        this.puzzleInput = puzzleInput;
    }
    public int numberPixelsLit(List<String> instructions) {
        if (instructions == null) {
            instructions = puzzleInput.getMultiLineFileAsStrings("day8input");
        }

        boolean[][] screen = new boolean[6][50];
        for (String instruction : instructions) {
            if (instruction.startsWith("rect")) {
                String[] delimited = instruction.split("\\s");
                String[] size = delimited[1].split("x");
                int x = Integer.valueOf(size[0]);
                int y = Integer.valueOf(size[1]);
                for (int i=0; i<x; ++i){
                    for (int j = 0; j<y; ++j) {
                        screen[j][i] = true;
                    }
                }
            } else if (instruction.startsWith("rotate")){
                String[] delimited = instruction.split("\\s");
                String[] location = delimited[2].split("=");
                if (location[0].equals("x")) {
                    rotateColumn(screen, Integer.valueOf(location[1]), Integer.valueOf(delimited[delimited.length-1]));
                } else if (location[0].equals("y")) {
                    rotateRow(screen, Integer.valueOf(location[1]), Integer.valueOf(delimited[delimited.length-1]));
                }
            }
        }
        return numberLit(screen);
    }

    private int numberLit(boolean[][] screen) {
        int count = 0;
        for (int i =0; i<screen.length; ++i) {
            for (int j=0; j<screen[0].length; ++j) {
                if (screen[i][j]) {
                    System.out.print("x");
                    count++;
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        return count;
    }

    private void rotateRow(boolean[][] screen, int row, int amount) {
        int j;
        for (int i = 0; i<amount; ++i){
            boolean lastIndex = screen[row][screen[0].length-1];
            for (j = screen[0].length-1; j>0; --j){
                screen[row][j] = screen[row][j-1];
            }
            screen[row][0] =  lastIndex;
        }
    }

    private void rotateColumn(boolean[][] screen, int column, int amount) {
        int j;
        for (int i = 0; i<amount; ++i){
            boolean lastIndex = screen[screen.length-1][column];
            for (j = screen.length-1; j>0; --j){
                screen[j][column] = screen[j-1][column];
            }
            screen[0][column] =  lastIndex;
        }
    }
}
