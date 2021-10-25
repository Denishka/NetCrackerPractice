package com.company.exceptions;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public SpaceIndexOutOfBoundsException(String s) {
        super(s);
    }

}
