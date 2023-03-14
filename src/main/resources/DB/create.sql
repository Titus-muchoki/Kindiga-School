CREATE DATABASE kindiga;
\c kindiga;
CREATE TABLE students(id SERIAL PRIMARY KEY, name VARCHAR, phoneNumber VARCHAR, email VARCHAR);
CREATE TABLE units(id SERIAL PRIMARY KEY, math VARCHAR, english VARCHAR, kiswahili VARCHAR, science VARCHAR, socialstudy VARCHAR, cre VARCHAR, studentid INTEGER);
CREATE DATABASE kindiga_test WITH TEMPLATE kindiga;