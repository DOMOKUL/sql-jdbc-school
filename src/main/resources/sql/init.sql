CREATE DATABASE task7;

CREATE USER postgres with encrypted password 'root';

GRANT ALL PRIVILEGES ON database task7 TO postgres;