package models.dao;

import models.MoverBio;
import models.MovingOrders;
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

        String sql = "INSERT INTO moving_movers_bios(name,inventory_charges,charge_per_distance ,contacts,extra_Services) VALUES (:name,:inventory_charges,:charge_per_distance,:contacts,:extra_Services)";

        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(moverBio)
                    .executeUpdate()
                    .getKey();
            moverBio.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<MoverBio> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT *FROM moving_movers_bios")
                    .executeAndFetch(MoverBio.class);
        }
    }

    @Override
    public List<MoverBio> getMoverByName(String moverName) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM moving_movers_bios WHERE name = :moverName")
                    .addParameter("moverName",moverName )
                    .executeAndFetch(MoverBio.class);
        }
    }

    @Override
    public MoverBio findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM moving_movers_bios WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(MoverBio.class);
        }
    }

    @Override
    public void deleteMoverById(int id) {
        String sql = "DELETE from moving_movers_bios WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void update(int id, String newName, String newExtra_Services, int newContacts, int newInventory_charges, int newCharge_per_distance) {
        String sql = "UPDATE moving_movers_bios SET name=:name,inventory_charges=:inventory_charges,charge_per_distance=:charge_per_distance ,contacts=:contacts,extra_Services=:extra_Services WHERE id= :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql,true)
                    .addParameter("id",id)
                    .addParameter("name",newName)
                    .addParameter("inventory_charges",newInventory_charges)
                    .addParameter("charge_per_distance",newCharge_per_distance)
                    .addParameter("contacts",newContacts)
                    .addParameter("extra_Services",newExtra_Services)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}



