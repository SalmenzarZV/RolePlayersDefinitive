package com.diac.saj.roleplayersdefinitive.view.adapter.viewholder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.diac.saj.roleplayersdefinitive.R;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.view.activity.EditDeleteActivity;


public class CharacterViewHolder extends RecyclerView.ViewHolder {

    public RoleCharacter character;
    public TextView tvCname, tvClevel, tvCcreation, tvCstate, tvCclass, tvCrace, tvCstrength, tvCdexterity,
            tvCconstitution, tvCintelligence,tvCwisdom, tvCcharisma;

    public ImageView ivCharacter;
    


    public CharacterViewHolder(@NonNull View itemView) {
        super(itemView);
        initialize(itemView);
    }

    private void initialize(View itemView) {
        tvCname = itemView.findViewById(R.id.tvCname);
        tvClevel = itemView.findViewById(R.id.tvClevel);
        tvCcreation = itemView.findViewById(R.id.tvCcreation);
        tvCstate = itemView.findViewById(R.id.tvCstate);
        tvCclass = itemView.findViewById(R.id.tvCclass);
        tvCrace = itemView.findViewById(R.id.tvCrace);
        tvCstrength = itemView.findViewById(R.id.tvCstrength);
        tvCdexterity = itemView.findViewById(R.id.tvCdexterity);
        tvCconstitution = itemView.findViewById(R.id.tvCconstitution);
        tvCintelligence = itemView.findViewById(R.id.tvCintelligence);
        tvCwisdom = itemView.findViewById(R.id.tvCwisdom);
        tvCcharisma = itemView.findViewById(R.id.tvCcharisma);
        ivCharacter = itemView.findViewById(R.id.ivCharacter);

        itemView.setOnClickListener(view -> {
            Log.v("jamaica", String.valueOf(character.level));
            Log.v("jamaica", character.name);
            Intent intent = new Intent(itemView.getContext(), EditDeleteActivity.class);
            intent.putExtra("character", character);
            itemView.getContext().startActivity(intent);
        });

    }
}
