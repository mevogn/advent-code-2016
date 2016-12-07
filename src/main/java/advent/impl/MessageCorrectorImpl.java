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
        int[] currentMinimums = new int[] {
                singalSize, singalSize, singalSize, singalSize, singalSize, singalSize, singalSize, singalSize
        };
        HashMap<String, int[]> letterCounter = initializeHashMap();
        for (int i =0; i<messageSignals.size(); ++i) {
            for (int j = 0; j<lengthOfStrings; ++ j) {
                String currentLetter = String.valueOf(messageSignals.get(i).charAt(j));
                int[] valueWrapper = letterCounter.get(currentLetter);
                valueWrapper[j]++;
                if ( currentMaximums[1][j] < valueWrapper[j]) {
                    currentMaximums[1][j] = valueWrapper[j];
                    currentMaximums[0][j] = (int) messageSignals.get(i).charAt(j);
                }
                if (currentMinimums[j] >= valueWrapper[j]){
                    currentMinimums[j] = valueWrapper[j];
                }
            }
        }

        if (isPartA) {
            return getFinalMessage(currentMaximums, lengthOfStrings);
        } else {
            return getLeastCommon(letterCounter, lengthOfStrings, currentMinimums); //todo
        }
    }

    public String getLeastCommon(HashMap<String, int[]> letterCounter, int lengthOfStrings, int[] minimums){
        String message = "";
        for (int i = 0; i<lengthOfStrings; ++i){
           for (int j = 97; j<122; ++j) {
               int[] counts = letterCounter.get(String.valueOf((char)j));
               if (counts[i] == minimums[i]){
                   message = message + ((char) j);
               }
           }
       }
       return message;
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
