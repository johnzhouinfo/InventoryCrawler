package model;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
