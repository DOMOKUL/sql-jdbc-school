SELECT first_name, last_name
FROM students JOIN students_courses USING(student_id)
WHERE course_id = ?