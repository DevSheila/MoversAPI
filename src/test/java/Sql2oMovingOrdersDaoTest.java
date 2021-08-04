import models.dao.Sql2oMovingOrdersDao;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oMovingOrdersDaoTest {
    private static Connection conn;
    private static Sql2oMovingOrdersDao movingOrdersDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/moving_orders";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", null);

        Sql2oMovingOrdersDao movingOrdersDao = new Sql2oMovingOrdersDao(sql2o);
        conn = sql2o.open();
    }


}
