DROP DATABASE course_management;
CREATE DATABASE IF NOT EXISTS course_management;
USE course_management;
CREATE TABLE Admin (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);
CREATE TABLE Student (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         dob DATE NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         sex BIT NOT NULL,
                         phone VARCHAR(20),
                         password VARCHAR(255) NOT NULL,
                         create_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Course (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        duration INT NOT NULL,
                        instructor VARCHAR(100) NOT NULL,
                        create_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Enrollment (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            student_id INT NOT NULL,
                            course_id INT NOT NULL,
                            registered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                            status ENUM('WAITING','DENIED','CANCEL','CONFIRMED') DEFAULT 'WAITING',
                            FOREIGN KEY (student_id) REFERENCES Student(id),
                            FOREIGN KEY (course_id) REFERENCES Course(id)
);
INSERT INTO Admin (username, password)
VALUES
    ('admin1', 'admin123'),
    ('admin2', 'admin456');
INSERT INTO Student (name, dob, email, sex, phone, password)
VALUES
    ('Nguyễn Văn A', '2000-05-15', 'a@student.com', 1, '0912345678', '123456'),
    ('Trần Thị B', '2001-08-22', 'b@student.com', 0, '0987654321', 'abcdef');
DELIMITER //
CREATE PROCEDURE sp_login_admin(IN in_username VARCHAR(50), IN in_password VARCHAR(255))
BEGIN
    SELECT * FROM Admin WHERE username = in_username AND password = in_password;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_login_student(IN in_email VARCHAR(100), IN in_password VARCHAR(255))
BEGIN
    SELECT * FROM Student WHERE email = in_email AND password = in_password;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_get_paginated_courses(IN p_limit INT, IN p_offset INT)
BEGIN
    SELECT * FROM Course
    ORDER BY id
    LIMIT p_limit OFFSET p_offset;
END //
DELIMITER ;

-- Thêm khóa học
DELIMITER //
CREATE PROCEDURE sp_insert_course(IN p_name VARCHAR(100), IN p_duration INT, IN p_instructor VARCHAR(100))
BEGIN
    INSERT INTO Course(name, duration, instructor)
    VALUES (p_name, p_duration, p_instructor);
END //
DELIMITER ;

-- Cập nhật khóa học
DELIMITER //
CREATE PROCEDURE sp_update_course(IN p_id INT, IN p_name VARCHAR(100), IN p_duration INT, IN p_instructor VARCHAR(100))
BEGIN
    UPDATE Course
    SET name = p_name,
        duration = p_duration,
        instructor = p_instructor
    WHERE id = p_id;
END //
DELIMITER ;
