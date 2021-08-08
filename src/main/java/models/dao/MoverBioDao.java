package models.dao;

import models.MoverBio;

import java.util.List;

public interface MoverBioDao {

    void add(MoverBio moverBio);

    //get all moversBio
    List<MoverBio>getAll();
    List<MoverBio> getMoverByName(String moverName);

    MoverBio findById(int id);


    void deleteMoverById(int id);
}
