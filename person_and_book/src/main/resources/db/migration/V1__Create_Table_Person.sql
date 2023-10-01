CREATE TABLE IF NOT EXISTS public.person (
	id uuid NOT NULL,
	created_at timestamp(6) NOT NULL,
	email character varying(255) NOT NULL,
	first_name character varying(50) NOT NULL,
	last_name character varying(20) NOT NULL,
	password character varying(100) NOT NULL,
	updated_at timestamp(6) NOT NULL,
	username character varying(20) NOT NULL,
	birthday timestamp(6),
	CONSTRAINT tb_person_pk_id PRIMARY KEY (id),
	CONSTRAINT tb_person_unique_email UNIQUE (email),
	CONSTRAINT tb_person_unique_username UNIQUE (username)
);
