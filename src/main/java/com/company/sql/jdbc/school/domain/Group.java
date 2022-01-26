package com.company.sql.jdbc.school.domain;

public record Group(Integer groupId, String groupName) {

    @Override
    public String toString() {
        return "groupId=" + groupId +
                ", groupName='" + groupName + '\'';
    }
}
