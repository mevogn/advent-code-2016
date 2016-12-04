package advent.impl;

import advent.PuzzleInput;
import advent.RoomNameDecoder;

import java.util.List;

public class RoomNameDecoderImpl implements RoomNameDecoder {
    private PuzzleInput puzzleInput;

    public RoomNameDecoderImpl(PuzzleInput puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    public int getSumSectorIDs(List<String> roomNames, boolean partA, String roomToMatch) {
        int sectorIDSum = 0;
        int sectorIDOfMatchedRoom =0;
        if (roomNames == null) {
            roomNames = puzzleInput.getMultiLineFileAsStrings("day4input");
        }
        int index = 0;
        for (String roomName : roomNames) {
            String[] delimitedName = roomName.split("-");
            String[] sectorAndChecksum = delimitedName[delimitedName.length - 1].split("\\[");
            String fullName = getAsOneString(delimitedName);
            String mostCommonChars = "";
            for (int i = 0; i < 5; ++i) {
                String commonChar = getMostCommonChar(fullName);
                mostCommonChars = mostCommonChars + commonChar;
                fullName = fullName.replace(commonChar, "");
            }
            if (checkSumMatchesCommon(sectorAndChecksum[1], mostCommonChars)) {
                sectorIDSum = sectorIDSum + Integer.parseInt(sectorAndChecksum[0]);
                String decryptedRoomName = decrpytedRoomName(getAsOneString(delimitedName), Integer.parseInt(sectorAndChecksum[0]));
                if (decryptedRoomName.contains(roomToMatch)){
                    sectorIDOfMatchedRoom = Integer.parseInt(sectorAndChecksum[0]);
                }
                roomNames.set(index, decryptedRoomName);
            } else {
                roomNames.set(index, null);
            }
            index++;
        }

        if (partA) {
            return sectorIDSum;
        } else {
            return sectorIDOfMatchedRoom;
        }
    }

    private String getAsOneString(String[] delimitedName) {
        String name = "";
        for (String partialName : delimitedName){
            if (!partialName.contains("[")) {
                name = name + partialName;
            }
        }
        return name;
    }

    private String getMostCommonChar(String fullName) {
        int i,j;
        String mostCommonChar = "";
        String charactersCounted = "";
        int largestCount = 0;
        for (i=0; i<fullName.length(); ++i) {
            String characterToCount = fullName.substring(i, i+1);
            if (!charactersCounted.contains(characterToCount)) {
                int countOfChar = 0;
                for (j = 0; j < fullName.length(); ++j) {
                    String letter = fullName.substring(j, j+1);
                    if (letter.equals(characterToCount)) {
                        countOfChar++;
                    }
                }
                charactersCounted = charactersCounted + characterToCount;
                if (countOfChar > largestCount) {
                    mostCommonChar = characterToCount;
                    largestCount = countOfChar;
                } else if (countOfChar == largestCount) {
                    if ((int)characterToCount.charAt(0) < (int) mostCommonChar.charAt(0)){
                        mostCommonChar = characterToCount;
                    }
                }
            }

        }
        return mostCommonChar;
    }

    private boolean checkSumMatchesCommon(String checkSum, String mostCommonChars) {
        boolean matches = true;
        for(int i = 0; i<checkSum.length()-1; ++i) {
            if (!mostCommonChars.contains(checkSum.substring(i, i+1))) {
                matches = false;
            }
        }
        return matches;
    }

    private String decrpytedRoomName(String roomName, int sectorID) {
        String realRoomName = "";
        for (int i = 0; i<roomName.length(); ++i) {
            char charToShift = roomName.charAt(i);
            int amountToShift = sectorID % 26;
            int newCharAscii = (int) charToShift + amountToShift;
            if (newCharAscii > 122) {
                newCharAscii = newCharAscii - 26;
            }
            realRoomName = realRoomName + Character.toString((char) newCharAscii);
        }
        return realRoomName;
    }
}

