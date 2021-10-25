package com.company.exceptions;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public FloorIndexOutOfBoundsException(String s) {
        super(s);
    }
}
