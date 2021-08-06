import models.MovingOrders;
import models.dao.Sql2oMovingOrdersDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oMovingOrdersDaoTest {
    private static Connection conn;
    private static    Sql2oMovingOrdersDao movingOrdersDao;
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/movers_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", null);
         movingOrdersDao = new Sql2oMovingOrdersDao(sql2o);


        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        movingOrdersDao.clearAll();
        System.out.println("clearing database");
    }


    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingMovingOrderSetsId() throws Exception {
        MovingOrders testOrder =new MovingOrders("Johnny","johnnytrejo@gmail.com","bedsitter","Diani,Sunrirse Apartments","Diani,oceanview apartments","Feather Hands Movers",5000,"approved","15:00pm Monday 3 August 2021");
        int originalOrderId = testOrder.getId();
        movingOrdersDao.add(testOrder);
        assertNotEquals(originalOrderId,testOrder.getId());
    }

    @Test
    public void addedMovingOrdersAreReturnedFromGetAll() throws Exception {
       MovingOrders testOrder = setupMovingOrders();
        movingOrdersDao.add(testOrder);
        assertEquals(1, movingOrdersDao.all().size());
    }

    @Test
    public void deleteByIdDeletesCorrectMovingOrder() throws Exception {
        MovingOrders testOrder = setupMovingOrders();
        movingOrdersDao.add(testOrder);
       movingOrdersDao.deleteMovingOrderById(testOrder.getId());
        assertEquals(0, movingOrdersDao.all().size());
    }
    @Test
    public void clearAll() throws Exception {
        MovingOrders testOrder =setupMovingOrders();
        movingOrdersDao.add(testOrder);
        movingOrdersDao.clearAll();
        assertEquals(0, movingOrdersDao.all().size());
    }

    public MovingOrders setupMovingOrders(){
        return new MovingOrders("Johnny","johnnytrejo@gmail.com","bedsitter","Diani,Sunrirse Apartments","Diani,oceanview apartments","Feather Hands Movers",5000,"approved","15:00pm Monday 3 August 2021");

    }


}
