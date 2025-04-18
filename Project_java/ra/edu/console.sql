DROP DATABASE course_management;
CREATE DATABASE IF NOT EXISTS course_management;
USE course_management;
CREATE TABLE Account (
  id INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role ENUM('ADMIN', 'STUDENT') NOT NULL,
  status ENUM('ACTIVE','INACTIVE','BLOCKED')
);

CREATE TABLE Students (
  id_account INT NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
   dob DATE NOT NULL,
   sex BIT NOT NULL,
   phone VARCHAR(20) NULL,
   create_at DATE DEFAULT(NOW()),
   FOREIGN KEY (id_account) REFERENCES Account(id)
);

CREATE TABLE Courses (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL UNIQUE,
  duration INT NOT NULL,
  instructor VARCHAR(100) NOT NULL,
  status ENUM('ACTIVE','INACTIVE','BLOCKED'),
  create_at DATE DEFAULT(NOW())
);

CREATE TABLE enrollments (
  id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT NOT NULL,
  course_id INT NOT NULL,
  registered_at DATE DEFAULT(CURRENT_TIMESTAMP()),
  status ENUM('WAITING','DENIED','CANCER','CONFIRM'),
  FOREIGN KEY(student_id) REFERENCES Students(id_account),
  FOREIGN KEY(course_id) REFERENCES Courses(id)
);

INSERT INTO Account (email, password, role, status)
VALUES
    ('admin@example.com', 'admin123', 'ADMIN', 'ACTIVE'),
    ('student1@example.com', 'student123', 'STUDENT', 'ACTIVE'),
    ('student2@example.com', 'student456', 'STUDENT', 'INACTIVE');

INSERT INTO Courses (name, duration, instructor, status)
VALUES
    ('Java Programming', 30, 'John Doe', 'ACTIVE'),
    ('Python Basics', 25, 'Jane Smith', 'ACTIVE'),
    ('Web Development', 40, 'Alice Johnson', 'INACTIVE'),
    ('Data Science', 50, 'Michael Brown', 'ACTIVE'),
    ('Machine Learning', 45, 'Emily Davis', 'ACTIVE'),
    ('Cloud Computing', 35, 'Chris Wilson', 'INACTIVE'),
    ('Cybersecurity', 20, 'Sophia Martinez', 'ACTIVE'),
    ('Mobile App Development', 60, 'David Garcia', 'ACTIVE'),
    ('Game Development', 55, 'Emma Anderson', 'INACTIVE'),
    ('Artificial Intelligence', 70, 'Olivia Thomas', 'ACTIVE');

DELIMITER //
CREATE PROCEDURE login_by_account(
    p_email VARCHAR(50),
    p_password VARCHAR(255),
    OUT return_code INT
)
BEGIN
    DECLARE statusAcc BOOLEAN;
    IF EXISTS (SELECT * FROM Account WHERE email = p_email AND password = p_password) THEN
        SET return_code = 1;
        SELECT status INTO statusAcc FROM Account WHERE email = p_email AND password = p_password;
        IF (statusAcc = 'BLOCK') THEN
            SET return_code = 2;
        ELSE
            SELECT id, email, role FROM Account WHERE email = p_email AND password = p_password;
        END IF;
    ELSE
        SET return_code = 0;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE find_course_by_name_p(p_name VARCHAR(100))
BEGIN
    SELECT * FROM Courses WHERE name = p_name AND status != 'BLOCKED';
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE find_course_by_id(p_id INT)
BEGIN
    SELECT * FROM Courses WHERE id = p_id AND status != 'BLOCKED';
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE list_course(
    p_limit INT,
    p_page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (p_page - 1) * p_limit;
    SELECT * FROM Courses WHERE status != 'BLOCKED'
    LIMIT p_limit OFFSET offset_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE total_course()
BEGIN
    SELECT COUNT(id) FROM Courses WHERE status != 'BLOCKED';
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE add_course(
    p_name VARCHAR(100),
    p_duration INT,
    p_instructor VARCHAR(100),
    p_status ENUM('ACTIVE','INACTIVE','BLOCKED')
)
BEGIN
    INSERT INTO Courses (name, duration, instructor, status)
    VALUES (p_name, p_duration, p_instructor, p_status);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_course(
    p_id INT,
    p_name VARCHAR(100),
    p_duration INT,
    p_instructor VARCHAR(100)
)
BEGIN
    UPDATE Courses
    SET name = p_name,
        duration = p_duration,
        instructor = p_instructor
    WHERE id = p_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE del_course(p_id INT)
BEGIN
    UPDATE Courses
    SET status = 'BLOCKED'
    WHERE id = p_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE find_course_by_name(
    p_name VARCHAR(100),
    p_limit INT,
    p_page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (p_page - 1) * p_limit;
    SELECT * FROM Courses
    WHERE status != 'BLOCKED' AND name LIKE CONCAT('%', p_name, '%')
    LIMIT p_limit OFFSET offset_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sort_by_name(
    p_order CHAR(5),
    p_limit INT,
    p_page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (p_page - 1) * p_limit;
    IF p_order = 'desc' THEN
        SELECT * FROM Courses WHERE status != 'BLOCKED'
        ORDER BY name DESC
        LIMIT p_limit OFFSET offset_in;
    ELSE
        SELECT * FROM Courses WHERE status != 'BLOCKED'
        ORDER BY name ASC
        LIMIT p_limit OFFSET offset_in;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sort_by_id(
    p_order CHAR(5),
    p_limit INT,
    p_page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (p_page - 1) * p_limit;
    IF p_order = 'desc' THEN
        SELECT * FROM Courses WHERE status != 'BLOCKED'
        ORDER BY id DESC
        LIMIT p_limit OFFSET offset_in;
    ELSE
        SELECT * FROM Courses WHERE status != 'BLOCKED'
        ORDER BY id ASC
        LIMIT p_limit OFFSET offset_in;
    END IF;
END //
DELIMITER ;


