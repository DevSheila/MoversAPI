package models.dao;

import models.MoverBio;

import java.util.List;

public interface MoverBioDao {

    void add(MoverBio moverBio);

    //get all moversBio
    List<MoverBio>getAll();

    void update(String name, String extra_Services,int contacts,int inventory_charges,int charge_per_distance);



}
