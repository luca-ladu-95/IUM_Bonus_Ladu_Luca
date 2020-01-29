/*Powered by Ladu Luca n° 65556*/
package com.example.esercizio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.esercizio.Login.utenti;

public class Registrazione extends AppCompatActivity {

    EditText nome, cognome, username, password, citta, data,confermaP;
    Button conferma;
    Persona person;
    public static final String PERSON_EXTRA4 = "package com.example.esercizio";

    DatePickerFragment datePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        datePickerFragment = new DatePickerFragment();


        //creo la persona
        person = new Persona();

        //recupero il valore dei campi

        nome = findViewById(R.id.regInputNome);
        cognome = findViewById(R.id.regInputCognome);
        data = findViewById(R.id.reginputData);
        username = findViewById(R.id.regInputUser);
        password = findViewById(R.id.regiInputPassword);
        citta = findViewById(R.id.regInputCitta);
        confermaP=findViewById(R.id.regiConfInputPassword);

        conferma = findViewById(R.id.registatiButton);


        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {


                    updatePerson();

                    utenti.put(person.getUsername(), person);


                    Intent showRESULT = new Intent(Registrazione.this, Login.class);
                    //Inserisco la persona dentro l'intent
                    showRESULT.putExtra(PERSON_EXTRA4, person);
                    //richiamo activity
                    startActivity(showRESULT);
                    finish();


                }
            }
        });


        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        //questa funzione permette di controllare che l'utente non scriva nella textview
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() { //funzione di view
            @Override
            public void onFocusChange(View v, boolean hasFocus) { //metodo chiamato quando lo stato della view cambia
                if (hasFocus) {
                    datePickerFragment.show(getSupportFragmentManager(), "datePicker");
                }
            }
        });

        datePickerFragment.setOnDatePickerFragmentChanged(new DatePickerFragment.DatePickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date) {
                //Associo il comportamento del bottone OK all'edit text della data, voglio che una
                // volta selezionata quindi ho premuto ok, l'edit text mostri la data selezionata
                // tramite il datepicker
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                data.setText(format.format(date.getTime()));
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });


    }

    boolean checkInput() {
        boolean flag = true;
        if (nome.getText() == null || nome.getText().toString().isEmpty()) {
            nome.setError("Inserisci il nome");
            flag = false;
        } else
            nome.setError(null);

        if (cognome.getText() == null || cognome.getText().toString().isEmpty()) {
            cognome.setError("Inserisci il cognome");
            flag = false;
        } else
            cognome.setError(null);










        if (username.getText() == null || username.getText().toString().isEmpty()) {
            username.setError("Inserisci lo username");
            flag = false;
        } else {
            //Username deve essere univoco
            if (utenti.containsKey(username.getText().toString())) {
                username.setError("L'utente esiste già");
                flag = false;
            } else
                username.setError(null);

        }


        if (confermaP.getText() == null || confermaP.getText().toString().isEmpty()|| !(confermaP.getText().toString().equals(password.getText().toString()))) {
            confermaP.setError("Le password non corrsipondono");
            flag = false;
        } else
            confermaP.setError(null);



        if (password.getText() == null || password.getText().toString().isEmpty()) {
            password.setError("Inserisci la password");
            flag = false;
        } else
            password.setError(null);


        if (citta.getText() == null || citta.getText().toString().isEmpty()) {
            citta.setError("Inserisci la citta");
            flag = false;
        } else
            citta.setError(null);

        if (data.getText() == null || data.getText().toString().isEmpty()) {
            data.setError("Inserisci la data");
            flag = false;
        } else
            data.setError(null);

        return flag;
    }

    void updatePerson() {

        this.person.setPassword(this.password.getText().toString());
        this.person.setCitta(this.citta.getText().toString());
        this.person.setUsername(this.username.getText().toString());
        this.person.setData_nascita(this.datePickerFragment.getDate());
        this.person.setNome(this.nome.getText().toString());
        this.person.setCognome(this.cognome.getText().toString());


    }
}
