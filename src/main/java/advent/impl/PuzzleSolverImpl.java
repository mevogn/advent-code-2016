package advent.impl;


import advent.*;

public class PuzzleSolverImpl implements PuzzleSolver {

    public static void main(String[] args) {
        PuzzleInput puzzleInput = new PuzzleInputImpl();

//        TaxiCab taxiCab = new TaxiCabImpl(puzzleInput);
//        int finalDistance = taxiCab.getShortestPath(true, null);
//        int firstDistance = taxiCab.getShortestPath(false, null);
//        System.out.println("day 1 part A: " + finalDistance);
//        System.out.println("day 1 part B: " + firstDistance);
//
//        Security security = new SecurityImpl(puzzleInput);
//        String securityCode = security.getBathroomCode(null);
//        String securityCode2 = security.getRealBathroomCode(null);
//        System.out.println("day 2 part A: " + securityCode);
//        System.out.println("day 2 part B: " + securityCode2);
//
//        Triangles triangles = new TrianglesImpl(puzzleInput);
//        int numPossibleTriangles = triangles.numberPossibleTriangles(null, true);
//        int numPossibleVertTriangles = triangles.numberPossibleTriangles(null, false);
//        System.out.println("day 3 part A: " + numPossibleTriangles);
//        System.out.println("day 3 part B: " + numPossibleVertTriangles);
//
//        RoomNameDecoder roomNameDecoder = new RoomNameDecoderImpl(puzzleInput);
//        int sectorIDSum = roomNameDecoder.getSumSectorIDs(null, true, "northpoleobject");
//        int sectorID = roomNameDecoder.getSumSectorIDs(null, false, "northpoleobject");
//        System.out.println("day 4 part A: " + sectorIDSum);
//        System.out.println("day 4 part B: " + sectorID);

//        DoorIDDecoder doorIDDecoder = new DoorIDDecoderImpl();
//        String password = doorIDDecoder.getDoorPassword("ugkcyxxp", true);
//        String secondPassword = doorIDDecoder.getDoorPassword("ugkcyxxp", false);
//        System.out.println("day 5 part A: " + password);
//        System.out.println("day 5 part B: " + secondPassword);

//        MessageCorrector messageCorrector = new MessageCorrectorImpl(puzzleInput);
//        String message = messageCorrector.getMostCommon(null, true);
//        String secondMessage = messageCorrector.getMostCommon(null, false);
//        System.out.println("day 6 part A: " + message);
//        System.out.println("day 6 part B: " + secondMessage);

        InternetProtocolChecker internetProtocolChecker = new InternetProtocolCheckerImpl(puzzleInput);
        int numMatches = internetProtocolChecker.getNumberIPs(null);
        int secondPart = internetProtocolChecker.getNumberAbas(null);
        System.out.println("day 7 part A: " + numMatches);
        System.out.println("day 7 part B: " + secondPart);
    }

}
