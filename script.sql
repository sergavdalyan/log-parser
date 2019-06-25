create schema if not exists parser collate utf8mb4_0900_ai_ci;

create table if not exists blocked_ip
(
  id char(36) not null PRIMARY KEY,
  ip varchar(50) null,
  comment varchar(250) null,
  constraint blocked_ip_id_uindex
    unique (id)
);

create table if not exists log
(
  id char(36) not null PRIMARY KEY,
  date timestamp null,
  request varchar(250) null,
  ip varchar(50) null,
  status int null,
  user_agent varchar(250) null,
  constraint log_id_uindex
    unique (id)
);
