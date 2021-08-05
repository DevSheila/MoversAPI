import com.google.gson.Gson;
import exceptions.ApiException;
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
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

        public static void main(String[]args) {
            Connection conn;
            Gson gson = new Gson();

            staticFileLocation("/public");

//            String connectionString = "jdbc:postgresql://localhost:5432/movers_api";
            String connectionString = "jdbc:postgresql://ec2-3-233-100-43.compute-1.amazonaws.com:5432/d2i79ahn66us9d";
//            Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");
            Sql2o sql2o = new Sql2o(connectionString, "bpexshqdqrrbsb","1ffebad8a419f253c87fc6590b625b6abb03e8d2faa9a79de26650134a598268");

            Sql2oMovingOrdersDao movingOrdersDao = new Sql2oMovingOrdersDao(sql2o);

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
                List<MovingOrders> movingOrdersByUsername = movingOrdersDao.getMovingOrderByUserName(userName);

                if (movingOrdersByUsername == null) {
                    throw new ApiException(404, String.format("No moving order with the user name: \"%s\" exists", req.params("userName")));
                } else if (movingOrdersByUsername.size() == 0) {
                    throw new ApiException(404, String.format("No moving order with the user name: \"%s\" exists", req.params("userName")));
                } else {
                    return gson.toJson(movingOrdersByUsername);
                }

            });

            get("/movingorders/company/:movingCompany", "application/json", (req, res) -> {
                String movingCompany = req.params("movingCompany");
                List<MovingOrders> movingOrdersByCompany = movingOrdersDao.getMovingOrderByMovingCompany(movingCompany);

                if (movingOrdersByCompany == null) {
                    throw new ApiException(404, String.format("No moving order with the company name: \"%s\" exists", req.params("movingCompany")));
                } else if (movingOrdersByCompany.size() == 0) {
                    throw new ApiException(404, String.format("No moving order with the company name: \"%s\" exists", req.params("movingCompany")));
                } else {
                    return gson.toJson(movingOrdersByCompany);

                }

            });

            //UPDATE
            put("/movingorders/update/:id/:status", "application/json", (req, res) -> {
                int movingOrderId = Integer.parseInt(req.params("id"));
                String movingOrderStatus = req.params("status");

                MovingOrders movingOrder = gson.fromJson(req.body(), MovingOrders.class);
                movingOrdersDao.update(movingOrderId, movingOrderStatus);
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
                } else {
                    return "{\"message\":\"Order cancelled .\"}";
                }

            });

            //FILTERS
            exception(ApiException.class, (exception, req, res) -> {
                ApiException err = exception;
                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put("status", err.getStatusCode());
                jsonMap.put("errorMessage", err.getMessage());
                res.type("application/json");
                res.status(err.getStatusCode());
                res.body(gson.toJson(jsonMap));
            });


            after((req, res) -> {
                res.type("application/json");
            });
        }
    }






