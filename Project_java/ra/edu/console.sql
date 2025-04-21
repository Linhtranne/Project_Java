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
                             status ENUM('WAITING','DENIED','CANCEL','CONFIRM'),
                             FOREIGN KEY(student_id) REFERENCES Students(id_account),
                             FOREIGN KEY(course_id) REFERENCES Courses(id)
);

INSERT INTO Account (email, password, role, status)
VALUES
    ('admin@example.com', 'admin123', 'ADMIN', 'ACTIVE'),
    ('student1@example.com', 'student123', 'STUDENT', 'ACTIVE'),
    ('student2@example.com', 'student456', 'STUDENT', 'INACTIVE'),
    ('student3@example.com', 'student789', 'STUDENT', 'ACTIVE'),
    ('student4@example.com', 'student012', 'STUDENT', 'ACTIVE'),
    ('student5@example.com', 'student345', 'STUDENT', 'INACTIVE'),
    ('student6@example.com', 'student678', 'STUDENT', 'ACTIVE'),
    ('student7@example.com', 'student901', 'STUDENT', 'BLOCKED'),
    ('student8@example.com', 'student234', 'STUDENT', 'ACTIVE'),
    ('student9@example.com', 'student567', 'STUDENT', 'INACTIVE');

INSERT INTO Students (id_account, name, dob, sex, phone, create_at) VALUES
                                                                        (1, 'Nguyễn Văn An', '2000-01-15', 1, '0912345678', NOW()),
                                                                        (2, 'Trần Thị Bình', '1999-03-22', 0, '0987654321', NOW()),
                                                                        (3, 'Lê Hoàng Cường', '2001-07-10', 1, '0901234567', NOW()),
                                                                        (4, 'Phạm Minh Duyên', '2002-11-05', 0, NULL, NOW()),
                                                                        (5, 'Hoàng Văn Em', '1998-09-18', 1, '0934567890', NOW()),
                                                                        (6, 'Ngô Thị Hồng', '2000-04-30', 0, '0971234567', NOW()),
                                                                        (7, 'Đặng Văn Khang', '1997-12-25', 1, '0945678901', NOW()),
                                                                        (8, 'Vũ Thị Lan', '2003-02-14', 0, NULL, NOW()),
                                                                        (9, 'Bùi Văn Minh', '1999-06-08', 1, '0967890123', NOW()),
                                                                        (10, 'Đỗ Thị Ngọc', '2001-08-20', 0, '0923456789', NOW());
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

DELIMITER //
CREATE PROCEDURE find_student_by_name(IN p_name VARCHAR(100))
BEGIN
SELECT * FROM Students WHERE name = p_name;
END //
DELIMITER ;

-- Tìm học viên theo ID
DELIMITER //
CREATE PROCEDURE find_student_by_id(IN p_id INT)
BEGIN
SELECT * FROM Students WHERE id_account = p_id;
END //
DELIMITER ;

-- Lấy danh sách học viên có phân trang
DELIMITER //
CREATE PROCEDURE list_students(IN p_limit INT, IN p_page INT)
BEGIN
    DECLARE v_offset INT;
    SET v_offset = (p_page - 1) * p_limit;
SELECT * FROM Students LIMIT p_limit OFFSET v_offset;
END //
DELIMITER ;

-- Đếm tổng số học viên
DELIMITER //
CREATE PROCEDURE total_students()
BEGIN
SELECT COUNT(*) FROM Students;
END //
DELIMITER ;

-- Tìm học viên theo tên có phân trang
DELIMITER //
CREATE PROCEDURE find_student_by_name_pagination(IN p_name VARCHAR(100), IN p_limit INT, IN p_page INT)
BEGIN
    DECLARE v_offset INT;
    SET v_offset = (p_page - 1) * p_limit;
SELECT * FROM Students WHERE name LIKE CONCAT('%', p_name, '%') LIMIT p_limit OFFSET v_offset;
END //
DELIMITER ;

-- Sắp xếp học viên theo tên
DELIMITER //
CREATE PROCEDURE sort_students_by_name(IN p_sort_by VARCHAR(10), IN p_limit INT, IN p_page INT)
BEGIN
    DECLARE v_offset INT;
    SET v_offset = (p_page - 1) * p_limit;
    IF p_sort_by = 'asc' THEN
SELECT * FROM Students ORDER BY name ASC LIMIT p_limit OFFSET v_offset;
ELSE
SELECT * FROM Students ORDER BY name DESC LIMIT p_limit OFFSET v_offset;
END IF;
END //
DELIMITER ;

-- Sắp xếp học viên theo ID
DELIMITER //
CREATE PROCEDURE sort_students_by_id(IN p_sort_by VARCHAR(10), IN p_limit INT, IN p_page INT)
BEGIN
    DECLARE v_offset INT;
    SET v_offset = (p_page - 1) * p_limit;
    IF p_sort_by = 'asc' THEN
SELECT * FROM Students ORDER BY id_account ASC LIMIT p_limit OFFSET v_offset;
ELSE
SELECT * FROM Students ORDER BY id_account DESC LIMIT p_limit OFFSET v_offset;
END IF;
END //
DELIMITER ;

-- Thêm học viên
DELIMITER //
CREATE PROCEDURE add_student(IN p_id_account INT, IN p_name VARCHAR(100), IN p_dob DATE, IN p_sex BIT, IN p_phone VARCHAR(20))
BEGIN
INSERT INTO Students (id_account, name, dob, sex, phone, create_at)
VALUES (p_id_account, p_name, p_dob, p_sex, p_phone, NOW());
END //
DELIMITER ;

-- Cập nhật học viên
DELIMITER //
CREATE PROCEDURE update_student(IN p_id_account INT, IN p_name VARCHAR(100), IN p_dob DATE, IN p_sex BIT, IN p_phone VARCHAR(20))
BEGIN
UPDATE Students
SET name = p_name, dob = p_dob, sex = p_sex, phone = p_phone
WHERE id_account = p_id_account;
END //
DELIMITER ;

-- Xóa học viên
DELIMITER //
CREATE PROCEDURE delete_student(IN p_id_account INT)
BEGIN
DELETE FROM Students WHERE id_account = p_id_account;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_account_password(IN p_account_id INT, IN p_new_password VARCHAR(255))
BEGIN
UPDATE Account SET password = p_new_password WHERE id = p_account_id;
END //
DELIMITER ;

-- Đăng ký khóa học
DELIMITER //
CREATE PROCEDURE register_course(IN p_student_id INT, IN p_course_id INT)
BEGIN
INSERT INTO enrollments (student_id, course_id, registered_at, status)
VALUES (p_student_id, p_course_id, CURRENT_TIMESTAMP(), 'WAITING');
END //
DELIMITER ;

-- Hủy đăng ký khóa học
DELIMITER //
CREATE PROCEDURE unregister_course(IN p_student_id INT, IN p_course_id INT)
BEGIN
UPDATE enrollments
SET status = 'CANCEL'
WHERE student_id = p_student_id AND course_id = p_course_id AND status IN ('WAITING', 'CONFIRM');
END //
DELIMITER ;

-- Lấy danh sách khóa học đã đăng ký có phân trang
DELIMITER //
CREATE PROCEDURE get_registered_courses(IN p_student_id INT, IN p_limit INT, IN p_page INT)
BEGIN
    DECLARE v_offset INT;
    SET v_offset = (p_page - 1) * p_limit;
SELECT c.*, e.status AS enrollment_status
FROM enrollments e
         JOIN Courses c ON e.course_id = c.id
WHERE e.student_id = p_student_id AND e.status IN ('WAITING', 'CONFIRM')
    LIMIT p_limit OFFSET v_offset;
END //
DELIMITER ;

-- Đếm số khóa học đã đăng ký
DELIMITER //
CREATE PROCEDURE count_registered_courses(IN p_student_id INT)
BEGIN
SELECT COUNT(*)
FROM enrollments
WHERE student_id = p_student_id AND status IN ('WAITING', 'CONFIRM');
END //
DELIMITER ;

