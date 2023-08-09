CREATE USER 'oopUser'@'localhost' IDENTIFIED BY 'oopUserPasswrd';
GRANT ALL PRIVILEGES ON *.* TO 'oopUser'@'localhost';
CREATE DATABASE oop_proj;

use oop_proj;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
    passwordHash VARCHAR(255)
);

DROP TABLE IF EXISTS friends;
CREATE TABLE friends (
	id INT PRIMARY KEY,
	user_id INT,
    friend_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (friend_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS quizzes;
CREATE TABLE quizzes (
	id INT PRIMARY KEY,
    creator_id INT,
    quiz_name VARCHAR(2000),
    time_taken INT,
    is_single_page bool,
    can_be_practiced bool,
    FOREIGN KEY (creator_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS questions;
CREATE TABLE questions (
	id INT PRIMARY KEY,
    quiz_id INT,
    question VARCHAR(2000),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

DROP TABLE IF EXISTS answers;
CREATE TABLE answers (
	id INT PRIMARY KEY,
    question_id INT,
    answer VARCHAR(2000),
    is_correct bool,
    FOREIGN KEY (question_id) REFERENCES questions(id)
);

DROP TABLE IF EXISTS quiz_history;
CREATE TABLE quiz_history (
	id INT PRIMARY KEY,
    score DECIMAL(5, 2),
    quiz_id INT,
    user_id INT,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS notifications;
CREATE TABLE notifications (
	id INT PRIMARY KEY,
	receiver_id INT,
    sender_id INT,
    FOREIGN KEY (receiver_id) REFERENCES users(id),
    FOREIGN KEY (sender_id) REFERENCES users(id),
    notif_type VARCHAR(20),
    notif_body VARCHAR(2000)
);

DROP TABLE IF EXISTS announcements;
CREATE TABLE announcements (
	id INT PRIMARY KEY,
    announcement VARCHAR(2000)
);

DROP TABLE IF EXISTS achievements;
CREATE TABLE achievements (
	id INT PRIMARY KEY,
    achievement VARCHAR(2000)
);

DROP TABLE IF EXISTS user_achievements;
CREATE TABLE user_achievements (
	id INT PRIMARY KEY,
    user_id INT,
    achievement_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (achievement_id) REFERENCES achievements(id)
);





