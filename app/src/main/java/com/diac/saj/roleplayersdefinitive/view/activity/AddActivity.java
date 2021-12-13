package com.diac.saj.roleplayersdefinitive.view.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

public class AddActivity extends AppCompatActivity{

    TextInputEditText tietADDname, tietADDlevel, tietADDcreation, tietADDstate, tietADDstrength,
                        tietADDdexterity, tietADDconstitution, tietADDintelligence, tietADDwisdom,
                        tietADDcharisma;
    Button btAdd;
    ImageView ivADDcharacter;
    Spinner spADDclass, spADDrace;

    TextInputLayout tfADDcreation, tfADDlevel, tfADDstrength, tfADDdexterity, tfADDconstitution,
                    tfADDintelligence, tfADDwisdom, tfADDcharisma;
    private CharacterViewModel cvm;
    private boolean firstTime = true;

    private Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initialize();

    }

    private void initialize() {
        itemFindView();
        getViewModel();
        defineListeners();
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



    private void setCreationValidation() {
        tietADDcreation.addTextChangedListener(new TextWatcher() {
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

    private void defineListeners() {
        defineAddListener();
        defineOnFocusListener();
        defineCreationListener();
        defineImageListener();
    }

    private void defineImageListener() {
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    Glide.with(this).load(uri).into(ivADDcharacter);
                    if (uri == null){
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
                        this.uri = null;
                    } else {
                        this.uri = uri;
                    }


                });

        ivADDcharacter.setOnClickListener(view -> {
            mGetContent.launch("image/*");
        });
    }

    private void defineCreationListener() {
        tfADDcreation.setStartIconOnClickListener(view -> {
            Log.v("jamaica", "sldjnf");
            DatePickerFragment newFragment = new DatePickerFragment(tietADDcreation);
            newFragment.show(getSupportFragmentManager(), "datepicker");
        });
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
         tfADDcreation = findViewById(R.id.tfADDcreation);
         tfADDlevel = findViewById(R.id.tfADDlevel);
         tfADDstrength = findViewById(R.id.tfADDstrength);
         tfADDdexterity = findViewById(R.id.tfADDdexterity);
         tfADDconstitution = findViewById(R.id.tfADDconstitution);
         tfADDintelligence = findViewById(R.id.tfADDintelligence);
         tfADDwisdom = findViewById(R.id.tfADDwisdom);
         tfADDcharisma = findViewById(R.id.tfADDcharisma);
    }

    private void defineOnFocusListener() {
        spADDclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //TODO OPTIMIZAR
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
        if (uri != null){
            roleCharacter.image = uri.toString();
        } else {
            roleCharacter.image = null;
        }


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

    public void dateValidation(){
        String date = tietADDcreation.getText().toString();
        if (date.length() != 0 && date.contains("/")){
            String[] dateParts = date.split("/");
            if (dateParts.length == 3){
                int day, month, year;
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                year = Integer.parseInt(dateParts[2]);
                if (isGoodDate(day, month, year)){
                    tfADDcreation.setHelperText("Looks Good!");
                } else {
                    tfADDcreation.setError("Invalid date");
                }
            } else {
                if(dateParts.length > 3){
                    tfADDcreation.setError("Invalid Format (dd/mm/aaaa)");
                } else {
                    tfADDcreation.setHelperText("dd/mm/aaaa");
                }
            }
        } else {
            if (specialCharsDate(date)){
                tfADDcreation.setError("Use only numbers and /");
            } else {
                tfADDcreation.setHelperText("dd/mm/aaaa");
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
        tietADDcharisma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietADDcharisma.getText().toString();
                if (!specialCharsNumber(field)){
                    tfADDcharisma.setHelperText("Looks Good!");
                } else {
                    tfADDcharisma.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfADDcharisma.setHelperText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void wisdomWatcher() {
        tietADDwisdom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietADDwisdom.getText().toString();
                if (!specialCharsNumber(field)){
                    tfADDwisdom.setHelperText("Looks Good!");
                } else {
                    tfADDwisdom.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfADDwisdom.setHelperText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void intelligenceWatcher() {
        tietADDintelligence.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietADDintelligence.getText().toString();
                if (!specialCharsNumber(field)){
                    tfADDintelligence.setHelperText("Looks Good!");
                } else {
                    tfADDintelligence.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfADDintelligence.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void constitutionWatcher() {
        tietADDconstitution.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietADDconstitution.getText().toString();
                if (!specialCharsNumber(field)){
                    tfADDconstitution.setHelperText("Looks Good!");
                } else {
                    tfADDconstitution.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfADDconstitution.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void dexterityWatcher() {
        tietADDdexterity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietADDdexterity.getText().toString();
                if (!specialCharsNumber(field)){
                    tfADDdexterity.setHelperText("Looks Good!");
                } else {
                    tfADDdexterity.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfADDdexterity.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void strengthWatcher() {
        tietADDstrength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietADDstrength.getText().toString();
                if (!specialCharsNumber(field)){
                    tfADDstrength.setHelperText("Looks Good!");
                } else {
                    tfADDstrength.setError("Insert > 0 Number");
                }

                if (field.length() == 0){
                    tfADDstrength.setHelperText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void levelWatcher() {
        tietADDlevel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field = tietADDlevel.getText().toString();
                if (!specialCharsNumber(field)){
                    tfADDlevel.setHelperText("Looks Good!");
                } else {
                    tfADDlevel.setError("Insert > 0 Number");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
