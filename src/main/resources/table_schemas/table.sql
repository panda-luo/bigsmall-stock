create schema money_monster collate utf8mb4_general_ci;

create table stock
(
  code char(6) null,
  current varchar(50) null,
  quote_change_rate varchar(50) null,
  quote_change varchar(50) null,
  trading_volume varchar(50) null,
  turnover bigint null,
  amplitude varchar(50) null,
  turnover_rate varchar(50) null,
  pe varchar(50) null,
  volume_radio_index varchar(50) null,
  high varchar(50) null,
  low varchar(50) null,
  open varchar(50) null,
  pre_close varchar(50) null,
  market_value bigint null,
  circulated_value bigint null,
  pb varchar(50) null,
  listed_date date null,
  industry varchar(50) null,
  region varchar(50) null,
  tag text null,
  create_time timestamp null,
  creator varchar(50) null,
  update_time timestamp null,
  updater varchar(50) null,
  constraint stock_pk
    unique (code)
);

create table stock_ea_field
(
  field smallint(3) null,
  name varchar(50) null,
  memo varchar(50) null
);

create table stock_simple
(
  code char(6) null,
  name varchar(50) null
);

create table stock_trend
(
  id bigint null,
  code char(6) null,
  trend text null,
  date date null,
  constraint stock_trend_pk
    unique (id)
);

