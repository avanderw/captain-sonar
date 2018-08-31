package avdw.java.captain.sonar.board;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Board {
    @JsonProperty(required = true)
    String name;
    @JsonProperty(required = true)
    List<BoardCell> boardCellList;
}
