package com.example.swrve.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swrve.models.Image;

import java.util.List;
@Dao
public interface ImageDAO {
    @Query("SELECT * FROM IMAGE ORDER BY ID")
    LiveData<List<Image>> loadAllImages();

    @Insert
    long insertImage(Image image);

    @Update
    void updateImage(Image image);

    @Delete
    void delete(Image image);

    @Query("SELECT * FROM IMAGE WHERE id = :id")
    LiveData<Image> loadImageById(int id);
}
