package models.dao;

import models.MoverBio;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMoverBioDao implements MoverBioDao {
    private final Sql2o sql2o;

    public Sql2oMoverBioDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(MoverBio moverBio) {

        String sql = "INSERT INTO moverBio(name,inventory_charges,charge_per_distance ,contacts,extra_Services) VALUES (:name,:inventory_charges,:charge_per_distance,:contacts,:extra_Services)";

        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(moverBio)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<MoverBio> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT *FROM moverBio")
                    .executeAndFetch(MoverBio.class);
        }
    }

    @Override
    public void update(String newName, String newExtra_Services, int newContacts, int newInventory_charges, int newCharge_per_distance) {
        String sql = "UPDATE moverBio SET(name,extra_Services,contacts,inventory_charges,charge_per_distance) = (:name, :extra_services,:charge_per_distance,:inventory_charges,:contacts)";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name",newName)
                    .addParameter("extra_Services",newExtra_Services)
                    .addParameter("contacts",newContacts)
                    .addParameter("inventory_charges",newInventory_charges)
                    .addParameter("charge_per_distance",newCharge_per_distance)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }
}



