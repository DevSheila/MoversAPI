# MoversAPI

A REST API for querying and retrieving moving orders by users.

# Setup Instructions
Clone the repo 
```shell
git@github.com:DevSheila/MoversAPI.git
```
Switch to the directory
```shell
cd MoversApi
```
Install the dependencies
```shell
build gradle
```
Run the app
```shell
gradle run
```

# Database Setup
1. In the terminal
##### To create the database
```shell
psql < create.sql
```
#### To drop the database
```shell
psql < drop.sql
```

2. In PSQL
#### Create the database
* CREATE DATABASE movers_api;
#### Connect to the database
* \c movers_api
#### Create the required tables
* CREATE TABLE moving_orders(id SERIAL PRIMARY KEY,user_name VARCHAR,user_email VARCHAR,inventory VARCHAR,current_location VARCHAR,new_location VARCHAR,moving_company VARCHAR,total_price int,\c movers_apiorder_status VARCHAR,\c movers_apipickup_time  VARCHAR\c movers_api);
#### Create a template
* CREATE DATABASE movers_api_test WITH TEMPLATE movers_api;

# Postman
### Routes
| Method | Route |
|--------|------ |
|POST    |/movingorders/new|
|GET     |/movingorders/:id|
|GET     |/movingorders/user/:userName|
|GET    |/movingorders/company/:movingCompany|
|GET     |/movingorders/:id/delete|

# Live link
[Live Link]()

# License

MIT