package advent.impl;

import advent.Decompressor;
import advent.PuzzleInput;

public class DecompressorImpl implements Decompressor {

    private PuzzleInput puzzleInput;

    DecompressorImpl(PuzzleInput puzzleInput) {
        this.puzzleInput = puzzleInput;
    }


    public long getDecompressedValue(String input) {
        if (input == null) {
            input = puzzleInput.getMultiLineFileAsString("day7input");
        }
        long sequence = 0;
        return sequence;
    }
}
