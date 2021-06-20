package com.example.api.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataUserDAO {
    @Insert
    Long InsertData(DataUser dataUser);

    @Query("Select * from user_db")
    List<DataUser> getData();

    @Update
    int updateData(DataUser item);

    @Delete
    void deleteData(DataUser item);

    @Query("SELECT EXISTS (SELECT * FROM user_db WHERE username = :username)")
    boolean dataExist(String username);
}
