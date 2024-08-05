
CREATE TABLE IF NOT EXISTS public.blogs_comments
(
    blog_id uuid NOT NULL,
    comments_id uuid NOT NULL,
    CONSTRAINT ukn89jpa0u28fxbkcwl0i8tl0o2 UNIQUE (comments_id)
)


CREATE TABLE IF NOT EXISTS public.comment
(
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    content text COLLATE pg_catalog."default",
    author_id uuid,
    blog_id uuid,
    CONSTRAINT comment_pkey PRIMARY KEY (id),
    CONSTRAINT fkir20vhrx08eh4itgpbfxip0s1 FOREIGN KEY (author_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkjgmhrwdxoypncmjnn5hw5ei03 FOREIGN KEY (blog_id)
        REFERENCES public.blogs (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.blogs
(
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    content text COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    author_id uuid,
    CONSTRAINT blogs_pkey PRIMARY KEY (id),
    CONSTRAINT fkt8g0udj2fq40771g38t2t011n FOREIGN KEY (author_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.users
(
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT users_role_check CHECK (role::text = ANY (ARRAY['PUBLISHER'::character varying, 'COMMENTER'::character varying]::text[]))
)