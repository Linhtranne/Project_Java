
CREATE DATABASE IF NOT EXISTS course_management;
USE course_management;
DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
                       admin_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       fullname VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       roles ENUM('USER','ADMIN') NOT NULL DEFAULT 'ADMIN'
) ENGINE=InnoDB;
DROP TABLE IF EXISTS student;
CREATE TABLE student (
                         student_id INT AUTO_INCREMENT PRIMARY KEY,
                         fullname VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         date_of_birth DATE NOT NULL,
                         gender ENUM('MALE','FEMALE','OTHER') NOT NULL,
                         address VARCHAR(255),
                         password VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CHECK (email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
                         CHECK (TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) >= 16)
) ENGINE=InnoDB;
DROP TABLE IF EXISTS course;
CREATE TABLE course (
                        course_id INT AUTO_INCREMENT PRIMARY KEY,
                        course_code VARCHAR(20) NOT NULL UNIQUE,
                        course_name VARCHAR(150) NOT NULL,
                        description TEXT,
                        duration_weeks INT NOT NULL,
                        start_date DATE NOT NULL,
                        end_date DATE NOT NULL,
                        status ENUM('OPEN','CLOSED') NOT NULL DEFAULT 'OPEN',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CHECK (duration_weeks > 0),
                        CHECK (start_date < end_date)
) ENGINE=InnoDB;
DROP TABLE IF EXISTS registration;
CREATE TABLE registration (
                              registration_id INT AUTO_INCREMENT PRIMARY KEY,
                              student_id INT NOT NULL,
                              course_id INT NOT NULL,
                              registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- Ngăn trùng lặp đăng ký cùng 1 học viên cho 1 khóa học
                              UNIQUE KEY uq_student_course (student_id, course_id),
                              FOREIGN KEY (student_id) REFERENCES student(student_id)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE,
                              FOREIGN KEY (course_id) REFERENCES course(course_id)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE
) ENGINE=InnoDB;