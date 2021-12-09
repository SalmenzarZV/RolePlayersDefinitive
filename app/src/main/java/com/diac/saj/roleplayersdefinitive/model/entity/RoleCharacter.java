package com.diac.saj.roleplayersdefinitive.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "character",
        indices = {@Index(value = "idclass"), @Index(value = "idrace")},
        foreignKeys = {@ForeignKey(entity = Race.class, parentColumns = "id", childColumns = "idrace", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = RoleClass.class, parentColumns = "id", childColumns = "idclass", onDelete = ForeignKey.CASCADE)})
public class RoleCharacter implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "idclass")
    public long idclass;

    @NonNull
    @ColumnInfo(name = "idrace")
    public long idrace;

    @NonNull
    @ColumnInfo(name = "level")
    public int level;

    @ColumnInfo(name = "state")
    public String state;


    @ColumnInfo(name = "creation")
    public String creation;

    @ColumnInfo(name = "strength")
    public int strength;


    @ColumnInfo(name = "dexterity")
    public int dexterity;

    @ColumnInfo(name = "constitution")
    public int constitution;


    @ColumnInfo(name = "intelligence")
    public int intelligence;


    @ColumnInfo(name = "wisdom")
    public int wisdom;


    @ColumnInfo(name = "charisma")
    public int charisma;

    public RoleCharacter(){

    }

    protected RoleCharacter(Parcel in){
        id = in.readLong();
        name = in.readString();
        level = in.readInt();
        creation = in.readString();
        state = in.readString();
        strength = in.readInt();
        dexterity = in.readInt();
        constitution = in.readInt();
        intelligence = in.readInt();
        wisdom = in.readInt();
        charisma = in.readInt();
        idrace = in.readLong();
        idclass = in.readLong();
    }

    public static final Creator<RoleCharacter> CREATOR = new Creator<RoleCharacter>() {
        @Override
        public RoleCharacter createFromParcel(Parcel in) {
            return new RoleCharacter(in);
        }

        @Override
        public RoleCharacter[] newArray(int size) {
            return new RoleCharacter[size];
        }
    };

    public boolean isValid(){
        return level >0 && !(name.isEmpty() || creation.isEmpty() || state.isEmpty());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(state);
        parcel.writeString(creation);
        parcel.writeInt(level);
        parcel.writeInt(strength);
        parcel.writeInt(dexterity);
        parcel.writeInt(constitution);
        parcel.writeInt(intelligence);
        parcel.writeInt(wisdom);
        parcel.writeInt(charisma);
    }
}
