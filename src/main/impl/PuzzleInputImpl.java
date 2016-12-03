package main.impl;

import main.PuzzleInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PuzzleInputImpl implements PuzzleInput{

    @Override
    public String[] getFileAsString(String fileName, String delimiter) {
        String filePath = "C:\\Users\\Maria\\IdeaProjects\\AdventOfCode2016\\src\\main\\resources\\" + fileName + ".txt";
        Path path = Paths.get(filePath);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path.toFile()));
            return br.readLine().split(delimiter);
        } catch (IOException e) {
            System.out.println("Error");
            return null;
        }
    }
}
