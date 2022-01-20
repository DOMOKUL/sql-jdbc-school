INSERT INTO courses(course_id, name, description)
VALUES (1, 'math', '<3'),
       (2, 'PE', ':('),
       (3, 'language', ':)');

INSERT INTO groups(group_id, name)
VALUES (1, 'RS-26'),
       (2, 'RD-25'),
       (3, 'RB-24');

INSERT INTO students(student_id, group_id, first_name, last_name)
VALUES (1, 1, 'Ivan', 'Ivanov'),
       (2, 2, 'Petr', 'Petrov'),
       (3, 3, 'Alex', 'Alexandrov'),
       (4, 2, 'Masha', 'Ivanova'),
       (5, 1, 'Kostya', 'Petrov'),
       (6, 3, 'Vlad', 'Valdov');

INSERT INTO students_courses(student_id, course_id)
VALUES (1, 1),
       (2, 1),
       (2, 3),
       (3, 2)
