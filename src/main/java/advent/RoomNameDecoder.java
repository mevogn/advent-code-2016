package advent;

import java.util.List;

public interface RoomNameDecoder {
    int getSumSectorIDs(List<String> roomNames, boolean partA, String roomLookingFor);
}
