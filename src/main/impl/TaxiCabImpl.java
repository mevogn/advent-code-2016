package main.impl;

import main.PuzzleInput;
import main.TaxiCab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaxiCabImpl implements TaxiCab{

    public static final String RIGHT_TURN = "R";
    public static final String LEFT_TURN = "L";

    PuzzleInput puzzleInput;

    public TaxiCabImpl(PuzzleInput puzzleInput){
        this.puzzleInput = puzzleInput;
    }

    public int getShortestPath(boolean partA) {
        String[] directions = puzzleInput.getFileAsString("testing", ", ");
        int dx = 0, dy = 0, currentDirection = 0;
        int firstDistance = -1;
        List<String> coordinates = new ArrayList<>();
        coordinates.add("0,0");
        for (String direction : directions) {
            int rotate =(direction.contains(RIGHT_TURN)) ? 1 : -1;
            currentDirection = (currentDirection + rotate) % 4;
            for (int i=0; i<Integer.parseInt(direction.substring(1)); ++i) {
                switch (currentDirection) {
                    case 0 :
                        dy++;
                        break;
                    case 1:
                        dx++;
                        break;
                    case 2:
                        dy--;
                        break;
                    case 3:
                        dx--;
                        break;
                }
                String thisCoordinate = dx + "," + dy;
                if (coordinates.contains(thisCoordinate) && firstDistance == -1){
                    firstDistance = Math.abs(dx) + Math.abs(dy);
                    coordinates.add(thisCoordinate);
                } else {
                    coordinates.add(thisCoordinate);
                }
            }
        }

        if (partA) {
            return Math.abs(dx) + Math.abs(dy);
        } else {
            return firstDistance;
        }
    }
}
