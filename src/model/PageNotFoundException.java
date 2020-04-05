package model;

/**
 * @Author: Lingjue
 * @Date: 2020-04-05
 */
public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
