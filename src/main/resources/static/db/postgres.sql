CREATE TABLE user_profile
(
  user_id serial NOT NULL,
  username character varying(20),
  password character varying(20) NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  email character varying(30) NOT NULL,
  CONSTRAINT user_profile_pkey PRIMARY KEY (user_id)
);

CREATE TABLE role
(
  role_id serial NOT NULL,
  role_name character varying(20) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (role_id)
);

CREATE TABLE user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES role (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES user_profile (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO user_profile(username,password,enabled,email) VALUES ('jack','jack', true,'j@j.com');
INSERT INTO user_profile(username,password,enabled,email) VALUES ('peter','peter', true,'p@p.com');
 
INSERT INTO role (role_name) VALUES ('ROLE_USER');
INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
