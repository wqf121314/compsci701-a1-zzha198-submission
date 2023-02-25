package kalah.service;

import kalah.domian.BoardInfo;

public interface GameService {
    BoardInfo init();

    boolean isHouseEmpty(BoardInfo boardInfo);

    BoardInfo move(BoardInfo boardInfo);

}
