DROP TABLE IF EXISTS ANSWERS;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS CREDENTIALS;
DROP TABLE IF EXISTS QUESTIONS;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS USER;
drop sequence if exists hibernate_sequence;

CREATE TABLE role(role_id BIGINT AUTO_INCREMENT PRIMARY KEY,right VARCHAR(128) NOT NULL);
CREATE TABLE user(user_id BIGINT AUTO_INCREMENT PRIMARY KEY,username VARCHAR(128),pic LONGBLOB,email VARCHAR(128),fk_role_id BIGINT);
CREATE TABLE category(category_id BIGINT,name VARCHAR(45) NOT NULL);
CREATE TABLE credentials(fk_user_id BIGINT NOT NULL,pass VARCHAR(2048), token VARCHAR(2048));
CREATE TABLE answers(answer_id BIGINT AUTO_INCREMENT PRIMARY KEY,information VARCHAR(128),fk_question_id BIGINT NOT NULL,fk_user_id BIGINT NOT NULL,date DATE,);
CREATE TABLE questions(question_id BIGINT AUTO_INCREMENT PRIMARY KEY,description VARCHAR(512),text VARCHAR(512),fk_category_id INT NOT NULL,fk_user_id INT NOT NULL,date DATE);

ALTER TABLE answers ADD FOREIGN KEY (fk_question_id) REFERENCES questions (question_id);
ALTER TABLE answers ADD FOREIGN KEY (fk_user_id) REFERENCES user (user_id);
-- ALTER TABLE credentials ADD FOREIGN KEY (fk_user_id) REFERENCES user (user_id);
ALTER TABLE questions ADD FOREIGN KEY (fk_category_id) REFERENCES category (category_id);
ALTER TABLE questions ADD FOREIGN KEY (fk_user_id) REFERENCES user (user_id);
ALTER TABLE user ADD FOREIGN KEY (fk_role_id) REFERENCES role (role_id);