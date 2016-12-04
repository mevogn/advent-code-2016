package advent;

import java.util.List;

public interface Security {
    String getBathroomCode(List<String> instructions);
    String getRealBathroomCode(List<String> instructions);
}
