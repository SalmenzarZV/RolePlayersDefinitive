package com.diac.saj.roleplayersdefinitive.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.diac.saj.roleplayersdefinitive.model.entity.Race;
import com.diac.saj.roleplayersdefinitive.model.repository.CharacterRepository;

import java.util.List;

public class RaceViewModel extends AndroidViewModel {

    private CharacterRepository repository;

    public RaceViewModel(@NonNull Application application) {
        super(application);
        repository = new CharacterRepository(application);
    }

    public LiveData<List<Race>> getRaces() {
        return repository.getRaces();
    }

    public LiveData<Race> getRace(long id) {
        return repository.getRace(id);
    }

    public void updateRace(Race... races) {
        repository.updateRace(races);
    }

    public void deleteRaces(Race... races) {
        repository.deleteRaces(races);
    }
}
