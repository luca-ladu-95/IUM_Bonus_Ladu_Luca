/*Powered by Ladu Luca n° 65556*/


package com.example.esercizio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import static com.example.esercizio.Login.utenti;

public class ModificaPassword extends AppCompatActivity {


    Persona persona;
    EditText password1, password2;
    TextView usernameText, passwordText, okText;
    Button home, aggiorna;
    public static final String PERSON_EXTRA3 = "package com.example.esercizio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_password);


        //associo i dati

        aggiorna = findViewById(R.id.modificaButton);
        home = findViewById(R.id.homeButton);
        password1 = findViewById(R.id.inputNuovaPassword);
        password2 = findViewById(R.id.inputConfermaPassword);
        okText = findViewById(R.id.errorText);
        usernameText = findViewById(R.id.userCambiaPasswordText);
        passwordText = findViewById(R.id.passwordCambiaPasswordText);


        okText.setVisibility(View.GONE);


        //Recupero l'intent

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.PERSON_EXTRA);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


        usernameText.setText(persona.getUsername());
        passwordText.setText(persona.getPassword());


        aggiorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkInput() && checkCambioPassword()) {
                    // cambio la password
                    persona.setPassword(password1.getText().toString());
                    //per semplicita rimuovo l'oggetto e lo ri inserisco con la nuova passwrod
                    utenti.remove(persona.getUsername());
                    utenti.put(persona.getUsername(), persona);

                    passwordText.setText(persona.getPassword());
                    okText.setVisibility(View.VISIBLE);
                    okText.setText("Password Aggiornata");


                }


            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creo oggetto per far comunicare le activity
                Intent showRESULT = new Intent(ModificaPassword.this, ResultActivity.class);
                //Inserisco la persona dentro l'intent
                showRESULT.putExtra(PERSON_EXTRA3, persona);
                //richiamo activity
                startActivity(showRESULT);
                finish();
            }
        });


    }


    private boolean checkInput() {
        boolean flag = true;
        if (password1.getText() == null || password1.getText().length() == 0) {
            password1.setError("Inserisci la password");
            flag = false;
        } else {
            password1.setError(null);

        }

        if (password2.getText() == null || password2.getText().length() == 0) {
            password2.setError("Inserisci la conferma password");
            flag = false;
        } else {
            password2.setError(null);
        }


        return flag;
    }

    private boolean checkCambioPassword() {

        //controllo prima che i campi non siano vuoti
        if (checkInput()) {
            // se la password 1 è uguale alla password 2 ed la password inserita non è uguale alla vecchia ok altrimenti false
            if (password1.getText().toString().equals(password2.getText().toString()) && !(password1.getText().toString().equals(persona.getPassword())))
                return true;
            else if (!password1.getText().toString().equals(password2.getText().toString())) {
                password1.setError("Le password non corrispondono");
                password2.setError("Le password non corrispondono");
            } else {
                if (password1.getText().toString().equals(persona.getPassword())) {
                    password1.setError("Non puoi inserire la vecchia password");
                    password2.setError("Non puoi inserire la vecchia password");
                }
            }
            return false;


        }

        return false;

    }

}



