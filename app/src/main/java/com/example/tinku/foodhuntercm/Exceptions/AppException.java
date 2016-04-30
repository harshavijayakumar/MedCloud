package com.example.tinku.foodhuntercm.Exceptions;

import com.example.tinku.foodhuntercm.adapter.FixApp;
/**
 * Created by sandeep on 4/29/2016.
 */
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
        return null;
    }

    /* Get the error number information for exception handling */
    public int getErrorNumber(){
        return errorNumber;
    }

}
