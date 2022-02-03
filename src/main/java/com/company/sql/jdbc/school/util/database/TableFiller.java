package com.company.sql.jdbc.school.util.database;

import java.io.FileNotFoundException;

public interface TableFiller {
    void fillDatabase(String filePath) throws FileNotFoundException;
}
