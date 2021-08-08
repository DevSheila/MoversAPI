package models.dao;

import models.MovingOrders;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import java.util.List;
import java.util.Locale;

public class Sql2oMovingOrdersDao implements MovingOrdersDao {
    private final Sql2o sql2o;
    public Sql2oMovingOrdersDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(MovingOrders movingOrder) {
        String sql = "INSERT INTO moving_orders(user_name,user_email,inventory,current_location,new_location,moving_company,total_price,order_status,pickup_time) " +
                "VALUES (:user_name,:user_email,:inventory,:current_location,:new_location,:moving_company,:total_price,:order_status,:pickup_time)";

        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(movingOrder)
                    .executeUpdate()
                    .getKey();
            movingOrder.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<MovingOrders> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM moving_orders")
                    .executeAndFetch(MovingOrders.class);
        }
    }


    @Override
    public List<MovingOrders> getMovingOrderByMovingCompany(String moving_company) {
        List<MovingOrders> companyMovingOrders ;

        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM moving_orders WHERE moving_company = :moving_company")
                    .addParameter("moving_company", moving_company)
                    .executeAndFetch(MovingOrders.class);
        }

    }

    @Override
    public List<MovingOrders> getMovingOrderByUserName(String userName) {
        List<MovingOrders> userMovingOrders;

        try (Connection con = sql2o.open()) {
//            String query = "SELECT * FROM items WHERE LOWER(user_name) LIKE '"+userName.toLowerCase(Locale.ROOT)+"%' OR LOWER(user_name) =LOWER(:userName) ";

           return con.createQuery("SELECT * FROM moving_orders WHERE user_name = :userName")
                    .addParameter("userName", userName)
                    .executeAndFetch(MovingOrders.class);
        }


    }

    @Override
    public MovingOrders findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM moving_orders WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(MovingOrders.class);
        }
    }

    public void update(int id, String status) {

        try(Connection con = sql2o.open()){
            String sql = "UPDATE moving_orders SET order_status= :status WHERE id= :id";
            con.createQuery(sql,true)
                    .addParameter("id", id)
                    .addParameter("status",status)
                    .executeUpdate();
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

    public void clearAll() {
        String sql = "DELETE from moving_orders";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
