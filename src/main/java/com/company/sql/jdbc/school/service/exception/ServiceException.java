package com.company.sql.jdbc.school.service.exception;

import com.company.sql.jdbc.school.dao.exception.DaoException;

public class ServiceException extends DaoException {

    public ServiceException(){super();}

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }
    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
