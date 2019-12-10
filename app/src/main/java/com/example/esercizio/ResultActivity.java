/*Powered by Ladu Luca n° 65556*/
package com.example.esercizio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class ResultActivity extends AppCompatActivity {

    Persona persona;
    TextView cittaText, nomeText, cognomeText, usernameText, dataText, passwordText, benvenutoText, modificaPassword;
    Button logout;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


    public static final String PERSON_EXTRA2 = "package com.example.esercizio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        benvenutoText = findViewById(R.id.OutputBenvenuto);
        nomeText = findViewById(R.id.OutputNome);
        cognomeText = findViewById(R.id.OutputCognome);
        usernameText = findViewById(R.id.OutputUsername);
        dataText = findViewById(R.id.OutputData);
        passwordText = findViewById(R.id.OutputPassword);
        logout = findViewById(R.id.Logoutbutton);
        cittaText = findViewById(R.id.OutputCitta);
        modificaPassword = findViewById(R.id.modificaPassword);

        //Recupero l'intent

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.PERSON_EXTRA);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }

        //Associo tutti i parametri
        nomeText.setText(persona.getNome());
        cognomeText.setText(persona.getCognome());
        benvenutoText.setText("Benvenuto " + persona.getNome());
        passwordText.setText(persona.getPassword());
        usernameText.setText(persona.getUsername());
        dataText.setText(format.format(persona.getData_nascita().getTime()));
        cittaText.setText(persona.getCitta());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                persona = null;
                Intent showRESULT = new Intent(ResultActivity.this, Login.class);
                //Inserisco la persona dentro l'intent
                showRESULT.putExtra(PERSON_EXTRA2, persona);
                //richiamo activity
                startActivity(showRESULT);
                finish();
            }
        });

        modificaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //creo oggetto per far comunicare le activity
                Intent showRESULT = new Intent(ResultActivity.this, ModificaPassword.class);
                //Inserisco la persona dentro l'intent
                showRESULT.putExtra(PERSON_EXTRA2, persona);
                //richiamo activity
                startActivity(showRESULT);
                finish();
            }
        });


    }


}
