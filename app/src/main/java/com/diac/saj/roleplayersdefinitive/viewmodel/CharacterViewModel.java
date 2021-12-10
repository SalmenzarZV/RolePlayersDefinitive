package com.diac.saj.roleplayersdefinitive.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.diac.saj.roleplayersdefinitive.model.entity.CharacterCR;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.model.repository.CharacterRepository;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    private CharacterRepository repository;

    public CharacterViewModel(@NonNull Application application) {
        super(application);
        repository = new CharacterRepository(application);
    }

    public void insertCharacter(RoleCharacter... characters) {
        repository.insertCharacter(characters);
    }

    public LiveData<List<RoleCharacter>> getCharacters() {
        return repository.getCharacters();
    }

    public LiveData<RoleCharacter> getCharacter(long id) {
        return repository.getCharacter(id);
    }

    public LiveData<List<CharacterCR>> getAllCharacter() {
        return repository.getAllCharacter();
    }

    public void updateCharacter(RoleCharacter... characters) {
        repository.updateCharacter(characters);
    }

    public void deleteCharacters(RoleCharacter... characters) {
        repository.deleteCharacters(characters);
    }

    public MutableLiveData<Long> getInsertResult() {
        return repository.getInsertResult();
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return repository.getInsertResults();
    }

    public void updateCharacterQuery(long id, long idclass, long idrace, String state, String creation, int strenght, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        repository.updateCharacterQuery(id, idclass, idrace, state, creation, strenght, dexterity, constitution, intelligence, wisdom, charisma);
    }
}
