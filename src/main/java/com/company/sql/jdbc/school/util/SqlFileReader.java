package com.company.sql.jdbc.school.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class SqlFileReader {

    public static String readSqlFile(String filePath) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }
}
