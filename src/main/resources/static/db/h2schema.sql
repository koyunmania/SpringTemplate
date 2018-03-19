CREATE TABLE user_profile
(
  user_id serial NOT NULL,
  username character varying(20) NOT NULL,
  password character varying(100) NOT NULL,
  enabled boolean NOT NULL DEFAULT false,
  email character varying(30),
  CONSTRAINT user_profile_pkey PRIMARY KEY (user_id)
);
CREATE TABLE role
(
  role_id serial NOT NULL,
  role character varying(20) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (role_id)
);
CREATE TABLE user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES role (role_id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES user_profile (user_id)
      ON UPDATE CASCADE ON DELETE CASCADE
);
