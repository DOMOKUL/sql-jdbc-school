package com.company.sql.jdbc.school.ui.exception;

import com.company.sql.jdbc.school.service.exception.ServiceException;

public class UiException extends ServiceException {

    public UiException(String message, Throwable cause) {
        super(message, cause);
    }
}
