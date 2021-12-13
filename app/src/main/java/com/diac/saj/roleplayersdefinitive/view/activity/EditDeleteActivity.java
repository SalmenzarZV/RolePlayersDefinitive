package com.diac.saj.roleplayersdefinitive.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.diac.saj.roleplayersdefinitive.R;
import com.diac.saj.roleplayersdefinitive.model.entity.Race;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleCharacter;
import com.diac.saj.roleplayersdefinitive.model.entity.RoleClass;
import com.diac.saj.roleplayersdefinitive.viewmodel.CharacterViewModel;
import com.diac.saj.roleplayersdefinitive.viewmodel.ClassViewModel;
import com.diac.saj.roleplayersdefinitive.viewmodel.RaceViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class EditDeleteActivity extends AppCompatActivity {

    TextInputEditText tietEDname, tietEDlevel, tietEDcreation, tietEDstate, tietEDstrength,
            tietEDdexterity, tietEDconstitution, tietEDintelligence, tietEDwisdom,
            tietEDcharisma;
    Button btEdit, btDelete;
    ImageView ivEDcharacter;
    Spinner spEDclass, spEDrace;

    RoleCharacter roleCharacter;

    TextInputLayout tfEDcreation, tfEDlevel, tfEDstrength, tfEDdexterity, tfEDconstitution,
            tfEDintelligence, tfEDwisdom, tfEDcharisma;
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
        setFieldsValidation();
    }

    private void setFieldsValidation() {
        setCreationValidation();
        setLvStatsValidation();
    }

    private void setLvStatsValidation() {
        levelWatcher();
        strengthWatcher();
        dexterityWatcher();
        constitutionWatcher();
        intelligenceWatcher();
        wisdomWatcher();
        charismaWatcher();
    }

    private void defineOnFocusListener() {
        spEDclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spEDclass.getSelectedItemPosition()) {
                    //TODO OPTIMIZAR
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
        //Log.v("jamaica", roleCharacter.level+"");
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
                //Log.v("jamaica", roleCharacter.id+"");
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
                //Log.v("jamaica", this.roleCharacter.id+"//"+roleCharacter.name);
                /*
                cvm.updateCharacterQuery(this.roleCharacter.id, roleCharacter.idclass,
                                         roleCharacter.idrace, roleCharacter.state,
                                         roleCharacter.creation,roleCharacter.strength,
                                         roleCharacter.dexterity,roleCharacter.constitution,
                                         roleCharacter.intelligence,roleCharacter.wisdom,
                                         roleCharacter.charisma);

                 */
                cvm.deleteCharacters(this.roleCharacter);
                cvm.insertCharacter(roleCharacter);
                Toast.makeText(this, "Updated Character", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "not valid fields", Toast.LENGTH_SHORT).show();
            }
        });

        btDelete.setOnClickListener(view -> {
            RoleCharacter roleCharacter = getCharacter();
            alert(cvm, roleCharacter);
        });
    }

    private void alert(CharacterViewModel cvm, RoleCharacter roleCharacter) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?")
                .setMessage("Are you sure do you want to delete this character?")
                .setNegativeButton(android.R.string.no, (dialog, which) -> {})
                .setPositiveButton( android.R.string.ok, (dialog, which) -> {
                    cvm.deleteCharacters(roleCharacter);
                    Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
                    finish();
                });
        builder.create().show();
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
        tfEDcreation = findViewById(R.id.tfEDcreation);
        tfEDlevel = findViewById(R.id.tfEDlevel);
        tfEDstrength = findViewById(R.id.tfEDstrength);
        tfEDdexterity = findViewById(R.id.tfEDdexterity);
        tfEDconstitution = findViewById(R.id.tfEDconstitution);
        tfEDintelligence = findViewById(R.id.tfEDintelligence);
        tfEDwisdom = findViewById(R.id.tfEDwisdom);
        tfEDcharisma = findViewById(R.id.tfEDcharisma);
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

    private void setCreationValidation() {
        tietEDcreation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dateValidation();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void dateValidation(){
        String date = tietEDcreation.getText().toString();
        if (date.length() != 0 && date.contains("/")){
            String[] dateParts = date.split("/");
            if (dateParts.length == 3){
                int day, month, year;
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                year = Integer.parseInt(dateParts[2]);
                if (isGoodDate(day, month, year)){
                    tfEDcreation.setHelperText("Looks Good!");
                } else {
                    tfEDcreation.setError("Invalid date");
                }
            } else {
                if(dateParts.length > 3){
                    tfEDcreation.setError("Invalid Format (dd/mm/aaaa)");
                } else {
                    tfEDcreation.setHelperText("dd/mm/aaaa");
                }
            }
        } else {
            if (specialCharsDate(date)){
                tfEDcreation.setError("Use only numbers and /");
            } else {
                tfEDcreation.setHelperText("dd/mm/aaaa");
            }
        }
    }

    private boolean isGoodDate(int day, int month, int year) {
        int actualYear = Calendar.getInstance().get(Calendar.YEAR);


        if (actualYear < year || year < 1974){
            return false;
        }
        if (month < 13){
            switch (month){
                case 2: if (day > 28) return false;
                    break;
                case 1:case 3:case 5:case 7:case 8:case 10:case 12: if (day > 31) return false;
                    break;

                default: if (day > 30) return false;
            }
        } else
            return false;


        return true;
    }

    private boolean specialCharsDate(String str){
        String valideChars = "1234567890/";
        for (char c: str.toCharArray()) {
            if (!valideChars.contains(Character.toString(c))){
                return true;
            }
        }
        return false;
    }

    private boolean specialCharsNumber(String str){
        String valideChars = "1234567890";
        for (char c: str.toCharArray()) {
            if (!valideChars.contains(Character.toString(c))){
                return true;
            }
        }
        return false;
    }

    private void charismaWatcher() {
        tietEDcharisma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietEDcharisma.getText().toString();
                if (!specialCharsNumber(field)){
                    tfEDcharisma.setHelperText("Looks Good!");
                } else {
                    tfEDcharisma.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfEDcharisma.setHelperText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void wisdomWatcher() {
        tietEDwisdom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietEDwisdom.getText().toString();
                if (!specialCharsNumber(field)){
                    tfEDwisdom.setHelperText("Looks Good!");
                } else {
                    tfEDwisdom.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfEDwisdom.setHelperText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void intelligenceWatcher() {
        tietEDintelligence.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietEDintelligence.getText().toString();
                if (!specialCharsNumber(field)){
                    tfEDintelligence.setHelperText("Looks Good!");
                } else {
                    tfEDintelligence.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfEDintelligence.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void constitutionWatcher() {
        tietEDconstitution.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietEDconstitution.getText().toString();
                if (!specialCharsNumber(field)){
                    tfEDconstitution.setHelperText("Looks Good!");
                } else {
                    tfEDconstitution.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfEDconstitution.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void dexterityWatcher() {
        tietEDdexterity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietEDdexterity.getText().toString();
                if (!specialCharsNumber(field)){
                    tfEDdexterity.setHelperText("Looks Good!");
                } else {
                    tfEDdexterity.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfEDdexterity.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void strengthWatcher() {
        tietEDstrength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietEDstrength.getText().toString();
                if (!specialCharsNumber(field)){
                    tfEDstrength.setHelperText("Looks Good!");
                } else {
                    tfEDstrength.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfEDstrength.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void levelWatcher() {
        tietEDlevel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietEDlevel.getText().toString();
                if (!specialCharsNumber(field)){
                    tfEDlevel.setHelperText("Looks Good!");
                } else {
                    tfEDlevel.setError("Insert > 0 Number");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}