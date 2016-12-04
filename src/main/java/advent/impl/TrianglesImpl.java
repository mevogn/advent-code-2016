package advent.impl;

import advent.PuzzleInput;
import advent.Triangles;

import java.util.List;

public class TrianglesImpl implements Triangles {

    PuzzleInput puzzleInput;

    public TrianglesImpl(PuzzleInput puzzleInput){
        this.puzzleInput = puzzleInput;
    }
    public int numberPossibleTriangles(List<String> triangleSides, boolean partA) {
        if (triangleSides == null) {
            triangleSides = puzzleInput.getMultiLineFileAsStrings("day3input");
        }

        int triangleCount = 0;
        if (partA) {
            for (String points : triangleSides) {
                String[] pointArray = points.trim().split("\\s+");
                int[] intPointAray = {Integer.parseInt(pointArray[0]), Integer.parseInt(pointArray[1]), Integer.parseInt(pointArray[2])};
                if (isValid(intPointAray)) {
                    triangleCount++;
                }
            }
        } else {
            if (triangleSides.size() % 3 == 0) {
                for (int i = 0; i < triangleSides.size() - 2; i = i + 3) {
                    String[] stringsPoints1 = triangleSides.get(i).trim().split("\\s+");
                    String[] stringsPoints2 = triangleSides.get(i+1).trim().split("\\s+");
                    String[] stringsPoints3 = triangleSides.get(i+2).trim().split("\\s+");
                    for (int j = 0; j<3; ++j) {
                        int[] intPoints = {Integer.parseInt(stringsPoints1[j]), Integer.parseInt(stringsPoints2[j]), Integer.parseInt(stringsPoints3[j])};
                        if (isValid(intPoints)) {
                            triangleCount++;
                        }
                    }
                }
            } else {
                System.out.println("Day 3 part 2 error: invalid number of rows");
            }
        }
        return triangleCount;
    }

    private int indexOfLongestSide(int[] pointArray) {
        int longestSide = 0;
        for (int i=0; i<pointArray.length-1; ++i) {
            if (pointArray[longestSide] < pointArray[i+1]){
                longestSide = i + 1;
            }
        }
        return longestSide;
    }

    private boolean isValid(int[] intPointAray) {
        boolean isValid =  false;
        int longestSideIndex = indexOfLongestSide(intPointAray);
        if (longestSideIndex == 0) {
            int otherSidesSum = intPointAray[1] + intPointAray[2];
            if (otherSidesSum > intPointAray[0]){
                isValid = true;
            }
        } else if (longestSideIndex == 1) {
            int otherSidesSum = intPointAray[0] + intPointAray[2];
            if (otherSidesSum > intPointAray[1]){
                isValid = true;
            }
        } else {
            int otherSidesSum = intPointAray[0] + intPointAray[1];
            if (otherSidesSum > intPointAray[2]){
                isValid = true;
            }
        }
        return isValid;
    }
}
