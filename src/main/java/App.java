import com.google.gson.Gson;
import exceptions.ApiException;
import models.MovingOrders;
import models.dao.Sql2oMovingOrdersDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.after;

public class App {
    public static void main(String[] args) {
        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/movers_api";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", null);

        Sql2oMovingOrdersDao movingOrdersDao = new Sql2oMovingOrdersDao(sql2o);

        //CREATE
        post("/movingorders/new", "application/json", (req, res) -> {
            MovingOrders movingOrder = gson.fromJson(req.body(), MovingOrders.class);
            movingOrdersDao.add(movingOrder);
            res.status(201);
            return gson.toJson(movingOrder);
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


        after((req, res) ->{
            res.type("application/json");
        });
    }
    }




