package com.be1.plant4you.exception.board;

public class DuplicateRequestException extends BoardException {

    public DuplicateRequestException(BoardErrorCode errorCode) {
        super(errorCode);
    }
}
