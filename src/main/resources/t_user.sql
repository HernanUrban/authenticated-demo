CREATE TABLE IF NOT EXISTS t_user(
     usr_id serial NOT NULL,
     usr_username   varchar(40) NOT NULL CHECK (usr_username <> '') UNIQUE,
     usr_fullname   varchar(40) NOT NULL CHECK (usr_fullname <> '') NOT NULL,
     usr_password   varchar(80) NOT NULL CHECK (usr_password <> '') NOT NULL,
     usr_creation_date timestamp,
     PRIMARY KEY(usr_id)
);