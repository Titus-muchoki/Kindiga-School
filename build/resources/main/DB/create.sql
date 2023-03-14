CREATE DATABASE kindiga;
\c kindiga;
CREATE TABLE students(id SERIAL PRIMARY KEY, name VARCHAR, phoneNumber VARCHAR, email VARCHAR);
CREATE DATABASE kindiga_test WITH TEMPLATE kindiga;