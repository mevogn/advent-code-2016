package advent.impl;

import advent.BalanceBots;
import advent.PuzzleInput;

import java.util.List;

public class BalanceBotsImpl implements BalanceBots {
    private PuzzleInput puzzleInput;

    public BalanceBotsImpl(PuzzleInput puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    public int botNumber(List<String> instructions, boolean isPartA) {
        if (instructions == null) {
            instructions = puzzleInput.getMultiLineFileAsStrings("day10input");
        }
        int botNumber = -1;
        int higherNumToCompare = 61;
        int lowerNumToCompare = 17;
        int[][] bots = new int[2][250];
        for (String instruction : instructions) {
            if (instruction.startsWith("value")) {
                String[] delimited = instruction.split("\\s");
                int botReceiving = Integer.valueOf(delimited[delimited.length-1]);
                if (bots[0][botReceiving] == 0){
                    bots[0][botReceiving] = Integer.valueOf(delimited[1]);
                } else if (bots[1][botReceiving] == 0) {
                    bots[1][botReceiving] = Integer.valueOf(delimited[1]);
                }
            }
        }
        String[] pendingInstructions = new String[250];
        int[] outputs = new int[3];
        for (String instruction : instructions) {
            if (instruction.startsWith("bot")) {
                int temp = attemptExchange(bots, instruction, higherNumToCompare, lowerNumToCompare, pendingInstructions, outputs);
                if (temp != -1 && botNumber == -1) {
                    botNumber = temp;
                }
            }
        }

        if (isPartA) {
            return botNumber;
        } else {
            return outputs[0] * outputs[1] * outputs[2];
        }
    }

    private int attemptExchange(int[][] bots, String instruction, int value1ToCompare, int value2ToCompare, String[] pending, int[] outputs) {
        int botNumber = -1;
        String[] delimited = instruction.split("\\s");
        int botGiving = Integer.valueOf(delimited[1]);
        int highValue = Math.max(bots[0][botGiving], bots[1][botGiving]);
        int lowValue = Math.min(bots[0][botGiving], bots[1][botGiving]);
        if (lowValue != 0 && highValue != 0) {
            if (highValue == value1ToCompare && lowValue == value2ToCompare) {
                botNumber = botGiving;
            }
            int botGettingLow = Integer.valueOf(delimited[6]);
            int botGettingHigh = Integer.valueOf(delimited[delimited.length - 1]);
            if (delimited[5].contains("bot")) {
                if (bots[0][botGettingLow] == 0) {
                    bots[0][botGettingLow] = lowValue;
                } else if (bots[1][botGettingLow] == 0) {
                    bots[1][botGettingLow] = lowValue;
                }
                if (pending[botGettingLow] != null) {
                    int temp = attemptExchange(bots, pending[botGettingLow], value1ToCompare, value2ToCompare, pending, outputs);
                    if (temp != -1 && botNumber == -1) {
                        botNumber = temp;
                    }
                }
            } else if (delimited[5].contains("output")) {
                if (botGettingLow < outputs.length) {
                    outputs[botGettingLow] = lowValue;
                }
            }
            if (delimited[delimited.length - 2].contains("bot")) {
                if (bots[0][botGettingHigh] == 0) {
                    bots[0][botGettingHigh] = highValue;
                } else if (bots[1][botGettingHigh] == 0) {
                    bots[1][botGettingHigh] = highValue;
                }
                if (pending[botGettingHigh] != null) {
                    int temp = attemptExchange(bots, pending[botGettingHigh], value1ToCompare, value2ToCompare, pending, outputs);
                    if (temp != -1 && botNumber == -1) {
                        botNumber = temp;
                    }
                }
            } else if (delimited[delimited.length - 2].contains("output")) {
                if (botGettingHigh < outputs.length) {
                    outputs[botGettingHigh] = highValue;
                }
            }
            bots[0][botGiving] = 0;
            bots[1][botGiving] = 0;
        } else {
            pending[botGiving] = instruction;
        }
        return botNumber;
    }
}
