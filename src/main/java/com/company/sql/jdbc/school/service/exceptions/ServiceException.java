package com.company.sql.jdbc.school.service.exceptions;

import com.company.sql.jdbc.school.exception.DaoException;

public class ServiceException extends DaoException {

    public ServiceException(){super();}

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }
}
