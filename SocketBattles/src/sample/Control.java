package sample;


import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe che si occupa di effettuare i controlli più frequenti
 * @author Ismaele Carbonari
 */
public class Control<T> {

    /**
     * Costruttore della classe Control
     */
    public Control() {
    }

    /**
     * Metodo che permette di controllare se la stringa passata da parametro è vuota
     * @param param
     * @return true se la stringa è vuota
     */
    public static boolean isEmpty(String param){
        return param.equals("");
    }

    /**
     * Metodo che permette di controllare se l'oggetto da parametro è nullo
     * @param <T>
     * @param ogg
     * @return true se l'oggetto è nullo
     */
    public static<T> boolean isNull(T ogg){
        return ogg==null;
    }

    /**
     * Metodo che controlla se la stringa è nulla o vuota
     * @param param
     * @return true se la stringa è nulla o vuota
     */
    public static boolean isNullOrEmpty(String param){
        return isEmpty(param)||isNull(param);
    }

}
