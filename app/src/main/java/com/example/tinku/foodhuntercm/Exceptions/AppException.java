package com.example.tinku.foodhuntercm.Exceptions;

/* Import appropriate libraries */
import com.example.tinku.foodhuntercm.adapter.FixApp;

/* App exception class for handling exceptions in the application */
public class AppException extends Exception implements FixApp  {
    private int errorNumber;
    private String errorMessage;

    /* throws a user exception, and get the errorNumber */
    public AppException(int errorNo, String errorMess) {
        super(errorMess);
        this.errorNumber = errorNo;
        this.errorMessage = errorMess;
    }

    /* fix problem by inputting from the console */
    public String genericexceptionfix() {
        // input the new file name from the console
        if (errorNumber == 0) {
            return null;
        } else {
            return errorMessage;
        }
    }

}
