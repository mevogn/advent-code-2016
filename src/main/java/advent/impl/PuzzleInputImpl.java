package advent.impl;


import advent.PuzzleInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PuzzleInputImpl implements PuzzleInput {

    public String[] getSingleLineFileAsStrings(String fileName, String delimiter) {
        String filePath = "C:\\Users\\Maria\\IdeaProjects\\AdventOfCode2016\\src\\main\\resources\\" + fileName + ".txt";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String[] delimitedArray = br.readLine().split(delimiter);
            br.close();
            return delimitedArray;
        } catch (IOException e) {
            System.out.println("Error");
            return null;
        }
    }

    public List<String> getMultiLineFileAsStrings(String fileName) {
        String filePath = "C:\\Users\\Maria\\IdeaProjects\\AdventOfCode2016\\src\\main\\resources\\" + fileName + ".txt";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
            List<String> lines = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
            return lines;
        } catch (IOException e) {
            System.out.println("Error");
            return null;
        }
    }
}
