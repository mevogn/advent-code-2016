package advent.impl;


import advent.PuzzleInput;
import advent.Security;

import java.util.List;

public class SecurityImpl implements Security {
    PuzzleInput puzzleInput;

    public SecurityImpl(PuzzleInput puzzleInput){
        this.puzzleInput = puzzleInput;
    }

    public String getBathroomCode(List<String> instructions) {
        if (instructions == null) {
            instructions = puzzleInput.getMultiLineFileAsStrings("day2input");
        }
        String code = "";
        int startingPoint = 5;
        for (String instruction : instructions) {
            for (int i = 0; i < instruction.length(); ++i) {
                char currentDirection = instruction.charAt(i);
                switch (currentDirection) {
                    case 'U':
                        if (startingPoint > 3) {
                            startingPoint = startingPoint - 3;
                        }
                        break;
                    case 'D':
                        if (startingPoint < 7) {
                            startingPoint = startingPoint + 3;
                        }
                        break;
                    case 'L':
                        if (startingPoint != 1 && startingPoint != 4 && startingPoint != 7) {
                            startingPoint--;
                        }
                        break;
                    case 'R':
                        if ((startingPoint % 3) != 0) {
                            startingPoint ++;
                        }
                        break;
                }
            }
            code = code + startingPoint;
        }
        return code;
    }

    public String getRealBathroomCode(List<String> instructions) {
        if (instructions == null) {
            instructions = puzzleInput.getMultiLineFileAsStrings("day2input");
        }
        String code = "";
        int startingPoint = 5;
        for (String instruction : instructions) {
            for (int i = 0; i < instruction.length(); ++i) {
                char currentDirection = instruction.charAt(i);
                switch (currentDirection) {
                    case 'U':
                        if ((startingPoint % 10) == 3) {
                            startingPoint = startingPoint - 2;
                        } else if ( (startingPoint > 5 && (startingPoint % 2) == 0 )|| startingPoint == 7 || startingPoint == 11) {
                            startingPoint = startingPoint - 4;
                        }
                        break;
                    case 'D':
                        if ((startingPoint % 10) == 1) {
                            startingPoint = startingPoint + 2;
                        } else if ( (startingPoint < 10 && (startingPoint % 2) == 0 )|| startingPoint == 7 || startingPoint == 3) {
                            startingPoint = startingPoint + 4;
                        }
                        break;
                    case 'L':
                        if ((startingPoint % 3) == 0 || startingPoint == 7 || startingPoint == 11 || ((startingPoint % 2) == 0 && startingPoint > 3)) {
                            startingPoint--;
                        }
                        break;
                    case 'R':
                        if ((startingPoint % 12) != 1 && startingPoint != 4 && startingPoint != 9 && startingPoint != 12) {
                            startingPoint++;
                    }
                        break;
                }
            }
            code = code + convertToLetter(startingPoint);
        }
        return code;
    }

    private String convertToLetter(int number) {
            switch (number) {
                case 10:
                    return "A";
                case 11:
                    return "B";
                case 12:
                    return "C";
                case 13:
                    return "D";
                default:
                    return String.valueOf(number);
            }
    }
}
