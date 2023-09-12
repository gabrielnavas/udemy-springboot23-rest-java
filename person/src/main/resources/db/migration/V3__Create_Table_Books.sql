CREATE TABLE IF NOT EXISTS  public.tb_book (
    id UUID,
    title character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    launch_date timestamp(6) NOT NULL,
    price numeric(9, 2) NOT NULL,
    CONSTRAINT tb_book_pk_id PRIMARY KEY (id),
    CONSTRAINT tb_book_unique_title unique (title)
);