DROP TABLE IF EXISTS user_profile;
CREATE TABLE user_profile
(
  user_id serial NOT NULL,
  username character varying(20) NOT NULL,
  password character varying(100) NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  email character varying(30),
  CONSTRAINT user_profile_pkey PRIMARY KEY (user_id)
);
INSERT INTO user_profile(username,password,enabled,email) VALUES ('a@a.com','123', true,'a@a.com');
INSERT INTO user_profile(username,password,enabled,email) VALUES ('b@b.com','123', true,'b@b.com');


DROP TABLE IF EXISTS role;
CREATE TABLE role
(
  role_id serial NOT NULL,
  role character varying(20) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (role_id)
);
INSERT INTO role (role) VALUES ('ROLE_USER');
INSERT INTO role (role) VALUES ('ROLE_ADMIN');
INSERT INTO role (role) VALUES ('ROLE_APIUSER');


DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES role (role_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES user_profile (user_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (1, 3);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);