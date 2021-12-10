package com.diac.saj.roleplayersdefinitive.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.diac.saj.roleplayersdefinitive.model.entity.CharacterCR;
import com.diac.saj.roleplayersdefinitive.model.entity.Race;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleClass;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertRace(Race... races);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertClass(RoleClass... classes);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(RoleCharacter roleCharacter);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertCharacter(RoleCharacter... roleCharacters);

    @Update
    int updateCharacter(RoleCharacter... characters);

    @Update
    int updateRace(Race... races);

    @Update
    int updateClass(RoleClass... classes);

    @Delete
    int deleteCharacters(RoleCharacter... characters);

    @Delete
    int deleteRaces(Race... races);

    @Delete
    int deleteClasses(RoleClass... classes);

    @Query("select * from character order by name asc")
    LiveData<List<RoleCharacter>> getCharacters();

    @Query("select rc.*, cc.id class_id, cc.name class_name, cr.id race_id, cr.name race_name from character rc join charclass cc on rc.idclass = cc.id join charrace cr on rc.idrace = cr.id order by name asc")
    LiveData<List<CharacterCR>> getAllCharacter();

    @Query("select * from charrace order by name asc")
    LiveData<List<Race>> getRaces();

    @Query("select * from charclass order by name asc")
    LiveData<List<RoleClass>> getClasses();

    @Query("select * from character where id = :id")
    LiveData<RoleCharacter> getPokemon(long id);

    @Query("select * from charrace where id = :id")
    LiveData<Race> getRace(long id);

    @Query("select id from charrace where name = :name")
    long getIdRace(String name);

    @Query("select * from charrace where name = :name")
    Race getRace(String name);

    @Query("select * from charclass where id = :id")
    LiveData<RoleClass> getClass(long id);

    @Query("select id from charclass where name = :name")
    long getIdClass(String name);

    @Query("select * from charclass where name = :name")
    RoleClass getClass(String name);

    @Query("update character SET idclass = :idclass, idrace = :idrace, state = :state, creation = :creation, strength = :strenght, dexterity = :dexterity, constitution = :constitution, intelligence = :intelligence, wisdom = :wisdom, charisma = :charisma WHERE id = :id")
    void updateCharacterQuery(long id, long idclass, long idrace, String state, String creation, int strenght,
                         int dexterity, int constitution, int intelligence, int wisdom, int charisma);
}

