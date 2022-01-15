package com.company.sql.jdbc.school.dao.exception;

public class DaoException extends RuntimeException{

    public DaoException(){
        super();
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(Throwable cause){
        super(cause);
    }
}
