package advent.impl;

import advent.PuzzleInput;
import advent.TaxiCab;

import java.util.HashSet;
import java.util.Set;

public class TaxiCabImpl implements TaxiCab {

    private static final String RIGHT_TURN = "R";

    private PuzzleInput puzzleInput;

    public TaxiCabImpl(PuzzleInput puzzleInput){
        this.puzzleInput = puzzleInput;
    }

    public int getShortestPath(boolean partA, String[] directions) {
        if (directions == null) {
            directions = puzzleInput.getSingleLineFileAsStrings("day1input", ", ");
        }

        int dx = 0, dy = 0, currentDirection = 0;
        int firstDistance = -1;
        Set<String> coordinates = new HashSet<String>();
        coordinates.add("0,0");
        for (String direction : directions) {
            int rotate =(direction.contains(RIGHT_TURN)) ? 1 : -1;
            currentDirection = (currentDirection + rotate) % 4;
            currentDirection = (currentDirection < 0) ? currentDirection + 4 : currentDirection;
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
