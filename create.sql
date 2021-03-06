SET MODE PostgreSQL;

CREATE DATABASE movers_api;
\c movers_api

CREATE TABLE moving_orders
(
    id SERIAL PRIMARY KEY,
    user_name VARCHAR,
    user_email VARCHAR,
    inventory VARCHAR,
    current_location VARCHAR,
    new_location VARCHAR,
    moving_company VARCHAR,
    total_price int,
    order_status VARCHAR,
    pickup_time  VARCHAR
);

CREATE TABLE moving_movers_bios
(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    extra_Services VARCHAR,
    contacts int,
    inventory_charges int,
    charge_per_distance int
);

CREATE DATABASE movers_api_test WITH TEMPLATE movers_api;
