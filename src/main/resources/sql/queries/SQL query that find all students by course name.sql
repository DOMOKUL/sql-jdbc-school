SELECT s.*
FROM courses c
         JOIN students_courses sc
              ON c.course_id = sc.course_id
         JOIN students s
              ON s.student_id = sc.student_id
GROUP BY s.student_id, name
HAVING c.name = ?