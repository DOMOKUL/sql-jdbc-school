CREATE DATABASE task7;

CREATE USER postgres with encrypted password 'root';

GRANT ALL PRIVILEGES ON database task7 TO postgres;

drop table if exists  groups, students, courses, students_courses cascade;

CREATE TABLE students
(
    student_id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    group_id   INTEGER NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL,
    CONSTRAINT fk_groups
        FOREIGN KEY (group_id)
            REFERENCES groups (group_id)
            ON DELETE SET NULL
);
CREATE UNIQUE INDEX students_unique_id_idx ON students (student_id);

CREATE TABLE groups
(
    group_id INTEGER NOT NULL,
    name     VARCHAR NOT NULL
);
CREATE UNIQUE INDEX groups_unique_id_idx ON groups (group_id);

CREATE TABLE courses
(
    course_id   INTEGER PRIMARY KEY NOT NULL,
    name        VARCHAR             NOT NULL,
    description VARCHAR             NOT NULL
);
CREATE UNIQUE INDEX courses_unique_id_idx ON courses (course_id);

CREATE TABLE students_courses
(
    student_id INTEGER NOT NULL,
    course_id  INTEGER NOT NULL,
    PRIMARY KEY (student_id, course_id),
    CONSTRAINT FK_students_courses_courses
        FOREIGN KEY (student_id) REFERENCES students (student_id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_students_courses_students
        FOREIGN KEY (course_id) REFERENCES courses (course_id)
            ON UPDATE CASCADE ON DELETE CASCADE
);