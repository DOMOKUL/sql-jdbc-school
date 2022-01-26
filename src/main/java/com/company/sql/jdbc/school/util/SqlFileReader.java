package com.company.sql.jdbc.school.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class SqlFileReader {

    public static String readSqlFile(String reader) throws IOException {
        StringJoiner stringJoiner = new StringJoiner(" ");
        String line;
        try (BufferedReader lineReader = new BufferedReader(new FileReader(reader))) {
            while ((line = lineReader.readLine()) != null) {
                stringJoiner.add(line);
            }
            return stringJoiner.toString();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
