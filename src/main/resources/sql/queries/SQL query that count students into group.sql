SELECT name, COUNT(s.group_id) AS count
FROM students AS s
         JOIN groups USING (group_id)
GROUP BY name