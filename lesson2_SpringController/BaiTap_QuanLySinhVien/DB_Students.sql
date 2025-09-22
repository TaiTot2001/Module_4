CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    score FLOAT NOT NULL
);

insert into students  (id, name, score) values ("S001","Nguyễn Văn A",8.5),("S002","Nguyễn Văn B",7.5);
select * from students;