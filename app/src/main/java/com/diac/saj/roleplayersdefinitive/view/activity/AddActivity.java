package com.diac.saj.roleplayersdefinitive.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.diac.saj.roleplayersdefinitive.R;
import com.diac.saj.roleplayersdefinitive.model.entity.Race;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleClass;
import com.diac.saj.roleplayersdefinitive.viewmodel.CharacterViewModel;
import com.diac.saj.roleplayersdefinitive.viewmodel.ClassViewModel;
import com.diac.saj.roleplayersdefinitive.viewmodel.RaceViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class AddActivity extends AppCompatActivity {

    TextInputEditText tietADDname, tietADDlevel, tietADDcreation, tietADDstate, tietADDstrength,
                        tietADDdexterity, tietADDconstitution, tietADDintelligence, tietADDwisdom,
                        tietADDcharisma;
    Button btAdd;
    ImageView ivADDcharacter;
    Spinner spADDclass, spADDrace;

    private CharacterViewModel cvm;
    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initialize();

    }

    private void initialize() {
        itemFindView();
        getViewModel();
        defineAddListener();
        defineOnFocusListener();
    }

    private void itemFindView() {
         tietADDname = findViewById(R.id.tietADDname);
         tietADDlevel = findViewById(R.id.tietADDlevel);
         tietADDcreation = findViewById(R.id.tietADDcreation);
         tietADDstate = findViewById(R.id.tietADDstate);
         tietADDstrength = findViewById(R.id.tietADDstrength);
         tietADDdexterity = findViewById(R.id.tietADDdexterity);
         tietADDconstitution = findViewById(R.id.tietADDconstitution);
         tietADDintelligence = findViewById(R.id.tietADDintelligence);
         tietADDwisdom = findViewById(R.id.tietADDwisdom);
         tietADDcharisma = findViewById(R.id.tietADDcharisma);
         btAdd = findViewById(R.id.btAdd);
         ivADDcharacter = findViewById(R.id.ivADDcharacter);
         spADDclass = findViewById(R.id.spADDclass);
         spADDrace = findViewById(R.id.spADDrace);
    }

    private void defineOnFocusListener() {
        spADDclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spADDclass.getSelectedItemPosition()) {
                    case 0:
                        ivADDcharacter.setImageResource(R.drawable.ic_baseline_question_mark_24);
                        break;
                    case 1:
                        ivADDcharacter.setImageResource(R.drawable.barbarian);
                        break;
                    case 2:
                        ivADDcharacter.setImageResource(R.drawable.bard);
                        break;
                    case 3:
                        ivADDcharacter.setImageResource(R.drawable.cleric);
                        break;
                    case 4:
                        ivADDcharacter.setImageResource(R.drawable.druid);
                        break;
                    case 5:
                        ivADDcharacter.setImageResource(R.drawable.fighter);
                        break;
                    case 6:
                        ivADDcharacter.setImageResource(R.drawable.monk);
                        break;
                    case 7:
                        ivADDcharacter.setImageResource(R.drawable.paladin);
                        break;
                    case 8:
                        ivADDcharacter.setImageResource(R.drawable.ranger);
                        break;
                    case 9:
                        ivADDcharacter.setImageResource(R.drawable.rogue);
                        break;
                    case 10:
                        ivADDcharacter.setImageResource(R.drawable.sorcerer);
                        break;
                    case 11:
                        ivADDcharacter.setImageResource(R.drawable.warlock);
                        break;
                    case 12:
                        ivADDcharacter.setImageResource(R.drawable.wizard);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void defineAddListener() {
        btAdd.setOnClickListener(view -> {
            RoleCharacter character = getCharacter();
            if  (character.isValid()){
                addCharacter(character);
            } else {
                Toast.makeText(this, "some fields aren't valid", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addCharacter(RoleCharacter character) {
        cvm.insertCharacter(character);
    }

    private RoleCharacter getCharacter() {
        String name, level, creation, state, strenght, dexterity, constitution, intelligence,
                wisdom, charisma;

        name = tietADDname.getText().toString();
        level = tietADDlevel.getText().toString();
        creation = tietADDcreation.getText().toString();
        state = tietADDstate.getText().toString();
        strenght = tietADDstrength.getText().toString();
        dexterity = tietADDdexterity.getText().toString();
        constitution = tietADDconstitution.getText().toString();
        intelligence = tietADDintelligence.getText().toString();
        wisdom = tietADDwisdom.getText().toString();
        charisma = tietADDcharisma.getText().toString();

        RoleClass roleClass = (RoleClass) spADDclass.getSelectedItem();
        Race race = (Race) spADDrace.getSelectedItem();
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



    private void getViewModel(){
        cvm = new ViewModelProvider(this).get(CharacterViewModel.class);

        cvm.getInsertResults().observe(this, list -> {
            if (list.get(0) > 0){
                if (firstTime){
                    firstTime = false;
                    alert();
                } else {
                    cleanFields();
                }
            } else {
                Toast.makeText(this, "error 10", Toast.LENGTH_SHORT).show();
            }
        });

        ClassViewModel classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
        classViewModel.getClasses().observe(this, roleClasses -> {

            if (!roleClasses.get(0).name.equals("<<select a class>>")){
                RoleClass roleClass = new RoleClass();
                roleClass.id = 0;
                roleClass.name = "<<select a class>>";
                roleClasses.add(0, roleClass);
            }

            ArrayAdapter<RoleClass> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roleClasses);
            spADDclass.setAdapter(adapter);
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
            spADDrace.setAdapter(adapter);
        });
    }

    private void cleanFields() {
        tietADDname.setText("");
        tietADDlevel.setText("");
        tietADDcreation.setText("");
        tietADDstate.setText("");
        tietADDstrength.setText("");
        tietADDdexterity.setText("");
        tietADDconstitution.setText("");
        tietADDintelligence.setText("");
        tietADDwisdom.setText("");
        tietADDcharisma.setText("");

        spADDclass.setSelection(0);
        spADDrace.setSelection(0);

    }



    private void alert() {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Insertar mas?")
                .setMessage("El personaje se ha insertado correctamente, desea seguir agregando personajes?")
                .setNegativeButton(android.R.string.no, (dialog, which) -> finish())
                .setPositiveButton( android.R.string.ok, (dialog, which) -> cleanFields());
        builder.create().show();
    }
}

