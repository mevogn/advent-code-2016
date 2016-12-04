package advent;

import java.util.List;

public interface PuzzleInput {

    String[] getSingleLineFileAsStrings(String fileName, String delimiter);

    List<String> getMultiLineFileAsStrings(String fileName);
}
