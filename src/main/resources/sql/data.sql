INSERT INTO ROLE (RIGHT) VALUES ('ROLE_USER');

INSERT INTO USER (USERNAME, PIC, EMAIL, FK_ROLE_ID) VALUES ('Mathew', RAWTOHEX(''), 'MATHEW@EMAIL.COM', 1);
INSERT INTO USER (USERNAME, PIC, EMAIL, FK_ROLE_ID) VALUES ('Ann', RAWTOHEX(''), 'ANN@EMAIL.COM', 1);
INSERT INTO USER (USERNAME, PIC, EMAIL, FK_ROLE_ID) VALUES ('Chester', RAWTOHEX(''), 'CHESTER@EMAIL.COM', 1);
INSERT INTO USER (USERNAME, PIC, EMAIL, FK_ROLE_ID) VALUES ('Sam', RAWTOHEX(''), 'SAM@EMAIL.COM', 1);

INSERT INTO CATEGORY (CATEGORY_ID, NAME) VALUES (1, 'clothes');
INSERT INTO CATEGORY (CATEGORY_ID, NAME) VALUES (2, 'electronics');

INSERT INTO CREDENTIALS (FK_USER_ID, PASS, TOKEN) VALUES (1, '$2a$10$cXvxqLzI3WpbNaRqU/abTeCbKxCQWmUfpDad7KOttmO14BWzfNhG2', '');
INSERT INTO CREDENTIALS (FK_USER_ID, PASS, TOKEN) VALUES (2, '12345', '');
INSERT INTO CREDENTIALS (FK_USER_ID, PASS, TOKEN) VALUES (3, '$2a$10$cXvxqLzI3WpbNaRqU/abTeCbKxCQWmUfpDad7KOttmO14BWzfNhG2', '');
INSERT INTO CREDENTIALS (FK_USER_ID, PASS, TOKEN) VALUES (4, '$2a$10$J5sM7xKdB073xWOrhnyOs.lQC.MbDJYhxoZjLNzNaFYnvP0244Nfu', '');

INSERT INTO QUESTIONS (DESCRIPTION, TEXT, FK_CATEGORY_ID, FK_USER_ID, DATE) VALUES ('WHERES MY PANTS','Wheres my pants?', 1, 1, '2016-08-29');
INSERT INTO ANSWERS (INFORMATION, FK_QUESTION_ID, FK_USER_ID, DATE) VALUES ('Look under the skirt', 1, 2, '2016-08-29');


-- 147831215911-rus2gotlcc8qm4j0n3ivu8th7erfr5jq.apps.googleusercontent.com