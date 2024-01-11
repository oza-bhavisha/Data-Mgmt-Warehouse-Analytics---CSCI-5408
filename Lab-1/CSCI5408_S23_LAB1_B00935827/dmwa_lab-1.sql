USE lab_1;

-- 1. Check how many directors are present in iMDB
SELECT DISTINCT COUNT(*) AS total_directors
FROM directors;

-- 2. Check how many movies are released post-year 2000
SELECT COUNT(*) AS total_movies
FROM movies
WHERE year > 2000;

-- 3. Find the list of genres of movies directed by Andrew Adamson
SELECT DISTINCT mg.genre
FROM movies m
JOIN movies_directors md ON m.id = md.movie_id
JOIN directors d ON md.director_id = d.id
JOIN movies_genres mg ON m.id = mg.movie_id
WHERE d.first_name = 'Andrew' AND d.last_name = 'Adamson';


-- 4. List of directors whose movies are ranked between 7 to 8 ranking
SELECT CONCAT(first_name, ' ', last_name) AS directors_full_name
FROM directors d
JOIN movies_directors md ON d.id = md.director_id
JOIN movies m ON md.movie_id = m.id
WHERE m.rank BETWEEN 7 AND 8;

-- 5. Find the role of Julliet Akinyi in Lost in Translation movie
SELECT r.role
FROM roles r
WHERE r.actor_id = ( SELECT a.id FROM actors a WHERE a.first_name = 'Julliet' AND a.last_name = 'Akinyi')
AND r.movie_id = ( SELECT m.id FROM movies m WHERE m.name = 'Lost in Translation');

-- 6. List of the movies that contain the letter ‘o' in any position
SELECT name
FROM movies
WHERE name LIKE '%o%';