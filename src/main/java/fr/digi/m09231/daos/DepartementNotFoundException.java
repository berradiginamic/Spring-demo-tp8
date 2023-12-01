package fr.digi.m09231.daos;

public class DepartementNotFoundException extends RuntimeException {
    public DepartementNotFoundException() {
        super();
    }

    public DepartementNotFoundException(String message) {
        super(message);
    }
}