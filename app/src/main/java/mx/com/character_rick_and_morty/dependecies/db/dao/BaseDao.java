package mx.com.character_rick_and_morty.dependecies.db.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;


public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T... entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<T> entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(T entity);

    @Delete
    void delete(T entity);
}
