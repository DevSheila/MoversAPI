package models;

import java.util.Objects;

public class MovingOrders {

    private String user_name;
    private String user_email;
    private String current_location;
    private String new_location;
    private String moving_company;
    private int total_price;
    private String order_status;
    private String pickup_time;

    public MovingOrders(String user_name, String user_email, String current_location, String new_location, String moving_company, int total_price, String order_status, String pickup_time) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.current_location = current_location;
        this.new_location = new_location;
        this.moving_company = moving_company;
        this.total_price = total_price;
        this.order_status = order_status;
        this.pickup_time = pickup_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(String current_location) {
        this.current_location = current_location;
    }

    public String getNew_location() {
        return new_location;
    }

    public void setNew_location(String new_location) {
        this.new_location = new_location;
    }

    public String getMoving_company() {
        return moving_company;
    }

    public void setMoving_company(String moving_company) {
        this.moving_company = moving_company;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovingOrders)) return false;
        MovingOrders that = (MovingOrders) o;
        return getTotal_price() == that.getTotal_price() && Objects.equals(getUser_name(), that.getUser_name()) && Objects.equals(getUser_email(), that.getUser_email()) && Objects.equals(getCurrent_location(), that.getCurrent_location()) && Objects.equals(getNew_location(), that.getNew_location()) && Objects.equals(getMoving_company(), that.getMoving_company()) && Objects.equals(getOrder_status(), that.getOrder_status()) && Objects.equals(getPickup_time(), that.getPickup_time());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_name(), getUser_email(), getCurrent_location(), getNew_location(), getMoving_company(), getTotal_price(), getOrder_status(), getPickup_time());
    }
}
