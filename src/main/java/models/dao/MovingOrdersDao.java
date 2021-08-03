package models.dao;

import models.MovingOrders;

import java.util.List;

public interface MovingOrdersDao {

    void add(MovingOrders movingOrder);
    List<MovingOrders> getMovingOrderByMovingCompany(String moving_company);
    List<MovingOrders> getMovingOrderByUserName(String userName);

}
