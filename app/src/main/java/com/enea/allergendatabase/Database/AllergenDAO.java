package com.enea.allergendatabase.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AllergenDAO {

    //Insert
    @Insert
    void insertAllergen(Allergen allergen);
    //Delete
    @Delete
    void deleteAllergen(Allergen allergen);
    //Update
    @Update
    void updateAllergen(Allergen allergen);
    //Query
    @Query("DELETE FROM allergen")
    void deleteAllAllergens();
    //Query LiveData
    @Query("SELECT * FROM allergen ORDER BY allergen_name")
    LiveData<List<Allergen>> getAllergens();

}
