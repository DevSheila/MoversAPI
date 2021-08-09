//import models.MovingOrders;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class MovingOrdersTest {
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    public MovingOrders setupMovingOrders (){
//        return new MovingOrders("Johnny","johnnytrejo@gmail.com","bedsitter","Diani,Sunrirse Apartments","Diani,oceanview apartments","Feather Hands Movers",5000,"approved","15:00pm Monday 3 August 2021");
//
//    }
//    @Test
//    public void getUserNameReturnsCorrectName() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals("Johnny", testOrder.getUser_name());
//    }
//    @Test
//    public void getUserEmailReturnsCorrectEmail() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals("johnnytrejo@gmail.com", testOrder.getUser_email());
//    }
//    @Test
//    public void getInventoryReturnsCorrectInventory() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals("bedsitter", testOrder.getInventory());
//    }
//    @Test
//    public void getCurrentLocationReturnsCorrectCurrentLocation() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals("Diani,Sunrirse Apartments", testOrder.getCurrent_location());
//    }
//
//
//    @Test
//    public void getNewLocationReturnsCorrectNewLocation() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals("Diani,oceanview apartments", testOrder.getNew_location());
//    }
//
//    @Test
//    public void getMovingCompanyReturnsCorrectMovingCompany() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals("Feather Hands Movers", testOrder.getMoving_company());
//
//    }
//
//
//    @Test
//    public void getTotalPriceReturnsCorrectTotalPrice() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals(5000, testOrder.getTotal_price());
//    }
//
//    @Test
//    public void getOrderStatusReturnsCorrectOrderStatus() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals("approved", testOrder.getOrder_status());
//    }
//
//    @Test
//    public void getPickUpTimeReturnsCorrectPickUpTime() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        assertEquals("15:00pm Monday 3 August 2021", testOrder.getPickup_time());
//    }
//
//    @Test
//    public void setUserNameSetsCorrectUserName() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        testOrder.setUser_name("Bree");
//        assertEquals("Bree", testOrder.getUser_name());
//    }
//
//    @Test
//    public void setUserEmailSetsCorrectUserEmail() throws Exception {
//        MovingOrders testOrder = setupMovingOrders();
//        testOrder.setUser_email("Bree@gmail.com");
//        assertEquals("Bree@gmail.com", testOrder.getUser_email());
//    }
//    }
