package com.example.esercizio;

import java.io.Serializable;
import java.util.Calendar;

public class Persona implements Serializable {


    private String nome;
    private String cognome;
    private String username;
    private Calendar data_nascita;
    private String password;
    private String citta;


    public Persona(String username, String nome, String cognome, Calendar data, String password, String citta) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setData_nascita(data);
        this.setPassword(password);
        this.setUsername(username);
        this.setCitta(citta);
    }

    public Persona() {
        this.setNome("");
        this.setCognome("");
        //this.setData("");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Calendar getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(Calendar data_nascita) {
        this.data_nascita = data_nascita;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
}
