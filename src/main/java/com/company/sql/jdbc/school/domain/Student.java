package com.company.sql.jdbc.school.domain;

public record Student(Integer studentId, Integer groupId, String firstName, String lastName) {

    @Override
    public String toString() {
        return "studentId=" + studentId +
                ", groupId=" + groupId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}
