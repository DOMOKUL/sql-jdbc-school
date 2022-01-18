package com.company.sql.jdbc.school.util.database;

import java.util.*;
import java.io.FileNotFoundException;

public interface TableFiller {
    void fillDatabase(List<String> filePath) throws FileNotFoundException;
}
