package main.impl;

import main.PuzzleInput;
import main.PuzzleSolver;
import main.TaxiCab;

public class PuzzleSolverImpl implements PuzzleSolver{

    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInputImpl();
        TaxiCab taxiCab = new TaxiCabImpl(puzzleInput);
        int finalDistance = taxiCab.getShortestPath(true);
        int firstDistance = taxiCab.getShortestPath(false);
        System.out.println(finalDistance);
        System.out.println(firstDistance);
    }

}
