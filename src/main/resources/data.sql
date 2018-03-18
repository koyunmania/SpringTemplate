INSERT INTO user_profile(username,password,enabled,email) VALUES ('a@a.com','123', true,'a@a.com');
INSERT INTO user_profile(username,password,enabled,email) VALUES ('b@b.com','123', true,'b@b.com');
INSERT INTO role (role) VALUES ('ROLE_USER');
INSERT INTO role (role) VALUES ('ROLE_ADMIN');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);