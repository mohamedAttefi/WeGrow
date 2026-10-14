package org.weGrow.exeption;

public class MatriculeExistantExeption extends RuntimeException {
    public MatriculeExistantExeption(String message) {
        super(message);
    }
}
