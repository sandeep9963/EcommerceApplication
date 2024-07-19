create table if not exists category
(
  id Integer not null primary key,
  description varchar(255),
  name varchar(255)
);

create table if not exists product
(
   id Integer not null primary key,
   description varchar(255),
   name varchar(255),
   available_quantity double precision not null,
   price numeric(38, 2),
   category_id Integer
          contraint fk_product_category reference category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;