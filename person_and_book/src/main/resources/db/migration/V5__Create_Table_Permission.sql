CREATE TABLE IF NOT EXISTS permission (
    id uuid,
    description varchar(255) not null,
    CONSTRAINT tb_permission_pk_id PRIMARY KEY (id),
    CONSTRAINT tb_permission_unique_description UNIQUE (description)
);