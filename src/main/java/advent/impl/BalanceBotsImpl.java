package advent.impl;

import advent.BalanceBots;
import advent.PuzzleInput;

import java.util.*;

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

    public int getSolution(int[] A) {
       int numberMoments = 0;
       Map<Integer, LightBulb> lightBulbMap = new HashMap<Integer, LightBulb>();

       List<Integer> list = new ArrayList<Integer>();
       for (int i : A) {
           list.add(i);
       }

       //loop to track moments
       for (int i= 0; i < A.length; ++i) {
           //check in this moment how many lights shine
           int numberShiningLights = 0;
           int numberOnLights = 0;
           LightBulb lightBulb = new LightBulb();
           lightBulb.setIsOn(true);
           lightBulbMap.put(A[i], lightBulb);
           for (int j =0; j < A.length -1; ++j) {
               //is J on at this point?
               //is J shining at this point?
               if (lightBulbMap.get(A[j]) == null) {
                   LightBulb newLightBulb = new LightBulb();
                   if (j <= i) {
                       newLightBulb.setIsOn(true);
                       if (isShining(list, A[j], i)) {
                           newLightBulb.setIsShining(true);
                           numberShiningLights++;
                       }
                       numberOnLights++;
                   }
               } else {
                   LightBulb currentLightBulb = lightBulbMap.get(A[j]);
                   if (currentLightBulb.isOn && currentLightBulb.isShining) {
                       numberShiningLights++;
                       numberOnLights++;
                   } else if (currentLightBulb.isOn) {
                       boolean isNowShining = isShining(list, A[j], i);
                       if (isNowShining) {
                           currentLightBulb.setIsShining(true);
                           numberShiningLights++;
                       }
                       numberOnLights++;
                   }
               }
           }
           if (numberShiningLights == numberOnLights) {
               numberMoments++;
           }
        }

        return numberMoments;
    }

    //check array if all previous lightbulbs before this index
    private boolean isShining(List<Integer> lights, int lightBulbNumber, int currentIndex) {
        boolean isLightAbleToShine = false;

        //all these lights must be accounted for
        for (int i = 1; i<=lightBulbNumber; ++i) {
            if (lights.indexOf((i)) > currentIndex) {
                isLightAbleToShine = false;
                break;
            }
            isLightAbleToShine = true;
        }
        return isLightAbleToShine;
    }

    private class LightBulb {
        boolean isOn = false;
        boolean isShining = false;

        LightBulb(){

        }

        private void setIsOn(boolean isOn) {
            this.isOn = isOn;
        }

        private void setIsShining(boolean isShining) {
            this.isShining = isShining;
        }
    }

    public int getOtherSolution(int[] A, int K, int L) {
        if (K + L > A.length) {
            return -1;
        }
        int maximumApples = 0;
        int biggestForK = 0;
        int biggestForL = 0;

        int indexOfK = 0;
        int indexOfL = 0;
        //find sequence for K
        for (int i=0; i<=A.length - K; ++i) {
            int kSum = 0;
            for (int j= 0; j< K; j++) {
                kSum = kSum + A[i+j];
            }
            if (kSum > biggestForK) {
                biggestForK = kSum;
                indexOfK = i;
            }
        }

        for (int i = 0; i<= A.length - L; ++i) {
            int lSum = 0;
            for (int j=0; j< L; ++j) {
                lSum = lSum + A[i+j];
            }
            if (lSum > biggestForL) {
                biggestForL = lSum;
                indexOfL = i;
            }
        }

        if (indexOfK + K - 1 < indexOfL) {
            maximumApples = biggestForK + biggestForL;
        } else if (indexOfL + L -1 < indexOfK) {
            maximumApples = biggestForK + biggestForL;
        }

        return maximumApples;
    }

    public int getOtherSolution2(int[] A, int K, int L) {
        if (K + L > A.length) {
            return -1;
        }
        int maximumApples = 0;
        AppleSequence kSequence = getBiggestSum(A, K);
        AppleSequence lSequence = getBiggestSum(A, L);

        if (kSequence.getStartingIndex() + K - 1 < lSequence.getStartingIndex()) {
            maximumApples = kSequence.getNumberApples() + lSequence.getNumberApples();
        } else if (lSequence.getStartingIndex() + L -1 < kSequence.getStartingIndex()) {
            maximumApples = kSequence.getNumberApples() + lSequence.getNumberApples();
        } else {
            //overlap exists
        }

        return maximumApples;
    }

    private AppleSequence getBiggestSum(int[] A, int numberConsecutive) {
        AppleSequence appleSequence = new AppleSequence();
        int biggest = 0;
        int index = 0;
        for (int i = 0; i<= A.length - numberConsecutive; ++i) {
            int lSum = 0;
            for (int j=0; j< numberConsecutive; ++j) {
                lSum = lSum + A[i+j];
            }
            if (lSum > biggest) {
                biggest = lSum;
                index = i;
            }
        }

        appleSequence.setNumberApples(biggest);
        appleSequence.setStartingIndex(index);
        return appleSequence;
    }

    private class AppleSequence{
        int numberApples;
        int startingIndex;

        AppleSequence() {
            numberApples = 0;
            startingIndex = 0;
        }

        private void setNumberApples(int num) {
            numberApples = num;
        }

        private void setStartingIndex(int index) {
            startingIndex = index;
        }

        private int getNumberApples() {
            return numberApples;
        }

        private int getStartingIndex() {
            return startingIndex;
        }
    }
}
