package com.company.sql.jdbc.school.exception;

public class DaoException extends RuntimeException{

    public DaoException(){
        super();
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Throwable throwable){
        super(message, throwable);
    }
}
