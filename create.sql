SET MODE PostgreSQL;

CREATE DATABASE movers_api;
\c movers_api

CREATE TABLE moving_orders
(
    id               SERIAL PRIMARY KEY,
    user_name        VARCHAR,
    user_email       VARCHAR,
    current_location VARCHAR,
    moving_company  VARCHAR,
    total_price      int,
    order_status     VARCHAR,
    timing           VARCHAR
);

CREATE DATABASE moving_api_test WITH TEMPLATE moving_api;


)
