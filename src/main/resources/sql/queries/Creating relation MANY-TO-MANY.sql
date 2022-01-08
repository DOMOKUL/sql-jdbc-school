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