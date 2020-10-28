create sequence seq_base_user;

create table base_user (
   id bigint primary key,
   username character varying(100) NOT NULL unique,
   password character varying(255) NOT NULL
);

create unique index on base_user (username);

create sequence seq_product;
create table product (
   id bigint primary key,
   title character varying(100) NOT NULL,
   url character varying(100) NOT NULL unique,
   description character varying(255) NOT NULL,
   image character varying(50)
);


create unique index on product (url);