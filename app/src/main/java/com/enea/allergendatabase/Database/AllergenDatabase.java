package com.enea.allergendatabase.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Allergen.class, version = 4)
public abstract class AllergenDatabase extends RoomDatabase {
    private static AllergenDatabase instance;
    public abstract AllergenDAO allergenDAO();
    public static synchronized AllergenDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AllergenDatabase.class, "allergen_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
