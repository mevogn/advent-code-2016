package advent.impl;

import advent.MessageCorrector;
import advent.PuzzleInput;

import java.util.HashMap;
import java.util.List;

public class MessageCorrectorImpl implements MessageCorrector{
    private PuzzleInput puzzleInput;

    MessageCorrectorImpl(PuzzleInput puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    public String getMostCommon(List<String> messageSignals, boolean isPartA) {
        if (messageSignals == null) {
            messageSignals = puzzleInput.getMultiLineFileAsStrings("day6input");
        }
        int singalSize = messageSignals.size();
        int lengthOfStrings = messageSignals.get(0).length();
        int[][] currentMaximums = new int[2][lengthOfStrings];
        HashMap<String, int[]> letterCounter = initializeHashMap();
        for (int i =0; i<singalSize; ++i) {
            for (int j = 0; j<lengthOfStrings; ++ j) {
                String currentLetter = String.valueOf(messageSignals.get(i).charAt(j));
                int[] valueWrapper = letterCounter.get(currentLetter);
                valueWrapper[j]++;
                if ( currentMaximums[1][j] < valueWrapper[j]) {
                    currentMaximums[1][j] = valueWrapper[j];
                    currentMaximums[0][j] = (int) messageSignals.get(i).charAt(j);
                }
            }
        }

        if (isPartA) {
            return getFinalMessage(currentMaximums, lengthOfStrings);
        } else {
            int[] currentMinimums = getMinimums(letterCounter, lengthOfStrings, singalSize);
            return getLeastCommon(letterCounter, lengthOfStrings, currentMinimums);
        }
    }

    private String getLeastCommon(HashMap<String, int[]> letterCounter, int lengthOfStrings, int[] minimums){
        String message = "";
        for (int i = 0; i<lengthOfStrings; ++i){
           for (int j = 97; j<=122; ++j) {
               int[] counts = letterCounter.get(String.valueOf((char)j));
               if (counts[i] == minimums[i]){
                   message = message + ((char) j);
                   break;
               }
           }
       }
       return message;
    }

    private int[] getMinimums(HashMap<String, int[]> letterCounter, int lengthOfStrings, int numRows) {
        int[] currentMinimums = new int[] {
                numRows, numRows, numRows, numRows, numRows, numRows, numRows, numRows
        };
        for (int i = 0; i<lengthOfStrings; ++i){
            for (int j = 97; j<=122; ++j) {
                int[] counts = letterCounter.get(String.valueOf((char)j));
                if (counts[i] <= currentMinimums[i]){
                    currentMinimums[i] = counts[i];
                }
            }
        }

        return currentMinimums;
    }

    private HashMap<String, int[]> initializeHashMap(){
        HashMap<String, int[]> letterCounter = new HashMap<String, int[]>();
        for (int i = 97; i<=122; ++i){
            letterCounter.put(String.valueOf((char) i), new int[]{0,0,0,0,0,0,0,0});
        }
        return letterCounter;
    }

    private String getFinalMessage(int[][] currentMaximums, int lengthOfStrings) {
        String finalMessage = "";
        for (int i = 0; i < lengthOfStrings; ++i) {
            finalMessage = finalMessage + ((char) currentMaximums[0][i]);
        }
        return finalMessage;
    }
}
