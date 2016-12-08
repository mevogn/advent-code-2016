package advent.impl;

import advent.InternetProtocolChecker;
import advent.PuzzleInput;

import java.util.ArrayList;
import java.util.List;

public class InternetProtocolCheckerImpl implements InternetProtocolChecker{
    private PuzzleInput puzzleInput;

    InternetProtocolCheckerImpl(PuzzleInput puzzleInput) {
        this.puzzleInput = puzzleInput;
    }

    public int getNumberIPs(List<String> ipAddresses) {
        if (ipAddresses == null) {
            ipAddresses = puzzleInput.getMultiLineFileAsStrings("day7input");
        }
        int numSupports = 0;
        for (String ipAddress : ipAddresses) {
            String[] delimitedAddress = ipAddress.split("\\[|]");
            boolean valid = true;
            int i;
            for (i = 1; i<delimitedAddress.length; i=i+2) {
                valid = containsAbba(delimitedAddress[i]);
                if (valid){break;}
            }
            if (!valid) {
                boolean containsAbba = false;
                for (i = 0; i < delimitedAddress.length; i = i + 2) {
                    containsAbba = containsAbba(delimitedAddress[i]);
                    if (containsAbba) {break;}
                }
                if (containsAbba) {
                    numSupports++;
                }
            }
        }
        return numSupports;
    }

    public int getNumberAbas(List<String> ipAddresses) {
        if (ipAddresses == null) {
            ipAddresses = puzzleInput.getMultiLineFileAsStrings("day7input");
        }
        int numSupports = 0;
        for (String ipAddress : ipAddresses) {
            String[] delimitedAddress = ipAddress.split("\\[|]");
            int i;
            List<String> abasForAddress = new ArrayList<String>();
            for (i = 0; i < delimitedAddress.length; i = i + 2) {
                abasForAddress.addAll(getAbas(delimitedAddress[i]));
            }
            boolean abaMatches = false;
            for (i = 1; i<delimitedAddress.length; i=i+2) {
                for (int j = 0; j<abasForAddress.size(); ++j) {
                    if (delimitedAddress[i].contains(abasForAddress.get(j))) {
                        abaMatches = true;
                        break;
                    }
                }
            }
            if (abaMatches) {
                numSupports++;
            }
        }
        return numSupports;
    }

    private boolean containsAbba(String currentString){
        boolean containsAbba = false;
        for(int i=0; i<currentString.length()-3; ++i) {
            if (currentString.charAt(i) == currentString.charAt(i+3)) {
                if (currentString.charAt(i+1) == currentString.charAt(i+2)) {
                    if (currentString.charAt(i) != currentString.charAt(i+1)) {
                        containsAbba = true;
                    }
                }
            }
        }
        return containsAbba;
    }

    private List<String> getAbas(String currentString){
        List<String> allAbas = new ArrayList<String>();
        for(int i=0; i<currentString.length()-2; ++i) {
            if (currentString.charAt(i) == currentString.charAt(i+2)) {
                String letterOne = Character.toString(currentString.charAt(i+1));
                String thisAba = letterOne + Character.toString(currentString.charAt(i)) + letterOne;
                if (!allAbas.contains(thisAba)) {
                    allAbas.add(thisAba);
                }
            }
        }
        return allAbas;
    }
}
