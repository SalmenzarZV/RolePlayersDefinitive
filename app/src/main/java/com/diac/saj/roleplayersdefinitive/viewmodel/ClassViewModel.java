package com.diac.saj.roleplayersdefinitive.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.diac.saj.roleplayersdefinitive.model.entity.RoleClass;
import com.diac.saj.roleplayersdefinitive.model.repository.CharacterRepository;

import java.util.List;

public class ClassViewModel extends AndroidViewModel {

    private CharacterRepository repository;

    public ClassViewModel(@NonNull Application application) {
        super(application);
        repository = new CharacterRepository(application);
    }

    public LiveData<List<RoleClass>> getClasses() {
        return repository.getClasses();
    }

    public LiveData<RoleClass> getClass(long id) {
        return repository.getClass(id);
    }

    public void updateClass(RoleClass... classes) {
        repository.updateClass(classes);
    }

    public void deleteClasses(RoleClass... classes) {
        repository.deleteClasses(classes);
    }
}
