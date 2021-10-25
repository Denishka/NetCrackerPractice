package com.company.exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException {
    @Override
        public String getMessage() {
            return super.getMessage();
    }

    public InvalidRoomsCountException(String s) {
        super(s);
    }
}
