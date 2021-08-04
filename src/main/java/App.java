import com.google.gson.Gson;
import models.MovingOrders;
import models.dao.Sql2oMovingOrdersDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/movers_api";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "wildlife");

        Sql2oMovingOrdersDao movingOrdersDao = new Sql2oMovingOrdersDao(sql2o);

        //CREATE
        post("/movingorders/new", "application/json", (req, res) -> {
            MovingOrders movingOrder = gson.fromJson(req.body(), MovingOrders.class);
            movingOrdersDao.add(movingOrder);
            res.status(201);
            return gson.toJson(movingOrder);
        });

        //GET BY ID
        get("/movingorders/:id","application/json",(request, response) -> {
            int movingOrder = Integer.parseInt(request.params("id"));
            return gson.toJson(movingOrdersDao.findById(movingOrder));
        });

    }



}
