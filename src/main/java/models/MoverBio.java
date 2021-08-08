package models;

import java.util.Objects;

public class MoverBio {
    private int id;
    private String extra_Services;
    private String name;
    private int contacts;
    private int inventory_charges;
    private int charge_per_distance;


    public MoverBio(String extra_Services, String name,int contacts,int inventory_charges,int charge_per_distance ){
        this.extra_Services = extra_Services;
        this.charge_per_distance = charge_per_distance;
        this.name = name;
        this.inventory_charges =inventory_charges;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtra_Services() {
        return extra_Services;
    }

    public void setExtra_Services(String extra_Services) {
        this.extra_Services = extra_Services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContacts() {
        return contacts;
    }

    public void setContacts(int contacts) {
        this.contacts = contacts;
    }

    public int getInventory_charges() {
        return inventory_charges;
    }

    public void setInventory_charges(int inventory_charges) {
        this.inventory_charges = inventory_charges;
    }

    public int getCharge_per_distance() {
        return charge_per_distance;
    }

    public void setCharge_per_distance(int charge_per_distance) {
        this.charge_per_distance = charge_per_distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoverBio)) return false;
        MoverBio moverBio = (MoverBio) o;
        return contacts == moverBio.contacts &&
                inventory_charges == moverBio.inventory_charges &&
                charge_per_distance == moverBio.charge_per_distance &&
                Objects.equals(extra_Services, moverBio.extra_Services) &&
                Objects.equals(name, moverBio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extra_Services, name, contacts, inventory_charges, charge_per_distance);
    }
}
