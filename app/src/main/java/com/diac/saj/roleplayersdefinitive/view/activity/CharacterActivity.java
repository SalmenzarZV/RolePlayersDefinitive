package com.diac.saj.roleplayersdefinitive.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.diac.saj.roleplayersdefinitive.R;
import com.diac.saj.roleplayersdefinitive.model.entity.CharacterCR;
import com.diac.saj.roleplayersdefinitive.view.adapter.CharacterAdapter;
import com.diac.saj.roleplayersdefinitive.viewmodel.CharacterViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvCharacters = findViewById(R.id.rvCharacters);
        rvCharacters.setLayoutManager(new LinearLayoutManager(this));

        CharacterViewModel cvm = new ViewModelProvider(this).get(CharacterViewModel.class);
        CharacterAdapter characterAdapter = new CharacterAdapter(this);

        rvCharacters.setAdapter(characterAdapter);

        LiveData<List<CharacterCR>> listaCharacters = cvm.getAllCharacter();




        listaCharacters.observe(this, characterAdapter::setCharacterList);
        /*
        long id;
        for (CharacterCR ccr: listaCharacters.getValue()) {
            id = ccr.roleclass.id;
            Log.v("jamaica", ""+id);
        }*/


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent= new Intent(this, AddActivity.class);
            startActivity(intent);
        });


    }
}