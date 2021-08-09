import exceptions.*;
import com.google.gson.Gson;
import models.MoverBio;
import models.MovingOrders;
import models.dao.MoverBioDao;
import models.dao.Sql2oMoverBioDao;
import models.dao.Sql2oMovingOrdersDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import static spark.Spark.after;

public class App {
    static int getHerokuAssignedPort(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null){
            return parseInt(processBuilder.environment().get("PORT"));
        }else{
            return 4567; //return default port
        }
    }
    public static void main(String[] args) {
        Connection conn;
        Gson gson = new Gson();

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/movers_api";
        //change the postgres password to your default password
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "wildlife");

//        String connectionString = "jdbc:postgresql://ec2-54-173-138-144.compute-1.amazonaws.com:5432/dddmu9votdb2rq"; //
//        Sql2o sql2o = new Sql2o(connectionString,"ipsoepahlqfvwa","19b4136cef2db8ebe2b2735a8ff8583bc22d92db57d784a0be2ed6a0f1ae216e");

        Sql2oMovingOrdersDao movingOrdersDao = new Sql2oMovingOrdersDao(sql2o);
        Sql2oMoverBioDao moverBioDao = new Sql2oMoverBioDao(sql2o);

        get("/", (req, res) -> "It's working!");

        //get all orders
        get("/api/movingorders","application/json",(request, response) -> {//accepts a request in format JSON
            if (movingOrdersDao.getAll().size() > 0){
                return gson.toJson(movingOrdersDao.getAll());
            }else {
                return "{\"message\":\"Sorry, no movingorders are currently listed in the database\"}";
            }
        });

        //get all moversbio
        get("/api/moverbio","application/json",(request, response) -> {//accepts a request in format JSON
            if (moverBioDao.getAll().size() > 0){
                return gson.toJson(moverBioDao.getAll());
            }else {
                return "{\"message\":\"Sorry, no moverbio are currently listed in the database\"}";
            }
        });

        //CREATE
        post("/api/movingorders/new", "application/json", (req, res) -> {
            MovingOrders movingOrder = gson.fromJson(req.body(), MovingOrders.class);
            movingOrdersDao.add(movingOrder);
            res.status(201);
            return gson.toJson(movingOrder);
        });

        //create new movers bio
        post("/api/moverbio/new","application/json",(request, response) -> {
            MoverBio moverBio = gson.fromJson(request.body(),MoverBio.class);
            moverBioDao.add(moverBio);
            response.status(201);
            return gson.toJson(moverBio);
        });

        //READ
        get("/api/movingorders/:id", "application/json", (req, res) -> {
            int movingOrderId = parseInt(req.params("id"));
            MovingOrders movingOrderToFind = movingOrdersDao.findById(movingOrderId);
            if (movingOrderToFind == null) {
                throw new ApiException(404, String.format("No moving order with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(movingOrderToFind);
        });

        //get movers bio by id
        get("/api/moverbio/:id","application/json",(request, response) -> {
            int moverBioId = parseInt(request.params("id"));
            MoverBio moverBioToFindId = moverBioDao.findById(moverBioId);
            if (moverBioToFindId == null){
                throw new ApiException(404,String.format("No mover bio with the id; \"%s\" exists",request.params("id")));
            }
            return gson.toJson(moverBioToFindId);
        });

        get("/api/movingorders/user/:userName", "application/json", (req, res) -> {
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

        //get mover bio by name
        get("/api/moverbio/mover/:moverName","application.json",(request, response) -> {
            String moverName = request.params("moverName");
            List<MoverBio> moverBioByName = moverBioDao.getMoverByName(moverName);
            if (moverBioByName == null ){
                throw new ApiException(404,String.format("No moverbio with the name \"%s\" exists",request.params("moverName")));
            }else if (moverBioByName.size() == 0){
                throw new ApiException(404,String.format("No moverbio with the name \"%s\" exists",request.params("moverName")));
            }else{
                return gson.toJson(moverBioByName);
            }
        });

        get("/api/movingorders/company/:movingCompany", "application/json", (req, res) -> {
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
        put("/api/movingorders/update/:id/:status", "application/json", (req, res) -> {
            int movingOrderId = parseInt(req.params("id"));
            String movingOrderStatus = req.params("status");

            MovingOrders movingOrder = gson.fromJson(req.body(), MovingOrders.class);
            movingOrdersDao.update(movingOrderId,movingOrderStatus);
            res.status(201);
            return gson.toJson(movingOrder);
        });

        //update movers bio
        put("/api/moverbio/update/:id/:name/:inventory_charges/:charge_per_distance/:contacts/:extra_Services","application/json",(request, response) -> {
            int moverBioId = parseInt(request.params("id"));
            String moverName = request.params("name");
            int moverInventoryCharges = parseInt(request.params("inventory_charges"));
            int moverChargePerDistance = parseInt(request.params("charge_per_distance"));
            int moverContacts = parseInt(request.params("contacts"));
            String moverExtraServices = request.params("extra_Services");
            MoverBio moverBio = gson.fromJson(request.body(),MoverBio.class);
            moverBioDao.update(moverBioId,moverName,moverExtraServices,moverContacts,moverInventoryCharges,moverChargePerDistance);
            response.status(201);
            if (moverBioId != 0) {
                throw new ApiException(404, String.format("No moving order with the company name: \"%s\" exists", moverName,moverExtraServices,moverInventoryCharges,moverChargePerDistance,moverContacts,moverExtraServices));
            }
            return gson.toJson(moverBio);
        });




        //DELETE
        delete("/api/movingorders/:id", "application/json", (req, res) -> {
            int movingOrderId = parseInt(req.params("id"));
            MovingOrders movingOrderToFind = movingOrdersDao.findById(movingOrderId);
            movingOrdersDao.deleteMovingOrderById(movingOrderId);

            if (movingOrderToFind == null) {
                throw new ApiException(404, String.format("No moving order with the id: \"%s\" exists", req.params("id")));
            }else{
                return "{\"message\":\"Order cancelled .\"}";
            }

        });

       //delete mover by id
        delete("/api/moverbio/:id","application/json",(request, response) -> {
            int moverBioId = parseInt(request.params("id"));
            MoverBio moverBioToFind = moverBioDao.findById(moverBioId);
            moverBioDao.deleteMoverById(moverBioId);
            //check if id exists
            if (moverBioToFind == null){
                throw new ApiException(404,String.format("No moverbio with the id: \"%s\" exists",request.params("id")));
            }else {
                return "{\"message\":\"Moverbio deleted\"}";
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




