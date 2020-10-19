package com.enea.allergendatabase.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AllergenRepository {
    private AllergenDAO allergenDAO;
    private LiveData<List<Allergen>> allAllergens;

    public AllergenRepository (Application application){
        AllergenDatabase database = AllergenDatabase.getInstance(application);
        allergenDAO = database.allergenDAO();
        allAllergens = allergenDAO.getAllergens();
    }

    //Insert
    public void insert(Allergen allergen){
        new InsertAllergenAsyncTask(allergenDAO).execute(allergen);
    }


    //Delete
    public void deleteAllergen(Allergen allergen) {
        new DeleteAllergenAsyncTask(allergenDAO).execute(allergen);
    }
    public void deleteAllAllergens() {
        new DeleteAllAllergensAsyncTask(allergenDAO).execute();
    }


    public LiveData<List<Allergen>> getAllAllergens() {
        return allAllergens;
    }




    //AsyncTasks
    private static class InsertAllergenAsyncTask extends AsyncTask<Allergen, Void, Void> {
        private AllergenDAO allergenDAO;

        private InsertAllergenAsyncTask(AllergenDAO allergenDAO) {
            this.allergenDAO = allergenDAO;
        }

        @Override
        protected Void doInBackground(Allergen... allergens) {
            allergenDAO.insertAllergen(allergens[0]);
            return null;
        }
    }
    private static class DeleteAllergenAsyncTask extends AsyncTask<Allergen, Void, Void> {
        private AllergenDAO allergenDAO;

        private DeleteAllergenAsyncTask(AllergenDAO allergenDAO) {
            this.allergenDAO = allergenDAO;
        }

        @Override
        protected Void doInBackground(Allergen... allergens) {
            allergenDAO.deleteAllergen(allergens[0]);
            return null;
        }
    }
    private static class DeleteAllAllergensAsyncTask extends AsyncTask<Allergen, Void, Void> {
        private AllergenDAO allergenDAO;

        private DeleteAllAllergensAsyncTask(AllergenDAO allergenDAO) {
            this.allergenDAO = allergenDAO;
        }

        @Override
        protected Void doInBackground(final Allergen... params) {
            allergenDAO.deleteAllAllergens();
            return null;
        }
    }
}
