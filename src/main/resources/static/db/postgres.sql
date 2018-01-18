CREATE TABLE users(
   user_id SERIAL PRIMARY KEY,
   username varchar(20) NOT NULL,
   password varchar(20) NOT NULL,
   enabled boolean NOT NULL DEFAULT FALSE
   -- CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

create table roles (
   role_id SERIAL PRIMARY KEY,
   role varchar(20) NOT NULL
   -- CONSTRAINT roles_pkey PRIMARY KEY (role_id)
);

create table user_roles (
   user_id integer NOT NULL,
   role_id integer NOT NULL,
   CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
   FOREIGN KEY (user_id) REFERENCES users (user_id),
   FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

INSERT INTO users(username,password,enabled) VALUES ('jack','jack', true);
INSERT INTO users(username,password,enabled) VALUES ('peter','peter', true);
 
INSERT INTO roles (role) VALUES ('ROLE_USER');
INSERT INTO roles (role) VALUES ('ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);



-------------------------

CREATE TABLE users(
   username varchar(20) NOT NULL,
   password varchar(20) NOT NULL,
   enabled boolean NOT NULL DEFAULT FALSE,
   primary key(username)
);

create table user_roles (
  user_role_id SERIAL PRIMARY KEY,
  username varchar(20) NOT NULL,
  role varchar(20) NOT NULL,
  UNIQUE (username,role),
  FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO users(username,password,enabled) VALUES ('jack','jack', true);
INSERT INTO users(username,password,enabled) VALUES ('peter','peter', true);
 
INSERT INTO user_roles (username, role) VALUES ('jack', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('jack', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('peter', 'ROLE_USER');



-------------------------

