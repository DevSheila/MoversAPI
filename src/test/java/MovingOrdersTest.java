import models.MovingOrders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovingOrdersTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public MovingOrders setupMovingOrders (){
        return new MovingOrders("Johnny","johnnytrejo@gmail.com","bedsitter","Diani,Sunrirse Apartments","Diani,oceanview apartments","Feather Hands Movers",5000,"approved","15:00pm Monday 3 August 2021");

    }
    @Test
    public void getUserNameReturnsCorrectName() throws Exception {
        MovingOrders testDepartment = setupMovingOrders();
        assertEquals("Johnny", testDepartment.getUser_name());
    }
    @Test
    public void getUserEmailReturnsCorrectEmail() throws Exception {
        MovingOrders testDepartment = setupMovingOrders();
        assertEquals("johnnytrejo@gmail.com", testDepartment.getUser_email());
    }
    @Test
    public void getInventoryReturnsCorrectInventory() throws Exception {
        MovingOrders testDepartment = setupMovingOrders();
        assertEquals("bedsitter", testDepartment.getInventory());
    }
    @Test
    public void getCurrentLocationReturnsCorrectCurrentLocation() throws Exception {
        MovingOrders testDepartment = setupMovingOrders();
        assertEquals("Diani,Sunrirse Apartments", testDepartment.getCurrent_location());
    }

}
