package org.weGrow.exeption;

public class DepartementInexistantExeption extends RuntimeException {
    public DepartementInexistantExeption(String message) {
        super(message);
    }
}
