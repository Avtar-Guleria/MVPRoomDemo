package com.asg.android.poc.room.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.asg.android.poc.room.db.entity.Person;

import java.util.List;



/**
 * Created by AGularia on 03/01/18.
 */

@Dao
public interface PersonDao {

    @Query("Select * from Person")
    List<Person> getAllPersons();

    @Insert
    void insertAll(Person... person);

    @Delete
    void delete(Person person);
}
