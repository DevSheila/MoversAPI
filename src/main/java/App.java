import exceptions.*;
import com.google.gson.Gson;
import models.MovingOrders;
import models.dao.Sql2oMovingOrdersDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.after;

public class App {
    static int getHerokuAssignedPort(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null){
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }else{
            return 4567; //return default port
        }
    }
    public static void main(String[] args) {
        Connection conn;
        Gson gson = new Gson();

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

//        String connectionString = "jdbc:postgresql://localhost:5432/movers_api";
//        Sql2o sql2o = new Sql2o(connectionString, "postgres", "wildlife");

        String connectionString = "jdbc:postgresql://ec2-54-173-138-144.compute-1.amazonaws.com:5432/dddmu9votdb2rq"; //
        Sql2o sql2o = new Sql2o(connectionString,"ipsoepahlqfvwa","19b4136cef2db8ebe2b2735a8ff8583bc22d92db57d784a0be2ed6a0f1ae216e");

        Sql2oMovingOrdersDao movingOrdersDao = new Sql2oMovingOrdersDao(sql2o);

        get("/", (req, res) -> "It's working!");

        //CREATE
        post("/movingorders/new", "application/json", (req, res) -> {
            MovingOrders movingOrder = gson.fromJson(req.body(), MovingOrders.class);
            movingOrdersDao.add(movingOrder);
            res.status(201);
            return gson.toJson(movingOrder);
        });

        //READ
        get("/movingorders/:id", "application/json", (req, res) -> {
            int movingOrderId = Integer.parseInt(req.params("id"));
            MovingOrders movingOrderToFind = movingOrdersDao.findById(movingOrderId);
            if (movingOrderToFind == null) {
                throw new ApiException(404, String.format("No moving order with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(movingOrderToFind);
        });

        get("/movingorders/user/:userName", "application/json", (req, res) -> {
            String userName = req.params("userName");
            List<MovingOrders> movingOrdersByUsername=movingOrdersDao.getMovingOrderByUserName(userName);

            if ( movingOrdersByUsername== null) {
                throw new ApiException(404, String.format("No moving order with the user name: \"%s\" exists", req.params("userName")));
            }else if(movingOrdersByUsername.size() ==0){
                throw new ApiException(404, String.format("No moving order with the user name: \"%s\" exists", req.params("userName")));
            }else {
                return gson.toJson(movingOrdersByUsername);
            }

        });

        get("/movingorders/company/:movingCompany", "application/json", (req, res) -> {
            String movingCompany = req.params("movingCompany");
            List<MovingOrders> movingOrdersByCompany=movingOrdersDao.getMovingOrderByMovingCompany(movingCompany);

            if (  movingOrdersByCompany== null) {
                throw new ApiException(404, String.format("No moving order with the company name: \"%s\" exists", req.params("movingCompany")));
            }else if(movingOrdersByCompany.size() == 0){
                throw new ApiException(404, String.format("No moving order with the company name: \"%s\" exists", req.params("movingCompany")));
            }else{
                return gson.toJson( movingOrdersByCompany);

            }

        });

        //UPDATE
        put("/movingorders/update/:id/:status", "application/json", (req, res) -> {
            int movingOrderId = Integer.parseInt(req.params("id"));
            String movingOrderStatus = req.params("status");

            MovingOrders movingOrder = gson.fromJson(req.body(), MovingOrders.class);
            movingOrdersDao.update(movingOrderId,movingOrderStatus);
            res.status(201);
            return gson.toJson(movingOrder);
        });



        //DELETE
        get("/movingorders/:id/delete", "application/json", (req, res) -> {
            int movingOrderId = Integer.parseInt(req.params("id"));
            MovingOrders movingOrderToFind = movingOrdersDao.findById(movingOrderId);
            movingOrdersDao.deleteMovingOrderById(movingOrderId);

            if (movingOrderToFind == null) {
                throw new ApiException(404, String.format("No moving order with the id: \"%s\" exists", req.params("id")));
            }else{
                return "{\"message\":\"Order cancelled .\"}";
            }

        });

        //FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", exception.getStatusCode());
            jsonMap.put("errorMessage", exception.getMessage());
            res.type("application/json");
            res.status(exception.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });


        after((req, res) -> res.type("application/json"));
    }
    }




