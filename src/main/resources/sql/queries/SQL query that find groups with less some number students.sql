SELECT g.name,
       COUNT(s.group_id)
FROM groups g
         JOIN students s ON g.group_id = s.group_id
GROUP BY s.group_id, g.name
HAVING COUNT(s.group_id) <= ?