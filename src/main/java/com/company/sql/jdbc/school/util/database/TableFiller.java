package com.company.sql.jdbc.school.util.database;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface TableFiller {
    void fillDatabase(String filePath) throws FileNotFoundException, IOException;
}
