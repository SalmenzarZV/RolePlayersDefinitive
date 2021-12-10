package com.diac.saj.roleplayersdefinitive.model.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.model.entity.CharacterCR;
import com.diac.saj.roleplayersdefinitive.model.entity.Race;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleClass;
import com.diac.saj.roleplayersdefinitive.model.room.CharacterDao;
import com.diac.saj.roleplayersdefinitive.model.room.CharacterDatabase;

import java.util.List;

public class CharacterRepository {

    private final CharacterDao dao;
    private LiveData<List<CharacterCR>> allCharacter;
    private LiveData<List<RoleCharacter>> liveCharacters;
    private LiveData<List<RoleClass>> liveClasses;
    private LiveData<List<Race>> liveRaces;
    private LiveData<RoleCharacter> liveCharacter;
    private LiveData<RoleClass> liveClass;
    private LiveData<Race> liveRace;
    private final MutableLiveData<Long> liveInsertResult;
    private final MutableLiveData<List<Long>> liveInsertResults;
    private final SharedPreferences preferences;

    public CharacterRepository(Context context) {
        CharacterDatabase db = CharacterDatabase.getDatabase(context);
        dao = db.getDao();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        liveInsertResult = new MutableLiveData<>();
        liveInsertResults = new MutableLiveData<>();
        if (!getInit()){
            racesPreload();
            classesPreload();
            setInit();
        }
    }

    public void racesPreload(){
        String[] racesNames = new String[] {"Dragonborn", "Dwarf", "Elf", "Gnome","Half-Elf",
                "Halfling", "Half-Orc", "Human", "Tiefling"};
        Race[] races = new Race[racesNames.length];
        Race race;
        int cont=0;
        for (String s: racesNames) {
            race = new Race();
            race.name = s;
            races[cont] = race;
            cont++;
        }
        insertRace(races);
    }

    private void insertRace(Race... races) {
        Runnable r = () -> {
            dao.insertRace(races);
        };
        new Thread(r).start();
    }

    private long insertRaceGetId(Race race){
        List<Long> ids = dao.insertRace(race);
        if(ids.get(0) < 1) {
            return dao.getIdRace(race.name);
        } else {
            return ids.get(0);
        }
    }

    public LiveData<List<Race>> getRaces() {
        if(liveRaces == null) {
            liveRaces = dao.getRaces();
        }
        return liveRaces;
    }

    public LiveData<Race> getRace(long id) {
        if(liveRace == null) {
            liveRace = dao.getRace(id);
        }
        return liveRace;
    }

    public void updateRace(Race... races) {
        Runnable r = () -> {
            dao.updateRace(races);
        };
        new Thread(r).start();
    }

    public void deleteRaces(Race... races) {
        Runnable r = () -> {
            dao.deleteRaces(races);
        };
        new Thread(r).start();
    }

    public void classesPreload(){
        String[] classesNames = new String[] {"Barbarian", "Bard", "Cleric", "Druid","Fighter",
                "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer",
                "Warlock", "Wizard"};
        RoleClass[] classes = new RoleClass[classesNames.length];
        RoleClass aClass;
        int cont=0;
        for (String s: classesNames) {
            aClass = new RoleClass();

            aClass.name = s;
            classes[cont] = aClass;
            cont++;
        }
        insertClass(classes);
    }

    private void insertClass(RoleClass... classes) {
        Runnable r = () -> {
            dao.insertClass(classes);
        };
        new Thread(r).start();
    }

    private long insertClassGetId(RoleClass roleClass){
        List<Long> ids = dao.insertClass(roleClass);
        if(ids.get(0) < 1) {
            return dao.getIdClass(roleClass.name);
        } else {
            return ids.get(0);
        }
    }

    public LiveData<List<RoleClass>> getClasses() {
        if(liveClasses == null) {
            liveClasses = dao.getClasses();
        }
        return liveClasses;
    }

    public LiveData<RoleClass> getClass(long id) {
        if(liveClasses == null) {
            liveClass = dao.getClass(id);
        }
        return liveClass;
    }

    public void updateClass(RoleClass... classes) {
        Runnable r = () -> {
            dao.updateClass(classes);
        };
        new Thread(r).start();
    }

    public void deleteClasses(RoleClass... classes) {
        Runnable r = () -> {
            dao.deleteClasses(classes);
        };
        new Thread(r).start();
    }

    public void insertCharacter(RoleCharacter... characters) {
        Runnable r = () -> {
            List<Long> resultList = dao.insertCharacter(characters);
            liveInsertResult.postValue(resultList.get(0));
            liveInsertResults.postValue(resultList);
        };
        new Thread(r).start();
    }

    public LiveData<List<RoleCharacter>> getCharacters() {
        if(liveCharacters == null) {
            liveCharacters = dao.getCharacters();
        }
        return liveCharacters;
    }

    public LiveData<RoleCharacter> getCharacter(long id) {
        if(liveCharacter == null) {
            liveCharacter = dao.getPokemon(id);
        }
        return liveCharacter;
    }

    public LiveData<List<CharacterCR>> getAllCharacter() {
        if(allCharacter == null) {
            allCharacter = dao.getAllCharacter();
        }
        return allCharacter;
    }

    public void updateCharacter(RoleCharacter... characters) {
        Runnable r = () -> {
            dao.updateCharacter(characters);
        };
        new Thread(r).start();
    }

    public void deleteCharacters(RoleCharacter... characters) {
        Runnable r = () -> {
            dao.deleteCharacters(characters);
        };
        new Thread(r).start();
    }

    public void updateCharacterQuery(long id, long idclass, long idrace, String state, String creation, int strenght, int dexterity, int constitution, int intelligence, int wisdom, int charisma){
        Runnable r = () -> {
            dao.updateCharacterQuery(id, idclass, idrace, state, creation, strenght, dexterity, constitution, intelligence, wisdom, charisma);
        };
        new Thread(r).start();
    }

    public MutableLiveData<Long> getInsertResult() {
        return liveInsertResult;
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return liveInsertResults;
    }


    public boolean getInit() {
        return preferences.getBoolean("init", false);
    }

    public void setInit() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("init", true);
        editor.apply();
    }
}

