package models.dao;

import models.MovingOrders;

import java.util.List;

public interface MovingOrdersDao {

    void add(MovingOrders movingOrder);
    List<MovingOrders> getAll();
    List<MovingOrders> getMovingOrderByMovingCompany(String moving_company);
    List<MovingOrders> getMovingOrderByUserName(String userName);

    MovingOrders findById(int id);
    void deleteMovingOrderById(int id);

}