package com.diac.saj.roleplayersdefinitive.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.diac.saj.roleplayersdefinitive.model.entity.Race;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleClass;

@Database(entities = {RoleCharacter.class, RoleClass.class, Race.class}, version = 1, exportSchema = false)
public abstract class CharacterDatabase extends RoomDatabase {
    public abstract CharacterDao getDao();

    private static volatile CharacterDatabase INSTANCE;

    public static CharacterDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CharacterDatabase.class, "character").build();
        }
        return INSTANCE;
    }
}
