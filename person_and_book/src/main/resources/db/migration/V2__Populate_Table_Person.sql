INSERT INTO
	tb_person (id, first_name, last_name, username, email, birthday, "password", created_at, updated_at)
VALUES
	(gen_random_uuid (), 'João', 'Silva', 'João Silva', 'joaosilva@email.com', now(), '$2a$10$V6ghdkSCsCajiQJGDDhsIeHnYG9SHGWDRKYOESFEM1J8wotJCp.Oi', now(), now()),
	(gen_random_uuid (), 'Maria', 'Silva', 'Maria Silva', 'mariasilva@email.com', now(), '$2a$10$V6ghdkSCsCajiQJGDDhsIeHnYG9SHGWDRKYOESFEM1J8wotJCp.Oi', now(), now()),
	(gen_random_uuid (), 'Julia', 'Farias', 'Julia Farias', 'juliafarias@email.com', now(), '$2a$10$V6ghdkSCsCajiQJGDDhsIeHnYG9SHGWDRKYOESFEM1J8wotJCp.Oi', now(), now()),
	(gen_random_uuid (), 'Luiza', 'Fatima', 'Luiza Fatima', 'luizafatima@email.com', now(), '$2a$10$V6ghdkSCsCajiQJGDDhsIeHnYG9SHGWDRKYOESFEM1J8wotJCp.Oi', now(), now());

