package com.enea.allergendatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.enea.allergendatabase.Database.Allergen;
import com.enea.allergendatabase.Database.AllergenRepository;

import java.util.List;

public class AllergenViewModel extends AndroidViewModel {

    private AllergenRepository repository;
    private LiveData<List<Allergen>> allAllergens;

    public AllergenViewModel(@NonNull Application application) {
        super(application);

        repository = new AllergenRepository(application);
        allAllergens = repository.getAllAllergens();


    }
}
