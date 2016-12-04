package advent.impl;


import advent.PuzzleInput;
import advent.PuzzleSolver;
import advent.Security;
import advent.TaxiCab;

public class PuzzleSolverImpl implements PuzzleSolver {

    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInputImpl();

        TaxiCab taxiCab = new TaxiCabImpl(puzzleInput);
        int finalDistance = taxiCab.getShortestPath(true, null);
        int firstDistance = taxiCab.getShortestPath(false, null);
        System.out.println("day 1 part A: " + finalDistance);
        System.out.println("day 1 part B: " + firstDistance);

        Security security = new SecurityImpl(puzzleInput);
        String securityCode = security.getBathroomCode(null);
        String securityCode2 = security.getRealBathroomCode(null);
        System.out.println("day 2 part A: " + securityCode);
        System.out.println("day 2 part B: " + securityCode2);
    }

}
