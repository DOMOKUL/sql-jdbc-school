package com.company.sql.jdbc.school.util.database;

import java.io.FileNotFoundException;

public interface DataBaseFiller {
    void fillDatabase(String filePath) throws FileNotFoundException;
}
