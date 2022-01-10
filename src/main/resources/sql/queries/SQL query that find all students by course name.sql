SELECT s.*
FROM courses as c
         RIGHT JOIN students_courses as sc
                    ON c.course_id = sc.course_id
         LEFT JOIN students as s
                   ON sc.student_id = s.student_id
GROUP BY s.student_id, name
HAVING c.name = ?