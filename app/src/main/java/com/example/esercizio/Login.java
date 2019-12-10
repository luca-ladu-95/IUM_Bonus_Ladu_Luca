/*Powered by Ladu Luca n° 65556*/
package com.example.esercizio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class Login extends AppCompatActivity {


    EditText username, password;
    Button inserisci;
    TextView errorText, registatiText;
    Persona persona;
    static HashMap<String, Persona> utenti = new HashMap<String, Persona>();


    // nella vostra applicazione sarà qualcosa del tipo

    public static final String PERSON_EXTRA = "package com.example.esercizio";

    //attributo della classe appena creata
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //creo una nuova persona


        persona = new Persona();
        username = findViewById(R.id.inputUser);
        password = findViewById(R.id.inputPassword);
        inserisci = findViewById(R.id.Loginbutton);
        registatiText = findViewById(R.id.registrati);



/*prova debug


        Calendar data_nascita= Calendar.getInstance();
        data_nascita.set(1995,03,14);
        Persona persona1= new Persona("laduluca","luca","ladu",data_nascita,"1234","Tortolì");
        utenti.put("laduluca",persona1);
*/
        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkInput() && checkUtente(username.getText().toString(), utenti)) {


                    // Mi prendo la persona dalla lista
                    persona = utenti.get(username.getText().toString());

                    //creo oggetto per far comunicare le activity
                    Intent showRESULT = new Intent(Login.this, ResultActivity.class);
                    //Inserisco la persona dentro l'intent
                    showRESULT.putExtra(PERSON_EXTRA, persona);
                    //richiamo activity
                    startActivity(showRESULT);


                }
            }
        });


        registatiText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showRESULT = new Intent(Login.this, Registrazione.class);
                startActivity(showRESULT);
            }
        });


    }


    private boolean checkInput() {
        boolean flag = true;
        if (username.getText() == null || username.getText().length() == 0) {
            username.setError("Inserisci l'username");
            flag = false;
        } else {
            username.setError(null);

        }

        if (password.getText() == null || password.getText().length() == 0) {
            password.setError("Inserisci la password");
            flag = false;
        } else {
            password.setError(null);
        }


        return flag;
    }

    private boolean checkUtente(String us, HashMap<String, Persona> map) {
        boolean flag = true;
        Persona temp;

        if (map.get(us) == null) {
            //non esiste proprio l'utente
            username.setError("Lo username inserito non è valido");
            flag = false;
        } else {
            //Salvo la persona e controllo la password
            temp = map.get(us);
            username.setError(null);
            username.setText(username.getText());
            //controllo se la password inserita corrsponde a quella nel set
            if (!temp.getPassword().equals(password.getText().toString())) {
                flag = false;
                password.setError("La password inserita non corrisponde");
            } else {
                password.setError(null);


            }

        }

        return flag;
    }


    public void setUtenti(HashMap<String, Persona> utenti) {
        this.utenti = utenti;
    }
}
