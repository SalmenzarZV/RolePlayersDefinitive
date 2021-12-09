package com.diac.saj.roleplayersdefinitive.view.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.diac.saj.roleplayersdefinitive.R;
import com.diac.saj.roleplayersdefinitive.model.entity.CharacterCR;
import com.diac.saj.roleplayersdefinitive.model.entity.Race;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleClass;
import com.diac.saj.roleplayersdefinitive.view.adapter.viewholder.CharacterViewHolder;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder>{
    private List<CharacterCR> characterList;
    private Context context;

    public CharacterAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);

        return  new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        CharacterCR characterCR = characterList.get(position);
        RoleCharacter character = characterCR.character;
        holder.character = character;
        Race race = characterCR.race;
        RoleClass roleclass = characterCR.roleclass;
        holder.tvCname.setText(context.getString(R.string.Name)+character.name);
        holder.tvClevel.setText(context.getString(R.string.Lv)+character.level);
        holder.tvCcreation.setText(context.getString(R.string.Creation)+character.creation);
        holder.tvCstate.setText(context.getString(R.string.State)+character.state);
        holder.tvCclass.setText(context.getString(R.string.Class)+roleclass.name);
        holder.tvCrace.setText(context.getString(R.string.Race)+race.name);
        holder.tvCstrength.setText(context.getString(R.string.STR)+character.strength);
        holder.tvCdexterity.setText(context.getString(R.string.DEX)+character.dexterity);
        holder.tvCconstitution.setText(context.getString(R.string.CON)+character.constitution);
        holder.tvCintelligence.setText(context.getString(R.string.INT)+character.intelligence);
        holder.tvCwisdom.setText(context.getString(R.string.WIS)+character.wisdom);
        holder.tvCcharisma.setText(context.getString(R.string.CHA)+character.charisma);
        putPhoto(holder, character);
    }

    private void putPhoto(CharacterViewHolder holder, RoleCharacter character) {
        switch ((int) character.idclass) {
            case 1:
                holder.ivCharacter.setImageResource(R.drawable.barbarian);
                break;
            case 2:
                holder.ivCharacter.setImageResource(R.drawable.bard);
                break;
            case 3:
                holder.ivCharacter.setImageResource(R.drawable.cleric);
                break;
            case 4:
                holder.ivCharacter.setImageResource(R.drawable.druid);
                break;
            case 5:
                holder.ivCharacter.setImageResource(R.drawable.fighter);
                break;
            case 6:
                holder.ivCharacter.setImageResource(R.drawable.monk);
                break;
            case 7:
                holder.ivCharacter.setImageResource(R.drawable.paladin);
                break;
            case 8:
                holder.ivCharacter.setImageResource(R.drawable.ranger);
                break;
            case 9:
                holder.ivCharacter.setImageResource(R.drawable.rogue);
                break;
            case 10:
                holder.ivCharacter.setImageResource(R.drawable.sorcerer);
                break;
            case 11:
                holder.ivCharacter.setImageResource(R.drawable.warlock);
                break;
            case 12:
                holder.ivCharacter.setImageResource(R.drawable.wizard);
                break;
        }
    }


    @Override
    public int getItemCount() {
        if(characterList == null){
            return 0;
        }
        return characterList.size();
    }

    public void setCharacterList(List<CharacterCR> characterList) {
        this.characterList = characterList;
        notifyDataSetChanged();
    }


}

