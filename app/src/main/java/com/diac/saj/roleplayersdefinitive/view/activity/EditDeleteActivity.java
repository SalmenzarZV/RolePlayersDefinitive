package com.diac.saj.roleplayersdefinitive.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


import com.diac.saj.roleplayersdefinitive.R;
import com.diac.saj.roleplayersdefinitive.model.entity.Race;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleClass;
import com.diac.saj.roleplayersdefinitive.viewmodel.CharacterViewModel;
import com.diac.saj.roleplayersdefinitive.viewmodel.ClassViewModel;
import com.diac.saj.roleplayersdefinitive.viewmodel.RaceViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class EditDeleteActivity extends AppCompatActivity {

    TextInputEditText tietEDname, tietEDlevel, tietEDcreation, tietEDstate, tietEDstrength,
            tietEDdexterity, tietEDconstitution, tietEDintelligence, tietEDwisdom,
            tietEDcharisma;
    Button btEdit, btDelete;
    ImageView ivEDcharacter;
    Spinner spEDclass, spEDrace;

    RoleCharacter roleCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        initialize();
    }

    private void initialize() {
        roleCharacter = getIntent().getExtras().getParcelable("character");
        itemFindView();
        itemSetters(roleCharacter);
        defineOnFocusListener();
    }

    private void defineOnFocusListener() {
        spEDclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spEDclass.getSelectedItemPosition()) {
                    case 0:
                        ivEDcharacter.setImageResource(R.drawable.ic_baseline_question_mark_24);
                        break;
                    case 1:
                        ivEDcharacter.setImageResource(R.drawable.barbarian);
                        break;
                    case 2:
                        ivEDcharacter.setImageResource(R.drawable.bard);
                        break;
                    case 3:
                        ivEDcharacter.setImageResource(R.drawable.cleric);
                        break;
                    case 4:
                        ivEDcharacter.setImageResource(R.drawable.druid);
                        break;
                    case 5:
                        ivEDcharacter.setImageResource(R.drawable.fighter);
                        break;
                    case 6:
                        ivEDcharacter.setImageResource(R.drawable.monk);
                        break;
                    case 7:
                        ivEDcharacter.setImageResource(R.drawable.paladin);
                        break;
                    case 8:
                        ivEDcharacter.setImageResource(R.drawable.ranger);
                        break;
                    case 9:
                        ivEDcharacter.setImageResource(R.drawable.rogue);
                        break;
                    case 10:
                        ivEDcharacter.setImageResource(R.drawable.sorcerer);
                        break;
                    case 11:
                        ivEDcharacter.setImageResource(R.drawable.warlock);
                        break;
                    case 12:
                        ivEDcharacter.setImageResource(R.drawable.wizard);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void itemSetters(RoleCharacter roleCharacter) {
        Log.v("jamaica", roleCharacter.level+"");
        tietEDname.setText(roleCharacter.name);
        tietEDlevel.setText(roleCharacter.level+"");
        tietEDcreation.setText(roleCharacter.creation);
        tietEDstate.setText(roleCharacter.state);
        tietEDstrength.setText(String.valueOf(roleCharacter.strength));
        tietEDdexterity.setText(String.valueOf(roleCharacter.dexterity));
        tietEDconstitution.setText(String.valueOf(roleCharacter.constitution));
        tietEDintelligence.setText(String.valueOf(roleCharacter.intelligence));
        tietEDwisdom.setText(String.valueOf(roleCharacter.wisdom));
        tietEDcharisma.setText(String.valueOf(roleCharacter.charisma));


        CharacterViewModel cvm = new ViewModelProvider(this).get(CharacterViewModel.class);
        ClassViewModel clssvm = new ViewModelProvider(this).get(ClassViewModel.class);
        clssvm.getClasses().observe(this, roleClasses -> {

            if (!roleClasses.get(0).name.equals("<<select a class>>")){
                RoleClass roleClass = new RoleClass();
                roleClass.id = 0;
                roleClass.name = "<<select a class>>";
                roleClasses.add(0, roleClass);
                ArrayAdapter<RoleClass> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roleClasses);
                spEDclass.setAdapter(adapter);
                Log.v("jamaica", roleCharacter.id+"");
                spEDclass.setSelection((int) roleCharacter.idclass);
            }
        });
        RaceViewModel rvm = new ViewModelProvider(this).get(RaceViewModel.class);
        rvm.getRaces().observe(this, races -> {

            if (!races.get(0).name.equals("<<select a race>>")) {
                Race race = new Race();
                race.id = 0;
                race.name = "<<select a race>>";
                races.add(0, race);
            }

            ArrayAdapter<Race> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, races);
            spEDrace.setAdapter(adapter);
            spEDrace.setSelection((int) roleCharacter.idrace);
        });

        addListeners(cvm);

    }

    private void addListeners(CharacterViewModel cvm) {
        btEdit.setOnClickListener(view -> {
            RoleCharacter roleCharacter = getCharacter();
            if (roleCharacter.isValid()){
                cvm.updateCharacter(roleCharacter);
            }
        });

        btDelete.setOnClickListener(view -> {
            cvm.deleteCharacters(roleCharacter);
        });
    }


    private void itemFindView() {
        tietEDname = findViewById(R.id.tietEDname);
        tietEDlevel = findViewById(R.id.tietEDlevel);
        tietEDcreation = findViewById(R.id.tietEDcreation);
        tietEDstate = findViewById(R.id.tietEDstate);
        tietEDstrength = findViewById(R.id.tietEDstrength);
        tietEDdexterity = findViewById(R.id.tietEDdexterity);
        tietEDconstitution = findViewById(R.id.tietEDconstitution);
        tietEDintelligence = findViewById(R.id.tietEDintelligence);
        tietEDwisdom = findViewById(R.id.tietEDwisdom);
        tietEDcharisma = findViewById(R.id.tietEDcharisma);
        btEdit = findViewById(R.id.btEdit);
        btDelete = findViewById(R.id.btDelete);
        ivEDcharacter = findViewById(R.id.ivEDcharacter);
        spEDclass = findViewById(R.id.spEDclass);
        spEDrace = findViewById(R.id.spEDrace);
    }

    private RoleCharacter getCharacter() {
        String name, level, creation, state, strenght, dexterity, constitution, intelligence,
                wisdom, charisma;

        name = tietEDname.getText().toString().trim();
        level = tietEDlevel.getText().toString().trim();
        creation = tietEDcreation.getText().toString().trim();
        state = tietEDstate.getText().toString().trim();
        strenght = tietEDstrength.getText().toString().trim();
        dexterity = tietEDdexterity.getText().toString().trim();
        constitution = tietEDconstitution.getText().toString().trim();
        intelligence = tietEDintelligence.getText().toString().trim();
        wisdom = tietEDwisdom.getText().toString().trim();
        charisma = tietEDcharisma.getText().toString().trim();

        RoleClass roleClass = (RoleClass) spEDclass.getSelectedItem();
        Race race = (Race) spEDrace.getSelectedItem();
        RoleCharacter roleCharacter = new RoleCharacter();
        roleCharacter.name = name;
        roleCharacter.creation = creation;
        roleCharacter.state = state;

        try {
            roleCharacter.level = Integer.parseInt(level);
            roleCharacter.strength = Integer.parseInt(strenght);
            roleCharacter.dexterity = Integer.parseInt(dexterity);
            roleCharacter.constitution = Integer.parseInt(constitution);
            roleCharacter.intelligence = Integer.parseInt(intelligence);
            roleCharacter.wisdom = Integer.parseInt(wisdom);
            roleCharacter.charisma = Integer.parseInt(charisma);
        } catch (NumberFormatException nfe) {
            roleCharacter.level = -1;
            roleCharacter.strength = -1;
            roleCharacter.dexterity = -1;
            roleCharacter.constitution = -1;
            roleCharacter.intelligence = -1;
            roleCharacter.wisdom = -1;
            roleCharacter.charisma = -1;
        }

        roleCharacter.idclass = roleClass.id;
        roleCharacter.idrace = race.id;

        return roleCharacter;
    }

}