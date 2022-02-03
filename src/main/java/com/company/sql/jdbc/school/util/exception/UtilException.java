package com.company.sql.jdbc.school.util.exception;

import java.sql.SQLException;

public class UtilException extends SQLException {

    public UtilException() {
        super();
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

}
