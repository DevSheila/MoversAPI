package models.dao;

import models.MovingOrders;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import java.util.List;

public class Sql2oMovingOrdersDao implements MovingOrdersDao {
    private final Sql2o sql2o;
    public Sql2oMovingOrdersDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(MovingOrders movingOrder) {
        String sql = "INSERT INTO moving_orders(user_name,user_email,inventory,current_location,new_location,moving_company,total_price,order_status,pickup_time) VALUES (:user_name,:user_email,:inventory,:current_location,:new_location,:moving_company,:total_price,:order_status,:pickup_time)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(movingOrder)
//                    .addParameter("user_name", movingOrder.getUser_name())
//                    .addParameter("user_email", movingOrder.getUser_email())
//                    .addParameter("inventory",movingOrder.getInventory())
//                    .addParameter("current_location",movingOrder.getCurrent_location())
//                    .addParameter("new_location",movingOrder.getNew_location())
//                    .addParameter("moving_company",movingOrder.getMoving_company())
//                    .addParameter("total_price",movingOrder.getTotal_price())
//                    .addParameter("order_status",movingOrder.getOrder_status())
//                    .addParameter("pickup_time",movingOrder.getPickup_time())

                    .executeUpdate()
                    .getKey();
            movingOrder.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<MovingOrders> getMovingOrderByMovingCompany(String moving_company) {
        List<MovingOrders> companyMovingOrders ;

        try (Connection con = sql2o.open()) {
            companyMovingOrders = con.createQuery("SELECT * FROM moving_orders WHERE moving_company = :moving_company")
                    .addParameter("moving_company", moving_company)
                    .executeAndFetch(MovingOrders.class);
        }
        return companyMovingOrders;
    }

    @Override
    public List<MovingOrders> getMovingOrderByUserName(String userName) {
        List<MovingOrders> userMovingOrders;

        try (Connection con = sql2o.open()) {
            userMovingOrders = con.createQuery("SELECT * FROM moving_orders WHERE userName = :userName")
                    .addParameter("userName", userName)
                    .executeAndFetch(MovingOrders.class);
        }
        return userMovingOrders;

    }

    @Override
    public MovingOrders findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM moving_orders WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(MovingOrders.class);
        }
    }

    @Override
    public void deleteMovingOrderById(int id) {
        String sql = "DELETE from moving_orders WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
