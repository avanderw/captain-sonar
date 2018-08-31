package avdw.java.captain.sonar.board;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoardCell {
    @JsonProperty(required = true)
    Integer row;
    @JsonProperty(required = true)
    Integer col;
    @JsonProperty(required = true)
    Boolean isLand;

}
